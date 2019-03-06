/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.UsuarioEntity;
import java.io.Serializable;


/**
 * Clase que representa un técnico base
 * @author Valerie Parra Cortés
 */
public class TecnicoDTO extends UsuarioDTO implements Serializable{            
    public TecnicoDTO() {
    }
    
    public TecnicoDTO (UsuarioEntity uE){
        super(uE);
    }
      
}
