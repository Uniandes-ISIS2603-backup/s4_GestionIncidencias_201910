/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.EmpleadoDTO;
import java.util.logging.Logger;
import javax.ws.rs.POST;

/**
 *
 * @author estudiante
 */
public class EmpleadoResource {
    private static final Logger LOG = Logger.getLogger(EmpleadoResource.class.getName());
    
    @POST
     public EmpleadoDTO createEmpleado(EmpleadoDTO tecnico){
         return tecnico;
     }
    
}
