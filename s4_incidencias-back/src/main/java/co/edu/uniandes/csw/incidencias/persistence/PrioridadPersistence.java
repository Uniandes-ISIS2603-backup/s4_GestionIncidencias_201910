/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.persistence;

import co.edu.uniandes.csw.incidencias.entities.PrioridadEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Daniel Santamaria
 */

@Stateless
public class PrioridadPersistence {
    
    @PersistenceContext (unitName = "incidenciasPU")
    protected EntityManager em;
    
    public PrioridadEntity create(PrioridadEntity prioridadEntity) {
        em.persist(prioridadEntity);
        return prioridadEntity;
    }
    
    public PrioridadEntity find(Long prioridadId) {   
        return em.find(PrioridadEntity.class, prioridadId);
    }
    
    public List<PrioridadEntity> findAll() {
        TypedQuery<PrioridadEntity> query = em.createQuery("select u from PrioridadEntity u", PrioridadEntity.class);
        return query.getResultList();
    }
    
    public PrioridadEntity update(PrioridadEntity prioridadEntity) {
        return em.merge(prioridadEntity);
    }
    
    public void delete(Long prioridadId) {
        PrioridadEntity entity = em.find(PrioridadEntity.class, prioridadId);
        em.remove(entity);
    }
    
    public PrioridadEntity findByName(String nombre)
    {
        TypedQuery<PrioridadEntity> query = em.createQuery("select u from PrioridadEntity u", PrioridadEntity.class);
        query = query.setParameter("name", nombre);
        List<PrioridadEntity> sameName = query.getResultList();
        PrioridadEntity result;
        if(sameName == null)
        {
            result = null;
        }
        else if (sameName.isEmpty())
        {
            result = null;
        }
        else {
            result = sameName.get(0);
        }
        return result;
    }
    
}
