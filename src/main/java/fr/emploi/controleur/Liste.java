package fr.emploi.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.emploi.model.Pays;
import fr.emploi.myCon.Connexion;

public class Liste extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Liste() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection connexion = Connexion.obtenirConnexion();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Liste pour stocker les pays récupérés
        List<Pays> listePays = new ArrayList<Pays>();

        // Requête pour récupérer tous les pays
        String sql = "SELECT * FROM pays";

        try {
            // Préparer la requête
            preparedStatement = connexion.prepareStatement(sql);

            // Exécuter la requête
            resultSet = preparedStatement.executeQuery();

            // Parcourir les résultats et ajouter les pays à la liste
            while (resultSet.next()) {
                Pays pays = new Pays();
                pays.setId(resultSet.getInt("id"));
                pays.setNomPays(resultSet.getString("nomPays"));
                pays.setContinent(resultSet.getString("continent"));
                pays.setPopulation(resultSet.getBigDecimal("population"));
                pays.setCodeIndicatif(resultSet.getInt("codeIndicatif"));
                pays.setLangue(resultSet.getString("langue"));

                listePays.add(pays);
            }

            // Définir la liste de pays en tant qu'attribut de la requête
            request.setAttribute("listePays", listePays);

            // Rediriger vers la page JSP pour afficher les pays
            request.getRequestDispatcher("liste.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'erreur si nécessaire
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
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
