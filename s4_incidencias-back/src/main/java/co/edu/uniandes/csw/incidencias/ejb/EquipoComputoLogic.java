/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;

import co.edu.uniandes.csw.incidencias.entities.EquipoComputoEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.EquipoComputoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class EquipoComputoLogic {
    
    @Inject
    private EquipoComputoPersistence persistence;
    
    public EquipoComputoEntity createEquipoComputo(EquipoComputoEntity equipoComputoEntity) throws BusinessLogicException{
        if (persistence.findById(equipoComputoEntity.getIdEquipo()) != null) {
             throw new BusinessLogicException("Ya existe un EquipoComputo con el id de equipo\"" + equipoComputoEntity.getIdEquipo()+ "\"");
        }
        equipoComputoEntity = persistence.create(equipoComputoEntity);
        return equipoComputoEntity;
    }
    
    public List<EquipoComputoEntity> getEquipoComputos() {
        List<EquipoComputoEntity> equipoComputoes = persistence.findAll();
        return equipoComputoes;
    }

    public EquipoComputoEntity getEquipoComputo(Long equipoComputoId) {
        EquipoComputoEntity equipoComputoEntity = persistence.find(equipoComputoId);
        return equipoComputoEntity;
    }

    public EquipoComputoEntity updateEquipoComputo(Long equipoComputoId, EquipoComputoEntity equipoComputoEntity) {
        EquipoComputoEntity newEntity = persistence.update(equipoComputoEntity);
        return newEntity;
    }

    public void deleteEquipoComputo(Long equipoComputoId) throws BusinessLogicException {
        persistence.delete(equipoComputoId);
    }
}
