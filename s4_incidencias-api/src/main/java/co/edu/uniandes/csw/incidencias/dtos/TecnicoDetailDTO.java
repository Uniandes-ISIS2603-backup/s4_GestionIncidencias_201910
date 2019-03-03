/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;
import co.edu.uniandes.csw.incidencias.entities.TecnicoEntity;
import java.io.Serializable;
import java.util.logging.Logger;
/**
 * Clase que representará un TecnicoDetailDTO 
 * @author Valerie Parra Cortés
 */
public class TecnicoDetailDTO extends TecnicoDTO implements Serializable{    
    private static final Logger LOG = Logger.getLogger(TecnicoDetailDTO.class.getName());
    public TecnicoDetailDTO() {
        super();
    }
     public TecnicoDetailDTO(TecnicoEntity tecnicoEntity){
        super(tecnicoEntity);
    }

    
}