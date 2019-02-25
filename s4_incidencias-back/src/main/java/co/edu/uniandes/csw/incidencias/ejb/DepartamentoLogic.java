/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;

import co.edu.uniandes.csw.incidencias.entities.DepartamentoEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.DepartamentoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class DepartamentoLogic {
    
    @Inject
    private DepartamentoPersistence persistence;
    
    public DepartamentoEntity createDepartamento(DepartamentoEntity departamentoEntity) throws BusinessLogicException{
        if (persistence.findByName(departamentoEntity.getNombre()) != null) {
             throw new BusinessLogicException("Ya existe un Departamento con el nombre \"" + departamentoEntity.getNombre() + "\"");
        }
        departamentoEntity = persistence.create(departamentoEntity);
        return departamentoEntity;
    }
    
    public List<DepartamentoEntity> getDepartamentos() {
        List<DepartamentoEntity> departamentos = persistence.findAll();
        return departamentos;
    }

    public DepartamentoEntity getDepartamento(Long departamentoId) {
        DepartamentoEntity departamentoEntity = persistence.find(departamentoId);
        return departamentoEntity;
    }

    public DepartamentoEntity updateDepartamento(Long departamentoId, DepartamentoEntity departamentoEntity) {
        DepartamentoEntity newEntity = persistence.update(departamentoEntity);
        return newEntity;
    }

    public void deleteDepartamento(Long departamentoId) {
        persistence.delete(departamentoId);
    }
}
