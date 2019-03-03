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
 * @author Valerie Parra Cortés
 */

@Stateless
public class TecnicoLogic {
     @Inject
    private TecnicoPersistence persistence;   
     
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
     
     public  List<TecnicoEntity> getTecnicos(){
        List<TecnicoEntity> tecnicos = persistence.findAll();
        return tecnicos;
     }
     
     public TecnicoEntity getTecnico(Long tecnicoId) {
        TecnicoEntity departamentoEntity = persistence.find(tecnicoId);
        return departamentoEntity;
    }
     
     public TecnicoEntity updateTecnico(Long tecnicoId, TecnicoEntity tecnicoEntity) {
        TecnicoEntity newEntity = (TecnicoEntity) persistence.update(tecnicoEntity);
        return newEntity;
    }
    
      public void deleteTecnico(Long tecnicoId) {
        persistence.delete(tecnicoId);
    }
}
