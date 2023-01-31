/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package projecteloteria;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ausias
 */
public class ProjecteLoteriaTest {
    
    public ProjecteLoteriaTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        System.out.println("Inici del test de ProjecteLoteria");
    }
    
    @AfterAll
    public static void tearDownClass() {
        System.out.println("Final del test de ProjecteLoteria");
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
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
        int[] premis = null;
        ProjecteLoteria.NumeroPremiat(premis);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CompletarPremis method, of class ProjecteLoteria.
     */
    @Test
    public void testCompletarPremis() {
        System.out.println("CompletarPremis");
        int[] array_Premis = null;
        ProjecteLoteria.CompletarPremis(array_Premis);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of TrobarNumeroPremiat method, of class ProjecteLoteria.
     */
    @Test
    public void testTrobarNumeroPremiat() {
        System.out.println("TrobarNumeroPremiat");
        int[] array_NumerosPremiats = null;
        int numeroUsuari = 0;
        boolean expResult = false;
        boolean result = ProjecteLoteria.TrobarNumeroPremiat(array_NumerosPremiats, numeroUsuari);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
