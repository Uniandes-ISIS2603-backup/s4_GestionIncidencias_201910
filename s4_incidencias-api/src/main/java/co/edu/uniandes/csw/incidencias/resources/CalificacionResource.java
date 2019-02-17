/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.CalificacionDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("calificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
/**
 *
 * @author estudiante
 */
public class CalificacionResource {

    
    private static final Logger LOGGER = Logger.getLogger(CalificacionResource.class.getName());
    
    @POST
    public CalificacionDTO createCalificacion( CalificacionDTO calificacion)
    {
        return calificacion;
    }
}
