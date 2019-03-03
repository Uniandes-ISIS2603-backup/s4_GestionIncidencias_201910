/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.persistence;

import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidencias.persistence.EmpleadoPersistence;
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
public class EmpleadoPersistenceTest {
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<EmpleadoEntity> data = new ArrayList<EmpleadoEntity>();
    
     @Inject
    private EmpleadoPersistence ep;
     
     @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EmpleadoEntity.class.getPackage())
                .addPackage(EmpleadoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
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
        em.createQuery("delete from EmpleadoEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EmpleadoEntity entity = factory.manufacturePojo(EmpleadoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createEmpleadoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EmpleadoEntity newEntity = factory.manufacturePojo(EmpleadoEntity.class);
        EmpleadoEntity te = (EmpleadoEntity) ep.create(newEntity);
        Assert.assertNotNull(te);
        EmpleadoEntity entity = em.find(EmpleadoEntity.class, te.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    
    @Test
    public void findEmpleadoTest() {
        EmpleadoEntity entity = data.get(0);
        EmpleadoEntity newEntity = ep.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());  
    }
    @Test
    public void findAlEmpleadoTest() {
        List<EmpleadoEntity> results = ep.findAll();
        Assert.assertEquals(data.size(), results.size());
        for (EmpleadoEntity ent : results) {
            boolean found = false;
            for (EmpleadoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }   
    } 
    
    @Test
    public void deleteEmpleadoTest() {
        EmpleadoEntity entity = data.get(0);
        ep.delete(entity.getId());
        EmpleadoEntity deleted = em.find(EmpleadoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void updateEmpleadoTest() {
        EmpleadoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EmpleadoEntity newEntity = factory.manufacturePojo(EmpleadoEntity.class);
        newEntity.setId(entity.getId());
        ep.update(newEntity);
        EmpleadoEntity resp = em.find(EmpleadoEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
}
