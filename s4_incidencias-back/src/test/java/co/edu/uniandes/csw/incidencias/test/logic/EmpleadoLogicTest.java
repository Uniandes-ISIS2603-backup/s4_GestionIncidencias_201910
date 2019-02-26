/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.logic;

import co.edu.uniandes.csw.incidencias.ejb.EmpleadoLogic;
import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
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
  * @author Valerie Parra Cortés
 */

@RunWith(Arquillian.class)
public class EmpleadoLogicTest {
    
    private List<EmpleadoEntity> data= new ArrayList<EmpleadoEntity>();
    
    @Inject
    private EmpleadoLogic el;
    
    @Inject 
    private UserTransaction utx;
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @PersistenceContext
    private EntityManager em;
   
    
    @Deployment    
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EmpleadoEntity.class.getPackage())
                .addPackage(EmpleadoLogic.class.getPackage())
                .addPackage(EmpleadoPersistence.class.getPackage())
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
    public void createEmpleadoTest() throws BusinessLogicException {
        EmpleadoEntity newEntity = factory.manufacturePojo(EmpleadoEntity.class);
        EmpleadoEntity result = el.createEmpleadoEntity(newEntity);
        Assert.assertNotNull(result);
        EmpleadoEntity entity = em.find(EmpleadoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getCedula(), entity.getCedula());
        Assert.assertEquals(newEntity.getUsuario(), entity.getUsuario());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createEmpleadoMismaCedula() throws BusinessLogicException {
        EmpleadoEntity newEntity = factory.manufacturePojo(EmpleadoEntity.class);
        newEntity.setCedula(data.get(0).getCedula());
        el.createEmpleadoEntity(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createEmpleadoMismoUsuario() throws BusinessLogicException {
        EmpleadoEntity newEntity = factory.manufacturePojo(EmpleadoEntity.class);
        newEntity.setUsuario(data.get(0).getUsuario());
        el.createEmpleadoEntity(newEntity);
    }
    
    @Test
    public void getDepartamentosTest() {
        List<EmpleadoEntity> list = el.getEmpleados();
        Assert.assertEquals(data.size(), list.size());
        for (EmpleadoEntity entity : list) {
            boolean found = false;
            for (EmpleadoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getTecnicoTest() {
        EmpleadoEntity entity = data.get(0);
        EmpleadoEntity resultEntity = el.getEmpleado(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getUsuario(), resultEntity.getUsuario());
        Assert.assertEquals(entity.getCedula(), resultEntity.getCedula());
    }
    
    @Test
    public void updateTecnicoTest() {
        EmpleadoEntity entity = data.get(0);
        EmpleadoEntity pojoEntity = factory.manufacturePojo(EmpleadoEntity.class);
        pojoEntity.setId(entity.getId());
        el.updateEmpleado(pojoEntity);
        EmpleadoEntity resp = em.find(EmpleadoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getUsuario(),resp.getUsuario());
    }

    @Test
    public void deleteDepartamentoTest() throws BusinessLogicException {
        EmpleadoEntity entity = data.get(1);
        el.deleteEmpleado(entity.getId());
        EmpleadoEntity deleted = em.find(EmpleadoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
            
    
    
    
}
