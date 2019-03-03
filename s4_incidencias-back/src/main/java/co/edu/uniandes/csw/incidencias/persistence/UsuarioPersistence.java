/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.persistence;


import co.edu.uniandes.csw.incidencias.entities.UsuarioEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Clase que representa un usuario
 * @author Valerie Parra Cortés
 */
@Stateless
public class UsuarioPersistence {        
    @PersistenceContext (unitName = "incidenciasPU")
    protected EntityManager em;    
    public UsuarioEntity create(UsuarioEntity usuarioEntity) {
        em.persist(usuarioEntity);
        return usuarioEntity;
    }
    public UsuarioEntity update(UsuarioEntity usuarioEntity) {
        return em.merge(usuarioEntity);
    }
}
