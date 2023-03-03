/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecteloteria;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class EscullirIdioma {
    
    public static Scanner scan = new Scanner(System.in);
    
    public static String ObtenirIdioma(){
        File path = new File ("./idiomas");
        String [] fitxers=path.list();
       
        for (int i=0; i<fitxers.length; i++){
            //imprimim els arxius del directori idiomes sense la terminacio (.txt)
            for (int j=0; j<fitxers[i].length()-4; j++){
                System.out.print(fitxers[i].charAt(j));
            }
           System.out.println();
        }
        
        String fileIdioma=scan.next();
        boolean incorrecte=true;
        //si l'opcio del usuari esta equivocada, cridem un altre cop a la funcio fins a aconseguir un resultat valid
        for (int i=0; i<fitxers.length && incorrecte; i++){
            if ((fileIdioma+".txt").equals(fitxers[i]))
                incorrecte=false;
        }
        if(incorrecte){
            System.out.println("Invalid Option");
            fileIdioma = ObtenirIdioma();
        }
        return fileIdioma;
    }
   
    public static String ObtenirPath() {
        String idioma=ObtenirIdioma();
        //creem el path adequat depenent de la entrada del usuari
        String path="./idiomas/"+idioma+".txt";
        
        return path;
    }
}
