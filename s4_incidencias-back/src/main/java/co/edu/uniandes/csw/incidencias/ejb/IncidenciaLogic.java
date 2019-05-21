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
    /**
     * Atributo  que modela la relacion con la persistencia
     */
    @Inject
    private IncidenciaPersistence persistence;
    /**
     * Atributo  que modela larelacion con la clase actuacion
     */
    private ActuacionLogic actLogic;
    /**
     * Lista de actuaciones asociada a una incidencia
     */
    private ArrayList<ActuacionEntity> listaActuaciones = new ArrayList();
   
  
    /**
     * Crea una  incidencia a partir de un objeto entity
     * @param incidencia, objeto entity  a partir del cual se crea la actuacion
     * @return 
     * @throws BusinessLogicException en caso deque la incidencia ya este creada en la base de datos
     */
    public IncidenciaEntity createIncidencia(IncidenciaEntity incidencia)throws BusinessLogicException
    {
        //Solo debe existir una incidencia con  ese id
        if( persistence.find(incidencia.getId()) != null)
        {
            throw new BusinessLogicException("Ya existe una incidencia Con el id\""+ incidencia.getId()+"\"" );
        }
        incidencia = persistence.create(incidencia);
        return incidencia;
    }
    
    
    /**
     * Obtiene todas las incidencias que estan en la base de datos
     * @return lista con todas las incidencias
     */
    public List<IncidenciaEntity> getIncidencias() {
        List<IncidenciaEntity> prioridades = persistence.findAll();
        return prioridades;
    }
    /**
     * Obtiene la incidencia con  el id dado por parametro
     * @param prioridadId
     * @return devuelve la incidencia que tiene el id asociado
     */
    public IncidenciaEntity getIncidencia(Long prioridadId) {
        IncidenciaEntity prioridadEntity = persistence.find(prioridadId);
        return prioridadEntity;
    }
    /**
     * Actualiza la incidencia que tiene el id dado por parametro
     * @param incidenciaEntity 
     * @return 
     */
    public IncidenciaEntity updateIncidencia(IncidenciaEntity incidenciaEntity) {
        IncidenciaEntity newEntity = persistence.update(incidenciaEntity);
        return newEntity;
    }

    /**
     * Elimina la actuacion identificada con el id que entra por parametro
     * @param IncidenciaId, identificador de la incidencia
     */
    public void deleteIncidencia(Long IncidenciaId) {
        persistence.delete(IncidenciaId);
    }
}
