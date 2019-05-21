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
     * @param IncidenciasId
     * @param actuacionEntity
     * @return
     * @throws BusinessLogicException 
     */
    public ActuacionEntity createActuacion(Long IncidenciasId, ActuacionEntity actuacionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear actuacion");
        IncidenciaEntity incidencia3= incidencia.find(IncidenciasId);
        actuacionEntity.setIncidencia(incidencia3);
                
        LOGGER.log(Level.INFO, "Termina proceso de creación del review");
        return actuacion.create(actuacionEntity);
    }   
   
/**
 * Obtiene la lista de actuaciones de la incidencia que entra por parametro
 * @param IncidenciasId
 * @return 
 */
    public List<ActuacionEntity> getActuaciones(Long IncidenciasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los reviews asociados al book con id = {0}", IncidenciasId);
        IncidenciaEntity incidenciaEntity = incidencia.find(IncidenciasId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar los reviews asociados al book con id = {0}", IncidenciasId);
        return incidenciaEntity.getActuaciones();
    }
    /**
     * Obtiene la actuacion identificada con el id que entra por parametro  de la incidencia cuyo id entra por parametro
     * @param IncidenciasId
     * @param ActuacionesId
     * @return 
     */
     public ActuacionEntity getActuacion(Long IncidenciasId, Long ActuacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la actuacion con id = {0} de la incidencia con id = " + IncidenciasId, ActuacionesId);
        return actuacion.find(IncidenciasId, ActuacionesId);
    }
    /**
     * Borrar
     * @param IncidenciasId
     * @param ActuacionesId
     * @throws BusinessLogicException 
     */
     public void deleteActuacion(Long IncidenciasId, Long ActuacionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la actuacion con id = {0} de la incidencia con id = " + IncidenciasId, ActuacionesId);
       ActuacionEntity old = getActuacion(IncidenciasId, ActuacionesId);
        if (old == null) {
            throw new BusinessLogicException("El review con id = " + ActuacionesId + " no esta asociado a el libro con id = " + IncidenciasId);
        }
        actuacion.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la actuacion con id = {0} de la incidencia con id= " + IncidenciasId, ActuacionesId);
    }
  
   public ActuacionEntity updateActuacion(Long IncidenciasId, ActuacionEntity ActuacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la actuacion  con id = {0} de la incidencia con id = " + IncidenciasId, ActuacionesId.getId());
        IncidenciaEntity bookEntity = incidencia.find(IncidenciasId);
        ActuacionesId.setIncidencia(bookEntity);
        actuacion.update(ActuacionesId);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la actuacion  con id = {0} de la incidencia con id = " + IncidenciasId, ActuacionesId.getId());
        return ActuacionesId;
    }

    
    
}
