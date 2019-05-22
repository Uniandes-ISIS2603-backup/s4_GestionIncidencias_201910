/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.logic;
import co.edu.uniandes.csw.incidencias.ejb.TecnicoLogic;
import co.edu.uniandes.csw.incidencias.entities.TecnicoEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class TecnicoLogicTest {
   @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
  
    @Inject
    private TecnicoLogic tecnicoLogic;
    private List<TecnicoEntity> data = new ArrayList<TecnicoEntity>();
    
    private PodamFactory factory = new PodamFactoryImpl();
    @Deployment
    public static JavaArchive createDeployment() {   
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TecnicoEntity.class.getPackage())
                .addPackage(TecnicoLogic.class.getPackage())
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
        em.createQuery("delete from TecnicoEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TecnicoEntity entity = factory.manufacturePojo(TecnicoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test    
    public void createTecnicoTest() throws BusinessLogicException {
        TecnicoEntity newEntity = factory.manufacturePojo(TecnicoEntity.class);
        TecnicoEntity result = tecnicoLogic.createTecnico(newEntity);
        Assert.assertNotNull(result);
        TecnicoEntity entity = em.find(TecnicoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getCedula(), entity.getCedula());
        Assert.assertEquals(newEntity.getUsuario(), entity.getUsuario());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createTecnicoMismaCedula() throws BusinessLogicException {
        TecnicoEntity newEntity = factory.manufacturePojo(TecnicoEntity.class);
        newEntity.setCedula(data.get(0).getCedula());
        tecnicoLogic.createTecnico(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createTecnicoMismoUsuario() throws BusinessLogicException {
        TecnicoEntity newEntity = factory.manufacturePojo(TecnicoEntity.class);
        newEntity.setUsuario(data.get(0).getUsuario());
        tecnicoLogic.createTecnico(newEntity);
    }
    
    @Test
    public void getDepartamentosTest() {
        List<TecnicoEntity> list = tecnicoLogic.getTecnicos();
        Assert.assertEquals(data.size(), list.size());
        for (TecnicoEntity entity : list) {
            boolean found = false;
            for (TecnicoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getTecnicoTest() {
        TecnicoEntity entity = data.get(0);
        TecnicoEntity resultEntity = tecnicoLogic.getTecnico(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getUsuario(), resultEntity.getUsuario());
        Assert.assertEquals(entity.getCedula(), resultEntity.getCedula());
    }
    
    @Test
    public void updateTecnicoTest() {
        TecnicoEntity entity = data.get(0);
        TecnicoEntity pojoEntity = factory.manufacturePojo(TecnicoEntity.class);
        pojoEntity.setId(entity.getId());
        tecnicoLogic.updateTecnico(pojoEntity);
        TecnicoEntity resp = em.find(TecnicoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getUsuario(),resp.getUsuario());
    }

    @Test
    public void deleteDepartamentoTest() throws BusinessLogicException {
        TecnicoEntity entity = data.get(1);
        tecnicoLogic.deleteTecnico(entity.getId());
        TecnicoEntity deleted = em.find(TecnicoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }       
    }
      
    
    
    
