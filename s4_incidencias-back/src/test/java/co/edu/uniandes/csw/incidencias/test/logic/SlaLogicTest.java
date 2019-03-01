/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.logic;

import co.edu.uniandes.csw.incidencias.ejb.SlaLogic;
import co.edu.uniandes.csw.incidencias.entities.SlaEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.SlaPersistence;
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
 * @author Daniel Santamaría Álvarez
 */
@RunWith(Arquillian.class)
public class SlaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private SlaLogic slaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<SlaEntity> data = new ArrayList<SlaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {   
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SlaEntity.class.getPackage())
                .addPackage(SlaLogic.class.getPackage())
                .addPackage(SlaPersistence.class.getPackage())
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
        em.createQuery("delete from SlaEntity").executeUpdate();
    }
    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            SlaEntity entity = factory.manufacturePojo(SlaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createSlaTest() throws BusinessLogicException {
        SlaEntity newEntity = factory.manufacturePojo(SlaEntity.class);
        SlaEntity result = slaLogic.createSla(newEntity);
        Assert.assertNotNull(result);
        SlaEntity entity = em.find(SlaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createSlaConMismoIdTest() throws BusinessLogicException {
        SlaEntity newEntity = factory.manufacturePojo(SlaEntity.class);
        newEntity.setId(data.get(0).getId());
        slaLogic.createSla(newEntity);
    }
    
    @Test
    public void getSlasTest() {
        List<SlaEntity> list = slaLogic.getSlas();
        Assert.assertEquals(data.size(), list.size());
        for (SlaEntity entity : list) {
            boolean found = false;
            for (SlaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getSlaTest() {
        SlaEntity entity = data.get(0);
        SlaEntity resultEntity = slaLogic.getSla(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getIdSla(), resultEntity.getIdSla());
    }

    @Test
    public void updateSlaTest() {
        SlaEntity entity = data.get(0);
        SlaEntity pojoEntity = factory.manufacturePojo(SlaEntity.class);
        pojoEntity.setId(entity.getId());
        slaLogic.updateSla(pojoEntity.getId(), pojoEntity);
        SlaEntity resp = em.find(SlaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getIdSla(), resp.getIdSla());
    }

    @Test
    public void deleteSlaTest() throws BusinessLogicException {
        SlaEntity entity = data.get(1);
        slaLogic.deleteSla(entity.getId());
        SlaEntity deleted = em.find(SlaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
