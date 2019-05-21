/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.persistence;

import co.edu.uniandes.csw.incidencias.entities.ActuacionEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Guillermo Lobaton
 */
@Stateless
public class ActuacionPersistence {
    
    
    
     private static final Logger LOGGER = Logger.getLogger(ActuacionPersistence.class.getName());

    
    
    /**
     * Atributo que modela la relacioncon con el entity manager
     */
    @PersistenceContext(unitName =  "incidenciasPU")
    protected EntityManager em;
    
  
    /**
     * Crea una actuacion
     * @param actuacionEntity
     * @return 
     */
      public ActuacionEntity create(ActuacionEntity actuacionEntity) {
        LOGGER.log(Level.INFO, "Creando una actuacion nueva");
        em.persist(actuacionEntity);
        LOGGER.log(Level.INFO, "actuacion creado");
        return actuacionEntity;
    }
   
    
      /**
       * Retorna todas las actuaciones de a base de datos
       * @return 
       */
     public List<ActuacionEntity> findAll() {
        TypedQuery <ActuacionEntity> query = em.createQuery("select u from ActuacionEntity u", ActuacionEntity.class);
        return query.getResultList();
    }
     
     /**
      * 
      * @param ActuacionesId 
      */
    public void delete(Long ActuacionesId) {
        LOGGER.log(Level.INFO, "Borrando actuacion con id = {0}", ActuacionesId);
        ActuacionEntity actuacionEntity = em.find(ActuacionEntity.class, ActuacionesId);
        em.remove(actuacionEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la actuacion con id = {0}", ActuacionesId);
    }


       /**
      * Actualiza la actuacion que entra por parametro
      * @param actuacionEntity, informacion  ha actualizar
      * @return 
      */
        public ActuacionEntity update(ActuacionEntity actuacionEntity) {
        LOGGER.log(Level.INFO, "Actualizando review con id = {0}", actuacionEntity.getId());
        return em.merge(actuacionEntity);
    }



        public ActuacionEntity find(Long IncidenciaId, Long ActuacionesId) {
            
        LOGGER.log(Level.INFO, "Consultando el review con id = {0} del libro con id = " + IncidenciaId, ActuacionesId);
        TypedQuery<ActuacionEntity> q = em.createQuery("select p from ActuacionEntity p where (p.incidencia.id = :incidenciaid) and (p.id = :ActuacionesId)", ActuacionEntity.class);
        q.setParameter("incidenciaid", IncidenciaId);
        q.setParameter("ActuacionesId", ActuacionesId);
        List<ActuacionEntity> results = q.getResultList();
        ActuacionEntity actuacion = null;
        if (results == null) {
            actuacion = null;
        } else if (results.isEmpty()) {
            actuacion = null;
        } else if (results.size() >= 1) {
            actuacion = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el review con id = {0} del libro con id =" + IncidenciaId,ActuacionesId);
        return actuacion;
    }
         
        

}

