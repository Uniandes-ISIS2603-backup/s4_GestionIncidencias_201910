/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.CalificacionEntity;
import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidencias.entities.EquipoComputoEntity;
import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import co.edu.uniandes.csw.incidencias.entities.TecnicoEntity;
import java.io.Serializable;


import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "name": string,
 *      "isbn": string,
 *      "image: string,
 *      "description": string,
 *      "publishingdate": date,
 *      "editorial": {@link EditorialDTO}
 *   }
 * </pre> Por ejemplo una editorial se representa asi:<br>
/**
 *
 * @author estudiante
 */


//-------------------------Revisado y terminado-------------------------

public class IncidenciaDTO implements Serializable{
    /**
     * id de la  incidencia
     */
    private Long id;
    /**
     * Atributo que representa la fecha de una incidencia
     */
    private Date fecha;
    /**
     * Atributo que representa la descipcion de una incidencia
     */
    private String descripcion; 
    /**
     * prioridad de la incidencia, a que se debe de incluir el sla
     */
    private String prioridad;
    /**
     * Atributo que representa el estado de una incidencia
     */
    private String estado;
    /**
     * Atributo que representa la calificacion de una incidencia
     */
    private CalificacionDTO calificacion;
    /**
     * Atributo que representa el equipo de computo del cual  es objeto una incidencia
     */
    private EquipoComputoDTO equipoComputo;
    /**
     * Atributo que representa el tecnico que atiende una incidencia
     */
    private TecnicoDTO tecnico;
    /**
     * Atributo que representa el empleado que reporta  una incidencia
     */
    private EmpleadoDTO empleado;
  
        //Constructor
    /**
     * Constructor vacio
     */
    public IncidenciaDTO(){
        
    }
    
    
     
    //Constructor
    /**
     * Crea una incidencia a partir de un  objeto de tipo entity
     * @param entity, objeto entity que se usa para construir la incidencia
     */
    public IncidenciaDTO(IncidenciaEntity entity){
        
        this.id = entity.getId();
        this.fecha = entity.getFecha();
        this.descripcion = entity.getDescripcion();
        this.estado = entity.getEstado();
        this.prioridad = entity.getPrioridad();
        
        CalificacionDTO calificacion2 = new CalificacionDTO();
        calificacion2.setDescripcion(entity.getCalificacion().getDescripcion());
        calificacion2.setNumeroEst(entity.getCalificacion().getNumeroEst());
        calificacion = calificacion2;
        
        EquipoComputoDTO equipo = new EquipoComputoDTO();
        equipo.setDescripcion(entity.getEquipoComputo().getDescripcion());
        equipo.setId(entity.getEquipoComputo().getId());
        equipoComputo = equipo;
        
        TecnicoDTO tecnico1 = new TecnicoDTO();
        tecnico1.setCedula(entity.getTecnico().getCedula());
        tecnico1.setName(entity.getTecnico().getName());
        tecnico1.setPassword(entity.getTecnico().getPassword());
        tecnico1.setUsuario(entity.getTecnico().getUsuario());
        tecnico1.setId(entity.getTecnico().getId());
        tecnico = tecnico1;
        
        EmpleadoDTO empleado1 = new EmpleadoDTO();
        empleado1.setCedula(entity.getEmpleado().getCedula());
       empleado1.setName(entity.getEmpleado().getName());
        empleado1.setPassword(entity.getEmpleado().getPassword());
        empleado1.setUsuario(entity.getEmpleado().getUsuario());
       empleado1.setId(entity.getEmpleado().getId());
       empleado = empleado1;
       
       
       
    }
    
    /**
     * Crea un objeto entidad a partir de esta incidencia
     * @return un objeto de tipo entity
     */
    public IncidenciaEntity toEntity(){
        
        IncidenciaEntity incidencia = new IncidenciaEntity();
        incidencia.setId(getId());
        incidencia.setDescripcion(this.descripcion);
        incidencia.setFecha(this.fecha);
        incidencia.setEstado(this.estado);
        incidencia.setPrioridad(this.prioridad);
        
        
        
        CalificacionEntity calificacion2 = new CalificacionEntity();
        calificacion2.setDescripcion(this.getCalificacion().getDescripcion());
        calificacion2.setNumeroEst(this.getCalificacion().getNumeroEst());
        incidencia.setCalificacion(calificacion2);
        
        EquipoComputoEntity equipo = new EquipoComputoEntity();
        equipo.setDescripcion(this.getEquipoComputo().getDescripcion());
        equipo.setId(this.getEquipoComputo().getId());
        incidencia.setEquipoComputo(equipo);
        
        TecnicoEntity tecnico1 = new TecnicoEntity();
        tecnico1.setCedula(this.getTecnico().getCedula());
        tecnico1.setName(this.getTecnico().getName());
        tecnico1.setPassword(this.getTecnico().getPassword());
        tecnico1.setUsuario(this.getTecnico().getUsuario());
        tecnico1.setId(this.getTecnico().getId());
        incidencia.setTecnico(tecnico1);
        
        EmpleadoEntity empleado1 = new EmpleadoEntity();
        empleado1.setCedula(this.getEmpleado().getCedula());
       empleado1.setName(this.getEmpleado().getName());
        empleado1.setPassword(this.getEmpleado().getPassword());
        empleado1.setUsuario(this.getEmpleado().getUsuario());
       empleado1.setId(this.getEmpleado().getId());
 
       incidencia.setEmpleado(empleado1);
       
        
        
        return incidencia;
    }
    
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
    public CalificacionDTO getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(CalificacionDTO calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the equipoComputo
     */
    public EquipoComputoDTO getEquipoComputo() {
        return equipoComputo;
    }

    /**
     * @param equipoComputo the equipoComputo to set
     */
    public void setEquipoComputo(EquipoComputoDTO equipoComputo) {
        this.equipoComputo = equipoComputo;
    }

    /**
     * @return the tecnico
     */
    public TecnicoDTO getTecnico() {
        return tecnico;
    }

    /**
     * @param tecnico the tecnico to set
     */
    public void setTecnico(TecnicoDTO tecnico) {
        this.tecnico = tecnico;
    }

    /**
     * @return the empleado
     */
    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
     @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
   public void setPrioridad(String prioridad){
     
       this.prioridad=prioridad;
   }
    
}