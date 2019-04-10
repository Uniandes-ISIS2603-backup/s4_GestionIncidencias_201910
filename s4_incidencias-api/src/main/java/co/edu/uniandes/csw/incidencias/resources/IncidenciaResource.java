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
     * Crea una incidencia a partir del JSON que entra como parametro
     * @param incidencia JSON a partir del cual se crea la incidencia
     * @return una incidencia despues de haberla creado en la base de datos
     * @throws BusinessLogicException en caso de que se rompa una regla de negocio
     */
    @POST
    public IncidenciaDTO createIncidenciaDTO(IncidenciaDTO incidencia) throws BusinessLogicException{
        IncidenciaEntity incidenciaEntity = incidencia.toEntity();
       logic.createIncidencia(incidenciaEntity);
        return new IncidenciaDTO(incidenciaEntity);
    }
    /**
     * Obtiene  la incidencia que tiene asociado el  id que entra por parametro
     * @param id identifiador de la incidencia
     * @return la incidencia que tien  el id asociado
     */
    @GET
    @Path("{id: \\d+}")
    public IncidenciaDTO getIncidenciaDTO(@PathParam ("IncidenciasId")Long id){
        IncidenciaEntity incidenciaEntity = logic.getIncidencia(id);
        
        if(incidenciaEntity == null){
            throw new WebApplicationException("El recuerso /incidencias/" + id + "no exiate", 404);
        }
        return new IncidenciaDetailDTO(incidenciaEntity);
    } 
    /**
     * elimina la incidencia identificada con el id que entra por parametro
     * @param id identificador de la idencia que se desea eliminar
     * @throws BusinessLogicException si se incumple una de las reglas del  negocio
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteIncidencia(@PathParam("id") Long id) throws BusinessLogicException {

        IncidenciaEntity entity = logic.getIncidencia(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Incidencias/" + id + "No existe", 404);
        }        
       logic.deleteIncidencia(id);
    }
    /**
     * Actualiza a incidencia dado por parametro con el JSON dado por parametro
     * @param id de la incidencia a actualizar
     * @param incidencia (JSON) con la info actualizada
     * @return incidencia actualizado
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{id: \\d+}")
    public IncidenciaDTO updateTecnico(@PathParam("id") Long id, IncidenciaDTO actuacion) throws BusinessLogicException {
        
        if (logic.getIncidencia(id) == null) {
            throw new WebApplicationException("El recurso /Incidencias/" + id + "no existe", 404);
        }        
        return  new IncidenciaDTO(logic.updateIncidencia( actuacion.toEntity()));        
    }
    
}
