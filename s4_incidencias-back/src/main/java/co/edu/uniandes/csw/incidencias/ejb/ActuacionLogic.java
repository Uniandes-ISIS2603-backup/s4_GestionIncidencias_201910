/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;

import co.edu.uniandes.csw.incidencias.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.ActuacionPersistence;
import co.edu.uniandes.csw.incidencias.persistence.IncidenciaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Guillermo Lobaton
 */

@Stateless
public class ActuacionLogic {
   
    private static final Logger LOGGER = Logger.getLogger(ActuacionLogic.class.getName());

    /**
     * Atributo  que modela la relacion con la persistencia
     */
    @Inject
    private ActuacionPersistence actuacion;
    /**
     * Atributo  que modela la relacion con la persistencia
     */
    @Inject
    private IncidenciaPersistence incidencia;
    
    
    /**
     * Crea una actuacion 
     * @param incidenciasId
     * @param actuacionEntity
     * @return
     */
    public ActuacionEntity createActuacion(Long incidenciasId, ActuacionEntity actuacionEntity){
        LOGGER.log(Level.INFO, "Inicia proceso de crear actuacion");
        IncidenciaEntity incidencia3= incidencia.find(incidenciasId);
        actuacionEntity.setIncidencia(incidencia3);
                
        LOGGER.log(Level.INFO, "Termina proceso de creación del review");
        return actuacion.create(actuacionEntity);
    }   
   
/**
 * Obtiene la lista de actuaciones de la incidencia que entra por parametro
 * @param incidenciasId
 * @return 
 */
    public List<ActuacionEntity> getActuaciones(Long incidenciasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los reviews asociados al book con id = {0}", incidenciasId);
        IncidenciaEntity incidenciaEntity = incidencia.find(incidenciasId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar los reviews asociados al book con id = {0}", incidenciasId);
        return incidenciaEntity.getActuaciones();
    }
    /**
     * Obtiene la actuacion identificada con el id que entra por parametro  de la incidencia cuyo id entra por parametro
     * @param incidenciasId
     * @param actuacionesId
     * @return 
     */
     public ActuacionEntity getActuacion(Long incidenciasId, Long actuacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la actuacion con id = {0} de la incidencia con id = " + incidenciasId, actuacionesId);
        return actuacion.find(incidenciasId, actuacionesId);
    }
    /**
     * Borrar
     * @param incidenciasId
     * @param actuacionesId
     * @throws BusinessLogicException 
     */
     public void deleteActuacion(Long incidenciasId, Long actuacionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la actuacion con id = {0} de la incidencia con id = " + incidenciasId, actuacionesId);
       ActuacionEntity old = getActuacion(incidenciasId, actuacionesId);
        if (old == null) {
            throw new BusinessLogicException("El review con id = " + actuacionesId + " no esta asociado a el libro con id = " + incidenciasId);
        }
        actuacion.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la actuacion con id = {0} de la incidencia con id= " + incidenciasId, actuacionesId);
    }
  
   public ActuacionEntity updateActuacion(Long incidenciasId, ActuacionEntity actuacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la actuacion  con id = {0} de la incidencia con id = " + incidenciasId, actuacionesId.getId());
        IncidenciaEntity bookEntity = incidencia.find(incidenciasId);
        actuacionesId.setIncidencia(bookEntity);
        actuacion.update(actuacionesId);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la actuacion  con id = {0} de la incidencia con id = " + incidenciasId, actuacionesId.getId());
        return actuacionesId;
    }

    
    
}
