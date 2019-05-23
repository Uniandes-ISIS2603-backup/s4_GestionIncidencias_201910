/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;
import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidencias.entities.UsuarioEntity;
import java.io.Serializable;

/**
 * Clase que representa un empleado 
 * @author Valerie Parra Cortés
 */
public class EmpleadoDTO extends UsuarioDTO implements Serializable{         
      public EmpleadoDTO (){
        super();
    }
    public EmpleadoDTO (UsuarioEntity uE){
        super(uE);
    }   
    @Override
    public EmpleadoEntity toEntity(){
        EmpleadoEntity ue= new EmpleadoEntity();
        ue.setCedula(this.cedula);
        ue.setId(this.id);
        ue.setName(this.name);
        ue.setPassword(this.password);
        ue.setUsuario(this.usuario);
        return ue; 
    }
  }
