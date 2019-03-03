/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;
import co.edu.uniandes.csw.incidencias.entities.UsuarioEntity;

/**
 * Clase que representa un empleado 
 * @author Valerie Parra Cortés
 */
public class EmpleadoDTO extends UsuarioDTO {         
      public EmpleadoDTO (){
        super();
    }
    public EmpleadoDTO (UsuarioEntity uE){
        super(uE);
    }   
  }
