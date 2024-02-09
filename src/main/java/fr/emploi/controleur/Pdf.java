package fr.emploi.controleur;

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
import java.util.List;

import fr.emploi.model.Pays;
import fr.emploi.myCon.PaysDAO;

@WebServlet("/pdf")
public class Pdf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Pdf() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		//PaysDAO.generatePDF(filePath);
		PaysDAO.generatePDF1(filePath);

		// Téléchargez le PDF
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=pays.pdf");

		// Copier le contenu du fichier PDF dans la réponse HTTP
		try (InputStream inputStream = new FileInputStream(filePath);
				OutputStream outputStream = response.getOutputStream()) {
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
