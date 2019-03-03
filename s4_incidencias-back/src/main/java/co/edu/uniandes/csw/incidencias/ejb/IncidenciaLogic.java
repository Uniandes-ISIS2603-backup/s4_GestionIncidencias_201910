/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.ejb;

import co.edu.uniandes.csw.incidencias.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.IncidenciaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Guillermo Lobaton
 */
@Stateless
public class IncidenciaLogic {
    
    @Inject
    private IncidenciaPersistence persistence;
    
    private ActuacionLogic actLogic;
    
    private ArrayList<ActuacionEntity> listaActuaciones = new ArrayList();
   
    //Agrega una actuacion a la lista de actuaciones, ademas la crea en la base de datos
    public void addActuacion(ActuacionEntity act ) throws BusinessLogicException{
       listaActuaciones.add(act); 
       actLogic.createActuacion(act);
    }
    
    public IncidenciaEntity createIncidencia(IncidenciaEntity incidencia)throws BusinessLogicException
    {
        //Solo debe existir una prioridad
        if( persistence.find(incidencia.getId()) != null)
        {
            throw new BusinessLogicException("Ya existe una incidencia Con el id\""+ incidencia.getId()+"\"" );
        }
        incidencia = persistence.create(incidencia);
        return incidencia;
    }
    
    
    
    public List<IncidenciaEntity> getIncidencias() {
        List<IncidenciaEntity> prioridades = persistence.findAll();
        return prioridades;
    }

    public IncidenciaEntity getIncidencia(Long prioridadId) {
        IncidenciaEntity prioridadEntity = persistence.find(prioridadId);
        return prioridadEntity;
    }

    public IncidenciaEntity updateIncidencia(Long prioridadId, IncidenciaEntity prioridadEntity) {
        IncidenciaEntity newEntity = persistence.update(prioridadEntity);
        return newEntity;
    }

    public void deleteIncidencia(Long IncidenciaId) {
        persistence.delete(IncidenciaId);
    }
}
