/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;
import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
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
     * Constructor de la clase de EmpleadoDTO
     * @param eEntity entity a parsear
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
    
     /**
     * Método para transformar el DTO a una entidad.
     * @return la entidad del empleado 
     */
    
    public EmpleadoEntity toEntity(){
        EmpleadoEntity empleadoEntity= new EmpleadoEntity();
        empleadoEntity.setCedula(cedula);
        empleadoEntity.setId(id);
        empleadoEntity.setName(name);
        empleadoEntity.setPassword(password);
        empleadoEntity.setUsuario(usuario);
        return empleadoEntity; 
    }
    
    /**
     * Método toString de la clase
     * @return Cadena con objeto
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
