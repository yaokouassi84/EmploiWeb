package fr.emploi.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.awt.Paint;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import fr.emploi.myCon.PaysDAO;

@WebServlet("/baton")
public class GBaton extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public GBaton() {
		super();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("image/png");

	    // Créer un jeu de données pour le graphique en barres
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	    // Récupérer les données de la base de données à l'aide de votre DAO
	    List<Object[]> paysPopulationList = PaysDAO.getPaysPopulation();

	    // Créer le graphique en barres
	    JFreeChart chart = ChartFactory.createBarChart("Répartition de la population par pays", "Pays", "Population", dataset, PlotOrientation.VERTICAL, true, true, false);

	    // Générer des couleurs uniques pour chaque barre
	    List<Color> colors = generateUniqueColors(paysPopulationList.size());

	    for (int i = 0; i < paysPopulationList.size(); i++) {
	        Object[] paysPopulation = paysPopulationList.get(i);
	        String nomPays = (String) paysPopulation[0];
	        BigDecimal population = (BigDecimal) paysPopulation[1];

	        // Vérifier si la population est nulle
	        if (population != null) {
	            // Ajouter la barre avec la couleur correspondante
	            dataset.addValue(population.doubleValue(), "Population", nomPays);

	            // Récupérer le rendu pour les catégories (les barres)
	            CategoryItemRenderer renderer = chart.getCategoryPlot().getRenderer();

	            // Vérifier si le rendu est de type BarRenderer
	            if (renderer instanceof BarRenderer) {
	                // Convertir le rendu en BarRenderer
	                BarRenderer barRenderer = (BarRenderer) renderer;

	                // Définir la couleur de la série (la barre) actuelle
	                barRenderer.setSeriesPaint(i, colors.get(i));
	            } else {
	                // Gérer le cas où le rendu n'est pas de type BarRenderer
	                // Vous pouvez afficher un message d'erreur ou choisir une autre approche pour définir les couleurs
	                System.out.println("Le rendu n'est pas un BarRenderer");
	            }
	        } else {
	            // Gérer le cas où la population est nulle
	            // Ici, vous pouvez ignorer ce pays ou lui attribuer une valeur par défaut dans le graphique
	            // Nous ignorons simplement les pays avec une population nulle
	            System.out.println("Population null pour le pays: " + nomPays);
	        }
	    }

	    // Envoyer le graphique en barres en tant que réponse HTTP
	    OutputStream out = response.getOutputStream();
	    ChartUtilities.writeChartAsPNG(out, chart, 600, 400);
	}

	// Méthode pour générer des couleurs uniques
	private List<Color> generateUniqueColors(int count) {
	    List<Color> colors = new ArrayList<>();
	    Random random = new Random();
	    for (int i = 0; i < count; i++) {
	        // Générer une couleur aléatoire
	        Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
	        colors.add(color);
	    }
	    return colors;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
