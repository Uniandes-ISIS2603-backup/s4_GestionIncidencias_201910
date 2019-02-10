/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import java.io.Serializable;
/**
 *
 * @author cigonzalez
 */
public class UbicacionDTO  implements Serializable{
	
	private String id;
	private String descripcion;
	
	public UbicacionDTO () {
		
	}
	
	public String getId () {
		return id;
	}
	
	public void setId (String id) {
		this.id = id;
	}
	
	public String getDescripcion () {
		return descripcion;
	}
	
	
	public void setDescripcion (String descripcion) {
		this.descripcion = descripcion;
	}
    
}