/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.persistence;

import co.edu.uniandes.csw.incidencias.entities.EquipoComputoEntity;
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
public class EquipoComputoPersistence {
      @PersistenceContext(unitName = "incidenciasPU")
    protected EntityManager em;

    public EquipoComputoEntity create(EquipoComputoEntity equipoComputoEntity) {
        em.persist(equipoComputoEntity);
        return equipoComputoEntity;
    }

    public EquipoComputoEntity find(Long equipoComputoId) {
        return em.find(EquipoComputoEntity.class, equipoComputoId);
    }

    public List<EquipoComputoEntity> findAll() {
        TypedQuery query = em.createQuery("select u from EquipoComputoEntity u", EquipoComputoEntity.class);
        return query.getResultList();
    }

    public EquipoComputoEntity findById(Long idEquipo) {
        TypedQuery<EquipoComputoEntity> query = em.createQuery("Select e from EquipoComputoEntity e where e.id = :idEquipo",EquipoComputoEntity.class);
        query = query.setParameter("idEquipo", idEquipo);
        List<EquipoComputoEntity> sameId = query.getResultList();
        EquipoComputoEntity result;
        if(sameId == null)
        {
            result = null;
        }
        else if( sameId.isEmpty())
        {
            result = null;
        }
        else
        {
            result = sameId.get(0);
        }
        return result;
        
    }
    
     public EquipoComputoEntity update(EquipoComputoEntity equipoComputoEntity) {
         return em.merge(equipoComputoEntity);
     }
     
     public void delete(Long equipoComputoId) {
                 EquipoComputoEntity entity = em.find(EquipoComputoEntity.class, equipoComputoId);
                 em.remove(entity);
     }
}
