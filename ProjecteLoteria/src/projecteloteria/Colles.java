/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecteloteria;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
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
    
    public static void MenuColles (){
        
    }
    
    public static void DemanarColla () throws IOException{
        Utilities.LlegirLineaConcreta(34, NOM_FITXER_COLLES);
        String nom_colla = scan.nextLine();
        int any_sorteig = Utilities.LlegirInt(scan,Utilities.LlegirLineaConcreta(35, NOM_FITXER_COLLES));
        //Imposem amb un while que el nom no estigui repetit
        /*boolean nom_validat = false;
        while (!nom_validat){
            for ()
        }*/
    }
    
    public static void DemanarMembre () throws IOException{
        Utilities.LlegirLineaConcreta(36, NOM_FITXER_COLLES);
        String nom_membre = scan.nextLine();
        int numero_loteria = Utilities.LlegirInt(scan, Utilities.LlegirLineaConcreta(37, NOM_FITXER_COLLES), 0, MAXIM_NUMERO_LOTERIA);        
        int import_membre = Utilities.LlegirInt(scan, Utilities.LlegirLineaConcreta(38, NOM_FITXER_COLLES), IMPORT_MINIM, IMPORT_MAXIM);
        //Imposem amb un while que l'import també sigui múltiple de 5
        while (import_membre%DIVISOR_5 != 0){
            numero_loteria = Utilities.LlegirInt(scan, "Import: ", 0, MAXIM_NUMERO_LOTERIA);
        }
    }
    
    public static void Escriure (){
        DataOutputStream dos = Utilities.AbrirFicheroEscrituraBinario(NOM_FITXER_COLLES, true, true);
        
    }
}
