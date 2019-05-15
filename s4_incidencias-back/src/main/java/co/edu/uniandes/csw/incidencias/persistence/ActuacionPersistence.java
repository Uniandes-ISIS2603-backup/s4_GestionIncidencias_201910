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

/**
 *
 * @author Guillermo Lobaton
 */
@Stateless
public class ActuacionPersistence {
    /**
     * Atributo que modela la relacioncon con el entity manager
     */
    @PersistenceContext(unitName =  "incidenciasPU")
    protected EntityManager em;
    /**
     * Crea una actuacion en la base de datos
     * @param actuacionEntity objeto que se va apersistir en la base de datos
     * @return el objeto a persistir modificado
     */
    public ActuacionEntity create(ActuacionEntity actuacionEntity){        
        em.persist(actuacionEntity);
        return actuacionEntity;
    }
    /**
     * Encuentra el objeto que tiene  asociado el id que entra por parametro
     * @param actuacionId, idetificador de la actuacion
     * @return la atuacion que tiene el id asociado
     */
      public ActuacionEntity find(Long actuacionId){
        
      return em.find(ActuacionEntity.class,actuacionId);
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
     * Elimina una atuacion de la base de datos
     * @param ActuacionId, identificador de la actuacion a aleiminar
     * @return actuacion que se eleimino
     */
     public ActuacionEntity delete(long ActuacionId){
         ActuacionEntity a = em.find(ActuacionEntity.class, ActuacionId );
         em.remove(a);
         return a;
     }

       /**
      * Actualiza un a incidencia 
      * @param actuacionEntity, informacion  ha actualizar
      * @return 
      */
         public ActuacionEntity update(ActuacionEntity actuacionEntity) {
        return em.merge(actuacionEntity);
    }



}

