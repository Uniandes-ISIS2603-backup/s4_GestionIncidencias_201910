/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.logic;

import co.edu.uniandes.csw.incidencias.ejb.DepartamentoLogic;
import co.edu.uniandes.csw.incidencias.entities.DepartamentoEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.DepartamentoPersistence;
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
public class DepartamentoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private DepartamentoLogic departamentoLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<DepartamentoEntity> data = new ArrayList<DepartamentoEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {   
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DepartamentoEntity.class.getPackage())
                .addPackage(DepartamentoLogic.class.getPackage())
                .addPackage(DepartamentoPersistence.class.getPackage())
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
        em.createQuery("delete from DepartamentoEntity").executeUpdate();
    }
    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            DepartamentoEntity entity = factory.manufacturePojo(DepartamentoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createDepartamentoTest() throws BusinessLogicException {
        DepartamentoEntity newEntity = factory.manufacturePojo(DepartamentoEntity.class);
        DepartamentoEntity result = departamentoLogic.createDepartamento(newEntity);
        Assert.assertNotNull(result);
        DepartamentoEntity entity = em.find(DepartamentoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createDepartamentoConMismoNombreTest() throws BusinessLogicException {
        DepartamentoEntity newEntity = factory.manufacturePojo(DepartamentoEntity.class);
        newEntity.setNombre(data.get(0).getNombre());
        departamentoLogic.createDepartamento(newEntity);
    }
    
    @Test
    public void getDepartamentosTest() {
        List<DepartamentoEntity> list = departamentoLogic.getDepartamentos();
        Assert.assertEquals(data.size(), list.size());
        for (DepartamentoEntity entity : list) {
            boolean found = false;
            for (DepartamentoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getDepartamentoTest() {
        DepartamentoEntity entity = data.get(0);
        DepartamentoEntity resultEntity = departamentoLogic.getDepartamento(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
    }

    @Test
    public void updateDepartamentoTest() {
        DepartamentoEntity entity = data.get(0);
        DepartamentoEntity pojoEntity = factory.manufacturePojo(DepartamentoEntity.class);
        pojoEntity.setId(entity.getId());
        departamentoLogic.updateDepartamento(pojoEntity.getId(), pojoEntity);
        DepartamentoEntity resp = em.find(DepartamentoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
    }

    @Test
    public void deleteDepartamentoTest() throws BusinessLogicException {
        DepartamentoEntity entity = data.get(1);
        departamentoLogic.deleteDepartamento(entity.getId());
        DepartamentoEntity deleted = em.find(DepartamentoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
