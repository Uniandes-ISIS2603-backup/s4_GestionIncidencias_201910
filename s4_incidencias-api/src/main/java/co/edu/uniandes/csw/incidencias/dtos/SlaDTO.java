/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.CalificacionEntity;
import co.edu.uniandes.csw.incidencias.entities.SlaEntity;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Santamaría ÁLvarez
 */
public class SlaDTO implements Serializable{     

    private static final Logger LOG = Logger.getLogger(SlaDTO.class.getName());

    private Long id;
    private String descripcion;
    
    public SlaDTO()
    {
        
    }
    
    public SlaDTO(SlaEntity nuevoSlaEntity) {
                 if (nuevoSlaEntity != null) {
            this.id = nuevoSlaEntity.getId();
            this.descripcion = nuevoSlaEntity.getDescripcion();
        }
    }

    
    
    public SlaEntity toEntity()
    {
        SlaEntity SlaEntity = new SlaEntity();
        SlaEntity.setId(this.id);
        SlaEntity.setDescripcion(this.descripcion);
        return SlaEntity;
    }
    
    /**
     * @return la id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id la id nueva
     */
    public void setId(Long id) {
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
