/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.persistence;
import co.edu.uniandes.csw.incidencias.entities.TecnicoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * Clase que manejará la capa de persistencia del recurso técnico
 * @author Valerie Parra Cortés
 */
@Stateless
public class TecnicoPersistence extends UsuarioPersistence {
    
    /**
     * Método que retorna el técnico que se encuentra en la base de datos con el id dado por parámetro
     * @param tecnicoId  id del técnico
     * @return Entidad del técnico
     */
    public TecnicoEntity find(Long tecnicoId) {   
        return em.find(TecnicoEntity.class, tecnicoId);
    }
    
    /**
     * Método  que retorna todos los técnicos  que es están en la base de datos
     * @return lista de los técnicos
     */
    public List<TecnicoEntity> findAll() {
        TypedQuery<TecnicoEntity> query = em.createQuery("select u from TecnicoEntity u", TecnicoEntity.class);
        return query.getResultList();
    }
    
    /**
     * Métdo que elimina el técnico del id dado por parámetro
     * @param tecnicoId  id del técnico
     */
    public void delete(Long tecnicoId) {
        TecnicoEntity entity = em.find(TecnicoEntity.class, tecnicoId);
        em.remove(entity);
    }
    /**
     * Método que retorna el técnico de la base de datos con el usuario dado por parámetro
     * @param user usuario del técnico que se va a buscar
     * @return Entity del técnico
     */
    
    public TecnicoEntity findByUsuario(String user) {
        TypedQuery query = em.createQuery("Select e From TecnicoEntity e where e.usuario = :nombre", TecnicoEntity.class);
        query = query.setParameter("nombre", user);
        List<TecnicoEntity> sameName = query.getResultList();
        TecnicoEntity result;
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
     * Método que retorna el empleado de la base de datos de la cédula dada por parámetro
     * @param cedula del empleado que se esta buscando
     * @return TecnicoEntity correspondiente a la cédula buscada
     */
    
    public TecnicoEntity findByCedula(String cedula) {
        TypedQuery query = em.createQuery("Select e From TecnicoEntity e where e.cedula = :nombre", TecnicoEntity.class);
        query = query.setParameter("nombre", cedula);
        List<TecnicoEntity> sameName = query.getResultList();
        TecnicoEntity result;
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
