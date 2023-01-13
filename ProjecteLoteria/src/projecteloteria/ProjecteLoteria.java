package projecteloteria;

import utilities.Utilities;
import java.util.Random;
import java.util.Scanner;

public class ProjecteLoteria {
    
    static Scanner scan = new Scanner(System.in); 
    static Random rndm = new Random();
    
    public static void main(String[] args) {
        int array_NumerosPremiats [] = new int [1807];
        int array_Premis [] = new int [1807];
        NumeroPremiat(array_NumerosPremiats);
        
    }
    
    public static void NumeroPremiat (int premis []){
        for (int i=0;i!=premis.length;i++){
            premis[i] = rndm.nextInt(99999);
        }
    }
    
    public static void CompletarPremis (int array_Premis []){
        
    }
}
