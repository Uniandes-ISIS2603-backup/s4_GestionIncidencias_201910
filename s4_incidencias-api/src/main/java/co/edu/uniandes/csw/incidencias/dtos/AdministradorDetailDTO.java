/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.AdministradorEntity;
import java.io.Serializable;

/**
 * @author Juan Pablo Correa Puerta
 */
public class AdministradorDetailDTO extends UsuarioDetailDTO implements Serializable
{    
    
    
    public AdministradorDetailDTO( AdministradorEntity entidad )
    {
        super( entidad );
        
        
    }   
        
    public AdministradorDetailDTO()
    {
        super();
    }  
    
    @Override
    public AdministradorEntity toEntity()
    {
       return (AdministradorEntity) super.toEntity();
    }
        
}
