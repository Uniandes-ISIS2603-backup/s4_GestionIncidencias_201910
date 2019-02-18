/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.persistence;

import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
public class EmpleadoPersistence {
    
    @PersistenceContext (unitName = "incidenciasPU")
    protected EntityManager em;
    
    public EmpleadoEntity create(EmpleadoEntity empleadoEntity) {
        em.persist(empleadoEntity);
        return empleadoEntity;
    }
    
    public EmpleadoEntity find(Long empleadoId) {   
        return em.find(EmpleadoEntity.class, empleadoId);
    }
    
    public List<EmpleadoEntity> findAll() {
        TypedQuery<EmpleadoEntity> query = em.createQuery("select u from EmpleadoEntity u", EmpleadoEntity.class);
        return query.getResultList();
    }
    
    public EmpleadoEntity update(EmpleadoEntity empleadoEntity) {
        return em.merge(empleadoEntity);
    }
    
    public void delete(Long empleadoId) {
        EmpleadoEntity entity = em.find(EmpleadoEntity.class, empleadoId);
        em.remove(entity);
    }
    
}
