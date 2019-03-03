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
import java.util.logging.Logger;

/**
 * Clase que representa la clase Detail DTO
 * @author Valerie Parra Cortés
 */
public class EmpleadoDetailDTO extends EmpleadoDTO implements Serializable {

    private static final Logger LOG = Logger.getLogger(EmpleadoDetailDTO.class.getName());

    /**
     * Lista de incidencias del empleado
     */
    private List<IncidenciaDTO> incidencias;
    
     /**
     * Constructor para transformar un Entity a un DTO
     * @param empleadoEntity La entidad de la cual se construye el DTO
     */
     public EmpleadoDetailDTO(EmpleadoEntity empleadoEntity){
        super(empleadoEntity);
        if(empleadoEntity.getIncidencias()!=null){
            incidencias= new ArrayList<>();
            for(IncidenciaEntity entityIncidencia: empleadoEntity.getIncidencias()){
                incidencias.add(new IncidenciaDTO(entityIncidencia));
            }
        }
    }
    
    /**
     * Construye un empleado
     */
    public EmpleadoDetailDTO() {
        super();
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
 
    
    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el Empleado
     */
    
    @Override
    public EmpleadoEntity toEntity(){
        EmpleadoEntity eEntity= super.toEntity();
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
