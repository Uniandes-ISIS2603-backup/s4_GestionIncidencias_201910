/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.CalificacionEntity;

/**
 *
 * @author estudiante
 */
public class CalificacionDTO {
    private Long id;
    private Integer numeroEst;
    private String descripcion;

    public CalificacionDTO()
    {
        
    }
    public CalificacionDTO(CalificacionEntity nuevoCalificacionEntity) {
                 if (nuevoCalificacionEntity != null) {
            this.numeroEst = nuevoCalificacionEntity.getNumeroEst();
            this.descripcion = nuevoCalificacionEntity.getDescripcion();
        }
    }

    
    
    public CalificacionEntity toEntity()
    {
        CalificacionEntity calificacionEntity = new CalificacionEntity();
        calificacionEntity.setNumeroEst(this.numeroEst);
        calificacionEntity.setDescripcion(this.descripcion);
        return calificacionEntity;
    }
    /**
     * @return the numeroEst
     */
    public Integer getNumeroEst() {
        return numeroEst;
    }

    /**
     * @param numeroEst the numeroEst to set
     */
    public void setNumeroEst(Integer numeroEst) {
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
