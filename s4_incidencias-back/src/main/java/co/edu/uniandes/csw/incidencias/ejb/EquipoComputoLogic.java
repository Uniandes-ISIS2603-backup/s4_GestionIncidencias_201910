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
        if (persistence.findById(equipoComputoEntity.getId()) != null)
        {
             throw new BusinessLogicException("Ya existe un EquipoComputo con el id de equipo\"" + equipoComputoEntity.getId()+ "\"");
        }
        equipoComputoEntity = persistence.create(equipoComputoEntity);
        return equipoComputoEntity;
    }
    
    public List<EquipoComputoEntity> getEquipoComputos() {
        return persistence.findAll();
    }

    public EquipoComputoEntity getEquipoComputo(Long equipoComputoId) {
        return  persistence.find(equipoComputoId);
    }

    public EquipoComputoEntity updateEquipoComputo( EquipoComputoEntity equipoComputoEntity) {
        return  persistence.update(equipoComputoEntity);
    }

    public void deleteEquipoComputo(Long equipoComputoId) {
        persistence.delete(equipoComputoId);
    }
}
