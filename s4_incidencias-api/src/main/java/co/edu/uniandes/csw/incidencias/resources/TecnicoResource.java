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
    
    @Inject
    private TecnicoLogic tecnicoLogic;
     

     @POST
     public TecnicoDTO createTecnico(TecnicoDTO tecnico) throws BusinessLogicException{
        TecnicoEntity nuevo= tecnico.toEntity();
        TecnicoEntity eet=tecnicoLogic.createTecnico(nuevo);
        TecnicoDTO nuevoTecnico = new TecnicoDTO(eet);
        return nuevoTecnico;
     }
    
     @GET
     public List<TecnicoDetailDTO> getTecnicos(){
         return listEntity2DetailDTO(tecnicoLogic.getTecnicos());                
     }
     
     
     
     
     @GET
    @Path("{id: \\d+}")
    public TecnicoDetailDTO getTecnico(@PathParam("id") Long id){
        TecnicoEntity tecnico= tecnicoLogic.getTecnico(id);
         if (tecnico == null) {
            throw new WebApplicationException("El recurso /tecnicos/" + id + " no existe.", 404);
        }
        TecnicoDetailDTO nuevo = new TecnicoDetailDTO(tecnico);
        return nuevo;                  
    }
    
    
    @PUT
    @Path("{id: \\d+}")
    public TecnicoDetailDTO updateTecnico(@PathParam("id") Long id, TecnicoDetailDTO tecnico) throws BusinessLogicException {
        tecnico.setId(id);
        if (tecnicoLogic.getTecnico(id) == null) {
            throw new WebApplicationException("El recurso /tecnicos/" + id + " no existe.", 404);
        }
        TecnicoDetailDTO detailDTO = new TecnicoDetailDTO(tecnicoLogic.updateTecnico(tecnico.toEntity()));        
        return detailDTO;
    }
    
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTecnico(@PathParam("id") Long id) throws BusinessLogicException {

        TecnicoEntity entity = tecnicoLogic.getTecnico(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /tecnicos/" + id + " no existe.", 404);
        }        
        tecnicoLogic.deleteTecnico(id);        
    }
     
     
     
     
   
    private List<TecnicoDetailDTO> listEntity2DetailDTO(List<TecnicoEntity> entityList) {
        List<TecnicoDetailDTO> list = new ArrayList<>();
        for (TecnicoEntity entity : entityList) {
            list.add(new TecnicoDetailDTO(entity));
        }
        return list;
    }
     
}
