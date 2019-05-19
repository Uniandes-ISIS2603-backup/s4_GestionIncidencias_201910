/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.AdministradorDTO;
import co.edu.uniandes.csw.incidencias.dtos.AdministradorDetailDTO;
import co.edu.uniandes.csw.incidencias.ejb.AdministradorLogic;
import co.edu.uniandes.csw.incidencias.entities.AdministradorEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Representa al recurso Administrador
 * @author Juan Pablo Correa Puerta
 */
@Path("administradores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped 
public class AdministradorResource
{
    /**
     * Atributo para modelar la Lógica
     */
    @Inject
    private AdministradorLogic logica;
     
    @POST
    /**
     * Crea un administrador con base en el JSON que se envía
     * @param administrador JSON con la información del administrador
     * @return Un administrador en su forma de DTO
     * @throws BusinessLogicException cuando no se cumpla alguna regla de negocio
     */
    public AdministradorDTO createAdministrador( AdministradorEntity administrador ) throws BusinessLogicException
    {
       AdministradorEntity administradorEntity = logica.createAdministrador( administrador );
       return new AdministradorDTO( administradorEntity );
    }
    
    /**
     * Da todos los administradores del programa.    
     * @return JSONArray con los administradores. Si no hay ninguno retorna una lista vacía.
    */
    @GET
    public List<AdministradorDetailDTO> getAdministradores()
    {
            return listEntity2DetailDTO( logica.getAdministradores() );            
    }    
       
    /**
     * Convierte una lista de Entities a una lista de DTOs.
     * @param entityList corresponde a la lista de Entities que se va a convertir a DTOs.
     * @return la lista en forma DTO (json)
     */
    private List<AdministradorDetailDTO> listEntity2DetailDTO( List<AdministradorEntity> entityList )
    {
        List<AdministradorDetailDTO> list = new ArrayList<>();
        
        for (AdministradorEntity entity : entityList)
            list.add( new AdministradorDetailDTO(entity) );
        
        return list;
    }
    
    /**
     * Da el administrador con el id que entra por paràmetro
     * @param ID del administrador
     * @return JSON con la información de un administrado de la aplicación.
     * @throws WebApplicationException Si no hay un administrador con ese id.
    */
    @GET
    @Path("{id: \\d+}")
    public AdministradorDetailDTO getAdministrador( @PathParam("id") Long id ) throws WebApplicationException
    {
        AdministradorEntity admin = logica.getAdministrador(id);
         if (admin == null)
            throw new WebApplicationException("El recurso /empleados/" + id + " no existe.", 404);
        
        return new AdministradorDetailDTO( admin );
    }
    
    /**
     * Actualiza el administrador
     * @param ID del administrador
     * @return JSONArray del administrador con los cambios
     * @throws BusinessLogicException si no existe el administrador con el id enviado por parámetro
    */
    @PUT
    @Path("{id: \\d+}")
    public AdministradorDetailDTO updateAdministrador( @PathParam("id") Long id, AdministradorDetailDTO admin ) throws BusinessLogicException
    {
        admin.setId(id);
        if ( logica.getAdministrador(id) == null)
            throw new WebApplicationException("El administrador de id " + id + " no existe.", 404 );
        
        return new AdministradorDetailDTO( logica.updateAdministrador( admin.toEntity() ) );
    }
    
    /**
     * Borra un administrador
     * @param id del administrador a borrar
     * @throws BusinessLogicException si no existe el administrador con el id 
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteAdministrador(@PathParam("id") Long id) throws BusinessLogicException
    {
        AdministradorEntity entidad = logica.getAdministrador( id );
        if( entidad == null)
            throw new WebApplicationException("El administrador de id " + id + " no existe.", 404 );
               
        logica.deleteAdministrador( id );        
    }
}
