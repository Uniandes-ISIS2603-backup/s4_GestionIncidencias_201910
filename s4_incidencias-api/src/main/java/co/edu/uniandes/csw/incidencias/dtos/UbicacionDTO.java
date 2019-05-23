/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.UbicacionEntity;
import java.io.Serializable;
/**
 *
 * @author cigonzalez
 */
public class UbicacionDTO  implements Serializable{
	
	private long id;
	private String descripcion;
	
	public UbicacionDTO () {
		
	}
	
	public long getId () {
		return id;
	}
	
	public void setId (long id) {
		this.id = id;
	}
	
	public String getDescripcion () {
		return descripcion;
	}
	
	
	public void setDescripcion (String descripcion) {
		this.descripcion = descripcion;
	}
        
        public UbicacionEntity toEntity()
        {
            UbicacionEntity ue = new UbicacionEntity();
            ue.setId(this.id);
            ue.setDescripcion(this.descripcion);
            return ue;
        }
    
}