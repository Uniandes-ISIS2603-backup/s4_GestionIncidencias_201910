/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;
import co.edu.uniandes.csw.incidencias.entities.TecnicoEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.TecnicoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que manejará la capa de lógica del recurso técnico
 * @author Valerie Parra Cortés
 */

@Stateless
public class TecnicoLogic {
    /**
     * Atributo para acceder a la persistencia
     */
     @Inject
    private TecnicoPersistence persistence;   
     
     /**
      * Método que crea un técnico con el entity dado por parámetro
      * @param tecnico entity para subir
      * @return Tecnico Entity la entity
      * @throws BusinessLogicException Si se trata de crear un técnico con lamisma cédula o el mismo usuario
      */
     public TecnicoEntity createTecnico(TecnicoEntity tecnico) throws BusinessLogicException{
         //No debería existir un técnico con la misma cedula o el mismo  usuario
         
         if(persistence.findByCedula(tecnico.getCedula())!=null){
             throw new BusinessLogicException("Ya existe un Técnico con la cédula \"" + tecnico.getCedula() + "\"");
         }
         if(persistence.findByUsuario(tecnico.getUsuario())!=null){
             throw new BusinessLogicException("Ya existe un Técnico con el usuario \"" + tecnico.getUsuario() + "\"");
         }    
         
         tecnico=(TecnicoEntity) persistence.create(tecnico);
         return tecnico;
     }
     /**
      * Método que retorrna la lista de técnicos
      * @return lista de técnicos de la base de datos
      */
     public  List<TecnicoEntity> getTecnicos(){
        return persistence.findAll();        
     }
     
     /**
      * Método que retorna el técnico con el Id dado por parámetro
      * @param tecnicoId el id técnico
      * @return Enrity del id por parámetro
      */
     public TecnicoEntity getTecnico(Long tecnicoId) {
        return persistence.find(tecnicoId);        
    }
     /**
      * Métodoque actualiza un técnico dela base de datos con una entidad dada por parámetro
      * @param tecnicoEntity entidad a persistir
      * @return entidad persistida
      */
     public TecnicoEntity updateTecnico(TecnicoEntity tecnicoEntity) {        
        return (TecnicoEntity) persistence.update(tecnicoEntity);
    }
    /**
     * Método que elimina el técnico del id entrado pro parámetro
     * @param tecnicoId del técnico
     */
      public void deleteTecnico(Long tecnicoId) {
        persistence.delete(tecnicoId);
    }
}
