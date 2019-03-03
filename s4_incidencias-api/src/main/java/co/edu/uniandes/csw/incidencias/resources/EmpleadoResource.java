/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;


import co.edu.uniandes.csw.incidencias.dtos.EmpleadoDTO;
import co.edu.uniandes.csw.incidencias.dtos.EmpleadoDetailDTO;
import co.edu.uniandes.csw.incidencias.ejb.EmpleadoLogic;
import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
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

@Path("empleados")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
/**
 * Clase que representa un recurso empleado
 * @author Valerie Parra Cortés
 */
public class EmpleadoResource {
    
    private static final Logger LOG = Logger.getLogger(EmpleadoResource.class.getName());    
    
    @Inject
    private EmpleadoLogic empleadoLogic;
    
    @POST
     public EmpleadoDTO createEmpleado(EmpleadoDTO tecnico){
         return tecnico;
     }    
     
     
     /**
     * Busca y devuelve todos los empleados que existen en la aplicacion.
     *
     * @return JSONArray {@link EmpleadosDetailDTO} - Los Empleados encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<EmpleadoDetailDTO> getEmpleados() {
        List<EmpleadoDetailDTO> listaBooks = listEntity2DetailDTO(empleadoLogic.getEmpleados());        
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
    private List<EmpleadoDetailDTO> listEntity2DetailDTO(List<EmpleadoEntity> entityList) {
        List<EmpleadoDetailDTO> list = new ArrayList<>();
        for (EmpleadoEntity entity : entityList) {
            list.add(new EmpleadoDetailDTO(entity));
        }
        return list;
    }
}
