package fr.emploi.model;

import java.math.BigDecimal;

public class Pays {

	private int id;
	private String nomPays;
	private String continent;
	private BigDecimal population;
	private int codeIndicatif;
	
	private String langue;

	public Pays() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pays(int id,String nomPays, String continent, BigDecimal population, int codeIndicatif, String langue) {
		super();
		this.id = id;
		this.nomPays = nomPays;
		this.continent = continent;
		this.population = population;
		this.codeIndicatif = codeIndicatif;
		this.langue = langue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomPays() {
		return nomPays;
	}

	public void setNomPays(String nomPays) {
		this.nomPays = nomPays;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public BigDecimal getPopulation() {
		return population;
	}

	public void setPopulation(BigDecimal population) {
		this.population = population;
	}

	public int getCodeIndicatif() {
		return codeIndicatif;
	}

	public void setCodeIndicatif(int codeIndicatif) {
		this.codeIndicatif = codeIndicatif;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	@Override
	public String toString() {
		return "Pays [id=" + id + ", nomPays=" + nomPays + ", continent=" + continent + ", population=" + population
				+ ", codeIndicatif=" + codeIndicatif + ", langue=" + langue + "]";
	}
	
	
	
}
