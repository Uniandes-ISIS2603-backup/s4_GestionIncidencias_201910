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
    
    @PersistenceContext(unitName =  "incidenciasPU")
    protected EntityManager em;
    
    public ActuacionEntity create(ActuacionEntity actuacionEntity){
        
        em.persist(actuacionEntity);
        return actuacionEntity;
    }
      public ActuacionEntity find(Long actuacionId){
        
      return em.find(ActuacionEntity.class,actuacionId);
    }
      
     public List<ActuacionEntity> findAll() {
        TypedQuery <ActuacionEntity> query = em.createQuery("select u from ActuacionEntity u", ActuacionEntity.class);
        return query.getResultList();
    }
    
     public ActuacionEntity delete(long ActuacionId){
         ActuacionEntity a = em.find(ActuacionEntity.class, ActuacionId );
         em.remove(a);
         return a;
     }




}

