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
    public static final int LIMITNUMEROSPREMIAT = 100000;

    public static void main(String[] args) {
        PathIdioma = EscullirIdioma.ObtenirPath();
        int[] SorteigBuscat = BuscarPremisLoteria();
        System.out.println(SorteigBuscat[0]);
        System.out.println(SorteigBuscat[1]);

    }

    //Funcio de buscador de sorteigs
    public static int[] BuscarPremisLoteria() {
        //Inicialitzar nou array de premis cada busca per si es troba el sorteig escollit
        int[] SorteigEscollit = new int[TOTALPREMIS];

        try {
            System.out.print(Utilities.LlegirLineaConcreta(41, PathIdioma));
            //"Insereix l'any del sorteig al qual vols accedir:"
            int anyBuscat = scan.nextInt();

            //boolean indicador de si s'ha trobat el sorteig o no a l'hora de buscar
            boolean sorteigTrobat = false;
            RandomAccessFile rafLoteria = new RandomAccessFile(PathName, "rw");
            //bucle for que parseja el fitxer de premis en busca del sorteig, buscant per l'any introduit
            for (int ComptadorSorteigs = 1; ComptadorSorteigs <= rafLoteria.length() / TAMANY_TOTALPREMIS && !sorteigTrobat; ComptadorSorteigs++) {

                int posicion_indice = (ComptadorSorteigs - 1) * (TAMANY_TOTALPREMIS + FIRST_FOUR_BITS);
                rafLoteria.seek(posicion_indice);

                int anySorteig = rafLoteria.readInt();
                if (anySorteig == anyBuscat) {
                    sorteigTrobat = true;
                }

            }
            if (!sorteigTrobat) {
                System.out.print(Utilities.LlegirLineaConcreta(46, PathIdioma));
                rafLoteria.seek(rafLoteria.length());
                PremisLoteria PL = AfegirSorteig(anyBuscat, rafLoteria);
                SorteigEscollit = PL.array_NumerosPremiats;
            } else {
                SorteigEscollit = TreureSorteig(anyBuscat, rafLoteria);
                System.out.print(Utilities.LlegirLineaConcreta(45, PathIdioma));

            }

            rafLoteria.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HistorialLoteries.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HistorialLoteries.class.getName()).log(Level.SEVERE, null, ex);
        }

        return SorteigEscollit;
    }

    public static PremisLoteria AfegirSorteig(int anySorteig, RandomAccessFile rafLoteria) throws FileNotFoundException, IOException {

        PremisLoteria PL = InserirRegistreSorteig(anySorteig, rafLoteria);
        
        return PL;

    }

    public static PremisLoteria InserirRegistreSorteig(int anySorteig, RandomAccessFile rafLoteria) throws IOException {

        PremisLoteria PL = new PremisLoteria();
        PL.any = anySorteig;
        ProjecteLoteria.NumeroPremiatAmanyat(PL.array_NumerosPremiats);
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

    public static int[] TreureSorteig(int anyBuscat, RandomAccessFile rafLoteria) throws IOException {

        int[] SorteigEscollit = RegistreSorteigExistent(anyBuscat, rafLoteria);
        return SorteigEscollit;
    }

    public static int[] RegistreSorteigExistent(int anyBuscat, RandomAccessFile rafLoteria) throws IOException {

        int[] SorteigEscollit = new int[TOTALPREMIS];

        for (int i = 0; i < TOTALPREMIS; i++) {
            SorteigEscollit[i] = rafLoteria.readInt();
        }

        return SorteigEscollit;
    }

}
