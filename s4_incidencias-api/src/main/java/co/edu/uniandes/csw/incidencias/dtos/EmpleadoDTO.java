/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;
import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidencias.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.logging.Logger;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase que representa un empleado
 * @author Valerie Parra Cortés
 */
public class EmpleadoDTO extends UsuarioDTO implements Serializable{     
    private static final Logger LOG = Logger.getLogger(EmpleadoDTO.class.getName());  
        
    /**
     * Constructor vacio de la clase Empleado DTO
     */
    public EmpleadoDTO (){
        
    }
    /**
     * Constructor que crea un empleado con base a un Entity
     * @param uE UsuarioEntity
     */
    public EmpleadoDTO (UsuarioEntity uE){
        super(uE);
    }
   
  }
