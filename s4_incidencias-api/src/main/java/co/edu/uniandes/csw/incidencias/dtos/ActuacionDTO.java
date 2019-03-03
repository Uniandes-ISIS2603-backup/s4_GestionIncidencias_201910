
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
    private String Tipo;
    
    public ActuacionDTO(ActuacionEntity actuacion){
       this.fecha = actuacion.getFecha();
       this.descripcion = actuacion.getDescripcion();
       Tipo = null;
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
        return Tipo;
    }

    /**
     * @param Tipo the Tipo to set
     */
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    
    public ActuacionEntity toEntity(){
        ActuacionEntity actuacion1 = new ActuacionEntity();
        actuacion1.setDescripcion(descripcion);
        actuacion1.setFecha(fecha);
        
        return actuacion1;
    }

}
