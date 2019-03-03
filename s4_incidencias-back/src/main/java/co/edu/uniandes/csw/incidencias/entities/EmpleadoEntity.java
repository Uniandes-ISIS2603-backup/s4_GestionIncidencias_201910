/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.entities;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa un empleado
 * @author estudiante Valerie Parra Cortés
 */

@Entity
public class EmpleadoEntity extends UsuarioEntity  implements Serializable{
    private static final Logger LOG = Logger.getLogger(EmpleadoEntity.class.getName());
    
    @PodamExclude
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<IncidenciaEntity> incidencias;
    
}
