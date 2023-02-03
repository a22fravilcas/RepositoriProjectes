package projecteloteria;

import utilities.Utilities;
import java.util.Random;
import java.util.Scanner;

public class ProjecteLoteria {

    public static Scanner scan = new Scanner(System.in);
    public static Random rndm = new Random();
    public static final int TOTALPREMIS = 1807;
    public static int indexnummatch;
    public static int PREMI_ACUMULAT;
    public static int NUMERO_PREMIS_AMANYATS = 5; /*Quantitat de números amanysats que implementem
    per fer els jocs de proves. Són els 5 primers premis (encara que hi hagin 4 primers premis,
    hi ha dos números premiats com a quart, de manera que hem de controlar els 5 primers números)*/

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        int array_NumerosPremiats[] = new int[TOTALPREMIS];
        //Crida a funcio NumeroPremiat
        NumeroPremiat(array_NumerosPremiats);
        int array_Premis[] = new int[TOTALPREMIS];
        //Crida a funcio CompletarPremis
        CompletarPremis(array_Premis);
        boolean AltreNumero = true; //boolean per permetre al usuari introduir diversos numeros   
        System.out.println(array_NumerosPremiats[0]);//print per poder probar numeros, no estara en versio final
        while (AltreNumero) {
            boolean premiat = false;
            PREMI_ACUMULAT = 0;
            //Crida a funcio externa per verificar el numero de l'usuari
            int numeroUsuari = Utilities.demanaNumEnter("Introdueix el teu numero de loteria. "
                    + "El numero ha de ser de cinc digits: ");
            //Crida a funcio TrobarNumeroPremiat
            boolean NumeroTrobat = TrobarNumeroPremiat(array_NumerosPremiats, numeroUsuari);

            //If per saber si el numero escollit te premi principal.
            if (NumeroTrobat) {
                premiat = true;
                int premiTrobat = TrobarPremi(indexnummatch, array_Premis);
                System.out.println("Enhorabona, has aconeguit un premi principal. El teu premi es de " + premiTrobat + "€.");
            } else {
                System.out.println("El teu numero no correspon a cap premi principal.");
            }
            boolean AproxSeg = false, AproxTerc = false, PrimerTresXifresTercer = false, PrimerTresXifresQuart1 = false,
                    PrimerTresXifresQuart2 = false, UltimaXifraGordo = false, UltimesDosXifresGordo = false;

            boolean PrimeresTresXifresGordo = PrimeresTresXifresGordo(numeroUsuari, array_NumerosPremiats);
            if (PrimeresTresXifresGordo == false) {
                UltimesDosXifresGordo = UltimesDosXifresGordo(numeroUsuari, array_NumerosPremiats);
                if (UltimesDosXifresGordo == false) {
                    UltimaXifraGordo = UltimaXifraGordo(numeroUsuari, array_NumerosPremiats);
                }
            }

            boolean PrimeresTresXifresSegon = PrimeresTresXifresSegonPremi(numeroUsuari, array_NumerosPremiats);
            if (PrimeresTresXifresSegon == false) {
                PrimerTresXifresTercer = PrimeresTresXifresTercerPremi(numeroUsuari, array_NumerosPremiats);
                if (PrimerTresXifresTercer == false) {
                    PrimerTresXifresQuart1 = PrimeresTresXifresQuartPremi1(numeroUsuari, array_NumerosPremiats);
                    PrimerTresXifresQuart2 = PrimeresTresXifresQuartPremi2(numeroUsuari, array_NumerosPremiats);
                }
            }

            boolean AproxPrim = AproximacioPrimerPremi(numeroUsuari, array_NumerosPremiats);
            if (AproxPrim == false) {
                AproxSeg = AproximacioSegonPremi(numeroUsuari, array_NumerosPremiats);
                if (AproxSeg == false) {
                    AproxTerc = AproximacioTercerPremi(numeroUsuari, array_NumerosPremiats);
                }
            }

            if (premiat == false) {
                System.out.println("Enhorabona, has aconseguit :" + PREMI_ACUMULAT + "€");
                System.out.println("Vols desglosar el teu premmi?");
                if (scan.next().equals("Si")) {
                    if (UltimaXifraGordo) {
                        System.out.println("Per aconseguir l'ultima xifra del gordo has aconseguit el reintegrament del teu numero (20€)");
                    }
                    if (UltimesDosXifresGordo) {
                        System.out.println("Per aconseguir les dues ultimes xifres del gordo has aconseguit 100€");
                    }
                    if (PrimeresTresXifresGordo) {
                        System.out.println("Per aconseguir les tres primeres xifres del gordo has aconseguit 100€");
                    }
                    if (PrimeresTresXifresSegon) {
                        System.out.println("Per aconseguir les tres primeres xifres del segon premi has aconseguit 100€");
                    }
                    if (PrimerTresXifresTercer) {
                        System.out.println("Per aconseguir les tres primeres xifres del tercer premi has aconseguit 100€");
                    }
                    if (PrimerTresXifresQuart1) {
                        System.out.println("Per aconseguir les tres primeres xifres del quart premi has aconseguit 100€");
                    }
                    if (PrimerTresXifresQuart2) {
                        System.out.println("Per aconseguir les tres primeres xifres del quart premi has aconseguit 100€");
                    }
                    if (AproxPrim) {
                        System.out.println("Per aconseguir l'aproximacio del gordo has aconseguir 2000€");
                    }
                    if (AproxSeg) {
                        System.out.println("Per aconseguir l'aproximacio del segon premi has aconseguir 1250€");
                    }
                    if (AproxTerc) {
                        System.out.println("Per aconseguir l'aproximacio del tercer premi has aconseguir 960€");
                    }
                }

            }
            System.out.println("Tens un altre numero?");
            if (scan.next().equals("No")) {
                AltreNumero = false;
            }
        }
        scan.close();
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
            for (int j=0;j<numeros_premiats.length;j++){
                if (numeros_premiats[j]==numero_a_afegir){
                    repetit = true;
                }
            }
            while (repetit == true){
                numero_a_afegir = rndm.nextInt(100000);
                repetit = false;
                for (int j=0;j<numeros_premiats.length;j++){
                    if (numeros_premiats[j] == numero_a_afegir) {
                        repetit = true;
                    }
                }
            }
            numeros_premiats[i] = numero_a_afegir;
        }
    }
    
    public static void NumeroPremiatAmanyat(int numeros_premiats[]) {
        /*Fem un for en els 5 primers premis per tindre'ls amanyats de cara als jocs de proves.
        Implementem que aquests números siguin els matsixos que l "i" del for i així ens simplifica
        el programa
        */
        for (int i=0;i<numeros_premiats.length;i++){
            if (i<NUMERO_PREMIS_AMANYATS){
                numeros_premiats[i] = i;
            }
            else{
                //Repetim el procés per afegir randoms com en la funció anterior
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
    }   

    /**
     *
     * @param array_Premis
     */
    //Funcio que assigna el valor a l'array de premis WIP
    public static void CompletarPremis(int array_Premis[]) {

        final int CINQUE_PREMI = 6000;
        final int PEDREA_PREMI = 1000;

        array_Premis[0] = 400000;
        array_Premis[1] = 125000;
        array_Premis[2] = 50000;
        array_Premis[3] = 20000;
        array_Premis[4] = 20000;

        for (int i = 5; i < TOTALPREMIS; i++) {
            if (i >= 13) {
                array_Premis[i] = PEDREA_PREMI;
            } else {
                array_Premis[i] = CINQUE_PREMI;
            }
        }

    }

    /**
     *
     * @param array_NumerosPremiats
     * @param numeroUsuari
     * @return
     */
    //Funcio que busca si el numero de l'usuari correspon a un dels numeros premiats
    public static boolean TrobarNumeroPremiat(int array_NumerosPremiats[], int numeroUsuari) {

        boolean result = false;
        int i = 0;

        while (result == false && i < TOTALPREMIS) {

            if (array_NumerosPremiats[i] == numeroUsuari) {
                result = true;
                indexnummatch = i;
            }

            i++;
        }

        return result;
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
     * @param numeroUsuari
     * @param premis
     * @return
     */
    //Funcio que comprova si les tres ultimes xifres del numero de l'usuari son iguals a les del gordo
    public static boolean PrimeresTresXifresGordo(int numeroUsuari, int[] premis) {
        boolean xifresIguals = false;
        int premi = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[0]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT += premi;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @return
     */
    //Funcio que comprova si les tres ultimes xifres del numero de l'usuari son iguals a les del segon premi
    public static boolean PrimeresTresXifresSegonPremi(int numeroUsuari, int[] premis) {
        boolean xifresIguals = false;
        int premi = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[1]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT += premi;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @return
     */
    //Funcio que comprova si les tres ultimes xifres del numero de l'usuari son iguals a les del segon premi
    public static boolean PrimeresTresXifresTercerPremi(int numeroUsuari, int[] premis) {
        boolean xifresIguals = false;
        int premi = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[2]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT += premi;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @return
     */
    //Funcio que comprova si les tres ultimes xifres del numero de l'usuari son iguals a les del segon premi
    public static boolean PrimeresTresXifresQuartPremi1(int numeroUsuari, int[] premis) {
        boolean xifresIguals = false;
        int premi = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[3]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT += premi;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @return
     */
    //Funcio que comprova si les tres ultimes xifres del numero de l'usuari son iguals a les del segon premi
    public static boolean PrimeresTresXifresQuartPremi2(int numeroUsuari, int[] premis) {
        boolean xifresIguals = false;
        int premi = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[4]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT += premi;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @return
     */
    //Funcio que comprova si les dues ultimes xifres del numero de l'usuari son iguals a les del gordo
    public static boolean UltimesDosXifresGordo(int numeroUsuari, int[] premis) {
        boolean xifresIguals = false;
        int premi = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[0]);

        if (ArrayUsuari[4] == primerPremi[4] && ArrayUsuari[3] == primerPremi[3]) {
            xifresIguals = true;
            PREMI_ACUMULAT += premi;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @return
     */
    //Funcio que comprova si les dues ultimes xifres del numero de l'usuari son iguals a les del gordo
    public static boolean UltimesDosXifresSegonPremi(int numeroUsuari, int[] premis) {
        boolean xifresIguals = false;
        int premi = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[1]);

        if (ArrayUsuari[4] == primerPremi[4] && ArrayUsuari[3] == primerPremi[3]) {
            xifresIguals = true;
            PREMI_ACUMULAT += premi;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @return
     */
    //Funcio que comprova si les dues ultimes xifres del numero de l'usuari son iguals a les del gordo
    public static boolean UltimesDosXifresTercerPremi(int numeroUsuari, int[] premis) {
        boolean xifresIguals = false;
        int premi = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[2]);

        if (ArrayUsuari[4] == primerPremi[4] && ArrayUsuari[3] == primerPremi[3]) {
            xifresIguals = true;
            PREMI_ACUMULAT += premi;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @return
     */
    //Funcio que comprova si l'ultima xifra del numero de l'usuari es igual al del gordo
    public static boolean UltimaXifraGordo(int numeroUsuari, int[] premis) {
        boolean xifraIgual = false;
        int reintegrament = 20;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[0]);

        if (ArrayUsuari[4] == primerPremi[4]) {
            xifraIgual = true;
            PREMI_ACUMULAT += reintegrament;
        }

        return xifraIgual;
    }

    //Funcio que comproba si el numero del usuari es aproximacio al primer premi (+-1)
    public static boolean AproximacioPrimerPremi(int numeroUsuari, int[] premis) {
        boolean result = false;
        int premiAprox = 2000;
        if (numeroUsuari == premis[0]+1 || numeroUsuari == premis[0]-1) {
            result = true;
            PREMI_ACUMULAT += premiAprox;
        }

        return result;
    }

    //Funcio que comproba si el numero del usuari es aproximacio al segon premi (+-1)
    public static boolean AproximacioSegonPremi(int numeroUsuari, int[] premis) {
        boolean result = false;
        int premiAprox = 1250;
        if (numeroUsuari + 1 == premis[1] || numeroUsuari - 1 == premis[1]) {
            result = true;
            PREMI_ACUMULAT += premiAprox;
        }

        return result;
    }

    //Funcio que comproba si el numero del usuari es aproximacio al tercer premi (+-1)
    public static boolean AproximacioTercerPremi(int numeroUsuari, int[] premis) {
        boolean result = false;
        int premiAprox = 960;
        if (numeroUsuari + 1 == premis[2] || numeroUsuari - 1 == premis[2]) {
            result = true;
            PREMI_ACUMULAT += premiAprox;
        }

        return result;
    }
}
