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

@Path("empleados")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
/**
 * Clase que representa un recurso empleado
 * @author Valerie Parra Cortés
 */
public class EmpleadoResource {    
       
    @Inject
    private EmpleadoLogic empleadoLogic;
    
    
    @POST
     public EmpleadoDTO createEmpleado(EmpleadoDTO empleado) throws BusinessLogicException{
        EmpleadoEntity nuevo= empleado.toEntity();
        EmpleadoEntity eet=empleadoLogic.createEmpleadoEntity(nuevo);
        EmpleadoDTO nuevoEmpleado = new EmpleadoDTO(eet);
        return nuevoEmpleado;
     }    
    /**
     * Busca y devuelve todos los empleados que existen en la aplicacion.    
     * @return JSONArray {@link EmpleadosDetailDTO} - Los Empleados encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
    */
    @GET
    public List<EmpleadoDetailDTO> getEmpleados() {
        return listEntity2DetailDTO(empleadoLogic.getEmpleados());                
    }    
    
    @GET
    @Path("{id: \\d+}")
    public EmpleadoDetailDTO getEmpleado(@PathParam("id") Long id){
        EmpleadoEntity empleado= empleadoLogic.getEmpleado(id);
         if (empleado == null) {
            throw new WebApplicationException("El recurso /empleados/" + id + " no existe.", 404);
        }
        EmpleadoDetailDTO nuevo = new EmpleadoDetailDTO(empleado);
        return nuevo;                  
    }
    
    
    @PUT
    @Path("{id: \\d+}")
    public EmpleadoDetailDTO updateEmpleado(@PathParam("id") Long id, EmpleadoDetailDTO empleado) throws BusinessLogicException {
        empleado.setId(id);
        if (empleadoLogic.getEmpleado(id) == null) {
            throw new WebApplicationException("El recurso /empleados/" + id + " no existe.", 404);
        }
        EmpleadoDetailDTO detailDTO = new EmpleadoDetailDTO(empleadoLogic.updateEmpleado(empleado.toEntity()));        
        return detailDTO;
    }
    
    
    @DELETE
    @Path("{id: \\d+}")
    public void delteEmpleado(@PathParam("id") Long id) throws BusinessLogicException {

        EmpleadoEntity entity = empleadoLogic.getEmpleado(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /empleado/" + id + " no existe.", 404);
        }        
        empleadoLogic.deleteEmpleado(id);
        
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