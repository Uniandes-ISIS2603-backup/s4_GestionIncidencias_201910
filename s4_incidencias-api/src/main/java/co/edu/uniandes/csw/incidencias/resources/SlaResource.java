/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.SlaDTO;
import java.util.logging.Logger;
import javax.ws.rs.POST;

/**
 *
 * @author estudiante
 */
public class SlaResource {
    private static final Logger LOG = Logger.getLogger(SlaResource.class.getName());
    
    @POST
     public SlaDTO createSla(SlaDTO sla){
         return sla;
    }
     
     
    
}
