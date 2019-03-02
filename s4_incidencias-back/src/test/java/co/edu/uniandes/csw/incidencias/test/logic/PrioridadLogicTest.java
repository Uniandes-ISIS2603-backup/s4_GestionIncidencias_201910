/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.logic;

import co.edu.uniandes.csw.incidencias.ejb.DepartamentoLogic;
import co.edu.uniandes.csw.incidencias.ejb.PrioridadLogic;
import co.edu.uniandes.csw.incidencias.entities.DepartamentoEntity;
import co.edu.uniandes.csw.incidencias.entities.PrioridadEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.DepartamentoPersistence;
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
 * @author Daniel Santamaria
 */
@RunWith(Arquillian.class)
public class PrioridadLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private PrioridadLogic prioridad;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<PrioridadEntity> data = new ArrayList<PrioridadEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {   
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PrioridadEntity.class.getPackage())
                .addPackage(PrioridadLogic.class.getPackage())
                .addPackage(PrioridadPersistence.class.getPackage())
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
        em.createQuery("delete from PrioridadEntity").executeUpdate();
    }
    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PrioridadEntity entity = factory.manufacturePojo(PrioridadEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createPrioridadTest() throws BusinessLogicException {
        PrioridadEntity newEntity = factory.manufacturePojo(PrioridadEntity.class);
        PrioridadEntity result = prioridad.createPrioridad(newEntity);
        Assert.assertNotNull(result);
        PrioridadEntity entity = em.find(PrioridadEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getTipoPrioridad(), entity.getTipoPrioridad());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createPrioridadConMismoIdTest() throws BusinessLogicException {
        PrioridadEntity newEntity = factory.manufacturePojo(PrioridadEntity.class);
        newEntity.setId(data.get(0).getId());
        prioridad.createPrioridad(newEntity);
    }
    
    @Test
    public void getPrioridadesTest() {
        List<PrioridadEntity> list = prioridad.getPrioridades();
        Assert.assertEquals(data.size(), list.size());
        for (PrioridadEntity entity : list) {
            boolean found = false;
            for (PrioridadEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getPrioridadTest() {
        PrioridadEntity entity = data.get(0);
        PrioridadEntity resultEntity = prioridad.getPrioridad(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getTipoPrioridad(), resultEntity.getTipoPrioridad());
    }

    @Test
    public void updatePrioridadTest() {
        PrioridadEntity entity = data.get(0);
        PrioridadEntity pojoEntity = factory.manufacturePojo(PrioridadEntity.class);
        pojoEntity.setId(entity.getId());
        prioridad.updatePrioridad(pojoEntity.getId(), pojoEntity);
        PrioridadEntity resp = em.find(PrioridadEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }

    @Test
    public void deletePrioridadTest() throws BusinessLogicException {
        PrioridadEntity entity = data.get(1);
        prioridad.deletePrioridad(entity.getId());
        PrioridadEntity deleted = em.find(PrioridadEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}