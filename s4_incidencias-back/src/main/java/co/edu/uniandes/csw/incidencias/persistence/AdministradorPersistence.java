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
 * @author Juan Pablo Correa
 */
public class AdministradorPersistence
{
    @PersistenceContext(unitName = "incidenciasPU")
    protected EntityManager administradorEntidades;
    
    public AdministradorEntity create(AdministradorEntity adminEntity)
    {
        administradorEntidades.persist(adminEntity);
        return adminEntity;
    }
    
    public AdministradorEntity find(Long id)
    {   
        return administradorEntidades.find(AdministradorEntity.class, id);
    }
    
    public List<AdministradorEntity> findAll()
    {
        TypedQuery<AdministradorEntity> query = administradorEntidades.createQuery("SELECT u FROM AministradorEntity u", AdministradorEntity.class);
        return query.getResultList();
    }
    
    public AdministradorEntity update(AdministradorEntity adminEntity)
    {
        return administradorEntidades.merge(adminEntity);
    }
    
    public void delete(Long id)
    {
        AdministradorEntity entity = administradorEntidades.find(AdministradorEntity.class, id);
        administradorEntidades.remove(entity);
    }
    
    public AdministradorEntity findByUsuario(String user)
    {
        TypedQuery query = administradorEntidades.createQuery("SELECT e FROM AministradorEntity e WHERE e.usuario = :nombre", AdministradorEntity.class);
        query = query.setParameter("nombre", user);
        List<AdministradorEntity> sameName = query.getResultList();
        
        AdministradorEntity respuesta = null;

        if ( sameName != null && !sameName.isEmpty() )
            respuesta = sameName.get(0);
        
        return respuesta;
    }
    
     public AdministradorEntity findByCedula(String cedula)
     {
        TypedQuery query = administradorEntidades.createQuery("SELECT e FROM AministradorEntity e WHERE e.cedula = :nombre", AdministradorEntity.class);
        query = query.setParameter("nombre", cedula);
        List<AdministradorEntity> sameName = query.getResultList();
        
        AdministradorEntity respuesta = null;

        if ( sameName != null && !sameName.isEmpty() )
            respuesta = sameName.get(0);
        
        return respuesta;
    }
     
}
