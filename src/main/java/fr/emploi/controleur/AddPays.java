package fr.emploi.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import fr.emploi.model.Pays;
import fr.emploi.myCon.PaysDAO;

public class AddPays extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddPays() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomPays = request.getParameter("nomPays");
		String continent = request.getParameter("continent");
		BigDecimal population = new BigDecimal(request.getParameter("population"));
		int codeIndicatif = Integer.parseInt(request.getParameter("codeIndicatif"));
		String langue = request.getParameter("langue");
		
		Pays p = new Pays();
		p.setNomPays(nomPays);
		p.setContinent(continent);
		p.setPopulation(population);
		p.setCodeIndicatif(codeIndicatif);
		p.setLangue(langue);
		PaysDAO pdao = new PaysDAO();
		pdao.addPays(p);
		
		response.sendRedirect("succes.jsp");

	}

}
