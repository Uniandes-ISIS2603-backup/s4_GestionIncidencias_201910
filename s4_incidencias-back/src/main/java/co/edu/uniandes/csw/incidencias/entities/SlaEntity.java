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
 * @author Daniel Santamaría Álvarez
 */

@Entity
public class SlaEntity extends BaseEntity  implements Serializable{
    
    private String descripcion;    

    
    public SlaEntity(){
        
    }
    
    /**
     * @return la descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion la descripcion nueva
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
