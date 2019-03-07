/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;

import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidencias.entities.UsuarioEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.EmpleadoPersistence;
import javax.ejb.Stateless;
import java.util.List;
import javax.inject.Inject;

/**
 * Clase que manejará la capa de la lógica del recurso empleado
 * @author Valerie Parra Cortés
 */
@Stateless
public class EmpleadoLogic {
    @Inject
    private EmpleadoPersistence persistence;
    
    public EmpleadoEntity createEmpleadoEntity(EmpleadoEntity empleado) throws BusinessLogicException{
        if(persistence.findByCedula(empleado.getCedula())!=null){
             throw new BusinessLogicException("Ya existe un Empleado con la cédula \"" + empleado.getCedula() + "\"");
         }
         if(persistence.findByUsuario(empleado.getUsuario())!=null){
             throw new BusinessLogicException("Ya existe un Empleado con el usuario \"" + empleado.getUsuario() + "\"");
         }             
         empleado=persistence.create(empleado);
         return empleado;
    }
    
     

    
    public  List<EmpleadoEntity> getEmpleados(){
        return persistence.findAll();
        
     }
     
     public EmpleadoEntity getEmpleado(Long empleadoID) {
        return persistence.find(empleadoID);        
    }
     
     public EmpleadoEntity updateEmpleado(UsuarioEntity entity) {
        UsuarioEntity newEntity = persistence.update(entity);
        return (EmpleadoEntity) newEntity;
    }
    
      public void deleteEmpleado(Long empleadoId) {
        persistence.delete(empleadoId);
    }     
}
