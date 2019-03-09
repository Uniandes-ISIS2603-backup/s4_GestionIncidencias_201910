/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.PrioridadEntity;
import java.io.Serializable;

/**
 *
 * @author Daniel Santamaría Álvarez
 */
public class PrioridadDTO implements Serializable {
    
    private String tipoPrioridad;
    private SlaDTO sla;
    
    public PrioridadDTO()
    {
        
    }
    
    public String getTipoPrioridad()
    {
        return tipoPrioridad;
    }
    
    
    public void setTipoPrioridad(String ptipo)
    {
        this.tipoPrioridad = ptipo;
    }
    
    public SlaDTO getSLA(){
        return sla;
    }
   
    
     public PrioridadEntity toEntity()
    {
        PrioridadEntity prioridadEntity = new PrioridadEntity();
        prioridadEntity.setId(this.sla.getId());
        prioridadEntity.setTipoPrioridad(this.tipoPrioridad);
        return prioridadEntity;
    }
    
    public String toString(){
        return "Prioridad{'tipoPrioridad':"+tipoPrioridad+",'SLA':"+sla.toString()+"} ";
    }
    
}
