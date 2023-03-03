/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecteloteria;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import static projecteloteria.ProjecteLoteria.PREMI_ACUMULAT;
import static projecteloteria.ProjecteLoteria.PathIdioma;
import static projecteloteria.ProjecteLoteria.TOTALPREMIS;
import static projecteloteria.ProjecteLoteria.TrobarPremi;
import static projecteloteria.ProjecteLoteria.array_PremisPrincipals;
import static projecteloteria.ProjecteLoteria.indexnummatch;
import utilities.Utilities;

/**
 *
 * @author Cèlia Garcia, Wilson McCammond i Franc Villaba
 */
public class Colles {
    
    public static Scanner scan = new Scanner(System.in);
    
    //Classe Colla
    public static class Colla{
        String nom;
        int any;
        int numero_membres;
        int import_colla;
        int premi_colla;
    }
    
    //Classe Membre
    public static class Membre{
        String nom;
        int numero_loteria;
        int import_membre;
        int premi_membre;
    }
    
    //CONSTANTS
    public static final String NOM_FITXER_HISTORIAL = "./Historial_Loteria.bin";
    public static final String NOM_FITXER_COLLES = "./fitxer_colles.bin";
    public static final String NOM_FITXER_COLLES_REMPLAÇ = "./fitxer_colles_nou.bin";
    public static final String NOM_FITXER_NOMS_COLLES = "./fitxer_noms_colles.bin";
    public static final int IMPORT_MINIM = 5; //Import mínim que pot aportar un membre
    public static final int IMPORT_MAXIM = 60; //Import màxim que pot aportar un membre
    public static final int MAXIM_NUMERO_LOTERIA = 99999; //Número de loteria més gran
    public static final int DIVISOR_5 = 5; /*Divisor que utilitzem per comprovar que l'import
    d'un membre és múltiple de 5*/
    public static final String FORMAT_DADES = "%-12s"; 
    public static final int NUMERO_ATRIBUTS = 4; //Número d'atributs a imprimir de les colles i els membres
    public static final String [] OPCIONS_MENU_COLLES = {"1.Afegir colla","2.Modificar colla","3.Mostrar colles","4.Sortir"};
    public static final String [] OPCIONS_MENU_MODIFICAR_COLLA = {"1.Afegir membre"};
    
    public static int opcio_escollida;
    public static int numero_colles = 0;
    public static int numero_membres_colla_actual = 0; /*Representa el número de membres que té la colla la qual s'està imprimint
    en el seu instant, per saber quants membres s'han d'imprimir*/
    
    public static void main(String[] args) throws IOException {
        //linea 50
        Utilities.Menu(scan, OPCIONS_MENU_COLLES);
        CridarOpcionsMenuColles();
        
        /*RandomAccessFile raf = new RandomAccessFile(NOM_FITXER_COLLES,"rw");
        raf.seek(107);
        raf.writeUTF("hola");
        raf.close();*/
        
    }
    
    public static void CridarOpcionsMenuColles () throws IOException{
        while (opcio_escollida!=4){
            switch (opcio_escollida) {
                case 1:
                    Colla colla = DemanarCollaNova();
                    Membre membre = DemanarMembre(colla);
                    CompletarColla(colla, membre);
                    EscriureColla(colla);
                    EscriureMembre(membre,NOM_FITXER_COLLES);
                    break;
                case 2:
                    Utilities.Menu(scan, OPCIONS_MENU_MODIFICAR_COLLA);
                    CridarOpcionsMenuModificarColla();
                    break;
                case 3:
                    ComptarColles();
                    ImprimirFitxerColles();
                    break;
                default:
                    break;
            }
            System.out.println(""); //Deixem una línia de separació
            Utilities.Menu(scan, OPCIONS_MENU_COLLES);
        }
        
        
    }
    
     public static void CridarOpcionsMenuModificarColla () throws IOException{
        switch (opcio_escollida){
            case 1:
                ComptarColles();
                Colla colla = DemanarCollaExistent();
                Membre membre = DemanarMembre(colla);
                AfegirMembreModificarColla(colla,membre);
                break;           
        }
        
    }

    
       
    public static void ComptarColles (){
        DataInputStream dis = Utilities.AbrirFicheroLecturaBinario(NOM_FITXER_NOMS_COLLES, true);
        String linia = LlegirLiniaNomsColles(dis);
        while (linia!=null){
            numero_colles++;
            LlegirLiniaNomsColles(dis);
            linia = LlegirLiniaNomsColles(dis);
        }
        Utilities.CerrarFicheroBinario(dis);
    }
    
    public static Colla DemanarCollaNova () throws IOException{
        Colla colla = new Colla();
        System.out.print(Utilities.LlegirLineaConcreta(34, "./idiomas/catala.txt"));
        //Nom de la colla: 
        colla.nom = scan.nextLine();
        //Imposem amb un while que el nom no estigui repetit
        boolean nom_validat = ValidarNomNou(colla.nom);
        while (!nom_validat){
            System.out.print(Utilities.LlegirLineaConcreta(34, "./idiomas/catala.txt"));
            //Nom de la colla: 
            colla.nom = scan.nextLine();
            nom_validat = ValidarNomNou(colla.nom);
        }
        colla.any = Utilities.LlegirInt(scan,Utilities.LlegirLineaConcreta(35, "./idiomas/catala.txt"));
        boolean any_validat = ValidarAnyNou(colla.nom,Integer.toString(colla.any));
        while (!any_validat){
            colla.any = Utilities.LlegirInt(scan,Utilities.LlegirLineaConcreta(35, "./idiomas/catala.txt"));
            any_validat = ValidarAnyNou(colla.nom,Integer.toString(colla.any));
        }
        //Any del sorteig: 
        //Retornem la colla
        return colla;
    }
    
    public static int LlegirAnyHistorial (RandomAccessFile raf){
        int any_sorteig;
        try {
            any_sorteig = raf.readInt();
        } catch (IOException ex) {
            any_sorteig = -1;
        }
        //Retornem la línia
        return any_sorteig;
    }
    
    public static boolean ValidarAnySorteig (int any_sorteig) throws FileNotFoundException, IOException{
        RandomAccessFile raf = new RandomAccessFile(NOM_FITXER_HISTORIAL, "r");
        int any = LlegirAnyHistorial(raf);
        boolean any_validat=false;
        while (any!=-1){
            if (any_sorteig==any){
                any_validat=true;
            }
            raf.seek(raf.getFilePointer()+3615);
            any=LlegirAnyHistorial(raf);
        }
        raf.close();
        return any_validat;
    }
    
    public static Colla DemanarCollaExistent () throws IOException{
        Colla colla = new Colla();
        System.out.print(Utilities.LlegirLineaConcreta(34, "./idiomas/catala.txt"));
        //Nom de la colla: 
        colla.nom = scan.nextLine();
        //Imposem amb un while que el nom no estigui repetit
        boolean nom_validat = ValidarNomExistent(colla.nom);
        while (!nom_validat){
            System.out.print(Utilities.LlegirLineaConcreta(34, "./idiomas/catala.txt"));
            //Nom de la colla: 
            colla.nom = scan.nextLine();
            nom_validat = ValidarNomExistent(colla.nom);
        }
        colla.any = Utilities.LlegirInt(scan,Utilities.LlegirLineaConcreta(35, "./idiomas/catala.txt"));
        boolean any_validat = ValidarAnyExistent(colla.nom,Integer.toString(colla.any));
        while (!any_validat){
            colla.any = Utilities.LlegirInt(scan,Utilities.LlegirLineaConcreta(35, "./idiomas/catala.txt"));
            any_validat = ValidarAnyExistent(colla.nom,Integer.toString(colla.any));
        }
        //Any del sorteig: 
        //Retornem la colla
        return colla;
    }
    
    public static String LlegirLiniaNomsColles (DataInputStream dis){
        String linia;
        try {
            linia = dis.readUTF();
        } catch (IOException ex) {
            linia = null;
        }
        //Retornem la línia
        return linia;
    }
    
    public static boolean ValidarNomNou (String nom_colla) throws IOException{
        DataInputStream dis = Utilities.AbrirFicheroLecturaBinario(NOM_FITXER_NOMS_COLLES, true);
        boolean nom_validat = true;
        String linia = LlegirLiniaNomsColles(dis);
        while (linia!=null){
            if (nom_colla.equals(linia)){
                nom_validat = false;
            }
            linia = LlegirLiniaNomsColles(dis);
        }
        Utilities.CerrarFicheroBinario(dis);
        return nom_validat;
    }
    
    public static boolean ValidarAnyNou (String nom_colla, String any_colla) throws IOException{
        DataInputStream dis = Utilities.AbrirFicheroLecturaBinario(NOM_FITXER_NOMS_COLLES, true);
        boolean any_validat = true;
        String nom = LlegirLiniaNomsColles(dis);
        while (nom!=null){
            if (nom_colla.equals(nom)){
                String any = LlegirLiniaNomsColles(dis);
                if (any.equals(any_colla)){
                    any_validat = false;
                }             
            }
            nom = LlegirLiniaNomsColles(dis);
        }
        Utilities.CerrarFicheroBinario(dis);
        return any_validat;
    }
    
    public static boolean ValidarNomExistent (String nom_colla) throws IOException{
        DataInputStream dis = Utilities.AbrirFicheroLecturaBinario(NOM_FITXER_NOMS_COLLES, true);
        boolean nom_validat = false;
        String linia = LlegirLiniaNomsColles(dis);
        while (linia!=null){
            if (nom_colla.equals(linia)){
                nom_validat = true;
            }
            linia = LlegirLiniaNomsColles(dis);
        }
        Utilities.CerrarFicheroBinario(dis);
        return nom_validat;
    }
    
    public static boolean ValidarAnyExistent (String nom_colla, String any_colla) throws IOException{
        DataInputStream dis = Utilities.AbrirFicheroLecturaBinario(NOM_FITXER_NOMS_COLLES, true);
        boolean any_validat = false;
        String nom = LlegirLiniaNomsColles(dis);
        while (nom!=null){
            if (nom_colla.equals(nom)){
                String any = LlegirLiniaNomsColles(dis);
                if (any.equals(any_colla)){
                    any_validat = true;
                }             
            }
            nom = LlegirLiniaNomsColles(dis);
        }
        Utilities.CerrarFicheroBinario(dis);
        return any_validat;
    }
    
    public static Membre DemanarMembre (Colla colla) throws IOException{
        Membre membre = new Membre();
        System.out.print(Utilities.LlegirLineaConcreta(36, "./idiomas/catala.txt"));
        //Nom: 
        membre.nom = scan.nextLine();
        membre.numero_loteria = Utilities.LlegirInt(scan, Utilities.LlegirLineaConcreta(37, "./idiomas/catala.txt"), 0, MAXIM_NUMERO_LOTERIA);        
        //Número de Loteria: 
        membre.import_membre = Utilities.LlegirInt(scan, Utilities.LlegirLineaConcreta(38, "./idiomas/catala.txt"), IMPORT_MINIM, IMPORT_MAXIM);
        //Import: 
        
        //Imposem amb un while que l'import també sigui múltiple de 5
        while (membre.import_membre%DIVISOR_5 != 0){
            membre.import_membre = Utilities.LlegirInt(scan, "Import: ", IMPORT_MINIM, IMPORT_MAXIM);
        }
        //membre.premi_membre = 
        //Retornem el membre
        return membre;
    }
    
    public static int BuscarPremi (Colla colla, Membre membre) throws FileNotFoundException, IOException{
        RandomAccessFile raf = new RandomAccessFile(NOM_FITXER_HISTORIAL, "r");
        int any = LlegirAnyHistorial(raf);
        while (any!=-1){
            if (colla.any==any){
                int[] array_NumerosPremiats = new int [TOTALPREMIS];
                for (int i=0;i<TOTALPREMIS;i++){
                    array_NumerosPremiats[i]=raf.readInt();
                }
                PREMI_ACUMULAT = 0;
                if (premiat) {
                    int premiTrobat = TrobarPremi(indexnummatch, array_PremisPrincipals);
            }
            raf.seek(raf.getFilePointer()+3615);
            any=LlegirAnyHistorial(raf);
        }
    }
    
    public static void AfegirMembreModificarColla (Colla colla, Membre membre) throws IOException{
        DataInputStream dis1 = Utilities.AbrirFicheroLecturaBinario(NOM_FITXER_NOMS_COLLES, true);
        DataInputStream dis2 = Utilities.AbrirFicheroLecturaBinario(NOM_FITXER_COLLES, true);
        DataOutputStream dos = Utilities.AbrirFicheroEscrituraBinario(NOM_FITXER_COLLES_REMPLAÇ, true, true);
        //Recorrem el numero de colles per anar imprimint tota la informació de cada colla
        for (int i=1;i<=numero_colles;i++){
            String nom_colla = dis1.readUTF();
            String any_colla = dis1.readUTF();
            dos.writeUTF(String.format(FORMAT_DADES, any_colla));
            for (int j = 1; j <= NUMERO_ATRIBUTS; j++) {
                if (j == 1){
                    dis2.readUTF(); 
                } 
                else{
                    String dada_afegir = dis2.readUTF(); 
                    if (j == 2) {
                        numero_membres_colla_actual = Integer.parseInt(dada_afegir.trim());
                        if (nom_colla.contains(colla.nom) && any_colla.equals(Integer.toString(colla.any))){ 
                            dada_afegir = String.format(FORMAT_DADES, Integer.toString(numero_membres_colla_actual + 1));
                        }                    
                    }
                    if (j == 3) {
                        if (nom_colla.contains(colla.nom) && any_colla.equals(Integer.toString(colla.any))){ 
                            dada_afegir = String.format(FORMAT_DADES, Integer.toString(Integer.parseInt(dada_afegir.trim()) + membre.import_membre));
                        }                    
                    }
                    dos.writeUTF(dada_afegir);
                }              
            }     
            for (int k = 1; k <= numero_membres_colla_actual; k++) {
                for (int l = 1; l <= NUMERO_ATRIBUTS; l++) {
                    dos.writeUTF(dis2.readUTF());
                }
            }
            if (nom_colla.contains(colla.nom) && any_colla.equals(Integer.toString(colla.any))){ 
                Utilities.CerrarFicheroBinario(dos);
                EscriureMembre(membre,NOM_FITXER_COLLES_REMPLAÇ);
                dos = Utilities.AbrirFicheroEscrituraBinario(NOM_FITXER_COLLES_REMPLAÇ, true, true);
            }
        }
        numero_colles = 0;
        numero_membres_colla_actual = 0;
        //Tanquem
        Utilities.CerrarFicheroBinario(dis2);
        Utilities.CerrarFicheroBinario(dos);
        //Reemplacem el fitxer inicial pel nou
        Utilities.BorrarFichero(NOM_FITXER_COLLES);
        Utilities.RenombrarFichero(NOM_FITXER_COLLES_REMPLAÇ,NOM_FITXER_COLLES);
    }
    
    public static void CompletarColla (Colla colla, Membre membre) throws IOException{
        /*if (colla.numero_membres==0){
            DataOutputStream dos = Utilities.AbrirFicheroEscrituraBinario(NOM_FITXER_NOMS_COLLES, true, true);
            dos.writeUTF(colla.nom);
            Utilities.CerrarFicheroBinario(dos);
            numero_colles++;
        }*/
        colla.numero_membres++;
        colla.import_colla=colla.import_colla+membre.import_membre;
        colla.premi_colla=colla.premi_colla+membre.premi_membre;
    }
    
    public static void EscriureColla (Colla colla) throws IOException{
        DataOutputStream dos1 = Utilities.AbrirFicheroEscrituraBinario(NOM_FITXER_NOMS_COLLES, true, true);
        DataOutputStream dos2 = Utilities.AbrirFicheroEscrituraBinario(NOM_FITXER_COLLES, true, true);
        dos1.writeUTF(colla.nom);
        dos1.writeUTF(Integer.toString(colla.any));
        dos2.writeUTF(String.format(FORMAT_DADES, Integer.toString(colla.any)));
        dos2.writeUTF(String.format(FORMAT_DADES, Integer.toString(colla.numero_membres)));
        dos2.writeUTF(String.format(FORMAT_DADES,Integer.toString(colla.import_colla)));
        dos2.writeUTF(String.format(FORMAT_DADES,Integer.toString(colla.premi_colla)));
        Utilities.CerrarFicheroBinario(dos1);
        Utilities.CerrarFicheroBinario(dos2);
    }
    
    public static void EscriureMembre (Membre membre, String nomFitxer) throws IOException{
        DataOutputStream dos = Utilities.AbrirFicheroEscrituraBinario(nomFitxer, true, true);
        //RandomAccessFile raf = new RandomAccessFile(NOM_FITXER_COLLES,"rw");
        //raf.seek(224);
        dos.writeUTF(String.format(FORMAT_DADES,membre.nom));
        dos.writeUTF(String.format(FORMAT_DADES,Integer.toString(membre.numero_loteria)));
        dos.writeUTF(String.format(FORMAT_DADES,Integer.toString(membre.import_membre)));
        dos.writeUTF(String.format(FORMAT_DADES,Integer.toString(membre.premi_membre)));
        //raf.close();
        Utilities.CerrarFicheroBinario(dos);
    }
    
    public static String FormarLiniaInformacio (DataInputStream dis) throws IOException{
        //Iniciem la línia
        String linia_informacio=" ";
        //For segons el numero_atributs per formar la línia
        for (int i=1;i<=NUMERO_ATRIBUTS;i++){
            String dada_afegir = dis.readUTF();
            if (i==2 && numero_membres_colla_actual==0){
                numero_membres_colla_actual = Integer.parseInt(dada_afegir.trim());
            }
            linia_informacio=linia_informacio+dada_afegir;
        }
        return linia_informacio;
    }
    
    public static void ImprimirFitxerColles () throws IOException{
        DataInputStream dis1 = Utilities.AbrirFicheroLecturaBinario(NOM_FITXER_NOMS_COLLES, true);
        DataInputStream dis2 = Utilities.AbrirFicheroLecturaBinario(NOM_FITXER_COLLES, true);
        //Recorrem el numero de colles per anar imprimint tota la informació de cada colla
        for (int i=1;i<=numero_colles;i++){
            System.out.println("-------------------------------------------------"); //Deixem una línia de separació
            System.out.println(""); //Deixem una línia de separació
            System.out.println(dis1.readUTF()); //Imprimim el nom de la colla
            dis1.readUTF();
            System.out.println(""); //Deixem una línia de separació
            System.out.println(Utilities.LlegirLineaConcreta(39, "./idiomas/catala.txt"));
            //|    ANY    |  MEMBRES  |   IMPORT  |   PREMI   |    
            String linia_informacio_colla=FormarLiniaInformacio(dis2);
            System.out.println(linia_informacio_colla);
            System.out.println(Utilities.LlegirLineaConcreta(40, "./idiomas/catala.txt"));
            //|    NOM    |  NÚMERO   |   IMPORT  |   PREMI   |
            for (int j=1;j<=numero_membres_colla_actual;j++){
                String linia_informacio_membre = FormarLiniaInformacio(dis2);
                System.out.println(linia_informacio_membre);
            }
            numero_membres_colla_actual=0;
            System.out.println(""); //Deixem una línia de separació
            System.out.println("-------------------------------------------------"); //Deixem una línia de separació
        }
        numero_colles=0;
        Utilities.CerrarFicheroBinario(dis1);
        Utilities.CerrarFicheroBinario(dis2);
    }
}
