/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.persistence;

import co.edu.uniandes.csw.incidencias.entities.DepartamentoEntity;
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
public class DepartamentoPersistence {
    
    @PersistenceContext (unitName = "incidenciasPU")
    protected EntityManager em;
    
    public DepartamentoEntity create(DepartamentoEntity departamentoEntity) {
        em.persist(departamentoEntity);
        return departamentoEntity;
    }
    
    public DepartamentoEntity find(Long departamentoId) {   
        return em.find(DepartamentoEntity.class, departamentoId);
    }
    
    public List<DepartamentoEntity> findAll() {
        TypedQuery<DepartamentoEntity> query = em.createQuery("select u from DepartamentoEntity u", DepartamentoEntity.class);
        return query.getResultList();
    }
    
    public DepartamentoEntity update(DepartamentoEntity departamentoEntity) {
        return em.merge(departamentoEntity);
    }
    
    public void delete(Long departamentoId) {
        DepartamentoEntity entity = em.find(DepartamentoEntity.class, departamentoId);
        em.remove(entity);
    }
    
}
