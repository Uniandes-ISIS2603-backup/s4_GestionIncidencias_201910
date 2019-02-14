/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author estudiante
 */
public class IncidenciaDTO implements Serializable{
    
    private LocalDateTime fecha;
    private String descripcion;
    private String estado;
    private Double incidencia;
    
    //private CalificacionDTO calificacion;
    //private EquipoComputoDTO equipoComputo;
    //private TecnicoDTO tecnico;
    //private EmpleadoDTO empleado;
    //private PioridadDTOP prioridad;

    
    //Constructor
    public IncidenciaDTO(){
        
    }
    /**
     * @return the fecha
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the incidencia
     */
    public Double getIncidencia() {
        return incidencia;
    }

    /**
     * @param incidencia the incidencia to set
     */
    public void setIncidencia(Double incidencia) {
        this.incidencia = incidencia;
    }

}
