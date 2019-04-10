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
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
    
   
    
     @POST
    public ActuacionDTO createIncidenciaDTO(ActuacionDTO actuacion) throws BusinessLogicException{
        
        ActuacionEntity actuacionEntity = actuacion.toEntity();
        actuacionEntity = logica.createActuacion(actuacionEntity);
        return new ActuacionDTO(actuacionEntity);
    }
    
    @GET
    @Path("{equipoComputosId: \\d+}")
    public ActuacionDTO getEquipoComputo(@PathParam("actuacionId") Long actuacionId) {
       
        ActuacionEntity ActuacionEntity =  logica.getActuacion(actuacionId);
        if (ActuacionEntity == null) {
            throw new WebApplicationException("El recurso /Actuaciones/" + actuacionId + " no existe.", 404);
        }
        ActuacionDTO ActuacionDTO = new ActuacionDTO(ActuacionEntity);
       
        return ActuacionDTO;
    }
    
    
}
