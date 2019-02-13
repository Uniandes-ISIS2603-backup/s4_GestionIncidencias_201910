/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import javax.ws.rs.Path;
import co.edu.uniandes.csw.incidencias.dtos.IncidenciaDTO;
import co.edu.uniandes.csw.incidencias.dtos.SlaDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
/**
 *
 * @author estudiante
 */

@Path("Sla")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class SlaResource {
    
       
       
private final static Logger LOGGER = Logger.getLogger(IncidenciaResource.class.getName());
    
    @POST
    public SlaDTO createPrioridadDTO(SlaDTO incidencia){
        return incidencia;
    }
   
    
    @PUT
    public void UpdateSlaDTO(String estado){
        
    } 
    
    
}
