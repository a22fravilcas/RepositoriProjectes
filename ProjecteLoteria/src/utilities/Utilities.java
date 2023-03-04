/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import projecteloteria.Colles;

/**
 * Llibreria d'utilitats
 *
 * @author author
 * @version version
 *
 */
public class Utilities {

// <editor-fold defaultstate="collapsed" desc="Utilitats del Projecte de Loteria">
    private static Scanner scan = null;
    public static final String NOM_FITXER_TEXT = "./datos.txt";
    public static final String NOM_FITXER_BIN = "./datos.bin";
    public static final String PATH_FITXER = "./";
    public static final String EXTENSIONS_FITXER_TXT = ".txt";
    public static final String EXTENSIONS_FITXER_BIN = ".bin";

    public static boolean Sistema() {
        String OS = null;
        boolean result = false;
        if (OS == null) {
            OS = System.getProperty("os.name");
        }

        result = OS.startsWith("Windows");

        return result;
    }

    public static File crearFichero(String nomFitxer) {
        File f = new File(nomFitxer);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return f;
    }

    public static void LeerFichero() {
        // Creamos el enlace con el fichero en el disco
        BufferedReader buf = AbrirFicheroLectura(NOM_FITXER_TEXT, true);

        String linea = LeerLinea(buf);
        while (linea != null) {
            System.out.println(linea);
            linea = LeerLinea(buf);
        }

        CerrarFichero(buf);

    }

    public static String LlegirLineaConcreta(int linea, String nomArxiu) throws IOException {

        String result = "";
        BufferedReader br = AbrirFicheroLectura(nomArxiu, true);
        for (int i = 0; i < linea; ++i) {
            result = br.readLine();
        }
        CerrarFichero(br);
        return result;
    }

    /**
     * Funcion que abre un fichero y, opcionalmente, lo crea si no existe
     *
     * @param nomFitxer Nombre del fichero a abrir
     * @param crear Si lo que queremos crear en el caso que no exista
     * @return File con el fichero que se ha abierto o null si no existe o no se
     * ha podido crear
     */
    public static File AbrirFichero(String nomFitxer, boolean crear) {
        File result = null;

        result = new File(nomFitxer);

        if (!result.exists()) {
            if (crear) {
                try {
                    result.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
                    result = null;
                }
            } else {
                result = null;
            }
        }

        return result;
    }

    /**
     * Abre un fichero para lectura
     *
     * @param nomFitxer Nombre del fichero
     * @param crear Indica si queremos crear el fichero o no, en el caso que no
     * exista
     * @return BufferedReader apuntando al fichero
     */
    public static BufferedReader AbrirFicheroLectura(String nomFitxer, boolean crear) {
        BufferedReader br = null;
        File f = AbrirFichero(nomFitxer, crear);

        if (f != null) {
            // Declarar el reader para poder leer el fichero¡
            FileReader reader;
            try {
                reader = new FileReader(f);
                // Buffered reader para poder leer más comodamente
                br = new BufferedReader(reader);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return br;
    }

    /**
     * Abre un fichero para lectura
     *
     * @param nomFitxer Nombre del fichero
     * @param crear Indica si queremos crear el fichero o no, en el caso que no
     * exista
     * @return BufferedReader apuntando al fichero
     */
    public static PrintWriter AbrirFicheroEscritura(String nomFitxer, boolean crear, boolean blnAnyadir) {
        PrintWriter pw = null;
        File f = AbrirFichero(nomFitxer, crear);

        if (f != null) {
            // Declarar el writer para poder escribir en el fichero¡
            FileWriter writer;
            try {
                writer = new FileWriter(f, blnAnyadir);
                // PrintWriter para poder escribir más comodamente
                pw = new PrintWriter(writer);
            } catch (IOException ex) {
                Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return pw;
    }

    /**
     * Cierra el fichero
     *
     * @param br fichero a cerrar
     */
    public static void CerrarFichero(BufferedReader br) {
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cierra el fichero
     *
     * @param pw fichero a cerrar
     */
    public static void CerrarFichero(PrintWriter pw) {
        pw.flush();
        pw.close();
    }

    /**
     * Lee una linea del fichero
     *
     * @param br BufferedReader con el fichero a leer
     * @return String
     */
    public static String LeerLinea(BufferedReader br) {
        String linea = null;

        try {
            linea = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }

        return linea;
    }

    /**
     * Lee una linea del fichero
     *
     * @param pw PrintWrite con el fichero a leer
     * @linea Linea a escribir
     */
    public static void EscribirLinea(PrintWriter pw, String linea) {
        pw.println(linea);
    }

    public static void EscribirFichero(boolean blnAnyadir) {
        // Creamos el enlace con el fichero en el disco
        PrintWriter pw = AbrirFicheroEscritura(NOM_FITXER_TEXT, true, blnAnyadir);

        String linea = PedirLineaTeclado();
        while (!linea.equals("")) {
            EscribirLinea(pw, linea);
            linea = PedirLineaTeclado();
        }

        CerrarFichero(pw);
    }

    public static String PedirLineaTeclado() {
        return scan.nextLine();
    }

    public static void BorrarFichero(String nomFitxer) {
        File f = new File(nomFitxer);
        f.delete();
    }

    public static void RenombrarFichero(String filename_origen, String filename_final) {
        File f = new File(filename_origen);
        File f2 = new File(filename_final);
        f.renameTo(f2);
    }

    public static DataOutputStream AbrirFicheroEscrituraBinario(String nomFitxer, boolean crear, boolean blnAnyadir) {
        DataOutputStream dos = null;
        File f = AbrirFichero(nomFitxer, crear);

        if (f != null) {
            // Declarar el writer para poder escribir en el fichero¡
            FileOutputStream writer;
            try {
                writer = new FileOutputStream(f, blnAnyadir);
                // PrintWriter para poder escribir más comodamente
                dos = new DataOutputStream(writer);
            } catch (IOException ex) {
                Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return dos;
    }

    public static void CerrarFicheroBinario(DataOutputStream dos) {
        try {
            dos.flush();
            dos.close();
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void CerrarFicheroBinario(DataInputStream dis) {
        try {
            dis.close();
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DataInputStream AbrirFicheroLecturaBinario(String nomFitxer, boolean crear) {
        DataInputStream dis = null;
        File f = AbrirFichero(nomFitxer, crear);

        if (f != null) {
            // Declarar el writer para poder escribir en el fichero¡
            FileInputStream reader;
            try {
                reader = new FileInputStream(f);
                // PrintWriter para poder escribir más comodamente
                dis = new DataInputStream(reader);
            } catch (IOException ex) {
                Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return dis;
    }

    /**
     *
     * @return
     */
    public static int LlegirInt() {
        int result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirInt(scan);

        return result;
    }

    /**
     *
     * @param missatge
     * @return
     */
    public static int LlegirInt(String missatge) {
        int result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirInt(scan, missatge);

        return result;
    }

    /**
     *
     * @param scan
     * @return
     */
    public static int LlegirInt(Scanner scan) {
        return LlegirInt(scan, null);
    }

    /**
     *
     * @param scan
     * @param missatge
     * @param valorMin
     * @param valorMax
     * @return
     */
    public static int LlegirInt(Scanner scan, String missatge, int valorMin, int valorMax) {
        int result = 0;
        do {
            result = LlegirInt(scan, missatge);
        } while (result < valorMin || result > valorMax);

        return result;
    }

    /**
     *
     * @param scan
     * @param missatge
     * @return
     */
    public static int LlegirInt(Scanner scan, String missatge) {
        boolean dadesCorrectes;
        int result = 0;
        do {
            if (missatge != null) {
                System.out.print(missatge);
            }
            dadesCorrectes = scan.hasNextInt();
            if (dadesCorrectes) {
                result = scan.nextInt();
                scan.nextLine();
            } else if (scan.hasNext()) {
                scan.nextLine();
            }
        } while (!dadesCorrectes);

        return result;
    }

    /**
     *
     * @param testnumber
     * @return
     */
    public static int reverseInt(int testnumber) {
        long reversedNum = 0;
        long input_long = testnumber;

        while (input_long != 0) {
            reversedNum = reversedNum * 10 + input_long % 10;
            input_long = input_long / 10;
        }

        if (reversedNum > Integer.MAX_VALUE || reversedNum < Integer.MIN_VALUE) {
            throw new IllegalArgumentException();
        }
        return (int) reversedNum;
    }

    /**
     *
     * @param missatge
     * @return
     */
    public static int demanaNumEnter(String missatge, String error) {
        Scanner scan = new Scanner(System.in);
        int result;
        System.out.print(missatge);

        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println(error);
        }
        result = scan.nextInt();

        //Tornar el resultat de la funció
        return result;
    }

    /**
     *
     * @param missatge
     * @param error
     * @param valorMin
     * @param valorMax
     * @return
     */
    public static int demanaNumEnterAcotat(String missatge, String error, int valorMin, int valorMax) {
        int result = 0;
        do {
            result = demanaNumEnter(missatge, error);
        } while (result < valorMin || result > valorMax);

        return result;
    }

    public static int[] intToArray(int testnumber) {
        int[] intArray;

        String num = Integer.toString(testnumber);

        intArray = new int[num.length()];

        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = num.charAt(i);
        }

        return intArray;
    }

    /**
     * Crea un Menú a partir del scan i un array amb les opcions del menú
     *
     * @param scan Escaner
     * @param array_opcions Array amb les opcions del menú, les quals són
     * Strings
     * @param opcio_escollida Opció escollida per l'usuari
     */
    public static void Menu(Scanner scan, String array_opcions[]) {
        //Imprimeix el títol MENÚ
        System.out.println("******** MENU ********");
        //Recorrem l'array d'opcions per anar imprimint les diferents opcions
        for (int i = 0; i <= array_opcions.length - 1; i++) {
            System.out.println(array_opcions[i]);
        }
        System.out.println(""); //Deixem una línia de separació
        //La registrem cridant LlegirInt()
        Colles.opcio_escollida = LlegirInt(scan, "> ", 1, array_opcions.length);
        System.out.println(""); //Deixem una línia de separació
    }

// </editor-fold>
}
