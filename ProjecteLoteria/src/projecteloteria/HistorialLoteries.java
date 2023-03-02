package projecteloteria;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static projecteloteria.ProjecteLoteria.PathIdioma;

import utilities.*;

public class HistorialLoteries {

    static class PremisLoteria {

        int any;
        int[] array_NumerosPremiats = new int[TOTALPREMIS];
        int[] array_PremisPrincipals = new int[TOTALPREMIS];

    }

    public static String nomFitxer = "Historial_Loteries";
    public static String PathName = Utilities.PATH_FITXER + nomFitxer + Utilities.EXTENSIONS_FITXER_BIN;
    public static File fitxer = new File(PathName);
    public static Scanner scan = new Scanner(System.in);
    public static final int TAMANY_INT = 4;
    public static final int TAMANY_TOTALPREMIS = 4 * 1807;
    public static final int TOTALPREMIS = 1807;
    public static final int FIRST_FOUR_BITS = 4;
    public static final int LIMITNUMEROPREMIAT = 100000;

    public static void main(String[] args) throws FileNotFoundException {
        //linea 34

        int anySorteig = scan.nextInt();

        BuscarPremisLoteria(anySorteig);

    }

    public static void BuscarPremisLoteria(int anyBuscat) {

        try {
            System.out.print(Utilities.LlegirLineaConcreta(41, PathName));
            //"Insereix l'any del sorteig al qual vols accedir:"

            boolean sorteigTrobat = false;
            RandomAccessFile rafLoteria = new RandomAccessFile(PathName, "rw");
            for (int ComptadorSorteigs = 1; ComptadorSorteigs <= rafLoteria.length() / TAMANY_TOTALPREMIS; ComptadorSorteigs++) {

                int posicion_indice = ((ComptadorSorteigs - 1) * TAMANY_TOTALPREMIS) + FIRST_FOUR_BITS;
                rafLoteria.seek(posicion_indice);

                int anySorteig = rafLoteria.readInt();
                if (anySorteig == anyBuscat) {
                    sorteigTrobat = true;
                }

            }
            if (!sorteigTrobat) {
                rafLoteria.seek(rafLoteria.length());
                AfegirSorteig(anyBuscat, rafLoteria);
            } else {
                
            }

            rafLoteria.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HistorialLoteries.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HistorialLoteries.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void AfegirSorteig(int anySorteig, RandomAccessFile rafLoteria) throws FileNotFoundException, IOException {

        PremisLoteria PL = InserirPremisFitxerLoteria(anySorteig, rafLoteria);

    }

    public static PremisLoteria InserirPremisFitxerLoteria(int anySorteig, RandomAccessFile rafLoteria) throws IOException {

        PremisLoteria PL = new PremisLoteria();
        PL.any = anySorteig;
        ProjecteLoteria.NumeroPremiat(PL.array_NumerosPremiats);
        ProjecteLoteria.CompletarPremisPrincipals(PL.array_PremisPrincipals);

        try {
            rafLoteria.writeInt(PL.any);
            for (int i = 0; i < PL.array_NumerosPremiats.length; i++) {
                rafLoteria.writeInt(PL.array_NumerosPremiats[i]);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HistorialLoteries.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HistorialLoteries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return PL;

    }

}
