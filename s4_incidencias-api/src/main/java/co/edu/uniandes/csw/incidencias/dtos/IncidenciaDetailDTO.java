/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import java.io.Serializable;
import java.util.Hashtable;

/**
 *
 * @author estudiante
 */
public class IncidenciaDetailDTO extends IncidenciaDTO implements Serializable
{
    
    //private ArrayList<ActuacionDTO> actuaciones;
    private Hashtable <String, ActuacionDTO> actuaciones = new Hashtable<String, ActuacionDTO>();
    
    public IncidenciaDetailDTO(){
               
    }    
    
    public IncidenciaDetailDTO(IncidenciaEntity incidencia){
               
         
    }  
     public void addIncidencencia(ActuacionDTO actuacion){
       actuaciones.put(actuacion.toString(),actuacion);
    }
      public void deleteIncidencencia(ActuacionDTO actuacion){
       actuaciones.remove(actuacion.toString());
    }
 
}
