/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.EquipoComputoEntity;

/**
 *
 * @author estudiante
 */
public class EquipoComputoDTO {
    private Long id;
    private String descripcion;

    
    public EquipoComputoDTO()
    {
        
    }
    
    public EquipoComputoDTO(EquipoComputoEntity equipo)
    {
        if(equipo != null)
        {
            this.id = equipo.getId();
            this.descripcion = equipo.getDescripcion();
        }
                
    }

    public EquipoComputoEntity toEntity()
    {
        EquipoComputoEntity equipoComputoEntity = new EquipoComputoEntity();
        equipoComputoEntity.setId(this.id);
        equipoComputoEntity.setDescripcion(this.descripcion);
        return equipoComputoEntity;
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
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
}
