/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;

import co.edu.uniandes.csw.incidencias.entities.PrioridadEntity;
import co.edu.uniandes.csw.incidencias.entities.SlaEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.SlaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel Santamaría Álvarez
 */

@Stateless
public class SlaLogic {
     
    @Inject
    private SlaPersistence persistence;
    
    public SlaEntity createSla(SlaEntity sla)throws BusinessLogicException
    {
        if( persistence.findAll() != null)
        {
            throw new BusinessLogicException("Ya existe un SLA Con el id \""+ sla.getId()+"\"" );
        }
        sla = persistence.create(sla);
        return sla;
    } 
    
    public List<SlaEntity> getSlas() {
        List<SlaEntity> slas = persistence.findAll();
        return slas;
    }

    public SlaEntity getSla(Long slaId) {
        SlaEntity slaEntity = persistence.find(slaId);
        return slaEntity;
    }

    public SlaEntity updateSla(Long slaId, SlaEntity slaEntity) {
        SlaEntity newEntity = persistence.update(slaEntity);
        return newEntity;
    }

    public void deleteSla(Long slaId) {
        persistence.delete(slaId);
    }
    
    
    
}
