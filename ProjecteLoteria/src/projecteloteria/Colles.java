/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecteloteria;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
import static projecteloteria.ProjecteLoteria.PathIdioma;
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
    public static final String NOM_FITXER_COLLES = "./fitxer_colles.bin";
    public static final String NOM_FITXER_NOMS_COLLES = "./fitxer_noms_colles.bin";
    public static final int IMPORT_MINIM = 5; //Import mínim que pot aportar un membre
    public static final int IMPORT_MAXIM = 60; //Import màxim que pot aportar un membre
    public static final int MAXIM_NUMERO_LOTERIA = 99999; //Número de loteria més gran
    public static final int DIVISOR_5 = 5; /*Divisor que utilitzem per comprovar que l'import
    d'un membre és múltiple de 5*/
    public static final String FORMAT_DADES = "%-12s"; 
    public static final int NUMERO_ATRIBUTS = 4; //Número d'atributs a imprimir de les colles i els membres
    
    public static int numero_colles = 0;
    public static int numero_membres_colla_actual = 0; /*Representa el número de membres que té la colla la qual s'està imprimint
    en el seu instant, per saber quants membres s'han d'imprimir*/
    
    public static void main(String[] args) throws IOException {
        //linea 50
        String opcions_menu [] = {"1.Afegir Colla","2.Modificar colla","3.Sortir"};
        int opcio_escollida = Utilities.Menu(scan, opcions_menu);
        ComptarColles();
        /*Colla colla = DemanarColla();
        Membre membre = DemanarMembre();
        CompletarColla(colla,membre);
        EscriureColla(colla);
        EscriureMembre(membre);*/
        ImprimirFitxerColles();
    }
    
    public static void CridarOpcionsMenuColles (int opcio_escollida) throws IOException{
        switch (opcio_escollida){
            case 1:
                ComptarColles();
                Colla colla = DemanarColla();
                Membre membre = DemanarMembre();
                CompletarColla(colla, membre);
                EscriureColla(colla);
                EscriureMembre(membre);
                
            case 2:
                
        }
        
    }
    
  //  public static void 
    
    public static void ComptarColles (){
        DataInputStream dis = Utilities.AbrirFicheroLecturaBinario(NOM_FITXER_NOMS_COLLES, true);
        String linia = LlegirLiniaNomsColles(dis);
        while (linia!=null){
            numero_colles++;
            linia = LlegirLiniaNomsColles(dis);
        }
    }
    
    public static Colla DemanarColla () throws IOException{
        Colla colla = new Colla();
        System.out.print(Utilities.LlegirLineaConcreta(34, PathIdioma));
        colla.nom = scan.nextLine();
        //Imposem amb un while que el nom no estigui repetit
        boolean nom_validat = ValidarNom(colla.nom);
        while (!nom_validat){
            System.out.print(Utilities.LlegirLineaConcreta(34, PathIdioma));
            colla.nom = scan.nextLine();
            nom_validat = ValidarNom(colla.nom);
        }
        colla.any = Utilities.LlegirInt(scan,Utilities.LlegirLineaConcreta(35, PathIdioma));
        
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
    
    public static boolean ValidarNom (String nom_colla) throws IOException{
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
    
    public static Membre DemanarMembre () throws IOException{
        Membre membre = new Membre();
        System.out.print(Utilities.LlegirLineaConcreta(36, PathIdioma));
        membre.nom = scan.nextLine();
        membre.numero_loteria = Utilities.LlegirInt(scan, Utilities.LlegirLineaConcreta(37, PathIdioma), 0, MAXIM_NUMERO_LOTERIA);        
        membre.import_membre = Utilities.LlegirInt(scan, Utilities.LlegirLineaConcreta(38, PathIdioma), IMPORT_MINIM, IMPORT_MAXIM);
        //Imposem amb un while que l'import també sigui múltiple de 5
        while (membre.import_membre%DIVISOR_5 != 0){
            membre.numero_loteria = Utilities.LlegirInt(scan, "Import: ", 0, MAXIM_NUMERO_LOTERIA);
        }
        //Retornem el membre
        return membre;
    }
    
    public static void CompletarColla (Colla colla, Membre membre) throws IOException{
        if (colla.numero_membres==0){
            DataOutputStream dos = Utilities.AbrirFicheroEscrituraBinario(NOM_FITXER_NOMS_COLLES, true, true);
            dos.writeUTF(colla.nom);
            Utilities.CerrarFicheroBinario(dos);
            numero_colles++;
        }
        colla.numero_membres++;
        colla.import_colla=colla.import_colla+membre.import_membre;
        colla.premi_colla=colla.premi_colla+membre.premi_membre;
    }
    
    public static void EscriureColla (Colla colla) throws IOException{
        DataOutputStream dos = Utilities.AbrirFicheroEscrituraBinario(NOM_FITXER_COLLES, true, true);
        dos.writeUTF(colla.nom);
        dos.writeUTF(String.format(FORMAT_DADES, Integer.toString(colla.any)));
        dos.writeUTF(String.format(FORMAT_DADES, Integer.toString(colla.numero_membres)));
        dos.writeUTF(String.format(FORMAT_DADES,Integer.toString(colla.import_colla)));
        dos.writeUTF(String.format(FORMAT_DADES,Integer.toString(colla.premi_colla)));
        Utilities.CerrarFicheroBinario(dos);
    }
    
    public static void EscriureMembre (Membre membre) throws IOException{
        DataOutputStream dos = Utilities.AbrirFicheroEscrituraBinario(NOM_FITXER_COLLES, true, true);
        dos.writeUTF(String.format(FORMAT_DADES,membre.nom));
        dos.writeUTF(String.format(FORMAT_DADES,Integer.toString(membre.numero_loteria)));
        dos.writeUTF(String.format(FORMAT_DADES,Integer.toString(membre.import_membre)));
        dos.writeUTF(String.format(FORMAT_DADES,Integer.toString(membre.premi_membre)));
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
        DataInputStream dis = Utilities.AbrirFicheroLecturaBinario(NOM_FITXER_COLLES, true);
        //Recorrem el numero de colles per anar imprimint tota la informació de cada colla
        for (int i=1;i<=numero_colles;i++){
            System.out.println(dis.readUTF()); //Imprimim el nom de la colla
            System.out.println(Utilities.LlegirLineaConcreta(39, PathIdioma));
            String linia_informacio_colla=FormarLiniaInformacio(dis);
            System.out.println(linia_informacio_colla);
            System.out.println(Utilities.LlegirLineaConcreta(40, PathIdioma));
            for (int j=1;j<=numero_membres_colla_actual;j++){
                String linia_informacio_membre = FormarLiniaInformacio(dis);
                System.out.println(linia_informacio_membre);
            }
            numero_membres_colla_actual=0;
        }
        Utilities.CerrarFicheroBinario(dis);
    }
}
