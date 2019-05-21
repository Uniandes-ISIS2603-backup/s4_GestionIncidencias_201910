/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.logic;


import co.edu.uniandes.csw.incidencias.ejb.IncidenciaLogic;
import co.edu.uniandes.csw.incidencias.entities.EmpleadoEntity;
import co.edu.uniandes.csw.incidencias.entities.EquipoComputoEntity;

import co.edu.uniandes.csw.incidencias.entities.IncidenciaEntity;
import co.edu.uniandes.csw.incidencias.entities.TecnicoEntity;
import co.edu.uniandes.csw.incidencias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.incidencias.persistence.EmpleadoPersistence;

import co.edu.uniandes.csw.incidencias.persistence.IncidenciaPersistence;
import co.edu.uniandes.csw.incidencias.persistence.TecnicoPersistence;
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
    private IncidenciaLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    private List<IncidenciaEntity> data = new ArrayList<IncidenciaEntity>();
    
    private List<EmpleadoEntity> empleadoPersistence = new ArrayList<EmpleadoEntity>();
    private List<TecnicoEntity> tecnicoPersistence = new ArrayList<TecnicoEntity>();
    private List<EquipoComputoEntity> equipos = new ArrayList<EquipoComputoEntity>();
    
    
    
    @Inject
    UserTransaction utx;
    
    /**
     * 
     * @return 
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(IncidenciaEntity.class.getPackage())
                .addPackage(IncidenciaLogic.class.getPackage())
                .addPackage(IncidenciaPersistence.class.getPackage())
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
   
     /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from IncidenciaEntity").executeUpdate();
       
    }

    
     private void insertData() {
        for (int i = 0; i < 3; i++) {
            IncidenciaEntity editorial = factory.manufacturePojo(IncidenciaEntity.class);
            em.persist(editorial);
            data.add(editorial);
        }
        
         for (int i = 0; i < 3; i++) {
            EmpleadoEntity entity = factory.manufacturePojo(EmpleadoEntity.class);
            em.persist(entity);
            empleadoPersistence.add(entity);
        }
         
           for (int i = 0; i < 3; i++) {
           TecnicoEntity entity = factory.manufacturePojo(TecnicoEntity.class);
           System.out.println(entity.getId());
           em.persist(entity);
           tecnicoPersistence.add(entity);
        }  
           
         for (int i = 0; i < 3; i++) {
            EquipoComputoEntity entity = factory.manufacturePojo(EquipoComputoEntity.class);
            em.persist(entity);
            equipos.add(entity);
        }
       
    }
     
     /**
     * Prueba para crear un Book
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createIncidenciasTest() throws BusinessLogicException {
        IncidenciaEntity newEntity = factory.manufacturePojo(IncidenciaEntity.class);
        newEntity.setEmpleado(empleadoPersistence.get(0));
        newEntity.setTecnico(tecnicoPersistence.get(0));
        newEntity.setEquipoComputo(equipos.get(0));
        
        
        
        IncidenciaEntity result = logic.createIncidencia(newEntity);
        Assert.assertNotNull(result);
        IncidenciaEntity entity = em.find(IncidenciaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getCalificacion(), entity.getCalificacion());
        Assert.assertEquals(newEntity.getEstado(), entity.getEstado());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

 /**
     * Prueba para consultar la lista de Books.
     */
    @Test
    public void getIncidenciasTest() {
        List<IncidenciaEntity> list = logic.getIncidencias();
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
   
     /**
     * Prueba para consultar un Book.
     */
    @Test
    public void getIncidenciaTest() {
        IncidenciaEntity entity = data.get(0);
        IncidenciaEntity resultEntity = logic.getIncidencia(entity.getId());
        Assert.assertNotNull(resultEntity);      
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getCalificacion(), resultEntity.getCalificacion());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());
        Assert.assertEquals(entity.getEstado(), resultEntity.getEstado());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    /**
     * Actualizar incidencia
     * @throws BusinessLogicException 
     */
   @Test
    public void updateIncidenciaTest() throws BusinessLogicException {
        IncidenciaEntity entity = data.get(0);
        IncidenciaEntity pojoEntity = factory.manufacturePojo(IncidenciaEntity.class);
       
        pojoEntity.setId(entity.getId());
        pojoEntity.setEmpleado(empleadoPersistence.get(0));
        pojoEntity.setTecnico(tecnicoPersistence.get(0));
        pojoEntity.setEquipoComputo(equipos.get(0));
         
        logic.updateIncidencia(pojoEntity.getId(), pojoEntity);

        IncidenciaEntity resp = em.find(IncidenciaEntity.class, entity.getId());
        
        Assert.assertEquals(entity.getId(), resp.getId());
        Assert.assertEquals(entity.getCalificacion(), resp.getCalificacion());
        
        
    }
    
     @Test
    public void deleteIncidenciaTest() throws BusinessLogicException {
        
        IncidenciaEntity entity = data.get(0);
        logic.deleteIncidencia(entity.getId());
        IncidenciaEntity deleted = em.find(IncidenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
    
    }
 

    

