package projecteloteria;

import utilities.Utilities;
import java.util.Random;
import java.util.Scanner;

public class ProjecteLoteria {
    
    static Scanner scan = new Scanner(System.in); 
    static Random rndm = new Random();
    
    public static void main(String[] args) {
        int array_NumerosPremiats [] = new int [1807];
        long array_Premis [] = new long [1807];
        NumeroPremiat(array_NumerosPremiats);
        
    }
    
    public static void NumeroPremiat (int premis[]){
        for (int i=0;i!=premis.length;i++){
            premis[i] = rndm.nextInt(99999);
        }
    }
    
    public static void CompletarPremis (long array_Premis[]){
        
        final int TOTALPADREO = 1807;
        final int PREMIOPADREO = 1000;
        
        array_Premis[0] = 4000000;
        array_Premis[1] = 1250000;
        array_Premis[2] = 500000;
        
        array_Premis[3] = 200000;
        array_Premis[4] = 200000;
        
        array_Premis[5] = 60000;
        array_Premis[6] = 60000;
        array_Premis[7] = 60000;
        array_Premis[8] = 60000;
        array_Premis[9] = 60000;
        array_Premis[10] = 60000;
        array_Premis[11] = 60000;
        array_Premis[12] = 60000;
        
        for (int i = 13; i < TOTALPADREO; i++) {
            array_Premis[i] = PREMIOPADREO;
        }
        
    }
}
