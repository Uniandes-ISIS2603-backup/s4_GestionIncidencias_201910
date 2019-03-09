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
    /**
     * Persistencia de empleados
     */
    @Inject
    private EmpleadoPersistence persistence;
    
    /**
     * Crea un empleado sino se violan las reglas de negocio
     * @param empleado a crear
     * @return empleado persistido
     * @throws BusinessLogicException si se romple una regla de negocio
     */
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
    
     /**
      * Encuentra todos los empleados de la base de datos y retorna una lista
      * @return Lista de todos los empleados
      */

    
    public  List<EmpleadoEntity> getEmpleados(){
        return persistence.findAll();
        
     }
     
    /**
     * Encuentra un empleado con id dado por parametro
     * @param empleadoID El id del empleado a buscar
     * @return Entity del empleado buscado
     */
     public EmpleadoEntity getEmpleado(Long empleadoID) {
        return persistence.find(empleadoID);        
    }
     
     /**
      * Actualiza un empleado con la inf dad por parametro
      * @param entity Entidad a actualizar
      * @return Entity persistida
      */
     public EmpleadoEntity updateEmpleado(UsuarioEntity entity) {
        UsuarioEntity newEntity = persistence.update(entity);
        return (EmpleadoEntity) newEntity;
    }
    
     /**
      * Borra el empleado con el id dado por parametro
      * @param empleadoId del empleado
      */
      public void deleteEmpleado(Long empleadoId) {
        persistence.delete(empleadoId);
    }     
}
