/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Daniel Santamaria
 */
public class PrioridadEntity extends BaseEntity implements Serializable {
    
    private String tipoPrioridad;
    private SlaEntity sla;
    
    public PrioridadEntity()
    {
        
    }
    

    /**
     * @return the tipoPrioridad
     */
    public String getTipoPrioridad() {
        return tipoPrioridad;
    }

    /**
     * @param tipoPrioridad the tipoPrioridad to set
     */
    public void setTipoPrioridad(String tipoPrioridad) {
        this.tipoPrioridad = tipoPrioridad;
    }

    /**
     * @return the sla
     */
    public SlaEntity getSla() {
        return sla;
    }

    /**
     * @param sla the sla to set
     */
    public void setSla(SlaEntity sla) {
        this.sla = sla;
    }
    
}
