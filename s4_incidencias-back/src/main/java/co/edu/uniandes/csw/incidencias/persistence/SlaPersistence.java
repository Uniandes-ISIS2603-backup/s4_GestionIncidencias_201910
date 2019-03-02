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
    
    public SlaEntity find(Long slaId) {   
        return em.find(SlaEntity.class, slaId);
    }
    
    public List<SlaEntity> findAll() {
        TypedQuery<SlaEntity> query = em.createQuery("select u from SlaEntity u", SlaEntity.class);
        return query.getResultList();
    }
    
    public SlaEntity update(SlaEntity slaEntity) {
        return em.merge(slaEntity);
    }
    
    public void delete(Long slaId) {
        SlaEntity entity = em.find(SlaEntity.class, slaId);
        em.remove(entity);
    }
    
    public SlaEntity findByUsuario(String user) {
        TypedQuery query = em.createQuery("Select e From SlaEntity e where e.usuario = :nombre", SlaEntity.class);
        query = query.setParameter("nombre", user);
        List<SlaEntity> sameName = query.getResultList();
        SlaEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        return result;
    }
    public SlaEntity findByCedula(String cedula) {
        TypedQuery query = em.createQuery("Select e From SlaEntity e where e.cedula = :nombre", SlaEntity.class);
        query = query.setParameter("nombre", cedula);
        List<SlaEntity> sameName = query.getResultList();
        SlaEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        return result;
    }
    
}
