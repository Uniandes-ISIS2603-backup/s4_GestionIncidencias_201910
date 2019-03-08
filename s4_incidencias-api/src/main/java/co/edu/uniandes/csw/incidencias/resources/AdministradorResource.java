/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.AdministradorDTO;
import co.edu.uniandes.csw.incidencias.ejb.AdministradorLogic;
import co.edu.uniandes.csw.incidencias.entities.AdministradorEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */
@Path("aministrador")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped 
public class AdministradorResource
{
     private static final Logger LOG = Logger.getLogger(AdministradorResource.class.getName());
 
     @Inject
     private AdministradorLogic logica;
     
     @POST
     public AdministradorDTO crearAdministrador(AdministradorDTO administrador) throws BusinessLogicException
     {
         AdministradorEntity administradorEntity = logica.createAdministrador( administrador.toEntity() );
         return new AdministradorDTO( administradorEntity );
     }
}
