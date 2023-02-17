/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecteloteria;
import java.util.Scanner;
import utilities.Utilities;

/**
 *
 * @author Usuario
 */
public class EscullirIdioma {
    public static Scanner scan = new Scanner(System.in);
    
    public static String ObtenirIdioma(){
        
        System.out.println("1.Catala");
        System.out.println("2.Castella");
        
        String fileIdioma=scan.next();
        return fileIdioma;
    }
    
    public static String ObtenirPath(){
        String idioma=ObtenirIdioma();
        boolean So=Utilities.Sistema();
        String path;
        if (So){
            path="C:\\Users\\Usuario\\Downloads"+idioma+".txt";
        }
        else
            path="./"+idioma+".txt";
        
        return path;
    }
    
    public static void main(String[] args) {
        
    }
}
