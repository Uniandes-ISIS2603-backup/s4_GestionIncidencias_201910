/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.ActuacionEntity;

import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;

import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author estudiante
 */
public class IncidenciaDetailDTO extends IncidenciaDTO implements Serializable
{
    
    /**
     * Atributo  que modela la lista de actuaciones de una incidencia
     */
    private ArrayList <ActuacionDTO> actuaciones = new ArrayList();
    
    /**
     * Contructor vacio
     */
    public IncidenciaDetailDTO(){
             super();  
    } 
    
    /**
     * Crea la lista de incidencia a partir de una objeto del tipo entity
     * @param incidencia - objeto de tipo rntidad
     */
    public IncidenciaDetailDTO(IncidenciaEntity incidencia){
       super(incidencia);
       if(incidencia!=null){
        if( incidencia.getActuaciones()!= null)  {
            ArrayList <ActuacionDTO> r = new ArrayList();
            for(ActuacionEntity a : incidencia.getActuaciones()){
                r.add(new ActuacionDTO(a));    
            }
            this.setActuaciones(r);
        }
         
       }
     
    }
    /**
     * Crea un objeto de tipo entidad a partir de esta clase
     * @return un objeto de tipo entidad
     */
    public IncidenciaEntity toEntity(){
        
        IncidenciaEntity a = super.toEntity();
        ArrayList<ActuacionEntity> o = new ArrayList();
        for(ActuacionDTO z : actuaciones){
            o.add(z.toEntity()); 
        }
        a.setActuaciones(o);
        return a;
    }

    /**
     * Agrega un actuacion  a la lista de actuaciones
     * @param actuacion, actuacion que se va a agregar
     */
     public void addIncidencencia(ActuacionDTO actuacion){
        getActuaciones().add(actuacion);
    }
     /**
      * Elimina una actuacion de la lista de actuaciones
      * @param actuacion 
      */
      public void deleteIncidencencia(ActuacionDTO actuacion){
        getActuaciones().remove(actuacion);
    }
      
 

    /**
     * @return the actuaciones
     */
    public ArrayList <ActuacionDTO> getActuaciones() {
        return actuaciones;
    }

    /**
     * @param actuaciones the actuaciones to set
     */
    public void setActuaciones(ArrayList <ActuacionDTO> actuaciones) {
        this.actuaciones = actuaciones;
    }

    /**
     * @return the actuaciones
     */
  
 
}
