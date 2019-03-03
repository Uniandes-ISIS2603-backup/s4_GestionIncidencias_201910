/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;


import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import co.edu.uniandes.csw.incidencias.entities.TecnicoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * Clase que representará un TecnicoDetailDTO 
 * @author Valerie Parra Cortés
 */
public class TecnicoDetailDTO extends UsuarioDetailDTO implements Serializable{
    
    private static final Logger LOG = Logger.getLogger(TecnicoDetailDTO.class.getName());
   /**
    * Constructor vacio de la clase
    */

    public TecnicoDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     * @param tecnicoEntity La entidad de la cual se construye el DTO
     */
     public TecnicoDetailDTO(TecnicoEntity tecnicoEntity){
        super(tecnicoEntity);
    }
}