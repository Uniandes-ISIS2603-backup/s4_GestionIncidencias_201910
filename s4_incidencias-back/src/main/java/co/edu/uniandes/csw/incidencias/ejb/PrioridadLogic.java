/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;

import co.edu.uniandes.csw.incidencias.entities.PrioridadEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.PrioridadPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel Santamaría
 */

@Stateless
public class PrioridadLogic {
    
    @Inject
    private PrioridadPersistence persistence;
    
    public PrioridadEntity createPrioridad(PrioridadEntity prioridad)throws BusinessLogicException
    {
        if( persistence.findByName(prioridad.getTipoPrioridad()) != null)
        {
            throw new BusinessLogicException("Ya existe una prioridad Con el nombre \""+ prioridad.getTipoPrioridad()+"\"" );
        }
        prioridad = persistence.create(prioridad);
        return prioridad;
    }
    
    
    
}
