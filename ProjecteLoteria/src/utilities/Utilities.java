/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.Scanner;
/**
 * Llibreria d'utilitats
 *
 * @author author
 * @version version
 *
 */
public class Utilities {
// <editor-fold defaultstate="collapsed" desc="Implementació de LlegirInt()">

    private static Scanner scan = null;

    /**
     *
     * @return
     */
    public static int LlegirInt() {
        int result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirInt(scan);

        return result;
    }

    /**
     *
     * @param missatge
     * @return
     */
    public static int LlegirInt(String missatge) {
        int result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirInt(scan, missatge);

        return result;
    }

    /**
     *
     * @param scan
     * @return
     */
    public static int LlegirInt(Scanner scan) {
        return LlegirInt(scan, null);
    }
    
    /**
     * 
     * @param scan
     * @param missatge
     * @param valorMin
     * @param valorMax
     * @return 
     */
    public static int LlegirInt(Scanner scan, String missatge, int valorMin, int valorMax) {
        int result = 0;
        do {
            result = LlegirInt(scan, missatge);
        } while (result < valorMin || result > valorMax);

        return result;
    }
    /**
     * 
     * @param scan
     * @param missatge
     * @return 
     */
    public static int LlegirInt(Scanner scan, String missatge) {
        boolean dadesCorrectes;
        int result = 0;
        do {
            if (missatge != null) {
                System.out.print(missatge);
            }
            dadesCorrectes = scan.hasNextInt();
            if (dadesCorrectes) {
                result = scan.nextInt();
            } else if (scan.hasNext()) {
                scan.nextLine();
            }
        } while (!dadesCorrectes);

        return result;
    }
    /**
     * 
     * @param testnumber
     * @return 
     */
    public static int reverseInt(int testnumber) {
        long reversedNum = 0;
        long input_long = testnumber;

        while (input_long != 0) {
            reversedNum = reversedNum * 10 + input_long % 10;
            input_long = input_long / 10;
        }

        if (reversedNum > Integer.MAX_VALUE || reversedNum < Integer.MIN_VALUE) {
            throw new IllegalArgumentException();
        }
        return (int) reversedNum;
    }
    /**
     * 
     * @param missatge
     * @return 
     */
    public static int demanaNumEnter(String missatge) {
        Scanner scan = new Scanner(System.in);
        int result;
        System.out.println(missatge);

        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("Ha de ser un numero enter. Torna a intentar-ho.");
        }
        result = scan.nextInt();

        //Tornar el resultat de la funció
        return result;
    }
    
    public static int[] intToArray(int testnumber){
        int[] intArray;
        
        String num = Integer.toString(testnumber);
        
        intArray= new int[num.length()];
        
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = num.charAt(i);
        }
   
        return intArray;
    }

// </editor-fold>
}
