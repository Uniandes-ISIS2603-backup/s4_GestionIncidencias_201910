/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.CalificacionDTO;
import co.edu.uniandes.csw.incidencias.ejb.CalificacionLogic;
import co.edu.uniandes.csw.incidencias.entities.CalificacionEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
 @Inject
    private CalificacionLogic calificacionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @POST
    public CalificacionDTO createCalificacion(CalificacionDTO calificacion) throws BusinessLogicException {
        
        CalificacionDTO nuevoCalificacionDTO = new CalificacionDTO(calificacionLogic.createCalificacion(calificacion.toEntity()));
 
        return nuevoCalificacionDTO;
    }

    @GET
    @Path("{calificacionsId: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("calificacionsId") Long calificacionsId) {
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: input: {0}", calificacionsId);
        CalificacionEntity calificacionEntity = calificacionLogic.getCalificacion(calificacionsId);
        if (calificacionEntity == null) {
            throw new WebApplicationException("El recurso /calificacions/" + calificacionsId + " no existe.", 404);
        }
        CalificacionDTO calificacionDetailDTO = new CalificacionDTO(calificacionEntity);
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: output: {0}", calificacionDetailDTO);
        return calificacionDetailDTO;
    }
    
         @GET
     public List<CalificacionDTO> getCalificaciones(){
         return listEntity2DTO(calificacionLogic.getCalificacions());                
     }

         private List<CalificacionDTO> listEntity2DTO(List<CalificacionEntity> entityList) {
        List<CalificacionDTO> list = new ArrayList<>();
        for (CalificacionEntity entity : entityList) {
            list.add(new CalificacionDTO(entity));
        }
        return list;
    }
    @PUT
    @Path("{calificacionsId: \\d+}")
    public CalificacionDTO updateCalificacion(@PathParam("calificacionsId") Long calificacionsId, CalificacionDTO calificacion) throws BusinessLogicException {
        calificacion.setId(calificacionsId);
        if (calificacionLogic.getCalificacion(calificacionsId) == null) {
            throw new WebApplicationException("El recurso /calificacions/" + calificacionsId + " no existe.", 404);
        }
        CalificacionDTO detailDTO = new CalificacionDTO(calificacionLogic.updateCalificacion(calificacionsId, calificacion.toEntity()));
        return detailDTO;
    }

    @DELETE
    @Path("{calificacionsId: \\d+}")
    public void deleteCalificacion(@PathParam("calificacionsId") Long calificacionsId) throws BusinessLogicException {
        CalificacionEntity entity = calificacionLogic.getCalificacion(calificacionsId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /calificacions/" + calificacionsId + " no existe.", 404);
        }
        calificacionLogic.deleteCalificacion(calificacionsId);
        LOGGER.info("CalificacionResource deleteCalificacion: output: void");
    }
    
}
