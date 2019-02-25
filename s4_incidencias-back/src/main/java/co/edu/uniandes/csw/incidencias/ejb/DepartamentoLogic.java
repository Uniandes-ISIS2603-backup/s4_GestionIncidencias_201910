/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;

import co.edu.uniandes.csw.incidencias.entities.DepartamentoEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.DepartamentoPersistence;
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
}
