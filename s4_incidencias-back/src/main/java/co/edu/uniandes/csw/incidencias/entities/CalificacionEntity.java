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
public class CalificacionEntity extends BaseEntity implements Serializable {
    private Integer numeroEst;
    
    private String descripcion;

    public CalificacionEntity()
    {
        
    }
    /**
     * @return the numeroEst
     */
    public int getNumeroEst() {
        return numeroEst;
    }

    /**
     * @param numeroEst the numeroEst to set
     */
    public void setNumeroEst(int numeroEst) {
        this.numeroEst = numeroEst;
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
}
