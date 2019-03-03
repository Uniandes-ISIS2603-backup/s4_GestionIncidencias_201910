/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author v.parrac
 */
public class TecnicoDTO extends UsuarioDTO implements Serializable{     

    private static final Logger LOG = Logger.getLogger(TecnicoDTO.class.getName());
    
    /**
     * Constructor vacio de la clase
     */
    public TecnicoDTO() {
    }
    
    /**
     * Constructor que crea un empleado con base a un Entity
     * @param uE UsuarioEntity
     */
    public TecnicoDTO (UsuarioEntity uE){
        super(uE);
    }
      
}
