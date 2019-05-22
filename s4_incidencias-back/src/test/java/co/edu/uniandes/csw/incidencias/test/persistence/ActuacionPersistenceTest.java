/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.persistence;

import co.edu.uniandes.csw.incidencias.entities.ActuacionEntity;
import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import co.edu.uniandes.csw.incidencias.persistence.ActuacionPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;



//----------------------------Clase terminada-------------------------------------------
/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class ActuacionPersistenceTest {
    
     @Inject
    private ActuacionPersistence actuacionPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ActuacionEntity> data = new ArrayList<ActuacionEntity>();
	
    private List<IncidenciaEntity> dataInc = new ArrayList<IncidenciaEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ActuacionEntity.class.getPackage())
                .addPackage(ActuacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

     /**
     * Configuración inicial de la prueba.
     */
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
        em.createQuery("delete from ActuacionEntity").executeUpdate();
        em.createQuery("delete from IncidenciaEntity").executeUpdate();
    }

      private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            IncidenciaEntity entity = factory.manufacturePojo(IncidenciaEntity.class);
            em.persist(entity);
            dataInc.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            ActuacionEntity entity = factory.manufacturePojo(ActuacionEntity.class);
            if (i == 0) {
                entity.setIncidencia(dataInc.get(0));
            }
            em.persist(entity);
            data.add(entity);
        }
      }
        
        
         /**
     * Prueba para crear una actuacion.
     */
    @Test
    public void createRActuacionTest() {

        PodamFactory factory = new PodamFactoryImpl();
        ActuacionEntity newEntity = factory.manufacturePojo(ActuacionEntity.class);
        ActuacionEntity result = actuacionPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ActuacionEntity entity = em.find(ActuacionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
    }
    
    /**
     * Prueba para consultar una actuacion.
     */
    @Test
    public void getActuacionTest() {
        ActuacionEntity entity = data.get(0);
        ActuacionEntity newEntity = actuacionPersistence.find(dataInc.get(0).getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getTipo(), newEntity.getTipo());
        Assert.assertEquals(entity.getDescripcion(), newEntity.getDescripcion());
    }
    
    
     /**
     * Prueba para eliminar un Review.
     */
    @Test
    public void deleteActuacionTest() {
        ActuacionEntity entity = data.get(0);
        actuacionPersistence.delete(entity.getId());
        ActuacionEntity deleted = em.find(ActuacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     @Test
    public void updateActuacionTest() {
        ActuacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ActuacionEntity newEntity = factory.manufacturePojo(ActuacionEntity.class);

        newEntity.setId(entity.getId());

        actuacionPersistence.update(newEntity);

        ActuacionEntity resp = em.find(ActuacionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
        Assert.assertEquals(newEntity.getTipo(), resp.getTipo());
         Assert.assertEquals(newEntity.getDescripcion(), resp.getDescripcion());
    }
        
        
    
      
      
    
}
