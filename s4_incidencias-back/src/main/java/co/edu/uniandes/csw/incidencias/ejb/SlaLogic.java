/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;

import co.edu.uniandes.csw.incidencias.entities.SlaEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.SlaPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel Santamaria
 */

@Stateless
public class SlaLogic {
     
    @Inject
    private SlaPersistence persistence;
    
    public SlaEntity createPrioridad(SlaEntity sla)throws BusinessLogicException
    {
        if( persistence.findByName(sla.getIdSla()) != null)
        {
            throw new BusinessLogicException("Ya existe una prioridad Con el nombre \""+ sla.getId()+"\"" );
        }
        sla = persistence.create(sla);
        return sla;
    }
    
    
    
}
