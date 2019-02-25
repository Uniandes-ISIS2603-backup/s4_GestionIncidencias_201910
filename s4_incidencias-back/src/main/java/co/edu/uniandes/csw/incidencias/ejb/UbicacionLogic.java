/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;

import co.edu.uniandes.csw.incidencias.entities.UbicacionEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.UbicacionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class UbicacionLogic {
    
    @Inject
    private UbicacionPersistence persistence;
    
    public UbicacionEntity createUbicacion(UbicacionEntity ubicacionEntity) throws BusinessLogicException{
        if (persistence.findByDescription(ubicacionEntity.getDescripcion()) != null) {
             throw new BusinessLogicException("Ya existe un Ubicacion con el descripcion \"" + ubicacionEntity.getDescripcion() + "\"");
        }
        ubicacionEntity = persistence.create(ubicacionEntity);
        return ubicacionEntity;
    }
    
    public List<UbicacionEntity> getUbicacions() {
        List<UbicacionEntity> ubicaciones = persistence.findAll();
        return ubicaciones;
    }

    public UbicacionEntity getUbicacion(Long ubicacionId) {
        UbicacionEntity ubicacionEntity = persistence.find(ubicacionId);
        return ubicacionEntity;
    }

    public UbicacionEntity updateUbicacion(Long ubicacionId, UbicacionEntity ubicacionEntity) {
        UbicacionEntity newEntity = persistence.update(ubicacionEntity);
        return newEntity;
    }

    public void deleteUbicacion(Long ubicacionId) throws BusinessLogicException {
        persistence.delete(ubicacionId);
    }
}
