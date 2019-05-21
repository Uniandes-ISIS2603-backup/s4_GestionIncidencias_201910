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
private final static Logger LOGGER = Logger.getLogger(IncidenciaResource.class.getName());

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
     * @param IncidenciaId
     * @return 
     */
    @GET
    @Path("{IncidenciaId: \\d+}")
    public IncidenciaDetailDTO getIncidencia(@PathParam("IncidenciaId") Long IncidenciaId) {
        LOGGER.log(Level.INFO, "IncidenciaResource getIncidencia: input: {0}", IncidenciaId);
        IncidenciaEntity incidenciaEntity = logic.getIncidencia(IncidenciaId);
        if (incidenciaEntity == null) {
            throw new WebApplicationException("El recurso /books/" + IncidenciaId + " no existe.", 404);
        }
        IncidenciaDetailDTO bookDetailDTO = new IncidenciaDetailDTO(incidenciaEntity);
        LOGGER.log(Level.INFO, "IncidenciaResource getIncidencia: output: {0}", bookDetailDTO);
        return bookDetailDTO;
    }
   /**
    * Actualiza una incidencia
    * @param IncidenciaId
    * @param incidencia
    * @return
    * @throws BusinessLogicException 
    */
     @PUT
    @Path("{IncidenciaId: \\d+}")
    public IncidenciaDetailDTO updateBook(@PathParam("IncidenciaId") Long IncidenciaId, IncidenciaDetailDTO incidencia) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "IncidenciaResource updateIncidencias: input: id: {0} , book: {1}", new Object[]{IncidenciaId, incidencia});
        incidencia.setId(IncidenciaId);
        if (logic.getIncidencia(IncidenciaId) == null) {
            throw new WebApplicationException("El recurso /books/" + IncidenciaId + " no existe.", 404);
        }
        IncidenciaDetailDTO detailDTO = new IncidenciaDetailDTO(logic.updateIncidencia(IncidenciaId, incidencia.toEntity()));
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
    public void deleteBook(@PathParam("IncidenciasId") Long IncidenciasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "IncidenciaResource deleteIncidencia: input: {0}", IncidenciasId);
        IncidenciaEntity entity = logic.getIncidencia(IncidenciasId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Incidencias/" + IncidenciasId + " no existe.", 404);
        }
        logic.deleteIncidencia(IncidenciasId);
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
}
