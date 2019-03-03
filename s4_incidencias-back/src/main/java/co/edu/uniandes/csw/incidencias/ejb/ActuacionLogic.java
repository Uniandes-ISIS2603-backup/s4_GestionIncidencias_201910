/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;

import co.edu.uniandes.csw.incidencias.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.ActuacionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Guillermo Lobaton
 */

@Stateless
public class ActuacionLogic {
    @Inject
    private ActuacionPersistence persistence;
    
    public ActuacionEntity createActuacion (ActuacionEntity  actuacion)throws BusinessLogicException{
        if( persistence.find(actuacion.getId()) != null)
        {
            throw new BusinessLogicException("Ya existe una prioridad Con el nombre \""+ actuacion.getId()+"\"" );
        }
        actuacion= persistence.create(actuacion);
        return actuacion;
    }
    
    
       
    
    public List<ActuacionEntity> getActuaciones() {
        List<ActuacionEntity> prioridades = persistence.findAll();
        return prioridades;
    }




    public ActuacionEntity getActuacion(Long prioridadId) {
        ActuacionEntity prioridadEntity = persistence.find(prioridadId);
        return prioridadEntity;
    }

  

    public void deleteActuacion(Long ActuacionId) {
        persistence.delete(ActuacionId);
    }
    
    
    
}
