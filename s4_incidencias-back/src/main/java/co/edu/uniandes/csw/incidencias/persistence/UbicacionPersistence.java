/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.persistence;

import co.edu.uniandes.csw.incidencias.entities.UbicacionEntity;
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
public class UbicacionPersistence {
    
    @PersistenceContext (unitName = "incidenciasPU")
    protected EntityManager em;
    
    public UbicacionEntity create(UbicacionEntity ubicacionEntity) {
        em.persist(ubicacionEntity);
        return ubicacionEntity;
    }
    
    public UbicacionEntity find(Long ubicacionId) {   
        return em.find(UbicacionEntity.class, ubicacionId);
    }
    
    public List<UbicacionEntity> findAll() {
        TypedQuery<UbicacionEntity> query = em.createQuery("select u from UbicacionEntity u", UbicacionEntity.class);
        return query.getResultList();
    }
    
        public UbicacionEntity update(UbicacionEntity ubicacionEntity) {
        return em.merge(ubicacionEntity);
    }
    
    public void delete(Long ubicacionId) {
        UbicacionEntity entity = em.find(UbicacionEntity.class, ubicacionId);
        em.remove(entity);
    }
    
}
