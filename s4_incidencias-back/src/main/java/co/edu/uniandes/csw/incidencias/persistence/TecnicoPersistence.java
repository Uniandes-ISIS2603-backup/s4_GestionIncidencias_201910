/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.persistence;

import co.edu.uniandes.csw.incidencias.entities.TecnicoEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
public class TecnicoPersistence {
    
    @PersistenceContext (unitName = "incidenciasPU")
    protected EntityManager em;
    
    public TecnicoEntity create(TecnicoEntity tecnicoEntity) {
        em.persist(tecnicoEntity);
        return tecnicoEntity;
    }
    
    public TecnicoEntity find(Long tecnicoId) {   
        return em.find(TecnicoEntity.class, tecnicoId);
    }
    
    public List<TecnicoEntity> findAll() {
        TypedQuery<TecnicoEntity> query = em.createQuery("select u from TecnicoEntity u", TecnicoEntity.class);
        return query.getResultList();
    }
    
    public TecnicoEntity update(TecnicoEntity tecnicoEntity) {
        return em.merge(tecnicoEntity);
    }
    
    public void delete(Long tecnicoId) {
        TecnicoEntity entity = em.find(TecnicoEntity.class, tecnicoId);
        em.remove(entity);
    }
    
}
