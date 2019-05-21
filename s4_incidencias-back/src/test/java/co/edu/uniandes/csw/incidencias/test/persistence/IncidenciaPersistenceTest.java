/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.persistence;

import co.edu.uniandes.csw.incidencias.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import co.edu.uniandes.csw.incidencias.persistence.ActuacionPersistence;
import co.edu.uniandes.csw.incidencias.persistence.IncidenciaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */

@RunWith(Arquillian.class)
public class IncidenciaPersistenceTest {
    
    
     @Inject
    private IncidenciaPersistence dp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private  List<IncidenciaEntity> data = new ArrayList ();
    
   
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(IncidenciaEntity.class.getPackage())
                .addPackage(IncidenciaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

     /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    
        /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from IncidenciaEntity").executeUpdate();
    }
    
     /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            IncidenciaEntity entity = factory.manufacturePojo(IncidenciaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * 
     */
   @Test
    public void createIncidenciaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        IncidenciaEntity newEntity = factory.manufacturePojo(IncidenciaEntity.class);
       
     
        IncidenciaEntity result = dp.create(newEntity);

        Assert.assertNotNull(result);

        IncidenciaEntity entity = em.find(IncidenciaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getEstado(), entity.getEstado());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCalificacion(), entity.getCalificacion());
    } 
    
    
     /**
     * Prueba para consultar la lista de Books.
     */
    @Test
    public void getIncidenciasTest() {
        List<IncidenciaEntity> list = dp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (IncidenciaEntity ent : list) {
            boolean found = false;
            for (IncidenciaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Book.
     */
    @Test
    public void getIncidenciaTest() {
        IncidenciaEntity entity = data.get(0);
        IncidenciaEntity newEntity = dp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        
        Assert.assertEquals(entity.getActuaciones(), newEntity.getActuaciones());
        Assert.assertEquals(entity.getDescripcion(), newEntity.getDescripcion());
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getPrioridad(), newEntity.getPrioridad());
    }

     /**
     * Prueba para eliminar un Book.
     */
    @Test
    public void deleteIncidenciaTest() {
        IncidenciaEntity entity = data.get(0);
        dp.delete(entity.getId());
        IncidenciaEntity deleted = em.find(IncidenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar un Book.
     */
    @Test
    public void updateIncidenciaTest() {
        IncidenciaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        IncidenciaEntity newEntity = factory.manufacturePojo(IncidenciaEntity.class);

        newEntity.setId(entity.getId());

        dp.update(newEntity);

        IncidenciaEntity resp = em.find(IncidenciaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getCalificacion(), resp.getCalificacion());
        Assert.assertEquals(newEntity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(newEntity.getId(), resp.getId());
        Assert.assertEquals(newEntity.getPrioridad(), resp.getPrioridad());
    }

    
    
}
