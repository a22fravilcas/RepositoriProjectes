package projecteloteria;

import java.io.*;
import java.util.*;
import utilities.*;

public class Projecte_UF3 {

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
            Utilities.AbrirFicheroEscrituraBinario(nomFitxer, true, true);
            
        }

    }
    
    

}
