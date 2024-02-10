package fr.emploi.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.emploi.myCon.PaysDAO;

@WebServlet("/creaTable")
public class CreerTables extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CreerTables() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Appel de la méthode pour créer la table "Pays"
        PaysDAO.createPaysTable();
        
        // Réponse HTTP
        response.setContentType("text/plain");
        response.getWriter().println("La table 'Pays' a été créée avec succès !");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
