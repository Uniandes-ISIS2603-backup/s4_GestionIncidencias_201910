/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author estudiante
 */
public class SlaDTO extends UsuarioDTO implements Serializable{     

    private static final Logger LOG = Logger.getLogger(SlaDTO.class.getName());

    private int id;
    private String descripcion;
    
    /**
     * @return la id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id la id nueva
     */
    public void setId(Integer id) {
        this.id = id;
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
