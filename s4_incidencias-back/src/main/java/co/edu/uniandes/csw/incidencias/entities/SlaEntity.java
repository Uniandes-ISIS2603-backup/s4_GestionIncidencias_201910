/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.entities;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class SlaEntity extends BaseEntity implements Serializable{
    
    private String idSla;
    private String descripcion;
    
    public SlaEntity()
    {
        
    }

    /**
     * @return the idSla
     */
    public String getIdSla() {
        return idSla;
    }

    /**
     * @param idSla the idSla to set
     */
    public void setIdSla(String idSla) {
        this.idSla = idSla;
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
