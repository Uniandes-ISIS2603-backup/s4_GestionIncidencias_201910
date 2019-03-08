/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.AdministradorDTO;
import co.edu.uniandes.csw.incidencias.ejb.AdministradorLogic;
import co.edu.uniandes.csw.incidencias.entities.AdministradorEntity;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Juan Pablo Correa Puerta
 */
@Path("administradores")
@Produces("application/json")
@Consumes("application/json")
public class AdministradorResource
{
    @Inject
    private AdministradorLogic logica;

    @POST
    public AdministradorDTO createAdministrador( AdministradorDTO admin )
    {
        AdministradorEntity nuevoAdmin = admin.toEntity();
        return null;
    }
    
}
