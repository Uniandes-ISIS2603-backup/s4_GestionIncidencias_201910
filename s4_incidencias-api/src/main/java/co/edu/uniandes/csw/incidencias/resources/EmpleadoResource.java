/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.EmpleadoDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
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
    @POST
     public EmpleadoDTO createEmpleado(EmpleadoDTO tecnico){
         return tecnico;
     }    
}
