package fr.emploi.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import fr.emploi.model.Image;
import fr.emploi.myCon.ImageDAO;

public class EnregistrerImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EnregistrerImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Récupération des données de la requête
		String nom = request.getParameter("nom");
		String url1 = request.getParameter("url1");
		String url2 = request.getParameter("url2");

		// Création de l'objet Image
		Image image = new Image();
		image.setNom(nom);
		image.setUrl1(url1);
		image.setUrl2(url2);

		// Insertion dans la base de données
		ImageDAO.insertImage(image);

		// Redirection vers une page de confirmation
		response.sendRedirect("confirmation.html");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Récupération de toutes les images depuis la base de données
		List<Image> images = ImageDAO.getAllImages();

	}

}
