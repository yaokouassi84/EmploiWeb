package fr.emploi.apis;

import java.util.ArrayList;
import java.util.List;

import fr.emploi.model.Pays;
import fr.emploi.myCon.PaysDAO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/pays")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaysResource {

	@Inject
    private PaysDAO paysDAO;
	private final List<Pays> paysList = new ArrayList<>();

    @POST
    public void createCountry(Pays country) {
    	PaysDAO.addPays(country);
    }
    

    @GET
    @Path("/{id}")
    public Pays getCountry(@PathParam("id") int id) {       
    	return PaysDAO.getPaysById(id);
    }

    @GET
    public List<Pays> getAllCountries() {
    	return PaysDAO.getAllPays();
    }

    @PUT
    @Path("/{id}")
    public void updateCountry(@PathParam("pays")  Pays country) {
    	PaysDAO.updatePays(country);
    }

    @DELETE
    @Path("/{id}")
    public void deleteCountry(@PathParam("id") int id) {
    	PaysDAO.deletePays(id);
    }
}
