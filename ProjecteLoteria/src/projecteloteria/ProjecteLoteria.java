package projecteloteria;

import utilities.Utilities;
import java.util.Random;
import java.util.Scanner;

public class ProjecteLoteria {

    static Scanner scan = new Scanner(System.in);
    static Random rndm = new Random();
    static final int TOTALPREMIS = 1807;
    static int indexnummatch;
    static int PREMI_ACUMULAT;

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
        boolean AltreNumero=true; //boolean per permetre al usuari introduir diversos numeros   
        System.out.println(array_NumerosPremiats[0]);//print per poder probar numeros, no estara en versio final
        while(AltreNumero){
        //Crida a funcio externa per verificar el numero de l'usuari
        int numeroUsuari = Utilities.demanaNumEnter("Introdueix el teu numero de loteria. "
                + "El numero ha de ser de cinc digits");
        //Crida a funcio TrobarNumeroPremiat
        boolean NumeroTrobat = TrobarNumeroPremiat(array_NumerosPremiats, numeroUsuari);

        //If per saber si el numero escollit te premi principal.
        if (NumeroTrobat) {
            int premiTrobat = TrobarPremi(indexnummatch, array_Premis);
            System.out.println("Enhorabona, has aconeguit un premi principal. El teu premi es de " + premiTrobat +   "€.");
        } else {
            System.out.println("El teu numero no correspon a cap premi principal.");
        }
        
        boolean UltimaXifraGordo = UltimaXifraGordo(numeroUsuari, array_Premis);
        boolean UltimesDosXifresGordo = UltimesDosXifresGordo(numeroUsuari, array_Premis);
        boolean PrimerTresXifresGordo = PrimeresTresXifresGordo(numeroUsuari, array_Premis);
        boolean PrimerTresXifresSegon = PrimeresTresXifresSegonPremi(numeroUsuari, array_Premis);
        boolean PrimerTresXifresTercer = PrimeresTresXifresTercerPremi(numeroUsuari, array_Premis);
        boolean PrimerTresXifresQuart1 = PrimeresTresXifresQuartPremi1(numeroUsuari, array_Premis);
        boolean PrimerTresXifresQuart2 = PrimeresTresXifresQuartPremi2(numeroUsuari, array_Premis);
        boolean AproxPrim = AproximacioPrimerPremi(numeroUsuari, array_Premis);
        boolean AproxSeg = AproximacioSegonPremi(numeroUsuari, array_Premis);
        boolean AproxTerc = AproximacioTercerPremi(numeroUsuari, array_Premis);
        System.out.println("Enhorabona, has aconseguit :"+PREMI_ACUMULAT+"€");
        System.out.println("Vols desglosar el teu premmi?");
        if (scan.next().equals("Si")){
            if (UltimaXifraGordo)
                System.out.println("Per aconseguir l'ultima xifra del gordo has aconseguit el reintegramnt del teu numero (20€)");
            if (UltimesDosXifresGordo)
                System.out.println("Per aconseguir les dues ultimes xifres del gordo has aconseguit 100€");
            if (PrimerTresXifresGordo)
                System.out.println("Per aconseguir les tres primeres xifres del gordo has aconseguit 100€");
            if (PrimerTresXifresSegon){
                System.out.println("Per aconseguir les tres primeres xifres del segon premi has aconseguit 100€");}
            if (PrimerTresXifresTercer){
                System.out.println("Per aconseguir les tres primeres xifres del tercer premi has aconseguit 100€");}
            if (PrimerTresXifresQuart1){
                System.out.println("Per aconseguir les tres primeres xifres del quart premi has aconseguit 100€");}
            if (PrimerTresXifresQuart2){
                System.out.println("Per aconseguir les tres primeres xifres del quart premi has aconseguit 100€");}
            if (AproxPrim){
                System.out.println("Per aconseguir l'aproximacio del gordo has aconseguir 2000€");}
            if (AproxSeg){
                System.out.println("Per aconseguir l'aproximacio del segon premi has aconseguir 1250");}
            if (AproxTerc){
                System.out.println("Per aconseguir l'aproximacio del tercer premi has aconseguir 960");}
            
        }
        System.out.println("Tens un altre numero?");
        if (scan.next().equals("No")){
            AltreNumero=false;
        }
    }
        scan.close();
    }
    /**
     *
     * @param premis
     */
    //Funcio que genera un array de numeros possibles premiats
    public static void NumeroPremiat(int premis[]) {
        for (int i = 0; i != premis.length; i++) {
            premis[i] = rndm.nextInt(100000);
        }
    }

    /**
     *
     * @param array_Premis
     */
    //Funcio que assigna el valor a l'array de premis WIP
    public static void CompletarPremis(int array_Premis[]) {

        final int TOTAL_PREMIS = 1807;
        final int CINQUE_PREMI = 6000;
        final int PEDREA_PREMI = 1000;
        

        array_Premis[0] = 400000;
        array_Premis[1] = 125000;
        array_Premis[2] = 50000;
        array_Premis[3] = 20000;
        array_Premis[4] = 20000;

        for (int i = 5; i < TOTAL_PREMIS; i++) {
            if (i>=13){
                array_Premis[i] = PEDREA_PREMI;
            }
            else{
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
        PREMI_ACUMULAT+=valorPremi;
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
        int premi=100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[0]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT+=premi;
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
        int premi=100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[1]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT+=premi;
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
        int premi=100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[2]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT+=premi;
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
        int premi=100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[3]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT+=premi;
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
        int premi=100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[4]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT+=premi;
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
        int premi=100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[0]);

        if (ArrayUsuari[4] == primerPremi[4] && ArrayUsuari[3] == primerPremi[3]) {
            xifresIguals = true;
            PREMI_ACUMULAT+=premi;
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
        int premi=100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[1]);

        if (ArrayUsuari[4] == primerPremi[4] && ArrayUsuari[3] == primerPremi[3]) {
            xifresIguals = true;
            PREMI_ACUMULAT+=premi;
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
        int premi=100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[2]);

        if (ArrayUsuari[4] == primerPremi[4] && ArrayUsuari[3] == primerPremi[3]) {
            xifresIguals = true;
            PREMI_ACUMULAT+=premi;
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
        int reintegrament=20;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[0]);

        if (ArrayUsuari[4] == primerPremi[4]) {
            xifraIgual = true;
            PREMI_ACUMULAT+=reintegrament;
        }

        return xifraIgual;
    }
    //Funcio que comproba si el numero del usuari es aproximacio al primer premi (+-1)
    public static boolean AproximacioPrimerPremi (int numeroUsuari, int[] premis){
        boolean result=false;
        int premiAprox=200;
        if ((numeroUsuari+1)==premis[0] || (numeroUsuari-1)==premis[0] ){
            result=true;
            PREMI_ACUMULAT+=premiAprox;
        }
        
        return result;
    }
    //Funcio que comproba si el numero del usuari es aproximacio al segon premi (+-1)
    public static boolean AproximacioSegonPremi (int numeroUsuari, int[] premis){
        boolean result=false;
        int premiAprox=1250;
        if (numeroUsuari+1==premis[1] ||numeroUsuari-1==premis[1] ){
            result=true;
            PREMI_ACUMULAT+=premiAprox;
        }
        
        return result;
     }
    //Funcio que comproba si el numero del usuari es aproximacio al tercer premi (+-1)
    public static boolean AproximacioTercerPremi (int numeroUsuari, int[] premis){
        boolean result=false;
        int premiAprox=960;
        if (numeroUsuari+1==premis[2] ||numeroUsuari-1==premis[2] ){
            result=true;
            PREMI_ACUMULAT+=premiAprox;
        }
        
        return result;
     }  
}

    
