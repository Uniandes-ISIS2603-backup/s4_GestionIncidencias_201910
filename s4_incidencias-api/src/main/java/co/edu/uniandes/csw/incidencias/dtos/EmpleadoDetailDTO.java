/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;
import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase que representa la clase Detail DTO
 * @author Valerie Parra Cortés
 */
public class EmpleadoDetailDTO extends EmpleadoDTO implements Serializable {

     /**
     * Lista de incidencias del empleado
     */
    private List<IncidenciaDTO> incidencias;
    
    public EmpleadoDetailDTO(EmpleadoEntity empleadoEntity){
        super(empleadoEntity);
        incidencias = new ArrayList();
            for (IncidenciaEntity ie : empleadoEntity.getIncidencias()) {
                incidencias.add(new IncidenciaDTO(ie));
            }            
        }   
        
    public EmpleadoDetailDTO() {
        super();
    }  
    
    @Override
    public EmpleadoEntity toEntity(){
        EmpleadoEntity eEntity= (EmpleadoEntity) super.toEntity();
           if(incidencias!=null){
            List<IncidenciaEntity> incidenciasEntity=new ArrayList<>();
            for (IncidenciaDTO incidenciaDTO : getIncidencias()) {
                incidenciasEntity.add(incidenciaDTO.toEntity());
            }
            eEntity.setIncidencias(incidenciasEntity);
        }
        return eEntity;
    }
               
    /**
      * Método que retorna la lista de incidencias del empleado
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
    
    
    public EmpleadoEntity toEntityy(){
        return super.toEntity() ;
    }
}