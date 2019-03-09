/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.persistence;

import co.edu.uniandes.csw.incidencias.entities.EquipoComputoEntity;
import co.edu.uniandes.csw.incidencias.persistence.EquipoComputoPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class EquipoComputoPersistenceTest {
    
        @Inject
    private EquipoComputoPersistence calificacionPersistence;

    @PersistenceContext
    private EntityManager em;
        @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EquipoComputoEntity.class.getPackage())
                .addPackage(EquipoComputoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Test
    public void createEquipoComputoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EquipoComputoEntity newEntity = factory.manufacturePojo(EquipoComputoEntity.class);
        EquipoComputoEntity result = calificacionPersistence.create(newEntity);

        Assert.assertNotNull(result);

        EquipoComputoEntity entity = em.find(EquipoComputoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getIdEquipo(), entity.getIdEquipo());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
    }
    
        @Test
    public void updateEquipoComputoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EquipoComputoEntity newEntity = factory.manufacturePojo(EquipoComputoEntity.class);
        EquipoComputoEntity result = calificacionPersistence.create(newEntity);
        result.setDescripcion("prueba");
        result.setIdEquipo(2);
        calificacionPersistence.update(result);

        EquipoComputoEntity entity = em.find(EquipoComputoEntity.class, result.getId());

        Assert.assertEquals("prueba", entity.getDescripcion());
    }

    @Test
    public void deleteEquipoComputoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EquipoComputoEntity newEntity = factory.manufacturePojo(EquipoComputoEntity.class);
        EquipoComputoEntity result = calificacionPersistence.create(newEntity);

        Assert.assertNotNull(result);

        calificacionPersistence.delete(result.getId());
        Assert.assertNull(em.find(EquipoComputoEntity.class, result.getId()));
        
    }
}
