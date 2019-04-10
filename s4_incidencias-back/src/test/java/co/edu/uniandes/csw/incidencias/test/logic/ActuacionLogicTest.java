/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.logic;

import co.edu.uniandes.csw.incidencias.ejb.ActuacionLogic;

import co.edu.uniandes.csw.incidencias.entities.ActuacionEntity;

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
    private ActuacionLogic ActuacionLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<ActuacionEntity> data = new ArrayList<ActuacionEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {   
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ActuacionEntity.class.getPackage())
                .addPackage(ActuacionLogic.class.getPackage())
                .addPackage(ActuacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
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

    private void clearData() {
        em.createQuery("delete from ActuacionEntity").executeUpdate();
    }
    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ActuacionEntity entity = factory.manufacturePojo(ActuacionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Test para probar el metodo que crea una actuacion
     * @throws BusinessLogicException 
     */
    @Test
    public void createActuacionTest() throws BusinessLogicException {
        ActuacionEntity newEntity = factory.manufacturePojo(ActuacionEntity.class);
        ActuacionEntity result = ActuacionLogic.createActuacion(newEntity);
        Assert.assertNotNull(result);
        ActuacionEntity entity = em.find(ActuacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
       
    }
    /**
     * Test para probar que pasa cuando dos actuaciones tienen el mismo id
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class)
    public void createActuacionMismoIdTest() throws BusinessLogicException {
        ActuacionEntity newEntity = factory.manufacturePojo(ActuacionEntity.class);
        newEntity.setId(data.get(0).getId());
        ActuacionLogic.createActuacion(newEntity);
    }
    /**
     * test para probar el metodo getAll
     */
    @Test
    public void getActuacionesTest() {
        List<ActuacionEntity> list = ActuacionLogic.getActuaciones();
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
     * Test para probar el metodo get
     */
    @Test
    public void getActuacionTest() {
        ActuacionEntity entity = data.get(0);
        ActuacionEntity resultEntity = ActuacionLogic.getActuacion(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        
    }

    /**
     * Test  para probar el metodo delete
     * @throws BusinessLogicException 
     */
    @Test
    public void deleteActuacionTest() throws BusinessLogicException {
        ActuacionEntity entity = data.get(1);
       ActuacionLogic.deleteActuacion(entity.getId());
        ActuacionEntity deleted = em.find(ActuacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
     @Test
    public void updateActuacionTest() {
        ActuacionEntity entity = data.get(0);
        ActuacionEntity pojoEntity = factory.manufacturePojo(ActuacionEntity.class);
        pojoEntity.setId(entity.getId());
        ActuacionLogic.updateActuacion(pojoEntity);
        ActuacionEntity resp = em.find(ActuacionEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
        
    }
}
