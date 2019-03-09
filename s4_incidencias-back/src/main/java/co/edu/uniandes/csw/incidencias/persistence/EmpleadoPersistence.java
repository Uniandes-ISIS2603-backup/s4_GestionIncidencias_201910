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
 * Clase que se encargará de la persistencia de la tabla EmpleadoEntity
 * @author Valerie Parra Cortés
 */
@Stateless
public class EmpleadoPersistence extends UsuarioPersistence{
    
        public EmpleadoEntity create(EmpleadoEntity usuarioEntity) {
        em.persist(usuarioEntity);
        return usuarioEntity;
    }

        
    
    /**
     * Método que retorna el empleado del id dado por parámetro
     * @param empleadoId id del empleado
     * @return empleadoENtity la entidad correspondienteal id
     */
    public EmpleadoEntity find(Long empleadoId) {   
        return em.find(EmpleadoEntity.class, empleadoId);
    }
    /**
     * Método que retorna todos los empleados de la base de datos
     * @return Lista de los empleados de la base de datos
     */
    public List<EmpleadoEntity> findAll() {
        TypedQuery<EmpleadoEntity> query = em.createQuery("select u from EmpleadoEntity u", EmpleadoEntity.class);
        return query.getResultList();
    }
    /**
     * Método que elimina el empleado dado por parámetro
     * @param empleadoId del empleado a eliminar
     */
    public void delete(Long empleadoId) {
        EmpleadoEntity entity = em.find(EmpleadoEntity.class, empleadoId);
        em.remove(entity);
    }
    
    /**
     * Método que retorna un empleado dado su usuario
     * @param user usuario del empleado que se esta buscando
     * @return Entity del empleado
     */
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
    
    /**
     * Método que retorna un empleado dada su cedula
     * @param cedula del empleado que se buscará
     * @return Entity del empleado correspondiente a la cedula por parámetro
     */
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
