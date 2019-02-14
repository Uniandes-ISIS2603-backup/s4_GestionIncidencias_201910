/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.TecnicoDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */
@Path("tecnicos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped 
public class TecnicoResource {
     private static final Logger LOG = Logger.getLogger(TecnicoResource.class.getName());
     
     @POST
     public TecnicoDTO createTecnico(TecnicoDTO tecnico){
         return tecnico;
     }
}
