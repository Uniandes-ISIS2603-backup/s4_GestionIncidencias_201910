/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.logic;

import co.edu.uniandes.csw.incidencias.ejb.EquipoComputoLogic;
import co.edu.uniandes.csw.incidencias.entities.EquipoComputoEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.EquipoComputoPersistence;
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
public class EquipoComputoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private EquipoComputoLogic equipoComputoLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<EquipoComputoEntity> data = new ArrayList<EquipoComputoEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {   
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EquipoComputoEntity.class.getPackage())
                .addPackage(EquipoComputoLogic.class.getPackage())
                .addPackage(EquipoComputoPersistence.class.getPackage())
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
        em.createQuery("delete from EquipoComputoEntity").executeUpdate();
    }
    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            EquipoComputoEntity entity = factory.manufacturePojo(EquipoComputoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createEquipoComputoTest() throws BusinessLogicException {
        EquipoComputoEntity newEntity = factory.manufacturePojo(EquipoComputoEntity.class);
        EquipoComputoEntity result = equipoComputoLogic.createEquipoComputo(newEntity);
        Assert.assertNotNull(result);
        EquipoComputoEntity entity = em.find(EquipoComputoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createEquipoComputoConMismoIdEquipo() throws BusinessLogicException {
        EquipoComputoEntity newEntity = factory.manufacturePojo(EquipoComputoEntity.class);
        newEntity.setId(data.get(0).getId());
        equipoComputoLogic.createEquipoComputo(newEntity);
    }
    
    @Test
    public void getEquipoComputosTest() {
        List<EquipoComputoEntity> list = equipoComputoLogic.getEquipoComputos();
        Assert.assertEquals(data.size(), list.size());
        for (EquipoComputoEntity entity : list) {
            boolean found = false;
            for (EquipoComputoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getEquipoComputoTest() {
        EquipoComputoEntity entity = data.get(0);
        EquipoComputoEntity resultEntity = equipoComputoLogic.getEquipoComputo(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());
    }

    @Test
    public void updateEquipoComputoTest() {
        EquipoComputoEntity entity = data.get(0);
        EquipoComputoEntity pojoEntity = factory.manufacturePojo(EquipoComputoEntity.class);
        pojoEntity.setId(entity.getId());
        equipoComputoLogic.updateEquipoComputo( pojoEntity);
        EquipoComputoEntity resp = em.find(EquipoComputoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
    }

    @Test
    public void deleteEquipoComputoTest() throws BusinessLogicException {
        EquipoComputoEntity entity = data.get(1);
        equipoComputoLogic.deleteEquipoComputo(entity.getId());
        EquipoComputoEntity deleted = em.find(EquipoComputoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
