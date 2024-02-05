package fr.emploi.myCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

	// Paramètres de connexion à la base de données
	private static final String URL = "jdbc:mysql://localhost:3306/emploi";
	private static final String UTILISATEUR = "root";
	private static final String MOT_DE_PASSE = "";

	// Méthode pour établir la connexion
	public static Connection obtenirConnexion() {
		Connection connexion = null;
		try {
			// Charger le driver JDBC
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Établir la connexion
			connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
			System.out.println("Connexion établie avec succès.");
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
		}
		return connexion;
	}

	// Méthode pour fermer la connexion
	public static void fermerConnexion(Connection connexion) {
		if (connexion != null) {
			try {
				connexion.close();
				System.out.println("Connexion fermée avec succès.");
			} catch (SQLException e) {
				System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
			}
		}
	}

}
