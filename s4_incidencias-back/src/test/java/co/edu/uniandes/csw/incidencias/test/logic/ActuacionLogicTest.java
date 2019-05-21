/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.logic;

import co.edu.uniandes.csw.incidencias.ejb.ActuacionLogic;

import co.edu.uniandes.csw.incidencias.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;

import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.ActuacionPersistence;

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
 * @author Guillermo  Lobaton
 */
@RunWith(Arquillian.class)
public class ActuacionLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ActuacionLogic reviewLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ActuacionEntity> data = new ArrayList<ActuacionEntity>();

    private List<IncidenciaEntity> dataInc = new ArrayList<IncidenciaEntity>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ActuacionEntity.class.getPackage())
                .addPackage(ActuacionLogic.class.getPackage())
                .addPackage(ActuacionPersistence.class.getPackage())
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
        em.createQuery("delete from ActuacionEntity").executeUpdate();
        em.createQuery("delete from ActuacionEntity").executeUpdate();
       
    }
     /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
      

        for (int i = 0; i < 3; i++) {
            IncidenciaEntity entity = factory.manufacturePojo(IncidenciaEntity.class);
            em.persist(entity);
            dataInc.add(entity);
        }

        for (int i = 0; i < 3; i++) {
            ActuacionEntity entity = factory.manufacturePojo(ActuacionEntity.class);
            entity.setIncidencia(dataInc.get(1));
            em.persist(entity);
            data.add(entity);
        }
    }
    
     /**
     * Prueba para crear una actuacion.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createActuacionTest() throws BusinessLogicException {
        ActuacionEntity newEntity = factory.manufacturePojo(ActuacionEntity.class);
        newEntity.setIncidencia(dataInc.get(1));
        ActuacionEntity result = reviewLogic.createActuacion(dataInc.get(1).getId(), newEntity);
        Assert.assertNotNull(result);
        ActuacionEntity entity = em.find(ActuacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
        Assert.assertEquals(newEntity.getIncidencia(), entity.getIncidencia());
    }
     /**
     * Prueba para consultar la lista de Reviews.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void getReviewsTest() throws BusinessLogicException {
        List<ActuacionEntity> list = reviewLogic.getActuaciones(dataInc.get(1).getId());
        Assert.assertEquals(data.size(), list.size());
        for (ActuacionEntity entity : list) {
            boolean found = false;
            for (ActuacionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Review.
     */
    @Test
    public void getActuacionTest() {
        ActuacionEntity entity = data.get(0);
        ActuacionEntity resultEntity = reviewLogic.getActuacion(dataInc.get(1).getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());
        Assert.assertEquals(entity.getTipo(), resultEntity.getTipo());
        
    }
    
    
     /**
     * Prueba para actualizar una actuacion
     */
    @Test
    public void updateActuacionTest() {
        ActuacionEntity entity = data.get(0);
        ActuacionEntity pojoEntity = factory.manufacturePojo(ActuacionEntity.class);

        pojoEntity.setId(entity.getId());

        reviewLogic.updateActuacion(dataInc.get(1).getId(), pojoEntity);

        ActuacionEntity resp = em.find(ActuacionEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(pojoEntity.getTipo(), resp.getTipo());
        Assert.assertEquals(pojoEntity.getIncidencia(), resp.getIncidencia());
    }
    
    
        /**
     * Prueba para eliminar un Review.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteActuacionTest() throws BusinessLogicException {
        ActuacionEntity entity = data.get(0);
        reviewLogic.deleteActuacion(dataInc.get(1).getId(), entity.getId());
        ActuacionEntity deleted = em.find(ActuacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para eliminarle un review a un book del cual no pertenece.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteActuacionConIncidenciaNoAsociadaTest() throws BusinessLogicException {
        ActuacionEntity entity = data.get(0);
        reviewLogic.deleteActuacion(dataInc.get(0).getId(), entity.getId());
    }
    
}
