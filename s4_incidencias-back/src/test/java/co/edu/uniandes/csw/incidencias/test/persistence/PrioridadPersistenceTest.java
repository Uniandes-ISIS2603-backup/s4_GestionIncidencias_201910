/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.persistence;

import co.edu.uniandes.csw.incidencias.entities.PrioridadEntity;
import co.edu.uniandes.csw.incidencias.persistence.PrioridadPersistence;
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
public class PrioridadPersistenceTest {
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<PrioridadEntity> data = new ArrayList<PrioridadEntity>();
    
     @Inject
    private PrioridadPersistence ep;
     
     @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PrioridadEntity.class.getPackage())
                .addPackage(PrioridadPersistence.class.getPackage())
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
        em.createQuery("delete from PrioridadEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PrioridadEntity entity = factory.manufacturePojo(PrioridadEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createPrioridadTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PrioridadEntity newEntity = factory.manufacturePojo(PrioridadEntity.class);
        PrioridadEntity te = ep.create(newEntity);
        Assert.assertNotNull(te);
        PrioridadEntity entity = em.find(PrioridadEntity.class, te.getId());
        Assert.assertEquals(newEntity.getTipoPrioridad(), entity.getTipoPrioridad());
    }

    
    @Test
    public void findPrioridadTest() {
        PrioridadEntity entity = data.get(0);
        PrioridadEntity newEntity = ep.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getTipoPrioridad(), newEntity.getTipoPrioridad());  
    }
    @Test
    public void findAlPrioridadTest() {
        List<PrioridadEntity> results = ep.findAll();
        Assert.assertEquals(data.size(), results.size());
        for (PrioridadEntity ent : results) {
            boolean found = false;
            for (PrioridadEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }   
    } 
    
    @Test
    public void deletePrioridadTest() {
        PrioridadEntity entity = data.get(0);
        ep.delete(entity.getId());
        PrioridadEntity deleted = em.find(PrioridadEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void updatePrioridadTest() {
        PrioridadEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PrioridadEntity newEntity = factory.manufacturePojo(PrioridadEntity.class);
        newEntity.setId(entity.getId());
        ep.update(newEntity);
        PrioridadEntity resp = em.find(PrioridadEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getTipoPrioridad(), resp.getTipoPrioridad());
    }
    
}
