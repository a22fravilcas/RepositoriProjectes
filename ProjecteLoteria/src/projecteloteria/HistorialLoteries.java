package projecteloteria;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import utilities.*;

public class HistorialLoteries {

    static class PremisLoteria {

        int[] array_PremisPrincipals;

    }

    public static String nomFitxer;
    public static String PathName = Utilities.PATH_FITXER + nomFitxer + Utilities.EXTENSIONS_FITXER_BIN;
    public static File fitxer = new File(PathName);
    public static Scanner scan = new Scanner(System.in);
    public static final int TAMANY_INT = 4;
    public static final int TAMANY_TOTALPREMIS = 4 * 1807;
    public static final int TOTALPREMIS = 1807;

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Insereix l'any al que vols accedir");
        int anySorteig = scan.nextInt();
        BuscarPremisLoteria(anySorteig);

    }

    public static void BuscarPremisLoteria(int numero) {

        try {

            int posicion_indice = (numero - 1) * TAMANY_TOTALPREMIS;
            RandomAccessFile raf = new RandomAccessFile(PathName, "rw");
            raf.seek(posicion_indice);
            int posicion_datos = raf.readInt();
            raf.close();

            RandomAccessFile rafLoteria = new RandomAccessFile(PathName, "rw");
            rafLoteria.seek(posicion_datos);

            PremisLoteria PL = InserirPremisFitxerLoteria(rafLoteria);
            rafLoteria.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HistorialLoteries.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HistorialLoteries.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static PremisLoteria InserirPremisFitxerLoteria(RandomAccessFile rafLoteria) throws IOException {

        PremisLoteria PL = new PremisLoteria();

        PL.array_PremisPrincipals = new int[TOTALPREMIS];

        try {
            PL.array_PremisPrincipals[0] = rafLoteria.readInt();
            PL.array_PremisPrincipals[1] = rafLoteria.readInt();
            PL.array_PremisPrincipals[2] = rafLoteria.readInt();
            PL.array_PremisPrincipals[3] = rafLoteria.readInt();
            PL.array_PremisPrincipals[4] = rafLoteria.readInt();
            int premiPedrea = rafLoteria.readInt();
            int cinquePremi = rafLoteria.readInt();

            for (int i = 5; i < TOTALPREMIS; i++) { //bucle per assignar els premis quue es repeteixen en molts numeros
                if (i >= 13) {
                    PL.array_PremisPrincipals[i] = premiPedrea;
                } else {
                    PL.array_PremisPrincipals[i] = cinquePremi;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HistorialLoteries.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HistorialLoteries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return PL;

    }
}
