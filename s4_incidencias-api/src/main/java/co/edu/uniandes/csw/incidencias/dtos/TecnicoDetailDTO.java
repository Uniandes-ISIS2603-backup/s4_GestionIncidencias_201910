/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import co.edu.uniandes.csw.incidencias.entities.TecnicoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase que representará un TecnicoDetailDTO 
 * @author Valerie Parra Cortés
 */
public class TecnicoDetailDTO extends TecnicoDTO implements Serializable{
    /**
     * Lista de incidencias del empleado
     */
    private List<IncidenciaDTO> incidencias;

    public List<IncidenciaDTO> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<IncidenciaDTO> incidencias) {
        this.incidencias = incidencias;
    }
    
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
        if(tecnicoEntity.getIncidencias()!=null){
            incidencias= new ArrayList<>();
            for(IncidenciaEntity entityIncidencia: tecnicoEntity.getIncidencias()){
                incidencias.add(new IncidenciaDTO(entityIncidencia));
            }
        }
    }
    
    
      
    @Override
    public TecnicoEntity toEntity(){
        TecnicoEntity eEntity= (TecnicoEntity) super.toEntity();
        if(incidencias!=null){
            List<IncidenciaEntity> incidenciasEntity=new ArrayList<>();
            for (IncidenciaDTO incidenciaDTO : getIncidencias()) {
                incidenciasEntity.add(incidenciaDTO.toEntity());
            }
            eEntity.setIncidencias(incidenciasEntity);
        }
        return eEntity;
    }    
}