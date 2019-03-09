/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;

import co.edu.uniandes.csw.incidencias.entities.AdministradorEntity;
import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Pablo Correa Puerta
 */
public class AdministradorDetailDTO extends UsuarioDetailDTO implements Serializable
{    
    /**
     * Lista de incidencias del empleado
     */
    private List<IncidenciaDTO> incidencias;
    
    public AdministradorDetailDTO( AdministradorEntity entidad )
    {
        super( entidad );
        List<IncidenciaDTO> incidencias = new ArrayList<>();
        
        for( IncidenciaEntity incidencia : entidad.getIncidencias() )
            incidencias.add( new IncidenciaDTO(incidencia) );
    }   
        
    public AdministradorDetailDTO()
    {
        super();
    }  
    
    @Override
    public AdministradorEntity toEntity()
    {
        AdministradorEntity entidad = (AdministradorEntity) super.toEntity();
        
        if(incidencias!=null)
        {
            List<IncidenciaEntity> incidenciasEntity=new ArrayList<>();
        
            for( IncidenciaDTO incidenciaDTO : getIncidencias() )
            {    
                incidenciasEntity.add(incidenciaDTO.toEntity());
                entidad.setIncidencias(incidenciasEntity);
            }
        }
        
        return entidad;
    }
        
}
