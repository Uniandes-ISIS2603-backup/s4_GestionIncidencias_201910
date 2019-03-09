/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;
import co.edu.uniandes.csw.incidencias.entities.AdministradorEntity;
import co.edu.uniandes.csw.incidencias.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * Clase que representa unadministrador
 * @author Valerie Parra Cortés
 */
public class AdministradorDTO extends UsuarioDetailDTO implements Serializable{
     private static final Logger LOG = Logger.getLogger(AdministradorDTO.class.getName());  
    
    public AdministradorDTO() {
        super();
    }
    
    public AdministradorDTO(UsuarioEntity ett)
    {
        super(ett);
    }     
}
