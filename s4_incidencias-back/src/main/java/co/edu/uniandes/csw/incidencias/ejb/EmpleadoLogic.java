/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;

import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.EmpleadoPersistence;
import javax.ejb.Stateless;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class EmpleadoLogic {
    @Inject
    private EmpleadoPersistence persistence;
    
    public EmpleadoEntity createEmpleadoEntity(EmpleadoEntity empleado) throws BusinessLogicException{
        if(persistence.findByCedula(empleado.getCedula())!=null){
             throw new BusinessLogicException("Ya existe un Técnico con la cédula \"" + empleado.getCedula() + "\"");
         }
         if(persistence.findByUsuario(empleado.getUsuario())!=null){
             throw new BusinessLogicException("Ya existe un Técnico con el usuario \"" + empleado.getUsuario() + "\"");
         }             
         empleado=persistence.create(empleado);
         return empleado;
    }
    
    public  List<EmpleadoEntity> getEmpleados(){
        List<EmpleadoEntity> empleados = persistence.findAll();
        return empleados;
     }
     
     public EmpleadoEntity getEmpleado(Long empleadoID) {
        EmpleadoEntity departamentoEntity = persistence.find(empleadoID);
        return departamentoEntity;
    }
     
     public EmpleadoEntity updateEmpleado( EmpleadoEntity entity) {
        EmpleadoEntity newEntity = persistence.update(entity);
        return newEntity;
    }
    
      public void deleteEmpleado(Long empleadoId) {
        persistence.delete(empleadoId);
    }     
}
