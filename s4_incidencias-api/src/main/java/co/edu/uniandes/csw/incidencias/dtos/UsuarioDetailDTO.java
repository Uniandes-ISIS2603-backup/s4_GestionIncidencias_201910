/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import co.edu.uniandes.csw.incidencias.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Clase que representa un usuario de la aplicación
 * @author Valerie Parra Cortés
 */
public class UsuarioDetailDTO extends UsuarioDTO {

    private static final Logger LOG = Logger.getLogger(UsuarioDetailDTO.class.getName());
   
    /**
     * Constructor que dado en Entity construye un DTO
     * Llama am método de la clase Usuario DTO
     * @param empleadoEntity el entity
     */
    
    public UsuarioDetailDTO(UsuarioEntity empleadoEntity) {
        super(empleadoEntity);
        if(empleadoEntity.getIncidencias()!=null){
            incidencias= new ArrayList<>();
            for(IncidenciaEntity entityIncidencia: empleadoEntity.getIncidencias()){
                incidencias.add(new IncidenciaDTO(entityIncidencia));
            }
        }
    }
    
    /**
     * Constructor vacio de la  clase
     * Llama el metodo de la clase Usuario DTO
     */
    public UsuarioDetailDTO(){
        super();
    }    
    
    
    /**
     * Lista de incidencias del empleado
     */
    protected List<IncidenciaDTO> incidencias;
    
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

    @Override
    public UsuarioEntity toEntity(){
        UsuarioEntity eEntity= super.toEntity();
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