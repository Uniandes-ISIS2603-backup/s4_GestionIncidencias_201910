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
public class SlaDTO {
    
    private String idSLA;
    private String descripcion;
    
    public SlaDTO()
    {
        
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
     * @return the idSLA
     */
    public String getIdSLA() {
        return idSLA;
    }

    /**
     * @param idSLA the idSLA to set
     */
    public void setIdSLA(String idSLA) {
        this.idSLA = idSLA;
    }
    
    public void toEntity(){
        
    }
    
    
    public String toString(){
        return "SLA {'id':"+idSLA+",'descripcion':"+descripcion+"}";
    }
    
            
    
}
