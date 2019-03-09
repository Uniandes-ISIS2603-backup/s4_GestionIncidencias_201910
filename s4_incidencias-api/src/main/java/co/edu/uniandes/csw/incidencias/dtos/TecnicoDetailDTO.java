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

/**
 * Clase que representará un TecnicoDetailDTO 
 * @author Valerie Parra Cortés
 */
public class TecnicoDetailDTO extends TecnicoDTO implements Serializable{        
       /**
     * Lista de incidencias del empleado
     */
    private List<IncidenciaDTO> incidencias;
    
    public TecnicoDetailDTO() {
        super();
    }
              
    /**
      * Método que retorna la lista de incidencias del tecnico
     * @return Las incidencias del empleado
     */
    public List<IncidenciaDTO> getIncidencias() {
        return incidencias;
    }

    /**
     * @param incidencias the incidencias to set
     */
    public void setIncidencias(List<IncidenciaDTO> incidencias) {
        this.incidencias = incidencias;
    }
    
     public TecnicoDetailDTO(TecnicoEntity tecnicoEntity){
        super(tecnicoEntity);
        List<IncidenciaDTO> incidenciasDTO=new ArrayList<>();
            for (IncidenciaEntity ie : tecnicoEntity.getIncidencias()) {
                incidenciasDTO.add(new IncidenciaDTO(ie));
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