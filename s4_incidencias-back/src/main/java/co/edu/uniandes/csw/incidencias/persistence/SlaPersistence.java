/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.persistence;

import co.edu.uniandes.csw.incidencias.entities.SlaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */

@Stateless
public class SlaPersistence {
     
    @PersistenceContext (unitName = "incidenciasPU")
    protected EntityManager em;
    
    public SlaEntity create(SlaEntity slaEntity) {
        em.persist(slaEntity);
        return slaEntity;
    }
    
    public SlaEntity find(Long prioridadId) {   
        return em.find(SlaEntity.class, prioridadId);
    }
    
    public List<SlaEntity> findAll() {
        TypedQuery<SlaEntity> query;
        query = em.createQuery("select u from SlaEntity u", SlaEntity.class);
        return query.getResultList();
    }
    
    public SlaEntity update(SlaEntity slaEntity) {
        return em.merge(slaEntity);
    }
    
    public void delete(Long slaId) {
        SlaEntity entity = em.find(SlaEntity.class, slaId);
        em.remove(entity);
    }
    
}
