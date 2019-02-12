/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author estudiante
 */
public class EmpleadoDetailDTO extends EmpleadoDTO implements Serializable {

    private static final Logger LOG = Logger.getLogger(EmpleadoDetailDTO.class.getName());

    private List<IncidenciaDTO> incidencias;
    
    public EmpleadoDetailDTO() {
    }

    /**
     * @return the incidencias
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
 
    
    
}
