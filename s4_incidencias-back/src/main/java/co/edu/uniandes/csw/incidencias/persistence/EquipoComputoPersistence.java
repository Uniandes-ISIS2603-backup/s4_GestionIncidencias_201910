/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.persistence;

import co.edu.uniandes.csw.incidencias.entities.EquipoComputoEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
public class EquipoComputoPersistence {
      @PersistenceContext(unitName = "incidenciasPU")
    protected EntityManager em;

    public EquipoComputoEntity create(EquipoComputoEntity calificacionEntity) {
        em.persist(calificacionEntity);
        return calificacionEntity;
    }

    public EquipoComputoEntity find(Long calificacionId) {
        return em.find(EquipoComputoEntity.class, calificacionId);
    }

    public List<EquipoComputoEntity> findAll() {
        TypedQuery query = em.createQuery("select u from EquipoComputoEntity u", EquipoComputoEntity.class);
        return query.getResultList();
    }
}
