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
public class DepartamentoDTO  implements Serializable{
	
	private String id;
	private String nombre;
	
	public DepartamentoDTO () {
		
	}
	
	public String getId () {
		return id;
	}
	
	public void setId (String id) {
		this.id = id;
	}
	
	public String getNombre () {
		return nombre;
	}
	
	
	public void setNombre (String nombre) {
		this.nombre = nombre;
	}
    
}