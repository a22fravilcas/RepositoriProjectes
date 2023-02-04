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
import static projecteloteria.ProjecteLoteria.TIPUS_PREMIS_SECUNDARIS;
import static projecteloteria.ProjecteLoteria.TOTALPREMIS;

/**
 *
 * @author rexru
 */
public class ProjecteLoteriaTest {
    
    
    static int array_PremisPrincipals[] = new int[TOTALPREMIS];
    
    static final int PREMI_ECONOMIC_GORDO = 400000;
    
    public ProjecteLoteriaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Inici del test de ProjecteLoteria");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Final del test de ProjecteLoteria");
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
    }

    /**
     * Test of NumeroPremiat method, of class ProjecteLoteria.
     */
    @Test
    public void testNumeroPremiat() {        
        System.out.println("NumeroPremiat");
        int array_NumerosPremiats[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiat(array_NumerosPremiats);
    }
    
    /**
     * Test of NumeroPremiatAmanyat method, of class ProjecteLoteria.
     */
    @Test
    public void testNumeroPremiatAmanyat() {        
        System.out.println("NumeroPremiatAmanyat");
        
        //Com tots els premis amanyats estan en ordre, recorrem l'array_NumerosPremiats i comprovem que cada número és igual l'índex de l'array
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        for (int i=0;i<array_NumerosPremiatsAmanyat.length;i++){
            assertEquals(i, array_NumerosPremiatsAmanyat[i]);
        }
    }
    
    /**
     * Test of CompletarPremisPrincipals method, of class ProjecteLoteria.
     */
    @Test
    public void testCompletarPremisPrincipals() {
        System.out.println("CompletarPremisPrincipals");
        ProjecteLoteria.CompletarPremisPrincipals(array_PremisPrincipals);
    }
    
    /**
     * Test of CompletarPremisSecundaris method, of class ProjecteLoteria.
     */
    @Test
    public void testCompletarPremisSecundaris() {
        System.out.println("CompletarPremisSecundaris");
        ProjecteLoteria.PremiSecundari[] array_PremisSecundaris = new ProjecteLoteria.PremiSecundari[TIPUS_PREMIS_SECUNDARIS];
        ProjecteLoteria.CompletarPremisSecundaris(array_PremisSecundaris);
    }
    
    /**
     * Test of TrobarPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testTrobarPremi() {
        System.out.println("TrobarPremi");
        int indexnummatch = 0; //Índex gordo
        int expResult = PREMI_ECONOMIC_GORDO; 
        int result = ProjecteLoteria.TrobarPremi(indexnummatch, array_PremisPrincipals);
        assertEquals(expResult, result);
    }

    /**
     * Test of TrobarNumeroPremiat method, of class ProjecteLoteria.
     */
    @Test
    public void testTrobarNumeroPremiat() {
        System.out.println("TrobarNumeroPremiat");
        int numeroUsuari = 00000; //Provem el gordo del sorteig amanyat, és el dècim 00000
        boolean expResult = true;
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        boolean result = ProjecteLoteria.TrobarNumeroPremiat(array_NumerosPremiatsAmanyat, array_PremisPrincipals, numeroUsuari);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of PrimeresTresXifresGordo method, of class ProjecteLoteria.
     */
    @Test
    public void testPrimeresTresXifresGordo() {
        System.out.println("PrimeresTresXifresGordo");
        int numeroUsuari = 00001; //Provem aquest número per comprovar que detecta les tres primeres xifres del gordo
        boolean expResult = true;
        ProjecteLoteria.PremiSecundari[] array_PremisSecundaris = new ProjecteLoteria.PremiSecundari[TIPUS_PREMIS_SECUNDARIS];
        ProjecteLoteria.CompletarPremisSecundaris(array_PremisSecundaris);
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        boolean result = ProjecteLoteria.PrimeresTresXifresGordo(numeroUsuari, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        assertEquals(expResult, result);
    }

    /**
     * Test of PrimeresTresXifresSegonPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testPrimeresTresXifresSegonPremi() {
        System.out.println("PrimeresTresXifresSegonPremi");
        int numeroUsuari = 00000; //Tornem a provar amb el número del gordo ja que el 00001 és el segon premi i per tan coincideixen les tres primeres xifres
        boolean expResult = true;
        ProjecteLoteria.PremiSecundari[] array_PremisSecundaris = new ProjecteLoteria.PremiSecundari[TIPUS_PREMIS_SECUNDARIS];
        ProjecteLoteria.CompletarPremisSecundaris(array_PremisSecundaris);
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        boolean result = ProjecteLoteria.PrimeresTresXifresSegonPremi(numeroUsuari, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        assertEquals(expResult, result);
    }

    /**
     * Test of PrimeresTresXifresTercerPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testPrimeresTresXifresTercerPremi() {
        System.out.println("PrimeresTresXifresTercerPremi");
        int numeroUsuari = 00000; //Tornem a provar amb el número del gordo ja que el 00002 és el tercer premi i per tan coincideixen les tres primeres xifres
        boolean expResult = true;
        ProjecteLoteria.PremiSecundari[] array_PremisSecundaris = new ProjecteLoteria.PremiSecundari[TIPUS_PREMIS_SECUNDARIS];
        ProjecteLoteria.CompletarPremisSecundaris(array_PremisSecundaris);
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        boolean result = ProjecteLoteria.PrimeresTresXifresTercerPremi(numeroUsuari, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        assertEquals(expResult, result);
    }

    /**
     * Test of PrimeresTresXifresQuartPremi1 method, of class ProjecteLoteria.
     */
    @Test
    public void testPrimeresTresXifresQuartPremi1() {
        System.out.println("PrimeresTresXifresQuartPremi1");
        int numeroUsuari = 00000; //Tornem a provar amb el número del gordo ja que el 00004 és el primer quart premi i per tan coincideixen les tres primeres xifres
        boolean expResult = true;
        ProjecteLoteria.PremiSecundari[] array_PremisSecundaris = new ProjecteLoteria.PremiSecundari[TIPUS_PREMIS_SECUNDARIS];
        ProjecteLoteria.CompletarPremisSecundaris(array_PremisSecundaris);
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        boolean result = ProjecteLoteria.PrimeresTresXifresQuartPremi1(numeroUsuari, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        assertEquals(expResult, result);
    }

    /**
     * Test of PrimeresTresXifresQuartPremi2 method, of class ProjecteLoteria.
     */
    @Test
    public void testPrimeresTresXifresQuartPremi2() {
        System.out.println("PrimeresTresXifresQuartPremi2");
        int numeroUsuari = 00000; //Tornem a provar amb el número del gordo ja que el 00005 és el segon quart premi i per tan coincideixen les tres primeres xifres
        boolean expResult = true;
        ProjecteLoteria.PremiSecundari[] array_PremisSecundaris = new ProjecteLoteria.PremiSecundari[TIPUS_PREMIS_SECUNDARIS];
        ProjecteLoteria.CompletarPremisSecundaris(array_PremisSecundaris);
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        boolean result = ProjecteLoteria.PrimeresTresXifresQuartPremi2(numeroUsuari, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        assertEquals(expResult, result);
    }

    /**
     * Test of UltimesDosXifresGordo method, of class ProjecteLoteria.
     */
    @Test
    public void testUltimesDosXifresGordo() {
        System.out.println("UltimesDosXifresGordo");
        int numeroUsuari = 10000; //Provem aquest número per comprovar que detecta les últimes dos xifres del gordo
        boolean expResult = true;
        ProjecteLoteria.PremiSecundari[] array_PremisSecundaris = new ProjecteLoteria.PremiSecundari[TIPUS_PREMIS_SECUNDARIS];
        ProjecteLoteria.CompletarPremisSecundaris(array_PremisSecundaris);
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        boolean result = ProjecteLoteria.UltimesDosXifresGordo(numeroUsuari, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        assertEquals(expResult, result);
    }

    /**
     * Test of UltimesDosXifresSegonPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testUltimesDosXifresSegonPremi() {
        System.out.println("UltimesDosXifresSegonPremi");
        int numeroUsuari = 10001; //Provem aquest número per comprovar que detecta les últimes dos xifres del segon premi
        boolean expResult = true;
        ProjecteLoteria.PremiSecundari[] array_PremisSecundaris = new ProjecteLoteria.PremiSecundari[TIPUS_PREMIS_SECUNDARIS];
        ProjecteLoteria.CompletarPremisSecundaris(array_PremisSecundaris);
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        boolean result = ProjecteLoteria.UltimesDosXifresSegonPremi(numeroUsuari, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        assertEquals(expResult, result);
    }

    /**
     * Test of UltimesDosXifresTercerPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testUltimesDosXifresTercerPremi() {
        System.out.println("UltimesDosXifresTercerPremi");
        int numeroUsuari = 10002; //Provem aquest número per comprovar que detecta les últimes dos xifres del tercer premi
        boolean expResult = true;
        ProjecteLoteria.PremiSecundari[] array_PremisSecundaris = new ProjecteLoteria.PremiSecundari[TIPUS_PREMIS_SECUNDARIS];
        ProjecteLoteria.CompletarPremisSecundaris(array_PremisSecundaris);
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        boolean result = ProjecteLoteria.UltimesDosXifresTercerPremi(numeroUsuari, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        assertEquals(expResult, result);
    }

    /**
     * Test of UltimaXifraGordo method, of class ProjecteLoteria.
     */
    @Test
    public void testUltimaXifraGordo() {
        System.out.println("UltimaXifraGordo");
        int numeroUsuari = 10000; //Provem aquest número per comprovar que detecta l'última xifra del gordo
        boolean expResult = true;
        ProjecteLoteria.PremiSecundari[] array_PremisSecundaris = new ProjecteLoteria.PremiSecundari[TIPUS_PREMIS_SECUNDARIS];
        ProjecteLoteria.CompletarPremisSecundaris(array_PremisSecundaris);
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        boolean result = ProjecteLoteria.UltimaXifraGordo(numeroUsuari, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        assertEquals(expResult, result);
    }

    /**
     * Test of AproximacioPrimerPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testAproximacioPrimerPremi() {
        System.out.println("AproximacioPrimerPremi");
        //int numeroUsuari1 = 99999; //Aquest és l'aproximació per sota del gordo (00000)
        int numeroUsuari2 = 00001; //Aquest és l'aproximació per sobra del gordo (00000)
        boolean expResult = true;
        ProjecteLoteria.PremiSecundari[] array_PremisSecundaris = new ProjecteLoteria.PremiSecundari[TIPUS_PREMIS_SECUNDARIS];
        ProjecteLoteria.CompletarPremisSecundaris(array_PremisSecundaris);
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        //boolean result1 = ProjecteLoteria.AproximacioPrimerPremi(numeroUsuari1, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        boolean result2 = ProjecteLoteria.AproximacioPrimerPremi(numeroUsuari2, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        //assertEquals(expResult, result1);
        assertEquals(expResult, result2);
    }

    /**
     * Test of AproximacioSegonPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testAproximacioSegonPremi() {
        System.out.println("AproximacioSegonPremi");
        int numeroUsuari1 = 00000; //Aquest és l'aproximació per sota del segon premi (00001)
        int numeroUsuari2 = 00002; //Aquest és l'aproximació per sobra del segon premi (00001)
        boolean expResult = true;
        ProjecteLoteria.PremiSecundari[] array_PremisSecundaris = new ProjecteLoteria.PremiSecundari[TIPUS_PREMIS_SECUNDARIS];
        ProjecteLoteria.CompletarPremisSecundaris(array_PremisSecundaris);
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        boolean result1 = ProjecteLoteria.AproximacioSegonPremi(numeroUsuari1, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        boolean result2 = ProjecteLoteria.AproximacioSegonPremi(numeroUsuari2, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        assertEquals(expResult, result1);
        assertEquals(expResult, result2);
    }

    /**
     * Test of AproximacioSegonPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testAproximacioTercerPremi() {
        System.out.println("AproximacioSegonPremi");
        int numeroUsuari1 = 00000; //Aquest és l'aproximació per sota del segon premi (00001)
        int numeroUsuari2 = 00002; //Aquest és l'aproximació per sobra del segon premi (00001)
        boolean expResult = true;
        ProjecteLoteria.PremiSecundari[] array_PremisSecundaris = new ProjecteLoteria.PremiSecundari[TIPUS_PREMIS_SECUNDARIS];
        ProjecteLoteria.CompletarPremisSecundaris(array_PremisSecundaris);
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        boolean result1 = ProjecteLoteria.AproximacioSegonPremi(numeroUsuari1, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        boolean result2 = ProjecteLoteria.AproximacioSegonPremi(numeroUsuari2, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        assertEquals(expResult, result1);
        assertEquals(expResult, result2);
    }
    
}
