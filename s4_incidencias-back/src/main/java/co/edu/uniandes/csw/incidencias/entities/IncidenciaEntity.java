/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */


@Entity
public class IncidenciaEntity extends BaseEntity implements  Serializable{

    @Temporal(TemporalType.DATE) 
    private Date fecha;
    private String descripcion; 
    @PodamExclude
    @ManyToOne (cascade = CascadeType.PERSIST,  fetch = javax.persistence.FetchType.LAZY)
    private EmpleadoEntity empleado;
     private String estado;
    private Double incidencia;
   @PodamExclude
    @OneToOne (cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = javax.persistence.FetchType.LAZY)
    private CalificacionEntity calificacion;
    private EquipoComputoEntity equipoComputo;
    @PodamExclude
    @ManyToOne (cascade = CascadeType.PERSIST,  fetch = javax.persistence.FetchType.LAZY)
    private TecnicoEntity  tecnico;
    @PodamExclude
    @OneToOne (cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = javax.persistence.FetchType.LAZY)
    private PrioridadEntity  prioridad;
    @PodamExclude 
    @OneToMany (cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = javax.persistence.FetchType.LAZY)
    private ArrayList<ActuacionEntity> actuaciones;
    


    
    
    
    
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
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
    
    public EmpleadoEntity getEmpleado(){
        return empleado;
    }
    public void settEmpleado(EmpleadoEntity empleado){
        this.empleado=empleado;
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

    /**
     * @return the calificacion
     */
    public CalificacionEntity getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(CalificacionEntity calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the equipoComputo
     */
    public EquipoComputoEntity getEquipoComputo() {
        return equipoComputo;
    }

    /**
     * @param equipoComputo the equipoComputo to set
     */
    public void setEquipoComputo(EquipoComputoEntity equipoComputo) {
        this.equipoComputo = equipoComputo;
    }

    /**
     * @return the tecnico
     */
    public TecnicoEntity getTecnico() {
        return tecnico;
    }

    /**
     * @param tecnico the tecnico to set
     */
    public void setTecnico(TecnicoEntity tecnico) {
        this.tecnico = tecnico;
    }

    /**
     * @return the prioridad
     */
    public PrioridadEntity getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(PrioridadEntity prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * @return the actuaciones
     */
    public ArrayList<ActuacionEntity> getActuaciones() {
        return actuaciones;
    }

    /**
     * @param actuaciones the actuaciones to set
     */
    public void setActuaciones(ArrayList<ActuacionEntity> actuaciones) {
        this.actuaciones = actuaciones;
    }
}
