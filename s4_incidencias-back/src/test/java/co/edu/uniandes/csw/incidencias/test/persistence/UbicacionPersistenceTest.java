/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.persistence;

import co.edu.uniandes.csw.incidencias.entities.UbicacionEntity;
import co.edu.uniandes.csw.incidencias.persistence.UbicacionPersistence;
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
public class UbicacionPersistenceTest {
    
    @Inject
    private UbicacionPersistence dp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<UbicacionEntity> data = new ArrayList<UbicacionEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {   
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UbicacionEntity.class.getPackage())
                .addPackage(UbicacionPersistence.class.getPackage())
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
        em.createQuery("delete from UbicacionEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            UbicacionEntity entity = factory.manufacturePojo(UbicacionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createUbicacionTest() {
        PodamFactory factory = new PodamFactoryImpl();
        UbicacionEntity newEntity = factory.manufacturePojo(UbicacionEntity.class);
        UbicacionEntity de = dp.create(newEntity);
        Assert.assertNotNull(de);
        UbicacionEntity entity = em.find(UbicacionEntity.class, de.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
    }
    
    @Test
    public void findUbicacionTest() {
        UbicacionEntity entity = data.get(0);
        UbicacionEntity newEntity = dp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getDescripcion(), newEntity.getDescripcion());  
    }
    
    @Test
    public void findAllUbicacionTest() {
        List<UbicacionEntity> results = dp.findAll();
        Assert.assertEquals(data.size(), results.size());
        for (UbicacionEntity ent : results) {
            boolean found = false;
            for (UbicacionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }   
    } 
    
    @Test
    public void deleteUbicacionTest() {
        UbicacionEntity entity = data.get(0);
        dp.delete(entity.getId());
        UbicacionEntity deleted = em.find(UbicacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateUbicacionTest() {
        UbicacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        UbicacionEntity newEntity = factory.manufacturePojo(UbicacionEntity.class);
        newEntity.setId(entity.getId());
        dp.update(newEntity);
        UbicacionEntity resp = em.find(UbicacionEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), resp.getDescripcion());
    }
}
