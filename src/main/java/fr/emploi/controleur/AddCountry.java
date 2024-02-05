package fr.emploi.controleur;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.emploi.myCon.Connexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddCountry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddCountry() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Récupérer les paramètres du formulaire
		String nomPays = request.getParameter("nomPays");
		String continent = request.getParameter("continent");
		BigDecimal population = new BigDecimal(request.getParameter("population"));
		int codeIndicatif = Integer.parseInt(request.getParameter("codeIndicatif"));
		String langue = request.getParameter("langue");

		// Établir la connexion à la base de données
		Connection connexion = Connexion.obtenirConnexion();
		PreparedStatement preparedStatement = null;

		// Requête d'insertion
		String sql = "INSERT INTO pays (nomPays, continent, population, codeIndicatif, langue) VALUES (?, ?, ?, ?, ?)";

		try {
			// Préparer la requête
			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setString(1, nomPays);
			preparedStatement.setString(2, continent);
			preparedStatement.setBigDecimal(3, population);
			preparedStatement.setInt(4, codeIndicatif);
			preparedStatement.setString(5, langue);

			// Exécuter la requête
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				response.sendRedirect("succes.jsp"); // Rediriger vers une page de succès
			} else {
				response.sendRedirect("echec.jsp"); // Rediriger vers une page d'échec
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("echec.jsp"); // Rediriger vers une page d'échec en cas d'erreur
		} finally {
			// Fermer la connexion et les ressources
			Connexion.fermerConnexion(connexion);
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
