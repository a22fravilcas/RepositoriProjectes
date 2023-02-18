package projecteloteria;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import utilities.*;

public class HistorialLoteries {

    public static String nomFitxer;
    public static String pathName = Utilities.PATH_FITXER + nomFitxer + Utilities.EXTENSIONS_FITXER_BIN;
    public static File fitxer = new File(pathName);
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        int anySorteig = scan.nextInt();
        HistorialSorteig(anySorteig);
    }

    public static void HistorialSorteig(int anySorteig) {

        nomFitxer = (anySorteig) + "";
        if (!fitxer.exists()) {
            Utilities.crearFichero(nomFitxer);
        } else {
            PremisFitxerLoteria();

        }

    }

    public static void PremisFitxerLoteria() {

        DataOutputStream dos = Utilities.AbrirFicheroEscrituraBinario(nomFitxer, false, true);

    }

    public static void InserirPremisFitxerLoteria(DataOutputStream dos, int[] array_PremisPrincipals) throws IOException {

        int CINQUE_PREMI = 0;
        int PEDREA_PREMI = 0;
        int PRIMERPREMI = 0;
        int SEGONPREMI = 0;
        int TERCERPREMI = 0;
        int QUARTICINQUEPREMI = 0;
        final int TOTALPREMIS = 1807;

        dos.writeInt(array_PremisPrincipals[0] = PRIMERPREMI);
        dos.writeInt(array_PremisPrincipals[1] = SEGONPREMI
        );
        dos.writeInt(array_PremisPrincipals[2] = TERCERPREMI
        );
        dos.writeInt(array_PremisPrincipals[3] = QUARTICINQUEPREMI
        );
        dos.writeInt(array_PremisPrincipals[4] = QUARTICINQUEPREMI
        );

        for (int i = 5; i < TOTALPREMIS; i++) { //bucle per assignar els premis quue es repeteixen en molts numeros
            if (i >= 13) {
                dos.writeInt(array_PremisPrincipals[i] = PEDREA_PREMI);
            } else {
                dos.writeInt(array_PremisPrincipals[i] = CINQUE_PREMI);
            }
        }
    }

}
