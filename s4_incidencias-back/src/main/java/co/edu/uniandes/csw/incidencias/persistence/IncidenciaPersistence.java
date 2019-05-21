/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.persistence;


import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
/**
 *
 * @author Lobaton
 */
@Stateless
public class IncidenciaPersistence {
    
    
    
    private static final Logger LOGGER = Logger.getLogger(IncidenciaPersistence.class.getName());

    /***
     * Atributo  que modela la relacion con el entity manager
     */
    @PersistenceContext(unitName =  "incidenciasPU")
    protected EntityManager em;
    
    
    /**
     * Crea una incidencia
     * @param incidenciaEntity
     * @return 
     */
     public IncidenciaEntity create(IncidenciaEntity incidenciaEntity) {
        LOGGER.log(Level.INFO, "Creando una incidencia nueva");
        em.persist(incidenciaEntity);
        LOGGER.log(Level.INFO, "Incidencia creada");
        return incidenciaEntity;
    }
    /**
     * Devuelve todas las incidencias de la base de datos.
     *
     * @return una lista con todos los libros que encuentre en la base de datos,
     * "select u from BookEntity u" es como un "select * from BookEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<IncidenciaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las incidencias");
        Query q = em.createQuery("select u from IncidenciaEntity u");
        return q.getResultList();
    }
    /**
     * Busca si hay alguna incidencia con el id que se envía de argumento
     *
     * @param IncidenciasId: id correspondiente al libro buscado.
     * @return un libro.
     */
    public IncidenciaEntity find(Long IncidenciasId) {
        LOGGER.log(Level.INFO, "Consultando la incidencia con id={0}", IncidenciasId);
        return em.find(IncidenciaEntity.class, IncidenciasId);
    }
     /**
     * Actualiza un libro.
     *
     * @param incidenciaEntity: la incidencia que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return un libro con los cambios aplicados.
     */
    public IncidenciaEntity update(IncidenciaEntity incidenciaEntity) {
        LOGGER.log(Level.INFO, "Actualizando la incidencia con id={0}", incidenciaEntity.getId());
        return em.merge(incidenciaEntity);
    }
     /**
     *
     * Borra un libro de la base de datos recibiendo como argumento el id del
     * libro
     *
     * @param incidenciaId: id correspondiente al libro a borrar.
     */
    public void delete(Long incidenciaId) {
        LOGGER.log(Level.INFO, "Borrando  la incidencia con id={0}", incidenciaId);
       IncidenciaEntity bookEntity = em.find(IncidenciaEntity.class, incidenciaId);
        em.remove(bookEntity);
    }

   
    //Acá el metodo  que saca todas las incidencias por el  id de empleado ---- pendiente
    
    
    
}
