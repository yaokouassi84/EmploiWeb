package fr.emploi.controleur;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import fr.emploi.myCon.PaysDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/camembert")
public class Camembert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Camembert() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/png");

		// Créer un jeu de données pour le graphic camembert
		DefaultPieDataset dataset = new DefaultPieDataset();

		// Récupérer les données de la base de données à l'aide de votre DAO
		List<Object[]> paysPopulationList = PaysDAO.getPaysPopulation();

		for (Object[] paysPopulation : paysPopulationList) {
			String nomPays = (String) paysPopulation[0];
			BigDecimal population = (BigDecimal) paysPopulation[1];

			// Vérifier si la population est nulle
			if (population != null) {
				dataset.setValue(nomPays, population.doubleValue());
			} else {
				// Gérer le cas où la population est nulle
				// Ici, vous pouvez ignorer ce pays ou lui attribuer une valeur par défaut dans
				// le graphique
				// Nous ignorons simplement les pays avec une population nulle
				System.out.println("Population null pour le pays: " + nomPays);
			}
		}

		// Créer le graphique camembert
		JFreeChart chart = ChartFactory.createPieChart("Répartition de la population par pays", dataset, true, true,
				false);

		// Envoyer le graphique camembert en tant que réponse HTTP
		OutputStream out = response.getOutputStream();
		ChartUtilities.writeChartAsPNG(out, chart, 600, 400);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
