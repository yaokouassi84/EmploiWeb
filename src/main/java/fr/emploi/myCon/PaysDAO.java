package fr.emploi.myCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.emploi.model.Pays;

public class PaysDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/emploi";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public static Pays getPaysById(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Pays pays = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String query = "SELECT * FROM pays WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pays = new Pays();
				pays.setId(rs.getInt("id"));
				pays.setNomPays(rs.getString("nomPays"));
				pays.setContinent(rs.getString("continent"));
				pays.setPopulation(rs.getBigDecimal("population"));
				pays.setCodeIndicatif(rs.getInt("codeIndicatif"));
				pays.setLangue(rs.getString("langue"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Fermer les ressources
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return pays;
	}

	// Méthode pour mettre à jour les informations d'un pays dans la base de données
	public static boolean updatePays(Pays pays) {
		// Initialisation de la connexion à la base de données
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		boolean updated = false;

		try {
			// Récupération de la connexion
			connexion = Connexion.obtenirConnexion();

			// Requête SQL pour mettre à jour les informations du pays
			String sql = "UPDATE pays SET nomPays=?, continent=?, population=?, codeIndicatif=?, langue=? WHERE id=?";

			// Préparation de la requête avec les valeurs à mettre à jour
			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setString(1, pays.getNomPays());
			preparedStatement.setString(2, pays.getContinent());
			preparedStatement.setBigDecimal(3, pays.getPopulation());
			preparedStatement.setInt(4, pays.getCodeIndicatif());
			preparedStatement.setString(5, pays.getLangue());
			preparedStatement.setInt(6, pays.getId());

			// Exécution de la requête
			int rowsAffected = preparedStatement.executeUpdate();

			// Vérification si la mise à jour a réussi
			if (rowsAffected > 0) {
				updated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Fermeture des ressources
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connexion != null) {
				try {
					connexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return updated;
	}
}
