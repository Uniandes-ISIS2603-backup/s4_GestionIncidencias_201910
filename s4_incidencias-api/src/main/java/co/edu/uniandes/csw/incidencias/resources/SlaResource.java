/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.CalificacionDTO;
import co.edu.uniandes.csw.incidencias.dtos.SlaDTO;
import co.edu.uniandes.csw.incidencias.ejb.SlaLogic;
import co.edu.uniandes.csw.incidencias.entities.CalificacionEntity;
import co.edu.uniandes.csw.incidencias.entities.SlaEntity;
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

/**
 *
 * @author Daniel Santamaría Álvarez
 */

@Path("Sla")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class SlaResource {
      
    private static final Logger LOGGER = Logger.getLogger(SlaResource.class.getName());
    
 @Inject
    private SlaLogic slaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @POST
    public SlaDTO createSla(SlaDTO sla) throws BusinessLogicException {
        
        SlaDTO nuevoSlaDTO = new SlaDTO(slaLogic.createSlaEntity(sla.toEntity()));
 
        return nuevoSlaDTO;
    }

    @GET
    @Path("{slaId: \\d+}")
    public SlaDTO getSla(@PathParam("slaId") Long slaId) {
        LOGGER.log(Level.INFO, "SlaResource getsla: input: {0}", slaId);
        SlaEntity slaEntity = slaLogic.getSla(slaId);
        if (slaEntity == null) {
            throw new WebApplicationException("El recurso /sla/" + slaId + " no existe.", 404);
        }
        SlaDTO slaDetailDTO = new SlaDTO(slaEntity);
        LOGGER.log(Level.INFO, "SlaResource getSla: output: {0}", slaDetailDTO);
        return slaDetailDTO;
    }

    @PUT
    @Path("{slaId: \\d+}")
    public SlaDTO updateCalificacion(@PathParam("slaId") Long slaId, SlaDTO sla) throws BusinessLogicException {
        sla.setId(slaId);
        if (slaLogic.getSla(slaId) == null) {
            throw new WebApplicationException("El recurso /calificacions/" + slaId + " no existe.", 404);
        }
        SlaDTO detailDTO = new SlaDTO(slaLogic.updateSla( sla.toEntity()));
        return detailDTO;
    }

    @DELETE
    @Path("{slaId: \\d+}")
    public void deleteSla(@PathParam("slaId") Long slaId) throws BusinessLogicException {
        SlaEntity entity = slaLogic.getSla(slaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /sla/" + slaId + " no existe.", 404);
        }
        slaLogic.deleteSla(slaId);
        LOGGER.info("SlaResource deleteSla: output: void");
    }
    
    
}
