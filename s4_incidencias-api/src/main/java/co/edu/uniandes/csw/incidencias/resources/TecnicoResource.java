/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.TecnicoDTO;
import co.edu.uniandes.csw.incidencias.dtos.TecnicoDetailDTO;
import co.edu.uniandes.csw.incidencias.ejb.TecnicoLogic;
import co.edu.uniandes.csw.incidencias.entities.TecnicoEntity;
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
 * Clase que representa un recurso ténico
 * @author estudiante
 */
@Path("tecnicos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped 
public class TecnicoResource {
    
    /**
     * Lògica de la aplicaciòn
     */
    @Inject
    private TecnicoLogic tecnicoLogic;
    
    
    private static final String NO_EXISTE=" no existe.";
    private static final String RA = "El recurso /calificacions/";
    /**
     * Crea un tècnico con el JSON entrado por parametro
     * @param tecnico (JSON) con la informaciòn del objeto a crear
     * @return Un TecnicoDTO con el id de la tabla de datos
     * @throws BusinessLogicException Si se rompe una regla de negocio
     */
     @POST
     public TecnicoDTO createTecnico(TecnicoDTO tecnico) throws BusinessLogicException{
        TecnicoEntity nuevo= tecnico.toEntity();
        TecnicoEntity eet=tecnicoLogic.createTecnico(nuevo);         
        return new TecnicoDTO(eet);
     }
    /**
     * Retorna todos los tecnicos de la base de datos
     * @return Lista con la lista de tecnicos de la base de datos     * 
     */
     @GET
     public List<TecnicoDetailDTO> getTecnicos(){
         return listEntity2DetailDTO(tecnicoLogic.getTecnicos());                
     }
     
     
     /**
      *  Devuelve el tècnico identificado con el id entrado por parametro
      * @param id del tècnico
      * @return TecnicoDetailDto COon el respectivo tecnico
      */
     
    @GET
    @Path("{id: \\d+}")
    public TecnicoDetailDTO getTecnico(@PathParam("id") Long id){
        TecnicoEntity tecnico= tecnicoLogic.getTecnico(id);
         if (tecnico == null) {
            throw new WebApplicationException(RA + id + NO_EXISTE, 404);
        }
         
        return new TecnicoDetailDTO(tecnico);                  
    }
    
       /**
     * Actualiza el tècnico dado por parametro con el JSON dado por parametro
     * @param userName del tècnico a actualizar
     * @return Tecnico actualizado
     
     */
    
    @GET
    @Path("users/{username}")
    public TecnicoDetailDTO getTecnicoPorUsuario(@PathParam("username") String userName){                 
        TecnicoEntity tt = tecnicoLogic.getTecnicoPorUsuario(userName);
        if (tt == null) {
            throw new WebApplicationException("No existe el usuario: " + userName , 404);
        }
        return new TecnicoDetailDTO(tt);
    }
    
    
    
    /**
     * Actualiza el tècnico dado por parametro con el JSON dado por parametro
     * @param id del tècnico a actualizar
     * @param tecnico (JSON) con la info actualizada
     * @return Tecnico actualizado
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{id: \\d+}")
    public TecnicoDetailDTO updateTecnico(@PathParam("id") Long id, TecnicoDetailDTO tecnico) throws BusinessLogicException {
        tecnico.setId(id);
        if (tecnicoLogic.getTecnico(id) == null) {
            throw new WebApplicationException(RA + id + NO_EXISTE, 404);
        }        
        return  new TecnicoDetailDTO(tecnicoLogic.updateTecnico(tecnico.toEntity()));        
    }
    
    /**
     * Borra el tècnico del id dado por parametro
     * @param id del tecnico
     * @throws BusinessLogicException  Si se romple una regla de negocio
     */
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTecnico(@PathParam("id") Long id) throws BusinessLogicException {

        TecnicoEntity entity = tecnicoLogic.getTecnico(id);
        if (entity == null) {
            throw new WebApplicationException(RA + id + NO_EXISTE, 404);
        }        
        tecnicoLogic.deleteTecnico(id);        
    }
     
     
         
   /**
    * Transforma una lista de entidades a DTO
    * @param entityList li
    * @return 
    */
    private List<TecnicoDetailDTO> listEntity2DetailDTO(List<TecnicoEntity> entityList) {
        List<TecnicoDetailDTO> list = new ArrayList<>();
        for (TecnicoEntity entity : entityList) {
            list.add(new TecnicoDetailDTO(entity));
        }
        return list;
    }
     
}
