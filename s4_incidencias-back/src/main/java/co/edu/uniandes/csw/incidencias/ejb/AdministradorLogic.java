/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;

import co.edu.uniandes.csw.incidencias.entities.AdministradorEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.AdministradorPersistence;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
public class AdministradorLogic
{
    @Inject
    private AdministradorPersistence persistencia;  
    
    public AdministradorEntity createAdministradorEntity( AdministradorEntity admin ) throws BusinessLogicException
    {
        if(persistencia.findByCedula(admin.getCedula())!=null)
            throw new BusinessLogicException("La cédula " + admin.getCedula() + " ya está siendo usada por un administrador.");
         
        if(persistencia.findByUsuario(admin.getUsuario())!=null)
            throw new BusinessLogicException("El usuario " + admin.getUsuario() + " ya está siendo usado por un administrador.");
                
        return persistencia.create(admin);
    }
    
}
