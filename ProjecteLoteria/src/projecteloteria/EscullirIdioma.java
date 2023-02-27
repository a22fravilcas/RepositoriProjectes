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
            System.out.println(fitxers[i]);
        }
        
        String fileIdioma=scan.next();
        boolean incorrecte=true;
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
        
        String path="./idiomas/"+idioma+".txt";
        
        return path;
    }
      
        
    
    
   
}
