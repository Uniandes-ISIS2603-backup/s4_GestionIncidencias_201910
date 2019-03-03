/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.EmpleadoDetailDTO;
import co.edu.uniandes.csw.incidencias.dtos.TecnicoDTO;
import co.edu.uniandes.csw.incidencias.dtos.TecnicoDetailDTO;
import co.edu.uniandes.csw.incidencias.ejb.TecnicoLogic;
import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidencias.entities.TecnicoEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
     private static final Logger LOG = Logger.getLogger(TecnicoResource.class.getName());     
     @POST
     public TecnicoDTO createTecnico(TecnicoDTO tecnico){
         return tecnico;
     }
     
     @GET
     public List<TecnicoDetailDTO> getTecnicos(){
         List<TecnicoDetailDTO> listaBooks = listEntity2DetailDTO(tecnicoLogic.getTecnicos());        
        return listaBooks;
     }
     
     /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EmpleadosEntity a una lista de
     * objetos EmpleadosDTO (json)
     *
     * @param entityList corresponde a la lista de libros de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de libros en forma DTO (json)
     */
    private List<TecnicoDetailDTO> listEntity2DetailDTO(List<TecnicoEntity> entityList) {
        List<TecnicoDetailDTO> list = new ArrayList<>();
        for (TecnicoEntity entity : entityList) {
            list.add(new TecnicoDetailDTO(entity));
        }
        return list;
    }
     
}
