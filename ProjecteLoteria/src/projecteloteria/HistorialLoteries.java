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
        int[] array_PremisPrincipals;

    }

    public static String nomFitxer = "Historial_Loteries";
    public static String PathName = Utilities.PATH_FITXER + nomFitxer + Utilities.EXTENSIONS_FITXER_BIN;
    public static File fitxer = new File(PathName);
    public static Scanner scan = new Scanner(System.in);
    public static final int TAMANY_INT = 4;
    public static final int TAMANY_TOTALPREMIS = 4 * 1807;
    public static final int TOTALPREMIS = 1807;
    public static final int FIRST_FOUR_BITS = 4;

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Insereix l'any del sorteig al qual vols accedir:");
        int anySorteig = scan.nextInt();
        BuscarPremisLoteria(anySorteig);

    }

    public static void BuscarPremisLoteria(int anyBuscat) {

        try {
            boolean sorteigTrobat = false;
            RandomAccessFile rafLoteria = new RandomAccessFile(PathName, "rw");
            for (int ComptadorSorteigs = 0; ComptadorSorteigs < rafLoteria.length() / TAMANY_TOTALPREMIS; ComptadorSorteigs++) {

                int posicion_indice = ((ComptadorSorteigs - 1) * TAMANY_TOTALPREMIS) + FIRST_FOUR_BITS;
                rafLoteria.seek(posicion_indice);

                int anySorteig = rafLoteria.readInt();
                if (anySorteig == anyBuscat) {
                    sorteigTrobat = true;
                }

            }
            if (!sorteigTrobat) {
                AfegirSorteig(rafLoteria);
            } else {
                
            }
            
            rafLoteria.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HistorialLoteries.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HistorialLoteries.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void AfegirSorteig(RandomAccessFile rafLoteria) throws FileNotFoundException, IOException {

        rafLoteria = new RandomAccessFile(PathName, "rw");
        PremisLoteria PL = InserirPremisFitxerLoteria(rafLoteria);

    }

    public static PremisLoteria InserirPremisFitxerLoteria(RandomAccessFile rafLoteria) throws IOException {

        PremisLoteria PL = new PremisLoteria();

        PL.array_PremisPrincipals = new int[TOTALPREMIS];

        try {
            PL.any = rafLoteria.readInt();
            PL.array_PremisPrincipals[0] = rafLoteria.readInt();
            PL.array_PremisPrincipals[1] = rafLoteria.readInt();
            PL.array_PremisPrincipals[2] = rafLoteria.readInt();
            PL.array_PremisPrincipals[3] = rafLoteria.readInt();
            PL.array_PremisPrincipals[4] = rafLoteria.readInt();
            int premiPedrea = rafLoteria.readInt();
            int cinquePremi = rafLoteria.readInt();

            for (int i = 5; i < TOTALPREMIS; i++) { //bucle per assignar els premis que es repeteixen en molts numeros
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
    
    public static void TreurePremisSorteig(){
        
    }
}
