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
import java.util.logging.Level;
import java.util.logging.Logger;
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
private static final Logger LOGGER = Logger.getLogger(IncidenciaResource.class.getName());
private final static String NE = " no existe.";
private final static String RA = "El recurso /calificacions/";

@Inject
private  IncidenciaLogic logic;  

  /**
   * 
   * @param incidencia
   * @return
   * @throws BusinessLogicException 
   */
    @POST
    public IncidenciaDTO createIncidencia(IncidenciaDTO incidencia) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BookResource createBook: input: {0}", incidencia);
        IncidenciaDTO nuevoBookDTO = new IncidenciaDTO(logic.createIncidencia(incidencia.toEntity()));
        LOGGER.log(Level.INFO, "IncidenciaResource createIncidencia: output: {0}", nuevoBookDTO);
        return nuevoBookDTO;
    }

    
    /**
     * 
     * @return 
     */
    @GET
    public List<IncidenciaDetailDTO> getIncidencias() {
        LOGGER.info("IncidenciaResource getIncidencias: input: void");
        
        List<IncidenciaDetailDTO> listaBooks = listEntity2DetailDTO(logic.getIncidencias());
        LOGGER.log(Level.INFO, "IncidenciaResource getIncidencias: output: {0}", listaBooks);
        return listaBooks;
    }
    /**
     * 
     * @param incidenciaId
     * @return 
     */
    @GET
    @Path("{IncidenciaId: \\d+}")
    public IncidenciaDetailDTO getIncidencia(@PathParam("IncidenciaId") Long incidenciaId) {
        LOGGER.log(Level.INFO, "IncidenciaResource getIncidencia: input: {0}", incidenciaId);
        IncidenciaEntity incidenciaEntity = logic.getIncidencia(incidenciaId);
        if (incidenciaEntity == null) {
            throw new WebApplicationException(RA+ incidenciaId + NE, 404);
        }
        IncidenciaDetailDTO bookDetailDTO = new IncidenciaDetailDTO(incidenciaEntity);
        LOGGER.log(Level.INFO, "IncidenciaResource getIncidencia: output: {0}", bookDetailDTO);
        return bookDetailDTO;
    }
   /**
    * Actualiza una incidencia
    * @param incidenciaId
    * @param incidencia
    * @return
    * @throws BusinessLogicException 
    */
     @PUT
    @Path("{IncidenciaId: \\d+}")
    public IncidenciaDetailDTO updateBook(@PathParam("IncidenciaId") Long incidenciaId, IncidenciaDetailDTO incidencia) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "IncidenciaResource updateIncidencias: input: id: {0} , book: {1}", new Object[]{incidenciaId, incidencia});
        incidencia.setId(incidenciaId);
        if (logic.getIncidencia(incidenciaId) == null) {
            throw new WebApplicationException(RA+ incidenciaId + NE, 404);
        }
        IncidenciaDetailDTO detailDTO = new IncidenciaDetailDTO(logic.updateIncidencia(incidenciaId, incidencia.toEntity()));
        LOGGER.log(Level.INFO, "IncidenciaResource updateIncidencia: output: {0}", detailDTO);
        return detailDTO;
    }
    /**
     * 
     * @param booksId
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{IncidenciasId: \\d+}")
    public void deleteBook(@PathParam("IncidenciasId") Long incidenciasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "IncidenciaResource deleteIncidencia: input: {0}", incidenciasId);
        IncidenciaEntity entity = logic.getIncidencia(incidenciasId);
        if (entity == null) {
            throw new WebApplicationException(RA + incidenciasId + NE, 404);
        }
        logic.deleteIncidencia(incidenciasId);
        LOGGER.info("IncidenciaResource deleteIncidencia: output: void");
    }
    

    /**
     * 
     * @param entityList
     * @return 
     */
    private List<IncidenciaDetailDTO> listEntity2DetailDTO(List<IncidenciaEntity> entityList) {
        List<IncidenciaDetailDTO> list = new ArrayList<>();
        for (IncidenciaEntity entity : entityList) {
            list.add(new IncidenciaDetailDTO(entity));
        }
        return list;
    }
    
    
    /**
     * 
     *
     * @param IncidenciasId
     * @return 
     */
     @Path("{IncidenciasId: \\d+}/actuaciones")
    public Class<ActuacionResource> getActuacionResource(@PathParam("IncidenciasId") Long IncidenciasId) {
        if (logic.getIncidencia(IncidenciasId) == null) {
            throw new WebApplicationException("El recurso /Incidencias/" + IncidenciasId + "/actuaciones no existe.", 404);
        }
        return ActuacionResource.class;
    }
    
   
    
}
