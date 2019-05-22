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
 * @author estudiante
 */
@Entity
public class UbicacionEntity extends BaseEntity implements Serializable {
    
    private String descripcion;
    
    /*
    This is a comment
    // this is a nested comment 
    This is another comment
    */
    public UbicacionEntity () {
        
    }  
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
