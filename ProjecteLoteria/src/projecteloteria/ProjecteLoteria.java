package projecteloteria;

import utilities.Utilities;
import java.util.Random;
import java.util.Scanner;

public class ProjecteLoteria {

    static Scanner scan = new Scanner(System.in);
    static Random rndm = new Random();
    static final int TOTALPREMIS = 1807;
    static int indexnummatch;

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

        //Crida a funcio externa per verificar el numero de l'usuari
        int numeroUsuari = Utilities.demanaNumEnter("Introdueix el teu numero de loteria. "
                + "El numero ha de ser de cinc digits");

        //Crida a funcio TrobarNumeroPremiat
        boolean NumeroTrobat = TrobarNumeroPremiat(array_NumerosPremiats, numeroUsuari);

        //If per saber si el numero escollit te premi principal.
        if (NumeroTrobat) {
            long premiTrobat = TrobarPremi(indexnummatch, array_Premis);
            System.out.println("Enhorabona, has aconeguit un premi principal. El teu premi es de " + premiTrobat + " €.");
        } else {
            System.out.println("El teu numero no correspon a cap premi principal.");
        }
    }

    /**
     *
     * @param premis
     */
    //Funcio que genera un array de numeros possibles premiats
    public static void NumeroPremiat(int premis[]) {
        for (int i = 0; i != premis.length; i++) {
            premis[i] = rndm.nextInt(99999);
        }
    }

    /**
     *
     * @param array_Premis
     */
    //Funcio que assigna el valor a l'array de premis WIP
    public static void CompletarPremis(int array_Premis[]) {

        final int TOTALPADREO = 1807;
        final int PREMIOPADREO = 1000;

        array_Premis[0] = 4000000;
        array_Premis[1] = 1250000;
        array_Premis[2] = 500000;
        array_Premis[3] = 200000;
        array_Premis[4] = 200000;
        array_Premis[5] = 60000;
        array_Premis[6] = 60000;
        array_Premis[7] = 60000;
        array_Premis[8] = 60000;
        array_Premis[9] = 60000;
        array_Premis[10] = 60000;
        array_Premis[11] = 60000;
        array_Premis[12] = 60000;

        for (int i = 13; i < TOTALPADREO; i++) {
            array_Premis[i] = PREMIOPADREO;
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

        return valorPremi;
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

        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[0]);

        if (ArrayUsuari[5] == primerPremi[5]) {
            xifraIgual = true;
        }

        return xifraIgual;
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

        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[0]);

        if (ArrayUsuari[5] == primerPremi[5] && ArrayUsuari[4] == primerPremi[4]) {
            xifresIguals = true;
        }

        return xifresIguals;
    }
    
    /**
     * 
     * @param numeroUsuari
     * @param premis
     * @return 
     */
    //Funcio que comprova si les tres ultimes xifres del numero de l'usuari son iguals a les del gordo
    public static boolean UltimesTresXifresGordo(int numeroUsuari, int[] premis) {
        boolean xifresIguals = false;

        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[0]);

        if (ArrayUsuari[5] == primerPremi[5]
                && ArrayUsuari[4] == primerPremi[4]
                && ArrayUsuari[3] == primerPremi[3]) {
            xifresIguals = true;
        }

        return xifresIguals;
    }
    
    public static boolean UltimesTresXifresSegonPremi(int numeroUsuari, int[] premis) {
        boolean xifresIguals = false;

        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);

        int[] primerPremi = Utilities.intToArray(premis[1]);

        if (ArrayUsuari[5] == primerPremi[5]
                && ArrayUsuari[4] == primerPremi[4]
                && ArrayUsuari[3] == primerPremi[3]) {
            xifresIguals = true;
        }

        return xifresIguals;
    }
}
