/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.resources;

import co.edu.uniandes.csw.incidencias.dtos.EmpleadoDTO;
import co.edu.uniandes.csw.incidencias.dtos.EmpleadoDetailDTO;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author estudiante
 */
public class EmpleadoResourceTest {
    
    public EmpleadoResourceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createEmpleado method, of class EmpleadoResource.
     */
    @Test
    public void testCreateEmpleado() throws Exception {
        System.out.println("createEmpleado");
        EmpleadoDTO empleado = null;
        EmpleadoResource instance = new EmpleadoResource();
        EmpleadoDTO expResult = null;
        EmpleadoDTO result = instance.createEmpleado(empleado);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmpleados method, of class EmpleadoResource.
     */
    @Test
    public void testGetEmpleados() {
        System.out.println("getEmpleados");
        EmpleadoResource instance = new EmpleadoResource();
        List<EmpleadoDetailDTO> expResult = null;
        List<EmpleadoDetailDTO> result = instance.getEmpleados();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmpleado method, of class EmpleadoResource.
     */
    @Test
    public void testGetEmpleado() {
        System.out.println("getEmpleado");
        Long id = null;
        EmpleadoResource instance = new EmpleadoResource();
        EmpleadoDetailDTO expResult = null;
        EmpleadoDetailDTO result = instance.getEmpleado(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEmpleado method, of class EmpleadoResource.
     */
    @Test
    public void testUpdateEmpleado() throws Exception {
        System.out.println("updateEmpleado");
        Long id = null;
        EmpleadoDetailDTO empleado = null;
        EmpleadoResource instance = new EmpleadoResource();
        EmpleadoDetailDTO expResult = null;
        EmpleadoDetailDTO result = instance.updateEmpleado(id, empleado);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteEmpleado method, of class EmpleadoResource.
     */
    @Test
    public void testDeleteEmpleado() throws Exception {
        System.out.println("deleteEmpleado");
        Long id = null;
        EmpleadoResource instance = new EmpleadoResource();
        instance.deleteEmpleado(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
