/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;
import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
/**
 * Clase que representa la clase Detail DTO
 * @author Valerie Parra Cortés
 */
public class EmpleadoDetailDTO extends UsuarioDetailDTO  {

    public EmpleadoDetailDTO(EmpleadoEntity empleadoEntity){
        super(empleadoEntity);        
    }    
    
    public EmpleadoDetailDTO() {
        super();
    }    
            
}
