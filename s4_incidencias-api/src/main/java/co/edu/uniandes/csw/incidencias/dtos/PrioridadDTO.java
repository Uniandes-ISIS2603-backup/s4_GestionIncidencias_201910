/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

/**
 *
 * @author estudiante
 */
public class PrioridadDTO {
    
    private String tipoPrioridad;
    private SlaDTO sla;
    
    public PrioridadDTO()
    {
        
    }
    
    public String darTipoPrioridad()
    {
        return tipoPrioridad;
    }
    
    public SlaDTO darSLA(){
        return sla;
    }
    
    public void toEntity(){
        
    }
    
    public String toString(){
        return "Prioridad{'tipoPrioridad':"+tipoPrioridad+",'SLA':"+sla.toString()+"} ";
    }
    
}
