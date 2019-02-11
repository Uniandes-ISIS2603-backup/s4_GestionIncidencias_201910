/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.IncidenciaDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
/**
 *
 * @author estudiante
 */
@Path("Incidencias")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class IncidenciaResource {
    
       
private final static Logger LOGGER = Logger.getLogger(IncidenciaResource.class.getName());
    
    @POST
    public IncidenciaDTO createIncidenciaDTO(IncidenciaDTO incidencia){
        return incidencia;
    }
   
    
    @PUT
    public void UpdateIncidenciaDTO(String estado){
        
    } 
    
}
