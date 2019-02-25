/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.persistence;


import co.edu.uniandes.csw.incidencias.entities.TecnicoEntity;
import org.junit.Assert;
import co.edu.uniandes.csw.incidencias.persistence.TecnicoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.runner.RunWith;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 * @author Valerie Parra Cortés
 */

@RunWith(Arquillian.class)
public class TecnicoPersistenceTest {
        
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<TecnicoEntity> data = new ArrayList<TecnicoEntity>();
    
    @Inject
    private TecnicoPersistence tp;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TecnicoEntity.class.getPackage())
                .addPackage(TecnicoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
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
    public void createTecnicoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        TecnicoEntity newEntity = factory.manufacturePojo(TecnicoEntity.class);
        TecnicoEntity te = tp.create(newEntity);
        Assert.assertNotNull(te);
        TecnicoEntity entity = em.find(TecnicoEntity.class, te.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    
    @Test
    public void findTecnicoTest() {
        TecnicoEntity entity = data.get(0);
        TecnicoEntity newEntity = tp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());  
    }
    @Test
    public void findAllTecnicoTest() {
        List<TecnicoEntity> results = tp.findAll();
        Assert.assertEquals(data.size(), results.size());
        for (TecnicoEntity ent : results) {
            boolean found = false;
            for (TecnicoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }   
    } 
    
    @Test
    public void deleteTecnicoTest() {
        TecnicoEntity entity = data.get(0);
        tp.delete(entity.getId());
        TecnicoEntity deleted = em.find(TecnicoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void updateTecnicoTest() {
        TecnicoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TecnicoEntity newEntity = factory.manufacturePojo(TecnicoEntity.class);
        newEntity.setId(entity.getId());
        tp.update(newEntity);
        TecnicoEntity resp = em.find(TecnicoEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
