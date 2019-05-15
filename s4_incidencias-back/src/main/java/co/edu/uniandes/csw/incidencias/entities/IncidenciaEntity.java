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
 * Clase que modela una incidencia en la base de datos
 * @author Guillermo Lobaton
 */


@Entity
public class IncidenciaEntity extends BaseEntity implements  Serializable{
    
    /**
     * Atribto que modela la fecha de una incidencia
     */
    @Temporal(TemporalType.DATE) 
    private Date fecha;
      /**
     * Atribto que modela la descricion de una incidencia
     */
    private String descripcion;
      /**
     * Atribto que modela el empleado que reporto la incidencia
     */
    @PodamExclude
    @ManyToOne (cascade = CascadeType.PERSIST,  fetch = javax.persistence.FetchType.LAZY)
    private EmpleadoEntity empleado;
    
    
    
    
    /**
     * Atribto que modela el estado de una incidencia
     */
     private String estado;
     /**
     * Atribto que modela la calificacion de una incidencia
     */
   @PodamExclude
    @OneToOne (cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = javax.persistence.FetchType.LAZY)
    private CalificacionEntity calificacion;
     /**
     * Atribto que modela el equipo de computoque presenta una incidencia
     */
   @OneToOne (cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = javax.persistence.FetchType.LAZY)
    private EquipoComputoEntity equipoComputo;
     /**
     * Atribto que modela el tecnico que atiende la incidencia
     */
 
   @PodamExclude
    @ManyToOne (cascade = CascadeType.PERSIST,  fetch = javax.persistence.FetchType.LAZY)
    private TecnicoEntity  tecnico;
      /**
     * Atribto que modela la prioridad de una incidencia
     */
    @PodamExclude
    @OneToOne (cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = javax.persistence.FetchType.LAZY)
    private PrioridadEntity  prioridad;
      /**
     * Atribto que modela la lista de actuaciones de una incidencia
     */
    @PodamExclude 
    @OneToMany (cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy= "incidencia" , fetch = javax.persistence.FetchType.LAZY)
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
