/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;

import co.edu.uniandes.csw.incidencias.entities.CalificacionEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.CalificacionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class CalificacionLogic {
    
    @Inject
    private CalificacionPersistence persistence;
    
    public CalificacionEntity createCalificacion(CalificacionEntity calificacionEntity) throws BusinessLogicException{
        if (persistence.findByDescription(calificacionEntity.getDescripcion()) != null) {
             throw new BusinessLogicException("Ya existe un Calificacion con el descripcion \"" + calificacionEntity.getDescripcion() + "\"");
        }
        calificacionEntity = persistence.create(calificacionEntity);
        return calificacionEntity;
    }
    
    public List<CalificacionEntity> getCalificacions() {
        return persistence.findAll();
    }

    public CalificacionEntity getCalificacion(Long calificacionId) {
        return persistence.find(calificacionId);
    }

    public CalificacionEntity updateCalificacion( CalificacionEntity calificacionEntity) {
        return persistence.update(calificacionEntity);
    }

    public void deleteCalificacion(Long calificacionId) {
        persistence.delete(calificacionId);
    }

}
