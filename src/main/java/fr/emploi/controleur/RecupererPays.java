package fr.emploi.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.emploi.model.Pays;
import fr.emploi.myCon.PaysDAO;

/**
 * Servlet implementation class RecupererPays
 */
public class RecupererPays extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RecupererPays() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Récupérer l'ID du pays à afficher à partir de la requête
		// int idPays = Integer.parseInt(request.getParameter("idPays"));
		String idPaysParam = request.getParameter("idPays");
		if (idPaysParam != null && !idPaysParam.isEmpty()) {
			int idPays = Integer.parseInt(idPaysParam);
			// Récupérer les informations du pays depuis la base de données par son ID
			Pays pays = PaysDAO.getPaysById(idPays);
			// Passer les informations du pays à la page JSP pour l'affichage
			request.setAttribute("pays", pays);
			request.getRequestDispatcher("afficherPays.jsp").forward(request, response);
		} else {

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
