package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.UbicacionDTO;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path('ubicaciones')
@Produces('application/json')
@Consumes('application/json')
@RequestScoped
public class UbicacionResource {
	
	private static final Logger LOGGER = Logger.getLogger(UbicacionResource.class.getName());
	
	@POST
	public UbicacionDTO crearUbicacion (UbicacionDTO ubicacion) {
		return ubicacion;
	}
}