/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.persistence;

import co.edu.uniandes.csw.incidencias.entities.ActuacionEntity;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import co.edu.uniandes.csw.incidencias.entities.DepartamentoEntity;
import co.edu.uniandes.csw.incidencias.persistence.ActuacionPersistence;
import co.edu.uniandes.csw.incidencias.persistence.DepartamentoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import javax.transaction.UserTransaction;
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
public class ActuacionPersistenceTest {
    
     @Inject
    private ActuacionPersistence dp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<ActuacionEntity> data = new ArrayList<ActuacionEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {   
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ActuacionEntity.class.getPackage())
                .addPackage(ActuacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
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

    private void clearData() {
        em.createQuery("delete from ActuacionEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ActuacionEntity entity = factory.manufacturePojo(ActuacionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createActuacionTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ActuacionEntity newEntity = factory.manufacturePojo(ActuacionEntity.class);
        ActuacionEntity de = dp.create(newEntity);
        Assert.assertNotNull(de);
       ActuacionEntity entity = em.find(ActuacionEntity.class, de.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    @Test
    public void findActuacionTest() {
       ActuacionEntity entity = data.get(0);
        ActuacionEntity newEntity = dp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());  
    }
    
    @Test
    public void findAllActuacionTest() {
        List<ActuacionEntity> results = dp.findAll();
        Assert.assertEquals(data.size(), results.size());
        for (ActuacionEntity ent : results) {
            boolean found = false;
            for (ActuacionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }   
    } 
    
   
}
