/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.logic;

import co.edu.uniandes.csw.incidencias.ejb.UbicacionLogic;
import co.edu.uniandes.csw.incidencias.entities.UbicacionEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
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
public class UbicacionLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private UbicacionLogic ubicacionLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<UbicacionEntity> data = new ArrayList<UbicacionEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {   
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UbicacionEntity.class.getPackage())
                .addPackage(UbicacionLogic.class.getPackage())
                .addPackage(UbicacionPersistence.class.getPackage())
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
        em.createQuery("delete from UbicacionEntity").executeUpdate();
    }
    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            UbicacionEntity entity = factory.manufacturePojo(UbicacionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createUbicacionTest() throws BusinessLogicException {
        UbicacionEntity newEntity = factory.manufacturePojo(UbicacionEntity.class);
        UbicacionEntity result = ubicacionLogic.createUbicacion(newEntity);
        Assert.assertNotNull(result);
        UbicacionEntity entity = em.find(UbicacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createUbicacionConMismaDescripcionTest() throws BusinessLogicException {
        UbicacionEntity newEntity = factory.manufacturePojo(UbicacionEntity.class);
        newEntity.setDescripcion(data.get(0).getDescripcion());
        ubicacionLogic.createUbicacion(newEntity);
    }
    
    @Test
    public void getUbicacionsTest() {
        List<UbicacionEntity> list = ubicacionLogic.getUbicacions();
        Assert.assertEquals(data.size(), list.size());
        for (UbicacionEntity entity : list) {
            boolean found = false;
            for (UbicacionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getUbicacionTest() {
        UbicacionEntity entity = data.get(0);
        UbicacionEntity resultEntity = ubicacionLogic.getUbicacion(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());
    }

    @Test
    public void updateUbicacionTest() {
        UbicacionEntity entity = data.get(0);
        UbicacionEntity pojoEntity = factory.manufacturePojo(UbicacionEntity.class);
        pojoEntity.setId(entity.getId());
        ubicacionLogic.updateUbicacion( pojoEntity);
        UbicacionEntity resp = em.find(UbicacionEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
    }

    @Test
    public void deleteUbicacionTest() throws BusinessLogicException {
        UbicacionEntity entity = data.get(1);
        ubicacionLogic.deleteUbicacion(entity.getId());
        UbicacionEntity deleted = em.find(UbicacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
