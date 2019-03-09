
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.ActuacionEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author estudiante
 */
public class ActuacionDTO implements Serializable {
    
    private Date fecha;
    private String descripcion;
    private String tipo;
    
    public ActuacionEntity toEntity(){
        ActuacionEntity actuacion = new ActuacionEntity();
        actuacion.setDescripcion(this.descripcion);
        actuacion.setFecha(this.fecha);
        actuacion.setTipo(this.tipo);
        return actuacion;
    }
    
    public ActuacionDTO(ActuacionEntity actuacion){
       this.fecha = actuacion.getFecha();
       this.descripcion = actuacion.getDescripcion();
       tipo = actuacion.getTipo();
    }
    public ActuacionDTO(){
        
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
     * @param Tipo the Tipo to set
     */
    public void setTipo(String Tipo) {
        this.tipo = Tipo;
    }
    
    
}
