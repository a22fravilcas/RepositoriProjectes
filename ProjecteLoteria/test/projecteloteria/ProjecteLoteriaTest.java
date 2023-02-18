/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package projecteloteria;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static projecteloteria.ProjecteLoteria.PREMI_ACUMULAT;
import static projecteloteria.ProjecteLoteria.TIPUS_PREMIS_SECUNDARIS;
import static projecteloteria.ProjecteLoteria.TOTALPREMIS;
import static projecteloteria.ProjecteLoteria.TrobarNumeroPremiat;
import static projecteloteria.ProjecteLoteria.TrobarPremi;
import static projecteloteria.ProjecteLoteria.indexnummatch;

/**
 *
 * @author rexru
 */
public class ProjecteLoteriaTest {
    
    //CONSTANTS
    public static int array_PremisPrincipals[] = new int[TOTALPREMIS];
    public static final int PREMI_ECONOMIC_GORDO = 400000;
    
    //SORTEIG AMANYAT
    public static final int INDEX_GORDO = 0; //Índex on es troba el gordo en la llista de PremisPrincipals
    public static final int NUMERO_GORDO = 18060;
    public static final int NUMERO_APROXIMACIO_PER_SOTA_GORDO = 18059;
    public static final int NUMERO_APROXIMACIO_PER_SOBRE_GORDO = 18061;
    public static final int NUMERO_APROXIMACIO_PER_SOTA_SEGON_PREMI = 18049;
    public static final int NUMERO_APROXIMACIO_PER_SOBRE_SEGON_PREMI = 18051;
    public static final int NUMERO_TERCER_PREMI = 18040;
    public static final int PREMI_ACUMULAT_NUMERO_TERCER_PREMI = 50120;
    public static final int NUMERO_APROXIMACIO_PER_SOTA_TERCER_PREMI = 18039;
    public static final int NUMERO_APROXIMACIO_PER_SOBRE_TERCER_PREMI = 18041;
    public static final int NUMERO_PROVA_ULTIMES_XIFRES_GORDO = 28060;
    public static final int NUMERO_PROVA_ULTIMES_XIFRES_SEGON_PREMI = 28050;
    public static final int NUMERO_PROVA_ULTIMES_XIFRES_TERCER_PREMI = 28040;
    public static final int NUMERO_NO_PREMIAT = 18033;
    public static final int PREMI_NUMERO_NO_PREMIAT = 0;
    
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
    public void testCompletarPremisSecundaris() throws IOException {
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
        int indexnummatch = INDEX_GORDO;
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
        int numeroUsuari = NUMERO_GORDO; //Provem el gordo del sorteig amanyat, és el dècim 18060
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
    public void testPrimeresTresXifresGordo() throws IOException {
        System.out.println("PrimeresTresXifresGordo");
        int numeroUsuari = NUMERO_APROXIMACIO_PER_SOBRE_GORDO; //Provem aquest número per comprovar que detecta les tres primeres xifres del gordo
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
    public void testPrimeresTresXifresSegonPremi() throws IOException {
        System.out.println("PrimeresTresXifresSegonPremi");
        int numeroUsuari = NUMERO_GORDO; //Tornem a provar amb el número del gordo ja que el 18050 és el segon premi i per tan coincideixen les tres primeres xifres
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
    public void testPrimeresTresXifresTercerPremi() throws IOException {
        System.out.println("PrimeresTresXifresTercerPremi");
        int numeroUsuari = NUMERO_GORDO; //Tornem a provar amb el número del gordo ja que el 18040 és el tercer premi i per tan coincideixen les tres primeres xifres
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
    public void testPrimeresTresXifresQuartPremi1() throws IOException {
        System.out.println("PrimeresTresXifresQuartPremi1");
        int numeroUsuari = NUMERO_GORDO; //Tornem a provar amb el número del gordo ja que el 18030 és el primer quart premi i per tan coincideixen les tres primeres xifres
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
    public void testPrimeresTresXifresQuartPremi2() throws IOException {
        System.out.println("PrimeresTresXifresQuartPremi2");
        int numeroUsuari = NUMERO_GORDO; //Tornem a provar amb el número del gordo ja que el 18020 és el segon quart premi i per tan coincideixen les tres primeres xifres
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
    public void testUltimesDosXifresGordo() throws IOException {
        System.out.println("UltimesDosXifresGordo");
        int numeroUsuari = NUMERO_PROVA_ULTIMES_XIFRES_GORDO; //Provem aquest número per comprovar que detecta les últimes dos xifres del gordo
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
    public void testUltimesDosXifresSegonPremi() throws IOException {
        System.out.println("UltimesDosXifresSegonPremi");
        int numeroUsuari = NUMERO_PROVA_ULTIMES_XIFRES_SEGON_PREMI; //Provem aquest número per comprovar que detecta les últimes dos xifres del segon premi
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
    public void testUltimesDosXifresTercerPremi() throws IOException {
        System.out.println("UltimesDosXifresTercerPremi");
        int numeroUsuari = NUMERO_PROVA_ULTIMES_XIFRES_TERCER_PREMI; //Provem aquest número per comprovar que detecta les últimes dos xifres del tercer premi
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
    public void testUltimaXifraGordo() throws IOException {
        System.out.println("UltimaXifraGordo");
        int numeroUsuari = NUMERO_PROVA_ULTIMES_XIFRES_GORDO; //Provem aquest número per comprovar que detecta l'última xifra del gordo
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
    public void testAproximacioPrimerPremi() throws IOException {
        System.out.println("AproximacioPrimerPremi");
        int numeroUsuari1 = NUMERO_APROXIMACIO_PER_SOTA_GORDO; //Aquest és l'aproximació per sota del gordo (18060)
        int numeroUsuari2 = NUMERO_APROXIMACIO_PER_SOBRE_GORDO; //Aquest és l'aproximació per sobra del gordo (18060)
        boolean expResult = true;
        ProjecteLoteria.PremiSecundari[] array_PremisSecundaris = new ProjecteLoteria.PremiSecundari[TIPUS_PREMIS_SECUNDARIS];
        ProjecteLoteria.CompletarPremisSecundaris(array_PremisSecundaris);
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        boolean result1 = ProjecteLoteria.AproximacioPrimerPremi(numeroUsuari1, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        boolean result2 = ProjecteLoteria.AproximacioPrimerPremi(numeroUsuari2, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        assertEquals(expResult, result1);
        assertEquals(expResult, result2);
    }

    /**
     * Test of AproximacioSegonPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testAproximacioSegonPremi() throws IOException {
        System.out.println("AproximacioSegonPremi");
        int numeroUsuari1 = NUMERO_APROXIMACIO_PER_SOTA_SEGON_PREMI; //Aquest és l'aproximació per sota del segon premi (18050)
        int numeroUsuari2 = NUMERO_APROXIMACIO_PER_SOBRE_SEGON_PREMI; //Aquest és l'aproximació per sobra del segon premi (18050)
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
    public void testAproximacioTercerPremi() throws IOException {
        System.out.println("AproximacioSegonPremi");
        int numeroUsuari1 = NUMERO_APROXIMACIO_PER_SOTA_TERCER_PREMI; //Aquest és l'aproximació per sota del tercer premi (18040)
        int numeroUsuari2 = NUMERO_APROXIMACIO_PER_SOBRE_TERCER_PREMI; //Aquest és l'aproximació per sobra del tercer premi (18040)
        boolean expResult = true;
        ProjecteLoteria.PremiSecundari[] array_PremisSecundaris = new ProjecteLoteria.PremiSecundari[TIPUS_PREMIS_SECUNDARIS];
        ProjecteLoteria.CompletarPremisSecundaris(array_PremisSecundaris);
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        boolean result1 = ProjecteLoteria.AproximacioTercerPremi(numeroUsuari1, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        boolean result2 = ProjecteLoteria.AproximacioTercerPremi(numeroUsuari2, array_NumerosPremiatsAmanyat, array_PremisSecundaris);
        assertEquals(expResult, result1);
        assertEquals(expResult, result2);
    }
    
    /**
     * Test of TrobarPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testCasTercerPremi() {
        //El dècim 18040 és el tercer premi, anem a demostrar que efectivament ho és
        System.out.println("Trobar Tercer Premi");
        PREMI_ACUMULAT = 0;
        int numeroUsuari = NUMERO_TERCER_PREMI;
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        boolean  premiat = TrobarNumeroPremiat(array_NumerosPremiatsAmanyat, array_PremisPrincipals, numeroUsuari);
        int premiTrobat = TrobarPremi(indexnummatch, array_PremisPrincipals);
        int expResult = PREMI_ACUMULAT_NUMERO_TERCER_PREMI;
        int result = PREMI_ACUMULAT;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of TrobarPremi method, of class ProjecteLoteria.
     */
    @Test
    public void testCasNumeroNoPremiat() {
        //El dècim 18040 és el tercer premi, anem a demostrar que efectivament ho és
        System.out.println("Numero no premiat");
        PREMI_ACUMULAT = 0;
        int numeroUsuari = NUMERO_NO_PREMIAT;
        int array_NumerosPremiatsAmanyat[] = new int[TOTALPREMIS];
        ProjecteLoteria.NumeroPremiatAmanyat(array_NumerosPremiatsAmanyat);
        boolean  premiat = TrobarNumeroPremiat(array_NumerosPremiatsAmanyat, array_PremisPrincipals, numeroUsuari);
        int premiTrobat = TrobarPremi(indexnummatch, array_PremisPrincipals);
        int expResult = PREMI_NUMERO_NO_PREMIAT;
        int result = PREMI_ACUMULAT;
        assertEquals(expResult, result);
    }
        
}
