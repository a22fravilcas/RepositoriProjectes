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
    public static final int IMPORT_MINIM = 5; //Import mínim que pot aportar un membre
    public static final int IMPORT_MAXIM = 60; //Import màxim que pot aportar un membre
    public static final int MAXIM_NUMERO_LOTERIA = 99999; //Número de loteria més gran
    public static final int DIVISOR_5 = 5; /*Divisor que utilitzem per comprovar que l'import
    d'un membre és múltiple de 5*/
    public static final String FORMAT_PARAULES = "%+50s"; 
    public static final String FORMAT_NUMEROS = "%-50s"; 
    public static final int NUMERO_ATRIBUTS_COLLA = 4;
    public static final int NUMERO_ATRIBUTS_MEMBRE = 4;
    
    public static int numero_colles = 0;
    
    public static void MenuColles (){
        
    }
    
    public static Colla DemanarColla () throws IOException{
        Colla colla = new Colla();
        Utilities.LlegirLineaConcreta(34, NOM_FITXER_COLLES);
        colla.nom = scan.nextLine();
        colla.any = Utilities.LlegirInt(scan,Utilities.LlegirLineaConcreta(35, NOM_FITXER_COLLES));
        //Imposem amb un while que el nom no estigui repetit
        /*boolean nom_validat = false;
        while (!nom_validat){
            for ()
        }*/
        //Retornem la colla
        return colla;
    }
    
    public static Membre DemanarMembre () throws IOException{
        Membre membre = new Membre();
        Utilities.LlegirLineaConcreta(36, NOM_FITXER_COLLES);
        membre.nom = scan.nextLine();
        membre.numero_loteria = Utilities.LlegirInt(scan, Utilities.LlegirLineaConcreta(37, NOM_FITXER_COLLES), 0, MAXIM_NUMERO_LOTERIA);        
        membre.import_membre = Utilities.LlegirInt(scan, Utilities.LlegirLineaConcreta(38, NOM_FITXER_COLLES), IMPORT_MINIM, IMPORT_MAXIM);
        //Imposem amb un while que l'import també sigui múltiple de 5
        while (membre.import_membre%DIVISOR_5 != 0){
            membre.numero_loteria = Utilities.LlegirInt(scan, "Import: ", 0, MAXIM_NUMERO_LOTERIA);
        }
        //Retornem el membre
        return membre;
    }
    
    public static Colla CompletarColla (Colla colla, Membre membre){
        numero_colles++;
        colla.numero_membres++;
        colla.import_colla=colla.import_colla+membre.import_membre;
        
        //Retornem la colla
        return colla;
    }
    
    public static void EscriureColla (Colla colla) throws IOException{
        DataOutputStream dos = Utilities.AbrirFicheroEscrituraBinario(NOM_FITXER_COLLES, true, true);
        dos.writeUTF(colla.nom);
        dos.writeUTF(String.format(FORMAT_NUMEROS, Integer.toString(colla.any)));
        dos.writeUTF(String.format(FORMAT_NUMEROS, Integer.toString(colla.numero_membres)));
        dos.writeUTF(String.format(FORMAT_NUMEROS,Integer.toString(colla.import_colla)));
        dos.writeUTF(String.format(FORMAT_NUMEROS,Integer.toString(colla.premi_colla)));
        Utilities.CerrarFicheroBinario(dos);
    }
    
    public static void EscriureMembre (Membre membre) throws IOException{
        DataOutputStream dos = Utilities.AbrirFicheroEscrituraBinario(NOM_FITXER_COLLES, true, true);
        dos.writeUTF(membre.nom);
        dos.writeUTF(String.format(FORMAT_NUMEROS,Integer.toString(membre.numero_loteria)));;
        dos.writeUTF(String.format(FORMAT_NUMEROS,Integer.toString(membre.import_membre)));;
        dos.writeUTF(String.format(FORMAT_NUMEROS,Integer.toString(membre.premi_membre)));;
        Utilities.CerrarFicheroBinario(dos);
    }
    
    public static String FormarLiniaInformacio (int numero_atributs) throws IOException{
        DataInputStream dis = Utilities.AbrirFicheroLecturaBinario(NOM_FITXER_COLLES, true);
        //Iniciem la línia
        String linia_informacio="";
        //For segons el numero_atributs per formar la línia
        for (int i=1;i<=numero_atributs;i++){
            linia_informacio=linia_informacio+dis.readUTF();
        }
        Utilities.CerrarFicheroBinario(dis);
        return linia_informacio;
    }
    
    public static void ImprimirFitxerColles () throws IOException{
        DataInputStream dis = Utilities.AbrirFicheroLecturaBinario(NOM_FITXER_COLLES, true);
        //Recorrem el numero de colles per anar imprimint tota la informació de cada colla
        for (int i=1;i<=numero_colles;i++){
            System.out.println(dis.readUTF()); //Imprimim el nom de la colla
            System.out.println(Utilities.LlegirLineaConcreta(39, PathIdioma));
            String linia_informacio_colla=FormarLiniaInformacio(NUMERO_ATRIBUTS_COLLA);
            System.out.println(linia_informacio_colla);
            System.out.println(Utilities.LlegirLineaConcreta(40, PathIdioma));
            String linia_informacio_membre = FormarLiniaInformacio(NUMERO_ATRIBUTS_MEMBRE);
            System.out.println(linia_informacio_membre);
        }
    }
}
