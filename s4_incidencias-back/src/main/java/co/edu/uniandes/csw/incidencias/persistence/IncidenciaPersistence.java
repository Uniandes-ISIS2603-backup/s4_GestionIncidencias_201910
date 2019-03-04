/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.persistence;


import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lobaton
 */
@Stateless
public class IncidenciaPersistence {
    @PersistenceContext(unitName =  "incidenciasPU")
    protected EntityManager em;
    
    public IncidenciaEntity create(IncidenciaEntity incidenciaEntity){
        
        em.persist(incidenciaEntity);
        return incidenciaEntity;
    }
      public IncidenciaEntity find(Long IncidenciaId){
        
      return em.find(IncidenciaEntity.class,IncidenciaId);
    }
      
     public List<IncidenciaEntity> findAll() {
        TypedQuery <IncidenciaEntity> query = em.createQuery("select u from IncidenciaEntity u", IncidenciaEntity.class);
        return query.getResultList();
    }
         public IncidenciaEntity update(IncidenciaEntity incidenciaEntity) {
        return em.merge(incidenciaEntity);
    }
    
    public void delete(Long incidenciaId) {
        IncidenciaEntity entity = em.find(IncidenciaEntity.class, incidenciaId);
        em.remove(entity);
    }
}
