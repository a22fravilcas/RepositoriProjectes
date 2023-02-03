/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package projecteloteria;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rexru
 */
public class ProjecteLoteriaTest {
    
    public ProjecteLoteriaTest() {
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
     * Test of main method, of class ProjecteLoteria.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ProjecteLoteria.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of NumeroPremiat method, of class ProjecteLoteria.
     */
    @Test
    public void testNumeroPremiat() {
        System.out.println("NumeroPremiat");
        int[] premis = new int [1807];
        ProjecteLoteria.NumeroPremiat(premis);
        int longitud_premis = 1807;
        int resultat = premis.length;
        // TODO review the generated test code and remove the default call to fail.
        assertEquals("Longitut buida", longitud_premis, resultat);
    }

    /**
     * Test of CompletarPremis method, of class ProjecteLoteria.
     */
    @Test
    public void testCompletarPremis() {
        System.out.println("CompletarPremis");
        int[] array_Premis = new int [1807];
        ProjecteLoteria.CompletarPremis(array_Premis);
        // TODO review the generated test code and remove the default call to fail.
      
    }

    /**
     * Test of TrobarNumeroPremiat method, of class ProjecteLoteria.
     */
    @Test
    public void testTrobarNumeroPremiat() {
        System.out.println("TrobarNumeroPremiat");
        int[] premis = new int [1807];
        ProjecteLoteria.NumeroPremiat(premis);
        int numeroUsuari = 0;
        boolean expResult = false;
        boolean result = ProjecteLoteria.TrobarNumeroPremiat(premis, numeroUsuari);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of TrobarPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testTrobarPremi() {
        System.out.println("TrobarPremi");
        int indexnummatch = 0;
        int[] premis = null;
        int expResult = 0;
        int result = ProjecteLoteria.TrobarPremi(indexnummatch, premis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of PrimeresTresXifresGordo method, of class ProjecteLoteria.
     */
    @Test
    public void testPrimeresTresXifresGordo() {
        System.out.println("PrimeresTresXifresGordo");
        int numeroUsuari = 0;
        int[] premis = null;
        boolean expResult = false;
        boolean result = ProjecteLoteria.PrimeresTresXifresGordo(numeroUsuari, premis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of PrimeresTresXifresSegonPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testPrimeresTresXifresSegonPremi() {
        System.out.println("PrimeresTresXifresSegonPremi");
        int numeroUsuari = 0;
        int[] premis = null;
        boolean expResult = false;
        boolean result = ProjecteLoteria.PrimeresTresXifresSegonPremi(numeroUsuari, premis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of PrimeresTresXifresTercerPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testPrimeresTresXifresTercerPremi() {
        System.out.println("PrimeresTresXifresTercerPremi");
        int numeroUsuari = 0;
        int[] premis = null;
        boolean expResult = false;
        boolean result = ProjecteLoteria.PrimeresTresXifresTercerPremi(numeroUsuari, premis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of PrimeresTresXifresQuartPremi1 method, of class ProjecteLoteria.
     */
    @Test
    public void testPrimeresTresXifresQuartPremi1() {
        System.out.println("PrimeresTresXifresQuartPremi1");
        int numeroUsuari = 0;
        int[] premis = null;
        boolean expResult = false;
        boolean result = ProjecteLoteria.PrimeresTresXifresQuartPremi1(numeroUsuari, premis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of PrimeresTresXifresQuartPremi2 method, of class ProjecteLoteria.
     */
    @Test
    public void testPrimeresTresXifresQuartPremi2() {
        System.out.println("PrimeresTresXifresQuartPremi2");
        int numeroUsuari = 0;
        int[] premis = null;
        boolean expResult = false;
        boolean result = ProjecteLoteria.PrimeresTresXifresQuartPremi2(numeroUsuari, premis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of UltimesDosXifresGordo method, of class ProjecteLoteria.
     */
    @Test
    public void testUltimesDosXifresGordo() {
        System.out.println("UltimesDosXifresGordo");
        int numeroUsuari = 0;
        int[] premis = null;
        boolean expResult = false;
        boolean result = ProjecteLoteria.UltimesDosXifresGordo(numeroUsuari, premis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of UltimesDosXifresSegonPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testUltimesDosXifresSegonPremi() {
        System.out.println("UltimesDosXifresSegonPremi");
        int numeroUsuari = 0;
        int[] premis = null;
        boolean expResult = false;
        boolean result = ProjecteLoteria.UltimesDosXifresSegonPremi(numeroUsuari, premis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of UltimesDosXifresTercerPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testUltimesDosXifresTercerPremi() {
        System.out.println("UltimesDosXifresTercerPremi");
        int numeroUsuari = 0;
        int[] premis = null;
        boolean expResult = false;
        boolean result = ProjecteLoteria.UltimesDosXifresTercerPremi(numeroUsuari, premis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of UltimaXifraGordo method, of class ProjecteLoteria.
     */
    @Test
    public void testUltimaXifraGordo() {
        System.out.println("UltimaXifraGordo");
        int numeroUsuari = 0;
        int[] premis = null;
        boolean expResult = false;
        boolean result = ProjecteLoteria.UltimaXifraGordo(numeroUsuari, premis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AproximacioPrimerPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testAproximacioPrimerPremi() {
        System.out.println("AproximacioPrimerPremi");
        int numeroUsuari = 0;
        int[] premis = null;
        boolean expResult = false;
        boolean result = ProjecteLoteria.AproximacioPrimerPremi(numeroUsuari, premis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AproximacioSegonPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testAproximacioSegonPremi() {
        System.out.println("AproximacioSegonPremi");
        int numeroUsuari = 0;
        int[] premis = null;
        boolean expResult = false;
        boolean result = ProjecteLoteria.AproximacioSegonPremi(numeroUsuari, premis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AproximacioTercerPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testAproximacioTercerPremi() {
        System.out.println("AproximacioTercerPremi");
        int numeroUsuari = 0;
        int[] premis = null;
        boolean expResult = false;
        boolean result = ProjecteLoteria.AproximacioTercerPremi(numeroUsuari, premis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
