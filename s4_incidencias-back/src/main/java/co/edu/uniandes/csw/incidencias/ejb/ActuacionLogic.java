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
    /**
     * Atributo  que modela la relacion con la persistencia
     */
    @Inject
    private ActuacionPersistence persistence;
    
    /**
     * Una actuacion a partir de una actuacion de tipo entity
     * @param actuacion
     * @return
     * @throws BusinessLogicException 
     */
    public ActuacionEntity createActuacion (ActuacionEntity  actuacion)throws BusinessLogicException{
        if( persistence.find(actuacion.getId()) != null)
        {
            throw new BusinessLogicException("Ya existe una prioridad Con el nombre \""+ actuacion.getId()+"\"" );
        }
        actuacion= persistence.create(actuacion);
        return actuacion;
    }
    
    
       
    /**
     * Retorna todas las actuaciones que estan la base de datos
     * @return 
     */
    public List<ActuacionEntity> getActuaciones() {
        List<ActuacionEntity> prioridades = persistence.findAll();
        return prioridades;
    }



    /**
     * Retornar la actuacion que tiene el id que entra por parametro
     * @param prioridadId, identificador de la actuacion
     * @return 
     */
    public ActuacionEntity getActuacion(Long prioridadId) {
        ActuacionEntity prioridadEntity = persistence.find(prioridadId);
        return prioridadEntity;
    }

  
    /**
     * Elimina la actuacion que tiene asociado el id que entra por parametro
     * @param ActuacionId, identificador de la actuacion
     */
    public void deleteActuacion(Long ActuacionId) {
        persistence.delete(ActuacionId);
    }
    /**
     * Actualiza la actuacion con  la actuacion que llega por parametro
     * @param actuacionEntity
     * @return 
     */
    public ActuacionEntity updateActuacion(ActuacionEntity actuacionEntity){
      ActuacionEntity newEntity = persistence.update(actuacionEntity);
        return newEntity;  
    }

    
    
}
