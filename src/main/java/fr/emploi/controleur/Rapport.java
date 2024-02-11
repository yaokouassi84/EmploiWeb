package fr.emploi.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import fr.emploi.model.Pays;
import fr.emploi.myCon.PaysDAO;


@WebServlet("/rapport")
public class Rapport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Rapport() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Extraire les données depuis le DAO
            List<Pays> paysList = PaysDAO.getAllPaysJasper();

            // Chemin vers le fichier .jrxml du modèle de rapport JasperReports
            String jrxmlFile = getServletContext().getRealPath("/WEB-INF/reports/pays_report.jrxml");

            // Compiler le modèle de rapport JasperReports
            JasperCompileManager.compileReportToFile(jrxmlFile);

            // Charger le modèle de rapport compilé
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                getServletContext().getRealPath("/WEB-INF/reports/pays_report.jasper"),
                new HashMap<>(), // Paramètres du rapport (s'ils existent)
                new JRBeanCollectionDataSource(paysList) // Données à inclure dans le rapport
            );

            // Exporter le rapport en tant que fichier PDF
            File pdfFile = File.createTempFile("rapport", ".pdf");
            net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFile.getAbsolutePath());

            // Envoyer le rapport en tant que réponse HTTP
            response.setContentType("application/pdf");
            response.setContentLength((int) pdfFile.length());
            java.io.FileInputStream fileInputStream = new java.io.FileInputStream(pdfFile);
            int i;
            while ((i = fileInputStream.read()) != -1) {
                response.getOutputStream().write(i);
            }
            response.getOutputStream().close();
            fileInputStream.close();
        } catch (JRException e) {
            e.printStackTrace();
            // Gérer les erreurs de JasperReports
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les autres erreurs
        }
    }


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
