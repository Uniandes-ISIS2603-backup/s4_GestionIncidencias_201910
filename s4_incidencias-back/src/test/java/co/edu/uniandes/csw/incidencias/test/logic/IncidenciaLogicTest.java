/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.logic;


import co.edu.uniandes.csw.incidencias.ejb.IncidenciaLogic;

import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;

import co.edu.uniandes.csw.incidencias.persistence.IncidenciaPersistence;
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
 * @author Guillermo Lobaton
 */
@RunWith(Arquillian.class)
public class IncidenciaLogicTest{
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IncidenciaLogic IncidenciaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<IncidenciaEntity> data = new ArrayList<IncidenciaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {   
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(IncidenciaEntity.class.getPackage())
                .addPackage(IncidenciaLogic.class.getPackage())
                .addPackage(IncidenciaPersistence.class.getPackage())
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
        em.createQuery("delete from IncidenciaEntity").executeUpdate();
    }
    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            IncidenciaEntity entity = factory.manufacturePojo(IncidenciaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createIncidenciaTest() throws BusinessLogicException {
        IncidenciaEntity newEntity = factory.manufacturePojo(IncidenciaEntity.class);
        IncidenciaEntity result = IncidenciaLogic.createIncidencia(newEntity);
        Assert.assertNotNull(result);
        IncidenciaEntity entity = em.find(IncidenciaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
       
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createIncidenciaMismoIdTest() throws BusinessLogicException {
        IncidenciaEntity newEntity = factory.manufacturePojo(IncidenciaEntity.class);
        newEntity.setId(data.get(0).getId());
        IncidenciaLogic.createIncidencia(newEntity);
    }
    
    @Test
    public void getIncidenciasTest() {
        List<IncidenciaEntity> list = IncidenciaLogic.getIncidencias();
        Assert.assertEquals(data.size(), list.size());
        for (IncidenciaEntity entity : list) {
            boolean found = false;
            for (IncidenciaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getIncidenciaTest() {
        IncidenciaEntity entity = data.get(0);
        IncidenciaEntity resultEntity = IncidenciaLogic.getIncidencia(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        
    }

    
    @Test
    public void deleteIncidenciaTest() throws BusinessLogicException {
       IncidenciaEntity entity = data.get(1);
       IncidenciaLogic.deleteIncidencia(entity.getId());
        IncidenciaEntity deleted = em.find(IncidenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void updateIncidenciaTest() {
        IncidenciaEntity entity = data.get(0);
        IncidenciaEntity pojoEntity = factory.manufacturePojo(IncidenciaEntity.class);
        pojoEntity.setId(entity.getId());
        IncidenciaLogic.updateIncidencia( pojoEntity);
        IncidenciaEntity resp = em.find(IncidenciaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
       
    }

}
