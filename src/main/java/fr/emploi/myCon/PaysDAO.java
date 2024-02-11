package fr.emploi.myCon;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import fr.emploi.model.Pays;

public class PaysDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/emploi";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	
	
	public static boolean addPays(Pays pays) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean added = false;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String query = "INSERT INTO pays (nomPays, continent, population, codeIndicatif, langue) VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pays.getNomPays());
			pstmt.setString(2, pays.getContinent());
			pstmt.setBigDecimal(3, pays.getPopulation());
			pstmt.setInt(4, pays.getCodeIndicatif());
			pstmt.setString(5, pays.getLangue());

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				added = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Fermer les ressources
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

		return added;
	}

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

	// Méthode pour créer la table "Pays"
	public static void createPaysTable() {
		// Obtenir la connexion à la base de données
		Connection connection = Connexion.obtenirConnexion();

		// Requête SQL pour créer la table "Pays"
		String createTableSQL = "CREATE TABLE Pays if not exists(" + "id INT AUTO_INCREMENT PRIMARY KEY,"
				+ "nomPays VARCHAR(255) NOT NULL," + "continent VARCHAR(225)" + "population BIGINT"+"codeIndicatif int"+"langue"+")";

		// Utilisation d'un objet Statement pour exécuter la requête
		try (Statement statement = connection.createStatement()) {
			// Exécution de la requête SQL
			if(createTableSQL == null) {
				
				statement.executeUpdate(createTableSQL);
				System.out.println("La table 'Pays' a été créée avec succès !");
				
			}
			else{
				System.out.println("La table 'Pays' est déja créée avec succès !");
			}
			
		} catch (SQLException e) {
			// Gestion des exceptions en cas d'erreur lors de l'exécution de la requête
			e.printStackTrace();
			System.out.println("Erreur lors de la création de la table 'Pays'.");
		} finally {
			// Fermeture de la connexion
			Connexion.fermerConnexion(connection);
		}
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

	public static boolean deletePays(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean deleted = false;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String query = "DELETE FROM pays WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				deleted = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Fermer les ressources
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

		return deleted;
	}

	public static List<Pays> getAllPays() {
		List<Pays> paysList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String query = "SELECT * FROM pays";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Pays pays = new Pays();
				pays.setId(rs.getInt("id"));
				pays.setNomPays(rs.getString("nomPays"));
				pays.setContinent(rs.getString("continent"));
				pays.setPopulation(rs.getBigDecimal("population"));
				pays.setCodeIndicatif(rs.getInt("codeIndicatif"));
				pays.setLangue(rs.getString("langue"));
				paysList.add(pays);
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

		return paysList;
	}

	// Autres méthodes de votre DAO
	public static void generatePDF(String filePath) {
		String query = "SELECT * FROM pays";
		List<Pays> paysList = new ArrayList<>();

		try (Connection conn = Connexion.obtenirConnexion();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Pays pays = new Pays();
				pays.setId(rs.getInt("id"));
				pays.setNomPays(rs.getString("nomPays"));
				pays.setContinent(rs.getString("continent"));
				pays.setPopulation(rs.getBigDecimal("population"));
				pays.setCodeIndicatif(rs.getInt("codeIndicatif"));
				pays.setLangue(rs.getString("langue"));
				paysList.add(pays);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try (OutputStream outputStream = new FileOutputStream(filePath)) {
			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);
			PDPageContentStream contentStream = new PDPageContentStream(document, page);

			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
			contentStream.setNonStrokingColor(Color.BLACK); // Définir la couleur du texte

			float y = page.getMediaBox().getHeight() - 50; // Position de départ du texte

			// Écrivez les données de paysList dans le PDF
			for (Pays pays : paysList) {
				contentStream.beginText();
				contentStream.newLineAtOffset(100, y); // Position de départ du texte
				contentStream.showText(pays.getNomPays() + ' ' + pays.getPopulation());
				contentStream.newLine(); // Ajouter une nouvelle ligne
				// contentStream.showText(pays.getPopulation());
				// contentStream.newLine(); // Ajouter une nouvelle ligne
				// contentStream.showText(pays.getCodeIndicatif());
				// contentStream.newLine(); // Ajouter une nouvelle ligne
				// Écrivez les autres propriétés du pays de la même manière
				contentStream.endText();
				y -= 20; // Décalage vertical de 20 points pour la prochaine ligne
			}

			contentStream.close(); // Fermez le flux de contenu
			document.save(outputStream); // Enregistrez le document PDF dans le flux de sortie
			document.close(); // Fermez le document PDF
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static void generatePDF(String filePath, List<Pays> paysList) { try
	 * (PDDocument document = new PDDocument()) { PDPage page = new PDPage();
	 * document.addPage(page);
	 * 
	 * try (PDPageContentStream contentStream = new PDPageContentStream(document,
	 * page)) { contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
	 * 
	 * float y = page.getMediaBox().getHeight() - 50; // Position de départ du texte
	 * 
	 * for (Pays pays : paysList) { contentStream.beginText();
	 * contentStream.newLineAtOffset(50, y); // Décalage horizontal de 50 points
	 * contentStream.showText("Nom du pays: " + pays.getNomPays()); // Écrivez les
	 * autres propriétés du pays de la même manière contentStream.endText();
	 * 
	 * y -= 20; // Décalage vertical de 20 points pour la prochaine ligne } }
	 * 
	 * // Enregistrez le document PDF document.save(filePath); } catch (IOException
	 * e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	public static void generatePDF1(String filePath) {
		String query = "SELECT * FROM pays";
		List<Pays> paysList = new ArrayList<>();

		try (Connection conn = Connexion.obtenirConnexion();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Pays pays = new Pays();
				pays.setId(rs.getInt("id"));
				pays.setNomPays(rs.getString("nomPays"));
				pays.setContinent(rs.getString("continent"));
				pays.setPopulation(rs.getBigDecimal("population"));
				pays.setCodeIndicatif(rs.getInt("codeIndicatif"));
				pays.setLangue(rs.getString("langue"));
				paysList.add(pays);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try (OutputStream outputStream = new FileOutputStream(filePath)) {
			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);
			PDPageContentStream contentStream = new PDPageContentStream(document, page);

			// Titre du tableau
			String title = "Liste des pays";
			float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(title) / 1000f * 12;
			float titleHeight = 20;
			float titleX = (page.getMediaBox().getWidth() - titleWidth) / 2;
			float titleY = page.getMediaBox().getHeight() - 30;
			contentStream.beginText();
			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
			contentStream.newLineAtOffset(titleX, titleY);
			contentStream.showText(title);
			contentStream.endText();

			// Noms des colonnes
			String[] columnNames = { "Nom du pays", "Continent", "Population", "Code indicatif", "Langue" };
			float[] columnWidths = { 0.3f, 0.2f, 0.2f, 0.15f, 0.15f }; // Définir la largeur des colonnes

			float margin = 50;
			float yStart = page.getMediaBox().getHeight() - margin - titleHeight - 10; // Y position du début du tableau
			float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
			float rowHeight = 20;
			float cellMargin = 5;
			float tableHeight = rowHeight * (paysList.size() + 1); // Hauteur du tableau incluant les noms de colonnes

			drawTableHeader(contentStream, margin, yStart, tableWidth, rowHeight, cellMargin, columnWidths,
					columnNames);
			drawTable(contentStream, margin, yStart - rowHeight, tableWidth, margin, rowHeight, cellMargin,
					columnWidths, paysList);

			contentStream.close();
			document.save(outputStream);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void drawTableHeader(PDPageContentStream contentStream, float x, float y, float width,
			float rowHeight, float cellMargin, float[] columnWidths, String[] columnNames) throws IOException {
		float nextX = x;
		float nextY = y;
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

		// Dessiner les noms des colonnes
		for (int i = 0; i < columnWidths.length; i++) {
			drawCell(contentStream, nextX, nextY, columnWidths[i] * width, rowHeight, columnNames[i]);
			nextX += columnWidths[i] * width;
		}
	}

	private static void drawTable(PDPageContentStream contentStream, float x, float y, float tableWidth, float margin,
			float rowHeight, float cellMargin, float[] columnWidths, List<Pays> paysList) throws IOException {
		float nextY = y;
		for (Pays pays : paysList) {
			drawRow(contentStream, x, nextY, tableWidth, rowHeight, cellMargin, columnWidths, pays);
			nextY -= rowHeight;
		}
	}

	private static void drawRow(PDPageContentStream contentStream, float x, float y, float tableWidth, float rowHeight,
			float cellMargin, float[] columnWidths, Pays pays) throws IOException {
		float nextX = x;
		contentStream.setFont(PDType1Font.HELVETICA, 12);

		// Dessiner les cellules de la ligne avec les données du pays
		drawCell(contentStream, nextX, y, columnWidths[0] * tableWidth, rowHeight, pays.getNomPays());
		nextX += columnWidths[0] * tableWidth;

		drawCell(contentStream, nextX, y, columnWidths[1] * tableWidth, rowHeight, pays.getContinent());
		nextX += columnWidths[1] * tableWidth;

		drawCell(contentStream, nextX, y, columnWidths[2] * tableWidth, rowHeight, pays.getPopulation().toString());
		nextX += columnWidths[2] * tableWidth;

		drawCell(contentStream, nextX, y, columnWidths[3] * tableWidth, rowHeight,
				String.valueOf(pays.getCodeIndicatif()));
		nextX += columnWidths[3] * tableWidth;

		drawCell(contentStream, nextX, y, columnWidths[4] * tableWidth, rowHeight, pays.getLangue());
	}

	private static void drawCell(PDPageContentStream contentStream, float x, float y, float width, float height,
			String text) throws IOException {
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		contentStream.addRect(x, y - height, width, height);
		contentStream.stroke();
		contentStream.beginText();
		contentStream.newLineAtOffset(x + 2, y - 12); // Décalage pour le centrage vertical du texte dans la cellule
		contentStream.showText(text);
		contentStream.endText();
	}
	
	
	public static List<Object[]> getPaysPopulation() {
        List<Object[]> paysPopulationList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "SELECT nomPays, population FROM pays";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String nomPays = rs.getString("nomPays");
                BigDecimal population = rs.getBigDecimal("population");
                Object[] paysPopulation = {nomPays, population};
                paysPopulationList.add(paysPopulation);
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

        return paysPopulationList;
    }
	
	// Méthode pour extraire tous les pays de la base de données
    public static List<Pays> getAllPaysJasper() {
        List<Pays> paysList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "SELECT * FROM pays";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Pays pays = new Pays();
                pays.setId(rs.getInt("id"));
                pays.setNomPays(rs.getString("nomPays"));
                pays.setContinent(rs.getString("continent"));
                pays.setPopulation(rs.getBigDecimal("population"));
                pays.setCodeIndicatif(rs.getInt("codeIndicatif"));
                pays.setLangue(rs.getString("langue"));
                paysList.add(pays);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de connexion ou d'exécution de la requête
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

        return paysList;
    }
}
