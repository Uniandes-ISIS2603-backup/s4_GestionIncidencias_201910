/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase para representar un técnico
 * @author Valerie Parra Cortés
 */

@Entity
public class TecnicoEntity extends UsuarioEntity {
    
    @PodamExclude
    @OneToMany(mappedBy = "tecnico", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<IncidenciaEntity> reviews = new ArrayList<IncidenciaEntity>();
  
}
