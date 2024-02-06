package fr.emploi.myCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.emploi.model.Image;

public class ImageDAO {

	// Requêtes SQL
	private static final String INSERT_IMAGE = "INSERT INTO images (nom, url1, url2) VALUES (?, ?, ?)";
	private static final String SELECT_ALL_IMAGES = "SELECT * FROM images";

	// Méthode pour insérer une image dans la base de données
	public static void insertImage(Image image) {
		try (Connection connexion = Connexion.obtenirConnexion();
				PreparedStatement preparedStatement = connexion.prepareStatement(INSERT_IMAGE)) {
			preparedStatement.setString(1, image.getNom());
			preparedStatement.setString(2, image.getUrl1());
			preparedStatement.setString(3, image.getUrl2());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Méthode pour récupérer toutes les images depuis la base de données
	public static List<Image> getAllImages() {
		List<Image> images = new ArrayList<>();
		try (Connection connexion = Connexion.obtenirConnexion();
				PreparedStatement preparedStatement = connexion.prepareStatement(SELECT_ALL_IMAGES);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				String url1 = resultSet.getString("url1");
				String url2 = resultSet.getString("url2");
				Image image = new Image(id, nom, url1, url2);
				images.add(image);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return images;
	}
}
