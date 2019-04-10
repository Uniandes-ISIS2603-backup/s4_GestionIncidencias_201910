/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.persistence;

import co.edu.uniandes.csw.incidencias.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import co.edu.uniandes.csw.incidencias.persistence.ActuacionPersistence;
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
 * @author estudiante
 */

@RunWith(Arquillian.class)
public class IncidenciaPersistenceTest {
    
    
     @Inject
    private IncidenciaPersistence dp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private  List<IncidenciaEntity> data = new ArrayList ();
    
    @Deployment
    public static JavaArchive createDeployment() {   
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(IncidenciaEntity.class.getPackage())
                .addPackage(IncidenciaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
           
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
        em.createQuery("delete from IncidenciaEntityntity").executeUpdate();
    }


    
    @Test
    public void createIncidenciaTest() {
        PodamFactory factory = new PodamFactoryImpl();
       IncidenciaEntity newEntity = factory.manufacturePojo(IncidenciaEntity.class);
       IncidenciaEntity de = dp.create(newEntity);
        Assert.assertNotNull(de);
        IncidenciaEntity entity = em.find(IncidenciaEntity.class, de.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    /**
     * test para probar el metodo  get 
     */
    @Test
    public void findIncidenciaTest() {
        
       PodamFactory factory = new PodamFactoryImpl();
       IncidenciaEntity newEntity = factory.manufacturePojo(IncidenciaEntity.class);
       IncidenciaEntity e = dp.create(newEntity);
         
       IncidenciaEntity newEntity2 = factory.manufacturePojo(IncidenciaEntity.class);
       IncidenciaEntity e2 = dp.create(newEntity2);
       
       Assert.assertNotNull(e);
       IncidenciaEntity u = em.find(IncidenciaEntity.class, e.getId());
       Assert.assertEquals(e,u);
       
       
    }
    
    /**
     * Test para probar el  metodo getAll de la clase de persistencia
     */
    @Test
    public void findAllIncidenciaTest() {
       
      
       
       List lista = dp.findAll();
              
            
            Assert.assertEquals(4,lista.size());
        }   
     
    /**
     * Test para probar el metodo delete de la clase de persistencia
     */
    @Test
    public void deleteIncidenciaTest() {
        
        PodamFactory factory = new PodamFactoryImpl();
        IncidenciaEntity newEntity2 = factory.manufacturePojo(IncidenciaEntity.class);
        IncidenciaEntity e2 = dp.create(newEntity2);
        List lista = dp.findAll();
        
        Assert.assertEquals(3, lista.size());
        dp.delete(e2.getId());
        
        List lista2 = dp.findAll();
        Assert.assertEquals(2, lista2.size());
    }
    /**
     * Metodo para probar el metodo update de la clase de persistencia
     */
    @Test
    public void updateIncidenciaTest() {
        
        PodamFactory factory = new PodamFactoryImpl();
        IncidenciaEntity newEntity = factory.manufacturePojo(IncidenciaEntity.class);
       
        IncidenciaEntity e2 = dp.create(newEntity);
        newEntity.setDescripcion("hola");
        dp.update(newEntity);
        
        Assert.assertEquals(newEntity.getDescripcion(), "hola");
    }
    
}
