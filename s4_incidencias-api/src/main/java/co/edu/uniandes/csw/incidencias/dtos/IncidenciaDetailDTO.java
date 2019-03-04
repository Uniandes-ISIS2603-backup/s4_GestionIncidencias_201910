/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidencias.entities.CalificacionEntity;
import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidencias.entities.EquipoComputoEntity;
import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import co.edu.uniandes.csw.incidencias.entities.PrioridadEntity;
import co.edu.uniandes.csw.incidencias.entities.TecnicoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author estudiante
 */
public class IncidenciaDetailDTO extends IncidenciaDTO implements Serializable
{
    
    //private ArrayList<ActuacionDTO> actuaciones;
    private ArrayList <ActuacionDTO> actuaciones = new ArrayList<ActuacionDTO>();
    
    public IncidenciaDetailDTO(){
             super();  
    } 
    
    public IncidenciaDetailDTO(IncidenciaEntity incidencia){
       super(incidencia);
       if(incidencia!=null){
        if( incidencia.getActuaciones()!= null)  {
            ArrayList <ActuacionDTO> r = new ArrayList();
            for(ActuacionEntity a : incidencia.getActuaciones()){
                r.add(new ActuacionDTO(a));    
            }
            this.setActuaciones(r);
        }
         
       }
     
    }
    
    public IncidenciaEntity toEntity(){
        
        IncidenciaEntity a = super.toEntity();
        ArrayList<ActuacionEntity> o = new ArrayList<ActuacionEntity>();
        for(ActuacionDTO z : actuaciones){
            o.add(z.toEntity()); 
        }
        a.setActuaciones(o);
        return a;
    }

    
    
    
    
    
     public void addIncidencencia(ActuacionDTO actuacion){
        getActuaciones().add(actuacion);
    }
      public void deleteIncidencencia(ActuacionDTO actuacion){
        getActuaciones().remove(actuacion);
    }
      
 

    /**
     * @return the actuaciones
     */
    public ArrayList <ActuacionDTO> getActuaciones() {
        return actuaciones;
    }

    /**
     * @param actuaciones the actuaciones to set
     */
    public void setActuaciones(ArrayList <ActuacionDTO> actuaciones) {
        this.actuaciones = actuaciones;
    }

    /**
     * @return the actuaciones
     */
  
 
}
