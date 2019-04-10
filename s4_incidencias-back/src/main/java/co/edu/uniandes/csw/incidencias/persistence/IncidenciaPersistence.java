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
    /***
     * Atributo  que modela la relacion con el entity manager
     */
    @PersistenceContext(unitName =  "incidenciasPU")
    protected EntityManager em;
    
    /**
     * Crea unaa incidencia en la base de datos
     * @param incidenciaEntity, datos de la incidencia
     * @return la incidencia creada
     */
    public IncidenciaEntity create(IncidenciaEntity incidenciaEntity){
        
        em.persist(incidenciaEntity);
        return incidenciaEntity;
    }
    /**
     * Encuentra una incidencia y la retorna
     * @param IncidenciaId, identificador de la incidencia buscada
     * @return la incidencia que encuentra
     */
      public IncidenciaEntity find(Long IncidenciaId){
        
      return em.find(IncidenciaEntity.class,IncidenciaId);
    }
     /**
      * Retorna todas las incidencias de la base de datos
      * @return 
      */
     public List<IncidenciaEntity> findAll() {
        TypedQuery <IncidenciaEntity> query = em.createQuery("select u from IncidenciaEntity u", IncidenciaEntity.class);
        return query.getResultList();
    }
     /**
      * Actualiza un a incidencia 
      * @param incidenciaEntity, informacion  ha actualizar
      * @return 
      */
         public IncidenciaEntity update(IncidenciaEntity incidenciaEntity) {
        return em.merge(incidenciaEntity);
    }
    
     /**
      * Borra una incidencia d ela base de datos
      * @param incidenciaId, identificador de la incidencia aborrar
      */
    public void delete(Long incidenciaId) {
        IncidenciaEntity entity = em.find(IncidenciaEntity.class, incidenciaId);
        em.remove(entity);
    }
}
