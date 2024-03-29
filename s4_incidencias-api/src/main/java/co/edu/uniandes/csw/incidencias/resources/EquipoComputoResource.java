/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.EquipoComputoDTO;
import co.edu.uniandes.csw.incidencias.ejb.EquipoComputoLogic;
import co.edu.uniandes.csw.incidencias.entities.EquipoComputoEntity;
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

@Path("equipos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
/**
 *
 * @author estudiante
 */
public class EquipoComputoResource {

    
    private static final Logger LOGGER = Logger.getLogger(EquipoComputoResource.class.getName());
    private static final String NE = " no existe.";
    private static final String RA = "El recurso /calificacions/";
    
 @Inject
    private EquipoComputoLogic equipoComputoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @POST
    public EquipoComputoDTO createEquipoComputo(EquipoComputoDTO equipoComputo) throws BusinessLogicException {
        
        return new EquipoComputoDTO(equipoComputoLogic.createEquipoComputo(equipoComputo.toEntity()));
    }

             @GET
     public List<EquipoComputoDTO> getCalificaciones(){
         return listEntity2DTO(equipoComputoLogic.getEquipoComputos());                
     }

         private List<EquipoComputoDTO> listEntity2DTO(List<EquipoComputoEntity> entityList) {
        List<EquipoComputoDTO> list = new ArrayList<>();
        for (EquipoComputoEntity entity : entityList) {
            list.add(new EquipoComputoDTO(entity));
        }
        return list;
    }
    @GET
    @Path("{equipoComputosId: \\d+}")
    public EquipoComputoDTO getEquipoComputo(@PathParam("equipoComputosId") Long equipoComputosId) {
        LOGGER.log(Level.INFO, "EquipoComputoResource getEquipoComputo: input: {0}", equipoComputosId);
        EquipoComputoEntity equipoComputoEntity = equipoComputoLogic.getEquipoComputo(equipoComputosId);
        if (equipoComputoEntity == null) {
            throw new WebApplicationException(RA + equipoComputosId + NE, 404);
        }
        EquipoComputoDTO equipoComputoDetailDTO = new EquipoComputoDTO(equipoComputoEntity);
        LOGGER.log(Level.INFO, "EquipoComputoResource getEquipoComputo: output: {0}", equipoComputoDetailDTO);
        return equipoComputoDetailDTO;
    }

    @PUT
    @Path("{equipoComputosId: \\d+}")
    public EquipoComputoDTO updateEquipoComputo(@PathParam("equipoComputosId") Long equipoComputosId, EquipoComputoDTO equipoComputo) throws BusinessLogicException {
        equipoComputo.setId(equipoComputosId);
        if (equipoComputoLogic.getEquipoComputo(equipoComputosId) == null) {
            throw new WebApplicationException(RA + equipoComputosId + NE, 404);
        }
        return new EquipoComputoDTO(equipoComputoLogic.updateEquipoComputo( equipoComputo.toEntity()));
    }

    
    
    @DELETE
    @Path("{equipoComputosId: \\d+}")
    public void deleteEquipoComputo(@PathParam("equipoComputosId") Long equipoComputosId) throws BusinessLogicException {
        EquipoComputoEntity entity = equipoComputoLogic.getEquipoComputo(equipoComputosId);
        if (entity == null) {
            throw new WebApplicationException(RA + equipoComputosId + NE, 404);
        }
        equipoComputoLogic.deleteEquipoComputo(equipoComputosId);
        LOGGER.info("EquipoComputoResource deleteEquipoComputo: output: void");
    }
    
}

