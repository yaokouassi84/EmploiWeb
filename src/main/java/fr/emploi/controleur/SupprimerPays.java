package fr.emploi.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.emploi.myCon.Connexion;


public class SupprimerPays extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerPays() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupérer l'ID du pays à supprimer depuis le formulaire
        int id = Integer.parseInt(request.getParameter("id"));

        // Établir la connexion à la base de données
        Connection connexion = Connexion.obtenirConnexion();
        PreparedStatement preparedStatement = null;

        // Requête de suppression
        String sql = "DELETE FROM pays WHERE id=?";

        try {
            // Préparer la requête
            preparedStatement = connexion.prepareStatement(sql);
            //preparedStatement.setInt(1, id);

            // Exécuter la requête
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                response.sendRedirect("liste"); // Rediriger vers la page ListePays après suppression
            } else {
                response.sendRedirect("error.jsp"); // Rediriger vers une page d'erreur si la suppression échoue
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Rediriger vers une page d'erreur en cas d'erreur SQL
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
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
