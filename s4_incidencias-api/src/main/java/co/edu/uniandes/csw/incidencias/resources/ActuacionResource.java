/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.ActuacionDTO;
import co.edu.uniandes.csw.incidencias.ejb.ActuacionLogic;
import co.edu.uniandes.csw.incidencias.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 *Clase que representacion un resource actuacion
 * @author Guillermo Lobaton
 */
//------------------------------------TERMINADO----------------------------------------------

@Produces("application/json")
@Consumes("application/json")
public class ActuacionResource {
    
    
     private static final Logger LOGGER = Logger.getLogger(ActuacionResource.class.getName());
     /**
     * Atributo  que modela la conexion con la capa de logica
     */
    @Inject
    private ActuacionLogic logica;
    
    
   /**
    * 
    * @param IncidenciasId
    * @param actuacion
    * @return
    * @throws BusinessLogicException 
    */
    @POST
    public  ActuacionDTO createActuacion(@PathParam("IncidenciasId") Long IncidenciasId, ActuacionDTO actuacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ActuacionResource createActuacion: input: {0}", actuacion); 
        ActuacionDTO nuevoActuacionDTO = new ActuacionDTO(logica.createActuacion(IncidenciasId, actuacion.toEntity()));
        LOGGER.log(Level.INFO, "ActuacionResource createActuacion: output: {0}", nuevoActuacionDTO);        
        return nuevoActuacionDTO;
    }
    /**
     * 
     * @param booksId
     * @param ActuacionesId
     * @return
     * @throws BusinessLogicException 
     */
  
    @GET
    @Path("{ActuacionesId: \\d+}")
    public ActuacionDTO getActuacion(@PathParam("IncidenciasId") Long booksId, @PathParam("ActuacionesId") Long ActuacionesId) throws BusinessLogicException {
       
        LOGGER.log(Level.INFO, "ActuacionResource getActuacion: input: {0}", ActuacionesId);
        ActuacionEntity entity = logica.getActuacion(booksId, ActuacionesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/reviews/" + ActuacionesId+ " no existe.", 404);
        }
        ActuacionDTO reviewDTO = new ActuacionDTO(entity);
        LOGGER.log(Level.INFO, "ReviewResource getReview: output: {0}", reviewDTO);
        return reviewDTO;
    }

    @DELETE
    @Path("{ActuacionesId: \\d+}")
    
    public void deleteActuacion(@PathParam("IncidenciasId") Long IncidenciasId, @PathParam("ActuacionesId") Long ActuacionesId) throws BusinessLogicException {
      
        ActuacionEntity entity = logica.getActuacion(IncidenciasId, ActuacionesId);
        
        if (entity == null) {
            throw new WebApplicationException("El recurso /Incidencias/" + IncidenciasId + "/Actuaciones/" + ActuacionesId + " no existe.", 404);
        }
        logica.deleteActuacion(IncidenciasId, ActuacionesId);
    }

    
    @PUT
    @Path("{ActuacionesId: \\d+}")
    public ActuacionDTO updateActuacion(@PathParam("IncidenciasId") Long IncidenciasId, @PathParam("ActuacionesId") Long ActuacionesId, ActuacionDTO actuacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ActuacionResource updateActuacion: input: IncideniciasId: {0} , ActuacionesId: {1} , actuacion:{2}", new Object[]{IncidenciasId, ActuacionesId, actuacion});
        
        if (ActuacionesId.equals(actuacion.getId())) {
            throw new BusinessLogicException("Los ids del Review no coinciden.");
        }
        ActuacionEntity entity = logica.getActuacion(IncidenciasId, ActuacionesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + IncidenciasId + "/reviews/" + ActuacionesId + " no existe.", 404);

        }
        
        ActuacionDTO reviewDTO = new ActuacionDTO(logica.updateActuacion(IncidenciasId, actuacion.toEntity()));
        LOGGER.log(Level.INFO, "ReviewResource updateReview: output:{0}", reviewDTO);
        return reviewDTO;

    }
    
 
   
    
  /**
   * Obtiene la lista de las actuaciones
   * @param IncidenciasId
   * @return 
   */
     
      @GET
    public List<ActuacionDTO> getActuaciones(@PathParam("IncidenciasId") Long IncidenciasId) {
        LOGGER.log(Level.INFO, "ActuacionResource getActuaciones: input: {0}", IncidenciasId);
        
        List<ActuacionDTO> listaDTOs = listEntity2DTO(logica.getActuaciones(IncidenciasId));
        LOGGER.log(Level.INFO, "ActuacionResource getActuaciones: output: {0}", listaDTOs);
        return listaDTOs;
    }
     
     
     
     /**
      * Crea una lista de actuacionesDTO  a partir de una lista de actuacionesEntiy
      * @param entityList lista de actuacioEntityn
      * @return 
      */
      private List<ActuacionDTO> listEntity2DTO(List<ActuacionEntity> entityList) {
        List<ActuacionDTO> list = new ArrayList<>();
        for (ActuacionEntity entity : entityList) {
            list.add(new ActuacionDTO(entity));
        }
        return list;
    }
     
     
    
}
