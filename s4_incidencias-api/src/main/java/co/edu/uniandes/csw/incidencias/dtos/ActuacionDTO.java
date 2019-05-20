
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.ActuacionEntity;
import java.io.Serializable;

import java.util.Date;

/**
 *
 * @author estudiante
 */
public class ActuacionDTO implements Serializable {
    
    /**
     * Atributo  que representa la fecha de la actuacion
     */
    private Date fecha;
     /**
     * Atributo  que representa la descripcion de la actuacion
     */
    private String descripcion;
     /**
     * Atributo  que representa lel tipo de la actuacion
     */
    private String tipo;
    
    
    /**
     * Relacion a una incidecnia 
     */
    private IncidenciaDTO incidencia;
    
    
     
    
    /**
     * Constructor vacio
     */
    public ActuacionDTO(){
        
    }
    
    
     /**
     * Crea una actuacionDTO apartir de un objeto entity
     * @param actuacion, actuacion a partir de la cual se va a crear el DTO 
     */
    public ActuacionDTO(ActuacionEntity actuacion){
       this.fecha = actuacion.getFecha();
       this.descripcion = actuacion.getDescripcion();
       tipo = actuacion.getTipo();
       
       if(actuacion.getIncidencia()!= null){
           IncidenciaDTO incidencia2 = new IncidenciaDTO(actuacion.getIncidencia());
           this.incidencia = incidencia2; 
       }
       else{
           this.incidencia = null; 
       }
    }
    
    
    
    /**
     * Convierte esta actuacion en un objeto del tipo entidad
     * @return la actuacion convertida a entidad
     */
    public ActuacionEntity toEntity(){
       
        ActuacionEntity actuacion = new ActuacionEntity();
        actuacion.setDescripcion(this.descripcion);
        actuacion.setFecha(this.fecha);
        actuacion.setTipo(this.tipo);
        
        if(this.incidencia!= null){
        actuacion.setIncidencia(this.incidencia.toEntity());
        }
        return actuacion;
    }
    
   
  
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the Tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the Tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
