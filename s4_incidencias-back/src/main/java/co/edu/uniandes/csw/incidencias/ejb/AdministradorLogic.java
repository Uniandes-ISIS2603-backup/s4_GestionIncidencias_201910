/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;

import co.edu.uniandes.csw.incidencias.entities.AdministradorEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.AdministradorPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Juan Pablo Correa Puerta
 */
public class AdministradorLogic
{
    @Inject
    private AdministradorPersistence persistencia;  
    
    public AdministradorEntity createAdministrador( AdministradorEntity admin ) throws BusinessLogicException
    {
        if(persistencia.findByCedula(admin.getCedula())!=null)
            throw new BusinessLogicException("La cédula " + admin.getCedula() + " ya está siendo usada por un administrador.");
         
        if(persistencia.findByUsuario(admin.getUsuario())!=null)
            throw new BusinessLogicException("El usuario " + admin.getUsuario() + " ya está siendo usado por un administrador.");
                
        return (AdministradorEntity) persistencia.create(admin);
    }
    
    public List<AdministradorEntity> getAdministradores()
    {
        return persistencia.findAll();
    }
     
    public AdministradorEntity getAdministrador(Long empleadoID)
    {
        return persistencia.find(empleadoID);        
    }
     
    public AdministradorEntity updateAdministrador( AdministradorEntity entidad )
    {
        return (AdministradorEntity) persistencia.update( entidad );
    }
    
     public void deleteAdministrador( Long id )
     {
        persistencia.delete( id );
    }     
}
