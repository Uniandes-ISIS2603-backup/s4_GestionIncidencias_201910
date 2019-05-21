/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;

import co.edu.uniandes.csw.incidencias.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.IncidenciaPersistence;
import co.edu.uniandes.csw.incidencias.persistence.TecnicoPersistence;
import co.edu.uniandes.csw.incidencias.persistence.EmpleadoPersistence;
import co.edu.uniandes.csw.incidencias.persistence.EquipoComputoPersistence;
import java.util.ArrayList;
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
public class IncidenciaLogic {
   
    
    private static final Logger LOGGER = Logger.getLogger(IncidenciaLogic.class.getName());
    /**
     * Atributo  que modela la relacion con la persistencia
     */
    @Inject
    private IncidenciaPersistence persistence;
    @Inject
    private TecnicoPersistence tecPersistence;
    @Inject
    private EmpleadoPersistence emplPersitence;
    @Inject
    private EquipoComputoPersistence equipoComPersitence;
    
    
    /**
     * Crea una incidencia
     * @param incidenciaEntity
     * @return
     * @throws BusinessLogicException 
     */
    
       public IncidenciaEntity createIncidencia(IncidenciaEntity incidenciaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del libro");
        if(incidenciaEntity.getEmpleado() == null || emplPersitence.find(incidenciaEntity.getEmpleado().getId() ) == null ){
            
             throw new BusinessLogicException("El empleado no existe");
        }
          if(incidenciaEntity.getTecnico() == null || tecPersistence.find(incidenciaEntity.getTecnico().getId() ) == null ){
            
             throw new BusinessLogicException("El tecnico no existe");
        }
          if(incidenciaEntity.getEquipoComputo() == null || equipoComPersitence.find(incidenciaEntity.getEquipoComputo().getId() ) == null ){
            
             throw new BusinessLogicException("El equipo de computo asociado no existe");
        }
          
         persistence.create(incidenciaEntity);
         LOGGER.log(Level.INFO, "Termina proceso de creación de la incidencia");
         return incidenciaEntity;
       
       }
  
     /**
     * Devuelve todos las incidencias que hay en la base de datos.
     *
     * @return Lista de entidades de tipo libro.
     */
    public List<IncidenciaEntity> getIncidencias() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las incidencias");
        List<IncidenciaEntity> incidencias = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las incidencias");
        return incidencias;
    }
   /**
    * Retorna la incidencia con  el id que entra por parametro
    * @param incidenciasId
    * @return 
    */
    public IncidenciaEntity getIncidencia(Long incidenciasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la incidencia  con id = {0}", incidenciasId);
        IncidenciaEntity incidenciaEntity = persistence.find(incidenciasId);
        
        if (incidenciaEntity == null) {
            LOGGER.log(Level.SEVERE, "El libro con el id = {0} no existe", incidenciasId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la incidencia con id = {0}", incidenciasId);
       
        return incidenciaEntity;
    }
    
    /**
     * Actualiza la incidencia
     * @param incidenciaId
     * @param incidenciaEntity
     * @return
     * @throws BusinessLogicException si incumple alguna de las condiciones 
     */
     public IncidenciaEntity updateIncidencia(Long incidenciaId, IncidenciaEntity incidenciaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la incidencia con id = {0}", incidenciaId);
       
         if(incidenciaEntity.getEmpleado() == null || emplPersitence.find(incidenciaEntity.getEmpleado().getId() ) == null ){
            
             throw new BusinessLogicException("El empleado no existe");
        }
          if(incidenciaEntity.getTecnico() == null || tecPersistence.find(incidenciaEntity.getTecnico().getId() ) == null ){
            
             throw new BusinessLogicException("El tecnico no existe");
        }
          if(incidenciaEntity.getEquipoComputo() == null || equipoComPersitence.find(incidenciaEntity.getEquipoComputo().getId() ) == null ){
            
             throw new BusinessLogicException("El equipo de computo asociado  no existe");
        }
          
        IncidenciaEntity newEntity = persistence.update(incidenciaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la incidencia con id = {0}", incidenciaEntity.getId());
        return newEntity;
    }
    
     
      /**
       * Borra la incidencia con id dado, solo  si no tiene incidencias asociadas
       * @param incidenciaId
       * @throws BusinessLogicException 
       */
      public void deleteIncidencia(Long incidenciaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el libro con id = {0}", incidenciaId);
        List<ActuacionEntity> actuaciones = getIncidencia(incidenciaId).getActuaciones();
       
        if (actuaciones != null && !actuaciones.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar la incidencia con id = " + incidenciaId + " porque tiene actuaciones");
        }
        persistence.delete(incidenciaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la incidencia con id = {0}", incidenciaId);
    }
   
   
}
