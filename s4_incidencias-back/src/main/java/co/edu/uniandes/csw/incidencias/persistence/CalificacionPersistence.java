/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.persistence;

import co.edu.uniandes.csw.incidencias.entities.CalificacionEntity;
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
public class CalificacionPersistence {
    
    @PersistenceContext (unitName = "incidenciasPU")
    protected EntityManager em;
    
    public CalificacionEntity create(CalificacionEntity calificacionEntity) {
        em.persist(calificacionEntity);
        return calificacionEntity;
    }
    
    public CalificacionEntity find(Long calificacionId) {   
        return em.find(CalificacionEntity.class, calificacionId);
    }
    
    public List<CalificacionEntity> findAll() {
        TypedQuery<CalificacionEntity> query = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        return query.getResultList();
    }
    
        public CalificacionEntity update(CalificacionEntity calificacionEntity) {
        return em.merge(calificacionEntity);
    }
    
    public void delete(Long calificacionId) {
        CalificacionEntity entity = em.find(CalificacionEntity.class, calificacionId);
        em.remove(entity);
    }
    
    public CalificacionEntity findByDescription(String descripcion) {
        TypedQuery query = em.createQuery("Select e From CalificacionEntity e where e.descripcion = :descripcion", CalificacionEntity.class);
        query = query.setParameter("descripcion", descripcion);
        List<CalificacionEntity> sameDescription = query.getResultList();
        CalificacionEntity result;
        if (sameDescription == null) {
            result = null;
        } else if (sameDescription.isEmpty()) {
            result = null;
        } else {
            result = sameDescription.get(0);
        }
        return result;
    }
}