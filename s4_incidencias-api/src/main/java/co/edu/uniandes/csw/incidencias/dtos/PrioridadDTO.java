/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import java.io.Serializable;

/**
 *
 * @author estudiante
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
   
    
    public void toEntity(){
        
    }
    
    public String toString(){
        return "Prioridad{'tipoPrioridad':"+tipoPrioridad+",'SLA':"+sla.toString()+"} ";
    }
    
}
