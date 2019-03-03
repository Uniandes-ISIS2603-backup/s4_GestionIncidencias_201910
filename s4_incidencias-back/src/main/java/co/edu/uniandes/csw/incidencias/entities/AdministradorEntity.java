/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.entities;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.Entity;

/**
 * Clase que representa un administrador
 * @author Valerie Parra Cortés
 */
@Entity
public class AdministradorEntity extends UsuarioEntity implements Serializable {
    private static final Logger LOG = Logger.getLogger(AdministradorEntity.class.getName());

    public AdministradorEntity() {
        super();
    }    
    
}
