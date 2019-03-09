/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.logic;

import co.edu.uniandes.csw.incidencias.ejb.CalificacionLogic;
import co.edu.uniandes.csw.incidencias.entities.CalificacionEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.CalificacionPersistence;
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
public class CalificacionLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private CalificacionLogic ubicacionLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<CalificacionEntity> data = new ArrayList<CalificacionEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {   
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionLogic.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
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
        em.createQuery("delete from CalificacionEntity").executeUpdate();
    }
    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createCalificacionTest() throws BusinessLogicException {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity result = ubicacionLogic.createCalificacion(newEntity);
        Assert.assertNotNull(result);
        CalificacionEntity entity = em.find(CalificacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionConMismaDescripcionTest() throws BusinessLogicException {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setDescripcion(data.get(0).getDescripcion());
        ubicacionLogic.createCalificacion(newEntity);
    }
    
    @Test
    public void getCalificacionsTest() {
        List<CalificacionEntity> list = ubicacionLogic.getCalificacions();
        Assert.assertEquals(data.size(), list.size());
        for (CalificacionEntity entity : list) {
            boolean found = false;
            for (CalificacionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity resultEntity = ubicacionLogic.getCalificacion(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());
    }

    @Test
    public void updateCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity pojoEntity = factory.manufacturePojo(CalificacionEntity.class);
        pojoEntity.setId(entity.getId());
        ubicacionLogic.updateCalificacion(pojoEntity.getId(), pojoEntity);
        CalificacionEntity resp = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
    }

    @Test
    public void deleteCalificacionTest() throws BusinessLogicException {
        CalificacionEntity entity = data.get(1);
        ubicacionLogic.deleteCalificacion(entity.getId());
        CalificacionEntity deleted = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}