/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @author Valerie Parra Cortés
 * Esta clase representará un Usuario (Ya sea técnico, administrador o empleado)
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable{   
    /**
     * Atributo que representa la lista de incidencias del empleado
     */
    @PodamExclude
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.PERSIST, orphanRemoval = true)
    protected List<IncidenciaEntity> incidencias;

    /**
     * Método que retorna la lista de incidencias del empleado
     * @return Lista de incidencias del empleado
     */
    public List<IncidenciaEntity> getIncidencias() {
        return incidencias;
    }
    /**
     * Método que cambia la lista de incidencias del empleado
     * @param incidencias la nueva lista de incidencias
     */
    public void setIncidencias(List<IncidenciaEntity> incidencias) {
        this.incidencias = incidencias;
    }  
    /**
     * Atributo para el usuario que tendrá el usuario
     */
    private String usuario;
    /**
     * Atributo para la contraseña del usuario
     */
    private String password;
    /**
     * Atributo donde se guardará la cédula del usuario
     */
    private String cedula;
    /**
     * Atributo donde se guardará el nombre del usuario
     */
    private String name; 
    
    public UsuarioEntity(){        
    }
    
    /**
     * @return the user
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the user to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }   
}