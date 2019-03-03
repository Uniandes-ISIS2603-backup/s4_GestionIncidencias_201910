/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * Clase que representa un empleado
 * @author Valerie Parra Cortés
 */
public class EmpleadoDTO extends UsuarioDTO implements Serializable{     

    private static final Logger LOG = Logger.getLogger(EmpleadoDTO.class.getName());
  
    /**
     * Constructor de la clase de EmpleadoDTO
     * @param eEntity entity a parsear
     * //TODO: Colocar las relaciones de muchos
     */
    public EmpleadoDTO(EmpleadoEntity eEntity) {
        if(!(eEntity==null)){
            name=eEntity.getName();
            cedula=eEntity.getCedula();
            id=eEntity.getId();
            usuario=eEntity.getUsuario();
            password=eEntity.getPassword();
        }
    }
    /**
     * Constructor vacio de la clase Empleado DTO
     */
    public EmpleadoDTO (){
        
    }
}
