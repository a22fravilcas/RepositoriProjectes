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
import static projecteloteria.ProjecteLoteria.AssignacioPremis;
import static projecteloteria.ProjecteLoteria.PREMI_ACUMULAT;
import static projecteloteria.ProjecteLoteria.PathIdioma;
import static projecteloteria.ProjecteLoteria.TOTALPREMIS;
import static projecteloteria.ProjecteLoteria.TrobarNumeroPremiat;
import static projecteloteria.ProjecteLoteria.TrobarPremi;
import static projecteloteria.ProjecteLoteria.array_PremisPrincipals;
import static projecteloteria.ProjecteLoteria.array_PremisSecundaris;
import static projecteloteria.ProjecteLoteria.indexnummatch;
import utilities.Utilities;

/**
 * Codi de l'apartat de les Colles
 * 
 * @author Cèlia Garcia, Wilson McCammond i Franc Villaba
 */
public class Colles {
    
    public static Scanner scan = new Scanner(System.in);
    
    /**
     * Classe Colla
     */
    public static class Colla{
        String nom;
        int any;
        int numero_membres;
        float import_colla;
        float premi_colla;
        float distribucio_colla;
    }
    
    /**
     * Classe Membre
     */
    public static class Membre{
        String nom;
        int numero_loteria;
        float import_membre;
        float premi_membre;
        float distribucio_membre;
    }
    
    //CONSTANTS
    public static final String NOM_FITXER_HISTORIAL = "./Historial_Loteries.bin";
    public static final String NOM_FITXER_COLLES = "./fitxer_colles.bin";
    public static final String NOM_FITXER_COLLES_REMPLAÇ = "./fitxer_colles_nou.bin";
    public static final String NOM_FITXER_NOMS_COLLES = "./fitxer_noms_colles.bin";
    public static final int IMPORT_MINIM = 5; //Import mínim que pot aportar un membre
    public static final int IMPORT_MAXIM = 60; //Import màxim que pot aportar un membre
    public static final int MAXIM_NUMERO_LOTERIA = 99999; //Número de loteria més gran
    public static final int DIVISOR_5 = 5; /*Divisor que utilitzem per comprovar que l'import
    d'un membre és múltiple de 5*/
    public static final String FORMAT_DADES = "%-12s"; 
    public static final int NUMERO_ATRIBUTS = 5; //Número d'atributs a imprimir de les colles i els membres
    public static String [] opcions_menu_colles = new String [4];
    public static String [] opcions_menu_modificar_colles = new String [1];
    
    public static int opcio_escollida;
    public static int numero_colles = 0;
    public static int numero_membres_colla_actual = 0; /*Representa el número de membres que té la colla la qual s'està imprimint
    en el seu instant, per saber quants membres s'han d'imprimir*/
    
    /**
     * Genera les opcions del menú de Colles en funció de l'idioma
     * 
     * @throws IOException 
     */
    public static void CrearOpcionsMenuColles () throws IOException{
        opcions_menu_colles[0] = Utilities.LlegirLineaConcreta(47, PathIdioma); //1.Afegir colla
        opcions_menu_colles[1] = Utilities.LlegirLineaConcreta(48, PathIdioma); //2.Modificar colla
        opcions_menu_colles[2] = Utilities.LlegirLineaConcreta(49, PathIdioma); //3.Mostrar colles 
        opcions_menu_colles[3] = Utilities.LlegirLineaConcreta(50, PathIdioma); //4.Sortir
    }
    
    /**
     * Genera les opcions del menú de Modificar Colles en funció de l'idioma
     * 
     * @throws IOException 
     */
    public static void CrearOpcionsMenuModificarColles () throws IOException{
        opcions_menu_modificar_colles[0] = Utilities.LlegirLineaConcreta(51, PathIdioma); //Afegir membre
    }
    
    /**
     * Procediment que crida el menú principal de les colles
     * 
     * @throws IOException 
     */
    public static void CridarOpcionsMenuColles () throws IOException{
        CrearOpcionsMenuColles();
        CrearOpcionsMenuModificarColles();
        while (opcio_escollida!=4){
            switch (opcio_escollida) {
                case 1:
                    Colla colla = DemanarCollaNova();
                    Membre membre = DemanarMembre(colla);
                    CompletarColla(colla, membre);
                    EscriureColla(colla);
                    EscriureMembre(membre,NOM_FITXER_COLLES);
                    DistribuirPremi(colla);
                    break;
                case 2:
                    opcio_escollida=0;                    
                    while (opcio_escollida!=1){
                        Utilities.Menu(scan, opcions_menu_modificar_colles);                        
                    }
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
            Utilities.Menu(scan, opcions_menu_colles);
        }
        
        
    }
    
    /**
     * Procediment que crida el menú de modificar colles
     * 
     * @throws IOException 
     */
     public static void CridarOpcionsMenuModificarColla() throws IOException {
        switch (opcio_escollida){
            case 1:
                ComptarColles();
                Colla colla = DemanarCollaExistent();
                Membre membre = DemanarMembre(colla);
                AfegirMembreModificarColla(colla,membre);
                DistribuirPremi(colla);
                break;           
        }       
    }

    /**
     * Procediment que compta el número de colles
     */    
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
    
    /**
     * Funció que demana una colla nova
     * 
     * @return Retorna la colla nova
     * 
     * @throws IOException 
     */
    public static Colla DemanarCollaNova () throws IOException{
        Colla colla = new Colla();
        System.out.print(Utilities.LlegirLineaConcreta(34, PathIdioma)); //Nom de la colla:
        colla.nom = scan.nextLine();
        //Imposem amb un while que el nom no estigui repetit
        boolean nom_validat = ValidarNomNou(colla.nom);
        while (!nom_validat){
            System.out.print(Utilities.LlegirLineaConcreta(34, PathIdioma)); //Nom de la colla: 
            colla.nom = scan.nextLine();
            nom_validat = ValidarNomNou(colla.nom);
        }
        colla.any = Utilities.LlegirInt(scan,Utilities.LlegirLineaConcreta(35, PathIdioma)); //Any del sorteig:
        boolean any_validat = ValidarAnyNou(colla.nom,Integer.toString(colla.any));
        while (!any_validat){
            colla.any = Utilities.LlegirInt(scan,Utilities.LlegirLineaConcreta(35, PathIdioma)); //Any del sorteig
            any_validat = ValidarAnyNou(colla.nom,Integer.toString(colla.any));
        }
        //Any del sorteig: 
        any_validat = ValidarAnySorteig(colla.any);
        while (!any_validat){
            colla.any = Utilities.LlegirInt(scan,Utilities.LlegirLineaConcreta(35, PathIdioma)); //Any del sorteig:
            any_validat = ValidarAnySorteig(colla.any);
        }
        
        //Retornem la colla
        return colla;
    }
    
    /**
     * Funció que llegeix l'any del sorteig en l'historial de sortejos
     * 
     * @param raf RandomAccessFile
     * @return Retorna l'any llegit
     */
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
    
    /**
     * Funció que valida si l'any d'una colla és vàlid. És a dir, valida si el sorteig de l'any en qüestió existeix perquè si no
     * no es pot crear una colla
     * 
     * @param any_sorteig Any del sorteig
     * @return Retorna mitjançant un boolean si l'any és vàlid o no
     * 
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static boolean ValidarAnySorteig (int any_sorteig) throws FileNotFoundException, IOException{
        RandomAccessFile raf = new RandomAccessFile(NOM_FITXER_HISTORIAL, "r");
        int any = LlegirAnyHistorial(raf);
        boolean any_validat=false;
        while (any!=-1){
            if (any_sorteig==any){
                any_validat=true;
            }
            raf.seek(raf.getFilePointer()+7228);
            any=LlegirAnyHistorial(raf);
        }
        raf.close();
        return any_validat;
    }
    
    /**
     * Funció que demana una colla existent
     * 
     * @return Retorna la colla existent
     * 
     * @throws IOException 
     */
    public static Colla DemanarCollaExistent () throws IOException{
        Colla colla = new Colla();
        System.out.print(Utilities.LlegirLineaConcreta(34, PathIdioma)); //Nom de la colla: 
        colla.nom = scan.nextLine();
        //Imposem amb un while que el nom no estigui repetit
        boolean nom_validat = ValidarNomExistent(colla.nom);
        while (!nom_validat){
            System.out.print(Utilities.LlegirLineaConcreta(34, PathIdioma)); //Nom de la colla: 
            colla.nom = scan.nextLine();
            nom_validat = ValidarNomExistent(colla.nom);
        }
        colla.any = Utilities.LlegirInt(scan,Utilities.LlegirLineaConcreta(35, PathIdioma)); //Any del sorteig:
        boolean any_validat = ValidarAnyExistent(colla.nom,Integer.toString(colla.any));
        while (!any_validat){
            colla.any = Utilities.LlegirInt(scan,Utilities.LlegirLineaConcreta(35, PathIdioma)); //Any del sorteig:
            any_validat = ValidarAnyExistent(colla.nom,Integer.toString(colla.any));
        }
        //Any del sorteig: 
        //Retornem la colla
        return colla;
    }
    
    /**
     * Llegeix els noms de les colles que estan registrats en el fitxer dels noms de les colles
     * 
     * @param dis DataInputStream
     * @return Retorna el nom llegit
     */
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
    
    /**
     * Valida que el nom de la colla no existeixi ja
     * 
     * @param nom_colla Nom de la colla
     * @return Retorna un boolean que indica si el nom és vàlid o no
     * 
     * @throws IOException 
     */
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
    
    /**
     * Valida que al introduir per una colla existent un altre any, aquest any no estigui ja restritat
     * 
     * @param nom_colla Nom de la colla
     * @param any_colla Any de la colla
     * @return Retorna si és vàlid mitjançant un boolean
     * 
     * @throws IOException 
     */
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
    
    /**
     * Valida que al introduir el nom d'una colla existent per modificar-la, efectivament existeixi
     * 
     * @param nom_colla Nom de la colla
     * @return Retorna la validació
     * 
     * @throws IOException 
     */
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
    
    /**
     * Valida que al introduir l'any d'una colla existent per modificar-la, efectivament existeixi
     * 
     * @param nom_colla Nom de la colla
     * @param any_colla Any de la colla
     * @return Validació
     * 
     * @throws IOException 
     */
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
    
    /**
     * Demana un membre de la colla
     * 
     * @param colla Colla
     * @return El membre
     * 
     * @throws IOException 
     */
    public static Membre DemanarMembre (Colla colla) throws IOException{
        Membre membre = new Membre();
        System.out.print(Utilities.LlegirLineaConcreta(36, PathIdioma)); //Nom: 
        membre.nom = scan.nextLine();
        membre.numero_loteria = Utilities.LlegirInt(scan, Utilities.LlegirLineaConcreta(37, PathIdioma), 0, MAXIM_NUMERO_LOTERIA); //Número de Loteria: 
        membre.import_membre = Utilities.LlegirInt(scan, Utilities.LlegirLineaConcreta(38, PathIdioma), IMPORT_MINIM, IMPORT_MAXIM); //Import: 
        
        //Imposem amb un while que l'import també sigui múltiple de 5
        while (membre.import_membre%DIVISOR_5 != 0){
            membre.import_membre = Utilities.LlegirInt(scan, "Import: ", IMPORT_MINIM, IMPORT_MAXIM);
        }
        BuscarPremi(colla,membre);
        //Retornem el membre
        return membre;
    }
    
    /**
     * Busca en l'historial de loteries el premi que li correspon al número que juga un membre
     * 
     * @param colla Colla
     * @param membre Membre
     * 
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void BuscarPremi (Colla colla, Membre membre) throws FileNotFoundException, IOException{
        RandomAccessFile raf = new RandomAccessFile(NOM_FITXER_HISTORIAL, "r");
        int any = LlegirAnyHistorial(raf);
        while (any!=-1){
            if (colla.any==any){
                int[] array_NumerosPremiats = new int [TOTALPREMIS];
                for (int i=0;i<TOTALPREMIS;i++){
                    array_NumerosPremiats[i]=raf.readInt();
                }
                PREMI_ACUMULAT = 0;
                boolean premiat = TrobarNumeroPremiat(array_NumerosPremiats, array_PremisPrincipals, membre.numero_loteria);
                if (premiat) {
                    TrobarPremi(indexnummatch, array_PremisPrincipals);
                }
                AssignacioPremis( membre.numero_loteria,  array_NumerosPremiats, array_PremisSecundaris);
                membre.premi_membre=PREMI_ACUMULAT*membre.import_membre/20;
                PREMI_ACUMULAT=0;
            }
            raf.seek(raf.getFilePointer()+7228);
            any=LlegirAnyHistorial(raf);
        }
    }
    
    /**
     * Afegir un nou membre quan es vol modificar una colla afegint un nou membre
     * 
     * @param colla Colla
     * @param membre Membre
     * 
     * @throws IOException 
     */
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
                            dada_afegir = String.format(FORMAT_DADES, Float.toString(Float.parseFloat(dada_afegir.trim()) + membre.import_membre));
                        }                    
                    }
                    if (j == 4) {
                        if (nom_colla.contains(colla.nom) && any_colla.equals(Integer.toString(colla.any))){ 
                            dada_afegir = String.format(FORMAT_DADES, Float.toString(Float.parseFloat(dada_afegir.trim()) + membre.premi_membre));
                        }  
                    }
                    if (j == 5) {
                        if (nom_colla.contains(colla.nom) && any_colla.equals(Integer.toString(colla.any))){ 
                            dada_afegir = String.format(FORMAT_DADES, Float.toString(Float.parseFloat(dada_afegir.trim()) + membre.premi_membre));
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
    
    /**
     * Completa una colla quan es crea perquè quan es van afegint membres s'ha d'actualitzar les diferents dades de la colla
     * 
     * @param colla Colla
     * @param membre Membre
     * 
     * @throws IOException 
     */
    public static void CompletarColla (Colla colla, Membre membre) throws IOException{
        colla.numero_membres++;
        colla.import_colla=colla.import_colla+membre.import_membre;
        colla.premi_colla=colla.premi_colla+membre.premi_membre;
        colla.distribucio_colla=colla.premi_colla;
    }
    
    /**
     * Fa la distribució dels premis, és a dir, què li toca a repartir per cada membre d'una colla
     * 
     * @param colla Colla
     * 
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void DistribuirPremi (Colla colla) throws FileNotFoundException, IOException{
        DataInputStream dis1 = Utilities.AbrirFicheroLecturaBinario(NOM_FITXER_NOMS_COLLES, true);
        RandomAccessFile raf2 = new RandomAccessFile(NOM_FITXER_COLLES, "rw");
        ComptarColles();
        for (int i = 1; i <= numero_colles; i++) {
            String nom_colla = dis1.readUTF();
            String any_colla = dis1.readUTF();
            
            if (nom_colla.equals(colla.nom) && any_colla.equals(Integer.toString(colla.any))){
                for (int m=1;m<=i-1;m++){
                    raf2.readUTF();
                    numero_membres_colla_actual = Integer.parseInt(raf2.readUTF().trim());
                    for (int n=1;n<=numero_membres_colla_actual*5+3;n++){
                        raf2.readUTF();
                    }
                }
                raf2.readUTF();
                
                for (int j = 2; j <= NUMERO_ATRIBUTS; j++) {
                        String dada_afegir = raf2.readUTF();
                        if (j == 2) {
                            numero_membres_colla_actual = Integer.parseInt(dada_afegir.trim());
                        }
                        if (j == 3) {
                            colla.import_colla = Float.parseFloat(dada_afegir.trim());
                        }
                        if (j == 4) {
                            colla.premi_colla = Float.parseFloat(dada_afegir.trim());
                        }
                }
                for (int k = 1; k <= numero_membres_colla_actual; k++) {
                    float import_membre = 0;
                    for (int l = 1; l <= NUMERO_ATRIBUTS - 1; l++) {
                        String dada_afegir = raf2.readUTF();
                        if (l == 3) {
                            import_membre = Float.parseFloat(dada_afegir.trim());
                        } else if (l == 4) {
                            String distribucio = Float.toString(colla.premi_colla * import_membre / colla.import_colla);
                            raf2.writeUTF(String.format(FORMAT_DADES, distribucio));
                        }
                    }
                }
                numero_membres_colla_actual = 0;

            }
        }
        numero_membres_colla_actual=0;
        numero_colles=0;
        Utilities.CerrarFicheroBinario(dis1);
        raf2.close();
    }
    
    /**
     * Registra una colla en el fitxer binari de colles
     * 
     * @param colla Colla
     * 
     * @throws IOException 
     */
    public static void EscriureColla (Colla colla) throws IOException{
        DataOutputStream dos1 = Utilities.AbrirFicheroEscrituraBinario(NOM_FITXER_NOMS_COLLES, true, true);
        DataOutputStream dos2 = Utilities.AbrirFicheroEscrituraBinario(NOM_FITXER_COLLES, true, true);
        dos1.writeUTF(colla.nom);
        dos1.writeUTF(Integer.toString(colla.any));
        dos2.writeUTF(String.format(FORMAT_DADES, Integer.toString(colla.any)));
        dos2.writeUTF(String.format(FORMAT_DADES, Integer.toString(colla.numero_membres)));
        dos2.writeUTF(String.format(FORMAT_DADES,Float.toString(colla.import_colla)));
        dos2.writeUTF(String.format(FORMAT_DADES,Float.toString(colla.premi_colla)));
        dos2.writeUTF(String.format(FORMAT_DADES,Float.toString(colla.distribucio_colla)));
        Utilities.CerrarFicheroBinario(dos1);
        Utilities.CerrarFicheroBinario(dos2);
    }
    
    /**
     * Registra un membre en el fitxer binari de colles
     * 
     * @param membre Membre
     * @param nomFitxer Camí del fitxer binari
     * 
     * @throws IOException 
     */
    public static void EscriureMembre (Membre membre, String nomFitxer) throws IOException{
        DataOutputStream dos = Utilities.AbrirFicheroEscrituraBinario(nomFitxer, true, true);
        dos.writeUTF(String.format(FORMAT_DADES,membre.nom));
        dos.writeUTF(String.format(FORMAT_DADES,Integer.toString(membre.numero_loteria)));
        dos.writeUTF(String.format(FORMAT_DADES,Float.toString(membre.import_membre)));
        dos.writeUTF(String.format(FORMAT_DADES,Float.toString(membre.premi_membre)));
        dos.writeUTF(String.format(FORMAT_DADES,Float.toString(0)));
        //raf.close();
        Utilities.CerrarFicheroBinario(dos);
    }
    
    /**
     * Llegeix les línies de informació de les colles i els membres que estan registrats en el fitxer de colles
     * 
     * @param dis DataInputStream
     * @return La línia de informació
     * 
     * @throws IOException 
     */
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
    
    /**
     * Imprimeix les taules de les colles per pantalla
     * 
     * @throws IOException 
     */
    public static void ImprimirFitxerColles () throws IOException{
        DataInputStream dis1 = Utilities.AbrirFicheroLecturaBinario(NOM_FITXER_NOMS_COLLES, true);
        DataInputStream dis2 = Utilities.AbrirFicheroLecturaBinario(NOM_FITXER_COLLES, true);
        //Recorrem el numero de colles per anar imprimint tota la informació de cada colla
        for (int i=1;i<=numero_colles;i++){
            System.out.println("-------------------------------------------------------------------"); //Deixem una línia de separació
            System.out.println(""); //Deixem una línia de separació
            System.out.println(dis1.readUTF()); //Imprimim el nom de la colla
            dis1.readUTF();
            System.out.println(""); //Deixem una línia de separació
            System.out.println(Utilities.LlegirLineaConcreta(39, PathIdioma)); //|    ANY    |  MEMBRES  |   IMPORT  |   PREMI   |    
            String linia_informacio_colla=FormarLiniaInformacio(dis2);
            System.out.println(linia_informacio_colla);
            System.out.println(Utilities.LlegirLineaConcreta(40, PathIdioma)); //|    NOM    |  NÚMERO   |   IMPORT  |   PREMI   |
            for (int j=1;j<=numero_membres_colla_actual;j++){
                String linia_informacio_membre = FormarLiniaInformacio(dis2);
                System.out.println(linia_informacio_membre);
            }
            numero_membres_colla_actual=0;
            System.out.println(""); //Deixem una línia de separació
            System.out.println("-------------------------------------------------------------------"); //Deixem una línia de separació
        }
        numero_colles=0;
        Utilities.CerrarFicheroBinario(dis1);
        Utilities.CerrarFicheroBinario(dis2);
    }
}
