/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *Atributo  que modela una actuacion el base de datos
 * @author Guillermo Lobaton
 */
@Entity
public class ActuacionEntity extends BaseEntity  implements Serializable {
        
    /**
     * Atributo que modela la fecha de la actuacion
     */
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    
    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private IncidenciaEntity incidencia;
    
    /**
     * Atributo que modela la descripcion de la actuacion
     */
    private String descripcion;
    /**
     * Atributo que modela lel tipo de la actuacion
     */
    private String tipo;

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
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @param incidencia the incidencia to set
     */
    public void setIncidencia(IncidenciaEntity incidencia) {
        this.incidencia = incidencia;
    }
    
    
    public IncidenciaEntity getIncidencia() {
        return incidencia;
    }
    
}
