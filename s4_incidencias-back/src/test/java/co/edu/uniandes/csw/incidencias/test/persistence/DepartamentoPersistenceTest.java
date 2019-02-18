/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.persistence;

import co.edu.uniandes.csw.incidencias.entities.DepartamentoEntity;
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
public class DepartamentoPersistenceTest {
    
    @Inject
    private DepartamentoPersistence dp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<DepartamentoEntity> data = new ArrayList<DepartamentoEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {   
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DepartamentoEntity.class.getPackage())
                .addPackage(DepartamentoPersistence.class.getPackage())
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
        em.createQuery("delete from DepartamentoEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            DepartamentoEntity entity = factory.manufacturePojo(DepartamentoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createDepartamentoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        DepartamentoEntity newEntity = factory.manufacturePojo(DepartamentoEntity.class);
        DepartamentoEntity de = dp.create(newEntity);
        Assert.assertNotNull(de);
        DepartamentoEntity entity = em.find(DepartamentoEntity.class, de.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    @Test
    public void findDepartamentoTest() {
        DepartamentoEntity entity = data.get(0);
        DepartamentoEntity newEntity = dp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());  
    }
    
    @Test
    public void findAllDepartamentoTest() {
        List<DepartamentoEntity> results = dp.findAll();
        Assert.assertEquals(data.size(), results.size());
        for (DepartamentoEntity ent : results) {
            boolean found = false;
            for (DepartamentoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }   
    } 
    
    @Test
    public void deleteDepartamentoTest() {
        DepartamentoEntity entity = data.get(0);
        dp.delete(entity.getId());
        DepartamentoEntity deleted = em.find(DepartamentoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateDepartamentoTest() {
        DepartamentoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        DepartamentoEntity newEntity = factory.manufacturePojo(DepartamentoEntity.class);
        newEntity.setId(entity.getId());
        dp.update(newEntity);
        DepartamentoEntity resp = em.find(DepartamentoEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
}
