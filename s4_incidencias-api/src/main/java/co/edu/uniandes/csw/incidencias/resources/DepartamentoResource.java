package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.DepartamentoDTO;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.logging.Logger;

@Path("departamentos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class DepartamentoResource {
	
	private static final Logger LOGGER = Logger.getLogger(DepartamentoResource.class.getName());
	
	@POST
	public DepartamentoDTO crearDepartamento (DepartamentoDTO departamento) {
		return departamento;
	}
}
