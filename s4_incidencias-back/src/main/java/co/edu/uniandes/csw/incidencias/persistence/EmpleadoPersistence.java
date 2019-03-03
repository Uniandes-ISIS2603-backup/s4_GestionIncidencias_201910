/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.persistence;
import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * @author estudiante
 */
@Stateless
public class EmpleadoPersistence extends UsuarioPersistence{
    
    public EmpleadoEntity find(Long empleadoId) {   
        return em.find(EmpleadoEntity.class, empleadoId);
    }
    /**
     * @return
     */
    public List<EmpleadoEntity> findAll() {
        TypedQuery<EmpleadoEntity> query = em.createQuery("select u from EmpleadoEntity u", EmpleadoEntity.class);
        return query.getResultList();
    }
           
    public void delete(Long empleadoId) {
        EmpleadoEntity entity = em.find(EmpleadoEntity.class, empleadoId);
        em.remove(entity);
    }
    
    public EmpleadoEntity findByUsuario(String user) {
        TypedQuery query = em.createQuery("Select e From EmpleadoEntity e where e.usuario = :nombre", EmpleadoEntity.class);
        query = query.setParameter("nombre", user);
        List<EmpleadoEntity> sameName = query.getResultList();
        EmpleadoEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        return result;
    }
    public EmpleadoEntity findByCedula(String cedula) {
        TypedQuery query = em.createQuery("Select e From EmpleadoEntity e where e.cedula = :nombre", EmpleadoEntity.class);
        query = query.setParameter("nombre", cedula);
        List<EmpleadoEntity> sameName = query.getResultList();
        EmpleadoEntity result;
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
