package projecteloteria;

import utilities.Utilities;
import java.util.Random;
import java.util.Scanner;

public class ProjecteLoteria {

    public static Scanner scan = new Scanner(System.in);
    public static Random rndm = new Random();
    public static final int TOTALPREMIS = 1807;
    public static final int TIPUS_PREMIS_SECUNDARIS = 12; //Nombre de diferents tipus de premis secundaris que hi ha
    public static int indexnummatch;
    public static int PREMI_ACUMULAT;
    public static int NUMERO_PREMIS_AMANYATS = 5;

    /*Quantitat de números amanysats que implementem
    per fer els jocs de proves. Són els 5 primers premis (encara que hi hagin 4 primers premis,
    hi ha dos números premiats com a quart, de manera que hem de controlar els 5 primers números)*/
    //Creem una classe per poder fer registres dels premis secundaris, amb el nom del premi, la quantitat que toca i si toca o no
    public static class PremiSecundari {

        String nom;
        boolean toca;
        String missatge_premi;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        int array_NumerosPremiats[] = new int[TOTALPREMIS];
        //Crida a funcio NumeroPremiat
        NumeroPremiat(array_NumerosPremiats);
        int[] array_PremisPrincipals = new int[TOTALPREMIS];
        PremiSecundari[] array_PremisSecundaris = new PremiSecundari[TIPUS_PREMIS_SECUNDARIS];
        //Crida a funcio CompletarPremisPrincipals i CompleatarPremisSecundaris
        CompletarPremisPrincipals(array_PremisPrincipals);
        CompletarPremisSecundaris(array_PremisSecundaris);
        boolean AltreNumero = true; //boolean per permetre al usuari introduir diversos numeros   
        System.out.println(array_NumerosPremiats[0]);
        System.out.println(array_NumerosPremiats[1]);//print per poder probar numeros, no estara en versio final
        while (AltreNumero) {
            boolean premiat = false;
            PREMI_ACUMULAT = 0;
            //Crida a funcio externa per verificar el numero de l'usuari
            int numeroUsuari = Utilities.demanaNumEnter("Introdueix el teu numero de loteria. "
                    + "El numero ha de ser de cinc digits: ");
            //Crida a funcio TrobarNumeroPremiat
            premiat = TrobarNumeroPremiat(array_NumerosPremiats, array_PremisPrincipals, numeroUsuari);
            if (premiat){
                int premiTrobat = TrobarPremi(indexnummatch, array_PremisPrincipals);
                System.out.println("Enhorabona, has aconeguit un premi principal. El teu premi es de " + premiTrobat + "€.");
            }
            else{
               System.out.println("El teu numero no correspon a cap premi principal.");
            }
            //If per saber si el numero escollit te premi principal.
            boolean AproxSeg = false, AproxTerc = false, PrimerTresXifresTercer = false, PrimerTresXifresQuart1 = false,
                    PrimerTresXifresQuart2 = false, UltimaXifraGordo = false, UltimesDosXifresGordo = false;

            TresXifresGordo(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris,
                    UltimesDosXifresGordo, UltimaXifraGordo);

            PrimTresXifSegon(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris,
                    PrimerTresXifresTercer, PrimerTresXifresQuart1, PrimerTresXifresQuart2);

             AproxPrim(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris, AproxSeg, AproxTerc);

            

            DesglosarPremi(array_PremisSecundaris);

            
            System.out.println("Tens un altre numero?");
            if (scan.next().equals("No")) {
                AltreNumero = false;
            }
        }
        scan.close();
    }

    /**
     *
     * @param numeroUsuari
     * @param array_NumerosPremiats
     * @param array_PremisSecundaris
     * @param UltimesDosXifresGordo
     * @param UltimaXifraGordo
     * @return
     */
    public static void TresXifresGordo(int numeroUsuari, int[] array_NumerosPremiats, PremiSecundari[] array_PremisSecundaris,
            boolean UltimesDosXifresGordo, boolean UltimaXifraGordo) {
        if (numeroUsuari!=array_NumerosPremiats[0]){
            boolean PrimeresTresXifresGordo = PrimeresTresXifresGordo(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
            if (PrimeresTresXifresGordo == false) {
                UltimesDosXifresGordo = UltimesDosXifresGordo(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
                if (UltimesDosXifresGordo == false) {
                    UltimaXifraGordo = UltimaXifraGordo(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
                }
            }
        }
    }

    /**
     *
     * @param numeroUsuari
     * @param array_NumerosPremiats
     * @param array_PremisSecundaris
     * @param PrimerTresXifresTercer
     * @param PrimerTresXifresQuart1
     * @param PrimerTresXifresQuart2
     * @return
     */
    public static void PrimTresXifSegon(int numeroUsuari, int[] array_NumerosPremiats, PremiSecundari[] array_PremisSecundaris,
            boolean PrimerTresXifresTercer, boolean PrimerTresXifresQuart1, boolean PrimerTresXifresQuart2) {
        if (numeroUsuari!=array_NumerosPremiats[1]){
            boolean PrimeresTresXifresSegon = PrimeresTresXifresSegonPremi(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
            if (PrimeresTresXifresSegon == false && numeroUsuari!=array_NumerosPremiats[2] ) {
                PrimerTresXifresTercer = PrimeresTresXifresTercerPremi(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
                if (PrimerTresXifresTercer == false && numeroUsuari!=array_NumerosPremiats[3]) {
                    PrimerTresXifresQuart1 = PrimeresTresXifresQuartPremi1(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
                    if (PrimerTresXifresQuart1 == false && numeroUsuari!=array_NumerosPremiats[4]) {
                         PrimerTresXifresQuart2 = PrimeresTresXifresQuartPremi2(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
                    }
                }
            }

        }
    }

    /**
     *
     * @param numeroUsuari
     * @param array_NumerosPremiats
     * @param array_PremisSecundaris
     * @param AproxSeg
     * @param AproxTerc
     * @return
     */
    public static void AproxPrim(int numeroUsuari, int[] array_NumerosPremiats, PremiSecundari[] array_PremisSecundaris, boolean AproxSeg, boolean AproxTerc) {

        boolean AproxPrim = AproximacioPrimerPremi(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
        if (AproxPrim == false) {
            AproxSeg = AproximacioSegonPremi(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
            if (AproxSeg == false) {
                AproxTerc = AproximacioTercerPremi(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
            }
        }

    }

    /**
     *
     * @param numeros_premiats
     */
    //Funcio que genera un array de numeros possibles premiats
    public static void NumeroPremiat(int numeros_premiats[]) {
        for (int i = 0; i != numeros_premiats.length; i++) {
            /*While per a què els números s'afegeixen mentre no estiguin repetits. Per a això,
            utilizem un boolean per saber si és repetit o no i recorrem l'array de premis*/
            boolean repetit = false;
            int numero_a_afegir = rndm.nextInt(100000);
            for (int j = 0; j < numeros_premiats.length; j++) {
                if (numeros_premiats[j] == numero_a_afegir) {
                    repetit = true;
                }
            }
            while (repetit == true) {
                numero_a_afegir = rndm.nextInt(100000);
                repetit = false;
                for (int j = 0; j < numeros_premiats.length; j++) {
                    if (numeros_premiats[j] == numero_a_afegir) {
                        repetit = true;
                    }
                }
            }
            numeros_premiats[i] = numero_a_afegir;
        }
    }

    public static void NumeroPremiatAmanyat(int numeros_premiats[]) {
        //Fiquem tots els premis amanyats en ordre, així és més fàcil
        for (int i = numeros_premiats.length-1; i >=0; i++) {
            numeros_premiats[i] = i;
        }
    }

    /**
     *
     * @param array_PremisPrincipals
     */
    //Funcio que assigna el valor a l'array de premis WIP
    public static void CompletarPremisPrincipals(int[] array_PremisPrincipals) {

        final int CINQUE_PREMI = 6000;
        final int PEDREA_PREMI = 1000;

        array_PremisPrincipals[0] = 400000;
        array_PremisPrincipals[1] = 125000;
        array_PremisPrincipals[2] = 50000;
        array_PremisPrincipals[3] = 20000;
        array_PremisPrincipals[4] = 20000;

        for (int i = 5; i < TOTALPREMIS; i++) {
            if (i >= 13) {
                array_PremisPrincipals[i] = PEDREA_PREMI;
            } else {
                array_PremisPrincipals[i] = CINQUE_PREMI;
            }
        }

    }

    public static void CompletarPremisSecundaris(PremiSecundari[] array_PremisSecundaris) {
        //Primerament recorrem l'array de premis secundaris per crear cada objecte de la classe
        for (int i = 0; i < array_PremisSecundaris.length; i++) {
            array_PremisSecundaris[i] = new PremiSecundari();
        }
        //Ara afegim tots els registres dels premis secundaris
        array_PremisSecundaris[0].nom = "AproximacioPrimerPremi";
        array_PremisSecundaris[0].missatge_premi = "Per aconseguir l'aproximacio del gordo has aconseguir 2000€";
        array_PremisSecundaris[1].nom = "AproximacioSegonPremi";
        array_PremisSecundaris[1].missatge_premi = "Per aconseguir l'aproximacio del segon premi has aconseguir 1250€";
        array_PremisSecundaris[2].nom = "AproximacioTercerPremi";
        array_PremisSecundaris[2].missatge_premi = "Per aconseguir l'aproximacio del tercer premi has aconseguir 960€";
        array_PremisSecundaris[3].nom = "PrimeresTresXifresGordo";
        array_PremisSecundaris[3].missatge_premi = "Per aconseguir les tres primeres xifres del gordo has aconseguit 100€";
        array_PremisSecundaris[4].nom = "PrimeresTresXifresSegonPremi";
        array_PremisSecundaris[4].missatge_premi = "Per aconseguir les tres primeres xifres del segon premi has aconseguit 100€";
        array_PremisSecundaris[5].nom = "PrimeresTresXifresTercerPremi";
        array_PremisSecundaris[5].missatge_premi = "Per aconseguir les tres primeres xifres del tercer premi has aconseguit 100€";
        array_PremisSecundaris[6].nom = "PrimeresTresXifresQuartPremi1";
        array_PremisSecundaris[6].missatge_premi = "Per aconseguir les tres primeres xifres d'un quart premi has aconseguit 100€";
        array_PremisSecundaris[7].nom = "PrimeresTresXifresQuartPremi2";
        array_PremisSecundaris[7].missatge_premi = "Per aconseguir les tres primeres xifres d'un quart premi has aconseguit 100€";
        array_PremisSecundaris[8].nom = "UltimesDosXifresGordo";
        array_PremisSecundaris[8].missatge_premi = "Per aconseguir les dues ultimes xifres del gordo has aconseguit 100€";
        array_PremisSecundaris[9].nom = "UltimesDosXifresSegonPremi";
        array_PremisSecundaris[9].missatge_premi = "Per aconseguir les dues ultimes xifres del segon premi has aconseguit 100€";
        array_PremisSecundaris[10].nom = "UltimesDosXifresTercerPremi";
        array_PremisSecundaris[10].missatge_premi = "Per aconseguir les dues ultimes xifres del tercer premi has aconseguit 100€";
        array_PremisSecundaris[11].nom = "UltimaXifraGordo";
        array_PremisSecundaris[11].missatge_premi = "Per aconseguir l'ultima xifra del gordo has aconseguit el reintegrament del teu numero (20€)";

        //Ara recorrem la porció de l'array entre PrimeresTresXifresGordo i UltimesDosXifresTercer premi per afegir el premi de 100€
    }

    /**
     *
     * @param indexnummatch
     * @param premis
     * @return
     */
    //Funcio que troba el premi corresponent al numero premiat
    public static int TrobarPremi(int indexnummatch, int[] premis) {

        int valorPremi = premis[indexnummatch];
        PREMI_ACUMULAT += valorPremi;
        return valorPremi;
    }

    /**
     *
     * @param array_NumerosPremiats
     * @param array_Premis
     * @param numeroUsuari
     */
    //Funcio que busca si el numero de l'usuari correspon a un dels numeros premiats
    public static boolean TrobarNumeroPremiat(int array_NumerosPremiats[], int array_Premis[], int numeroUsuari) {
        boolean result = false;
        int i = 0;
        while (result == false && i < TOTALPREMIS) {
            if (array_NumerosPremiats[i] == numeroUsuari) {
                result = true;
                indexnummatch = i; 
            }
            i++;
        }
        //Si result segueix sent false vol dir que no ha trobat cap premi principal
      
        return result;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si les tres ultimes xifres del numero de l'usuari son iguals a les del gordo
    public static boolean PrimeresTresXifresGordo(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifresIguals = false;
        int premi = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[0]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT += premi;
            array_PremisSecundaris[3].toca = true;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si les tres ultimes xifres del numero de l'usuari son iguals a les del segon premi
    public static boolean PrimeresTresXifresSegonPremi(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifresIguals = false;
        int premi = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[1]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT += premi;
            array_PremisSecundaris[4].toca = true;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si les tres ultimes xifres del numero de l'usuari son iguals a les del segon premi
    public static boolean PrimeresTresXifresTercerPremi(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifresIguals = false;
        int premi = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[2]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT += premi;
            array_PremisSecundaris[5].toca = true;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si les tres ultimes xifres del numero de l'usuari son iguals a les del segon premi
    public static boolean PrimeresTresXifresQuartPremi1(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifresIguals = false;
        int premi = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[3]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT += premi;
            array_PremisSecundaris[6].toca = true;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si les tres ultimes xifres del numero de l'usuari son iguals a les del segon premi
    public static boolean PrimeresTresXifresQuartPremi2(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifresIguals = false;
        int premi = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[4]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT += premi;
            array_PremisSecundaris[6].toca = true;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si les dues ultimes xifres del numero de l'usuari son iguals a les del gordo
    public static boolean UltimesDosXifresGordo(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifresIguals = false;
        int premi = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[0]);

        if (ArrayUsuari[4] == primerPremi[4] && ArrayUsuari[3] == primerPremi[3]) {
            xifresIguals = true;
            PREMI_ACUMULAT += premi;
            array_PremisSecundaris[7].toca = true;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si les dues ultimes xifres del numero de l'usuari son iguals a les del gordo
    public static boolean UltimesDosXifresSegonPremi(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifresIguals = false;
        int premi = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[1]);

        if (ArrayUsuari[4] == primerPremi[4] && ArrayUsuari[3] == primerPremi[3]) {
            xifresIguals = true;
            PREMI_ACUMULAT += premi;
            array_PremisSecundaris[8].toca = true;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si les dues ultimes xifres del numero de l'usuari son iguals a les del gordo
    public static boolean UltimesDosXifresTercerPremi(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifresIguals = false;
        int premi = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[2]);

        if (ArrayUsuari[4] == primerPremi[4] && ArrayUsuari[3] == primerPremi[3]) {
            xifresIguals = true;
            PREMI_ACUMULAT += premi;
            array_PremisSecundaris[9].toca = true;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si l'ultima xifra del numero de l'usuari es igual al del gordo
    public static boolean UltimaXifraGordo(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifraIgual = false;
        int reintegrament = 20;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[0]);

        if (ArrayUsuari[4] == primerPremi[4]) {
            xifraIgual = true;
            PREMI_ACUMULAT += reintegrament;
            array_PremisSecundaris[10].toca = true;
        }

        return xifraIgual;
    }

    //Funcio que comproba si el numero del usuari es aproximacio al primer premi (+-1)
    public static boolean AproximacioPrimerPremi(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean result = false;
        int premiAprox = 2000;
        int aproximacio_primer_premi_per_sota = premis[0] - 1;
        int aproximacio_primer_premi_per_sobra = premis[0] + 1;
        if (premis[0] == 00000) {
            aproximacio_primer_premi_per_sota = 99999;
        } else if (premis[0] == 99999) {
            aproximacio_primer_premi_per_sobra = 00000;
        }

        if (numeroUsuari == aproximacio_primer_premi_per_sota || numeroUsuari == aproximacio_primer_premi_per_sobra) {
            result = true;
            PREMI_ACUMULAT += premiAprox;
            array_PremisSecundaris[0].toca = true;
        }

        return result;
    }

    //Funcio que comproba si el numero del usuari es aproximacio al segon premi (+-1)
    public static boolean AproximacioSegonPremi(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean result = false;
        int premiAprox = 1250;
        int aproximacio_segon_premi_per_sota = premis[1] - 1;
        int aproximacio_segon_premi_per_sobra = premis[1] + 1;
        if (premis[1] == 00000) {
            aproximacio_segon_premi_per_sota = 99999;
        } else if (premis[1] == 99999) {
            aproximacio_segon_premi_per_sobra = 00000;
        }

        if (numeroUsuari == aproximacio_segon_premi_per_sota || numeroUsuari == aproximacio_segon_premi_per_sobra) {
            result = true;
            PREMI_ACUMULAT += premiAprox;
            array_PremisSecundaris[1].toca = true;
        }

        return result;
    }

    //Funcio que comproba si el numero del usuari es aproximacio al tercer premi (+-1)
    public static boolean AproximacioTercerPremi(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean result = false;
        int premiAprox = 960;
        int aproximacio_tercer_premi_per_sota = premis[2] - 1;
        int aproximacio_tercer_premi_per_sobra = premis[2] + 1;
        if (premis[2] == 00000) {
            aproximacio_tercer_premi_per_sota = 99999;
        } else if (premis[0] == 99999) {
            aproximacio_tercer_premi_per_sobra = 00000;
        }

        if (numeroUsuari == aproximacio_tercer_premi_per_sota || numeroUsuari == aproximacio_tercer_premi_per_sobra) {
            result = true;
            PREMI_ACUMULAT += premiAprox;
            array_PremisSecundaris[0].toca = true;
        }

        return result;
    }

    public static void DesglosarPremi(PremiSecundari[] array_PremisSecundaris) {
        System.out.println("Enhorabona, has aconseguit :" + PREMI_ACUMULAT + "€");
        System.out.println("Vols desglosar el teu premi?");
        boolean noSecundaris = true;
        if (scan.next().equals("Si")) {
            //Recorrem el array de premis secundaris i anem imprimint els premis que han tocat
            for (int i = 0; i < array_PremisSecundaris.length; i++) {
                if (array_PremisSecundaris[i].toca) {
                    noSecundaris = false;
                    System.out.println(array_PremisSecundaris[i].missatge_premi);
                }
            }
            if (noSecundaris) {
                System.out.println("No tens ningun premi secundari");
            }
        }
    }
}

