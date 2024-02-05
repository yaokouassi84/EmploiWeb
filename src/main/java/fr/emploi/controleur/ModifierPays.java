package fr.emploi.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import fr.emploi.model.Pays;
import fr.emploi.myCon.PaysDAO;

/**
 * Servlet implementation class ModifierPays
 */
public class ModifierPays extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifierPays() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nomPays = request.getParameter("nomPays");
		String continent = request.getParameter("continent");
		BigDecimal population = new BigDecimal(request.getParameter("population"));
		int codeIndicatif = Integer.parseInt(request.getParameter("codeIndicatif"));
		String langue = request.getParameter("langue");

		// Créer un objet Pays avec les nouvelles informations
		Pays pays = new Pays(id,nomPays, continent, population, codeIndicatif, langue);

		// Mettre à jour le pays dans la base de données
		boolean updated = PaysDAO.updatePays(pays);

		// Rediriger vers une page de confirmation ou de gestion des erreurs
		if (updated) {
			response.sendRedirect("liste.jsp"); // Redirection vers la liste des pays
		} else {
			response.sendRedirect("erreur.jsp"); // Redirection vers une page d'erreur
		}
	}

}
