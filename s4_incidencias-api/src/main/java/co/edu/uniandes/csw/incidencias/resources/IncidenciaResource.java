/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.IncidenciaDTO;
import co.edu.uniandes.csw.incidencias.dtos.IncidenciaDetailDTO;
import co.edu.uniandes.csw.incidencias.ejb.IncidenciaLogic;
import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
/**
 *
 * @author estudiante
 */
@Path("Incidencias")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class IncidenciaResource {
    
       //transforma a DTO
private final static Logger LOGGER = Logger.getLogger(IncidenciaResource.class.getName());
@Inject
private  IncidenciaLogic logic;    
    @POST
    public IncidenciaDTO createIncidenciaDTO(IncidenciaDTO incidencia) throws BusinessLogicException{
        IncidenciaEntity incidenciaEntity = incidencia.toEntity();
       logic.createIncidencia(incidenciaEntity);
        return new IncidenciaDTO(incidenciaEntity);
    }
   
    
    @PUT
    public void UpdateIncidenciaDTO(String estado){
        
    } 
    @GET
    @Path("(IncidenciasId: //d+)")
    public IncidenciaDTO getIncidenciaDTO(@PathParam ("IncidenciasId")Long id){
        IncidenciaEntity incidenciaEntity = logic.getIncidencia(id);
        
        if(incidenciaEntity == null){
            throw new WebApplicationException("El recuerso /incidencias/" + id + "no exiate", 404);
        }
        return new IncidenciaDetailDTO(incidenciaEntity);
    } 
    
}
