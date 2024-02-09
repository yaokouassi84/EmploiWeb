package fr.emploi.controleur;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import fr.emploi.model.Pays;
import fr.emploi.myCon.PaysDAO;

@WebServlet("/pdf1")
public class GenererPdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenererPdf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtenez le chemin du répertoire pour enregistrer le fichier PDF
        String directoryPath = request.getServletContext().getRealPath("/pdf");

        // Créez le répertoire s'il n'existe pas déjà
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Définissez le chemin complet du fichier PDF
        String filePath = directoryPath + File.separator + "pays.pdf";

        // Générez le PDF à partir des données de la base de données
        PaysDAO.generatePDF(filePath);

        // Lisez le contenu du PDF et passez-le à la vue JSP
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String pdfContent = pdfStripper.getText(document);
            request.setAttribute("pdfContent", pdfContent);
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("pdfContent", "Erreur lors de la lecture du PDF");
        }

        // Forward vers la vue JSP pour afficher le contenu du PDF
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewPdf.jsp");
        dispatcher.forward(request, response);
    }
    
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
