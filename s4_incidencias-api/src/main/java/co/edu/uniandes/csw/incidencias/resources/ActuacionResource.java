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

/**
 *Clase que representacion un resource actuacion
 * @author Guillermo Lobaton
 */
@Path("Actuaciones")

@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ActuacionResource {
    /**
     * Atributo  que modela la conexion con la capa de logica
     */
    @Inject
    private ActuacionLogic logica;
    
   
    /**
     * Crea una actuacion a partir del  objeto  que entra por parametro
     * @param actuacion, objeto DTO a partir del cual  se creara al actuacion
     * @return
     * @throws BusinessLogicException, lanza la excepcion si se incumple una regla del negocio
     */
     @POST
    public ActuacionDTO createIncidenciaDTO(ActuacionDTO actuacion) throws BusinessLogicException{
        
        ActuacionEntity actuacionEntity = actuacion.toEntity();
        actuacionEntity = logica.createActuacion(actuacionEntity);
        return new ActuacionDTO(actuacionEntity);
    }
    
    /**
     * Obtiene una actuacion a partir del id que entra por parametro
     * @param actuacionId, id de la actuacion buscada
     * @return un objeto  DTO que tiene como id el id que entra por parametro
     */
    @GET
    @Path("{id: \\d+}")
    public ActuacionDTO getActuacion(@PathParam("actuacionId") Long actuacionId) {
       
        ActuacionEntity ActuacionEntity =  logica.getActuacion(actuacionId);
        if (ActuacionEntity == null) {
            throw new WebApplicationException("El recurso /Actuaciones/" + actuacionId + " no existe.", 404);
        }
        ActuacionDTO ActuacionDTO = new ActuacionDTO(ActuacionEntity);
       
        return ActuacionDTO;
    }
    /**
     * Borra la actuacion que tiene asociado el id que entra por paramtro
     * @param actuacionId, id de la actuacion buscada
     */
     @DELETE
    @Path("{id: \\d+}")
    public void deleteActuacion(@PathParam("id") Long id) throws BusinessLogicException {

        ActuacionEntity entity = logica.getActuacion(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /actuaciones/" + id + "No existe", 404);
        }        
       logica.deleteActuacion(id);
    }
    /**
     * Actualiza a actuacion dado por parametro con el JSON dado por parametro
     * @param id de la actuacion a actualizar
     * @param actuacion (JSON) con la info actualizada
     * @return actuacion actualizado
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{id: \\d+}")
    public ActuacionDTO updateActuacion(@PathParam("id") Long id, ActuacionDTO actuacion) throws BusinessLogicException {
        
        if (logica.getActuacion(id) == null) {
            throw new WebApplicationException("El recurso /Actuaciones/" + id + "no existe", 404);
        }        
        return  new ActuacionDTO(logica.updateActuacion(actuacion.toEntity()));        
    }
    
     /**
     * Retorna todas las actuaciones de la base de datos
     * @return Lista con la lista de tecnicos de la base de datos     * 
     */
     @GET
     public List<ActuacionDTO> getTecnicos(){
         return listEntity2DTO(logica.getActuaciones());                
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
