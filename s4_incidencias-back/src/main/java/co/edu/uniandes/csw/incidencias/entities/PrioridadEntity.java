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
 * @author Daniel Santamaría 
 */

@Entity
public class PrioridadEntity extends BaseEntity  implements Serializable{
    
    private String tipoPrioridad;    

    
    public PrioridadEntity(){
        
    }
    
    /**
     * @return tipo prioridad
     */
    public String getTipoPrioridad() {
        return tipoPrioridad;
    }

    /**
     * @param tipoPrioridad nuevo tipo prioridad
     */
    public void setTipoPrioridad(String tipoPrioridad) {
        this.tipoPrioridad = tipoPrioridad;
    }
    
    
    
}
