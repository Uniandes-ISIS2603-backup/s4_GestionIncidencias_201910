/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;

import co.edu.uniandes.csw.incidencias.entities.SlaEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.SlaPersistence;
import javax.ejb.Stateless;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Daniel Santamaría Álvarez
 */
@Stateless
public class SlaLogic {
    @Inject
    private SlaPersistence persistence;
    
    public SlaEntity createSlaEntity(SlaEntity sla) throws BusinessLogicException{
        if(persistence.find(sla.getId())!=null){
             throw new BusinessLogicException("Ya existe un Sla con ese ID \"" + sla.getId() + "\"");
         }           
         sla=persistence.create(sla);
         return sla;
    }
    
    public  List<SlaEntity> getSlas(){
        return persistence.findAll();
     }
     
     public SlaEntity getSla(Long slaID) {
        return persistence.find(slaID);
    }
     
     public SlaEntity updateSla( SlaEntity entity) {
        return persistence.update(entity);
    }
    
      public void deleteSla(Long slaId) {
        persistence.delete(slaId);
    }     
}
