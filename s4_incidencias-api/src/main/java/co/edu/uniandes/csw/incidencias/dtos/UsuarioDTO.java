/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.dtos;
import co.edu.uniandes.csw.incidencias.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.logging.Logger;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase que representa el UsuarioDTO
 * @author Valerie Parra Cortés
 */
public class UsuarioDTO implements Serializable{
    
    private static Logger LOG = Logger.getLogger(UsuarioDTO.class.getName());
    
    
    
    public UsuarioDTO(UsuarioEntity eEntity) {
        if(eEntity!=null){
            name=eEntity.getName();
            cedula=eEntity.getCedula();
            id=eEntity.getId();
            usuario=eEntity.getUsuario();
            password=eEntity.getPassword();
        }
    }
    
    public UsuarioDTO() {
        
    }
    
    /**
     * El nombre del usuario
     */
    protected String name;
    /**
     * La cédula del usuario
     */
    protected String cedula;
    /**
     * ID del usuario
     */
    protected Long id;
    /**
     * Usuario del user
     */
    protected String usuario;
    /**
     * Contraseña del usuario
     */
    protected String password;

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
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the user
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param user the user to set
     */
    public void setUsuario(String user) {
        this.usuario = user;
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
     * Método para transformar el DTO a una entidad.
     * @return la entidad del empleado 
     */
    

     public UsuarioEntity toEntity(){
        UsuarioEntity ue= new UsuarioEntity();
        ue.setCedula(cedula);
        ue.setId(id);
        ue.setName(name);
        ue.setPassword(password);
        ue.setUsuario(usuario);
        return ue; 
    }
     
     @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
