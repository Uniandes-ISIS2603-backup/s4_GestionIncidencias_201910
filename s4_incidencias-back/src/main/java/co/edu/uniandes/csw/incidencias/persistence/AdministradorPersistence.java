/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.persistence;

import co.edu.uniandes.csw.incidencias.entities.AdministradorEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author Juan Pablo Correa Puerta
 */
public class AdministradorPersistence extends UsuarioPersistence
{   
    public AdministradorEntity find(Long id)
    {   
        return em.find(AdministradorEntity.class, id);
    }
    
    public List<AdministradorEntity> findAll()
    {
        TypedQuery<AdministradorEntity> query = em.createQuery("SELECT u FROM AdministradorEntity u", AdministradorEntity.class);
        return query.getResultList();
    }
    
    public AdministradorEntity update(AdministradorEntity adminEntity)
    {
        return em.merge(adminEntity);
    }
    
    public void delete(Long id)
    {
        AdministradorEntity entity = em.find(AdministradorEntity.class, id);
        em.remove(entity);
    }
    
    public AdministradorEntity findByUsuario(String user)
    {
        TypedQuery query = em.createQuery("SELECT e FROM AdministradorEntity e WHERE e.usuario = :nombre", AdministradorEntity.class);
        query = query.setParameter("nombre", user);
        List<AdministradorEntity> sameName = query.getResultList();
        
        AdministradorEntity respuesta = null;

        if ( sameName != null && !sameName.isEmpty() )
            respuesta = sameName.get(0);
        
        return respuesta;
    }
    
     public AdministradorEntity findByCedula(String cedula)
     {
        TypedQuery query = em.createQuery("SELECT e FROM AdministradorEntity e WHERE e.cedula = :nombre", AdministradorEntity.class);
        query = query.setParameter("nombre", cedula);
        List<AdministradorEntity> sameName = query.getResultList();
        
        AdministradorEntity respuesta = null;

        if ( sameName != null && !sameName.isEmpty() )
            respuesta = sameName.get(0);
        
        return respuesta;
    }
}
