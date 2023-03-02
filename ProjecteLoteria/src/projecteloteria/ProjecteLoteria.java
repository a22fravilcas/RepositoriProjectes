package projecteloteria;

import java.io.IOException;
import utilities.Utilities;
import java.util.Random;
import java.util.Scanner;

public class ProjecteLoteria {

    public static Scanner scan = new Scanner(System.in);
    public static Random rndm = new Random();
    public static final int TOTALPREMIS = 1807;
    public static final int TIPUS_PREMIS_SECUNDARIS = 12; //Nombre de diferents tipus de premis secundaris que hi ha
    public static int indexnummatch;
    public static int PREMI_ACUMULAT;
    public static String PathIdioma;

    public static class PremiSecundari {

        String nom;
        boolean toca;
        String missatge_premi;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {

        PathIdioma = EscullirIdioma.ObtenirPath();

        PathIdioma=EscullirIdioma.ObtenirPath();
        

        int array_NumerosPremiats[] = new int[TOTALPREMIS];
        //Crida a funcio NumeroPremiat
        
        //Cridar aqui a Historial loteries
        NumeroPremiat(array_NumerosPremiats); 
        
        int[] array_PremisPrincipals = new int[TOTALPREMIS];
        PremiSecundari[] array_PremisSecundaris = new PremiSecundari[TIPUS_PREMIS_SECUNDARIS];
        //Crida a funcio CompletarPremisPrincipals i CompleatarPremisSecundaris
        CompletarPremisPrincipals(array_PremisPrincipals);
        CompletarPremisSecundaris(array_PremisSecundaris);
        
        boolean AltreNumero = true; //boolean per permetre al usuari introduir diversos numeros   
      
        System.out.println(array_NumerosPremiats[0]);
        System.out.println(array_NumerosPremiats[1]);//print per poder probar numeros, no estara en versio final
     
        while (AltreNumero) {
            PREMI_ACUMULAT = 0;
            //personaSola(continuar)/colles/(cridar a les funcions pertinents
            
            //Crida a funcio externa per verificar el numero de l'usuari
            int numeroUsuari = Utilities.demanaNumEnter(Utilities.LlegirLineaConcreta(1, PathIdioma)//Introdueix el teu numero de loteria.  
                    + Utilities.LlegirLineaConcreta(2, PathIdioma)/*El numero ha de ser de cinc digits:*/,
                    Utilities.LlegirLineaConcreta(33, PathIdioma));//Introdueix un valor valid
            

            //Crida a funcio TrobarNumeroPremiat
            boolean premiat = TrobarNumeroPremiat(array_NumerosPremiats, array_PremisPrincipals, numeroUsuari);
            
            if (premiat) {
                int premiTrobat = TrobarPremi(indexnummatch, array_PremisPrincipals);
                System.out.println(Utilities.LlegirLineaConcreta(3, PathIdioma) + premiTrobat + "€.");
                //Enhorabona, has aconeguit un premi principal. El teu premi es de 
            } else {
                System.out.println(Utilities.LlegirLineaConcreta(4, PathIdioma));
                //El teu numero no correspon a cap premi principal.
            }
            
            //If per saber si el numero escollit te premi principal.
            boolean AproxSeg = false, AproxTerc = false, PrimerTresXifresTercer = false, PrimerTresXifresQuart1 = false,
                    PrimerTresXifresQuart2 = false, UltimaXifraGordo = false, UltimesDosXifresGordo = false;

            TresXifresGordo(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris,
                    UltimesDosXifresGordo, UltimaXifraGordo);
            //cridem a les funcions que decideixen quins premis secundaris sumar
            
            PrimTresXifSegon(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris,
                    PrimerTresXifresTercer, PrimerTresXifresQuart1, PrimerTresXifresQuart2);

            AproxPrim(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris, AproxSeg, AproxTerc);
            //cridem a la funcio que decideix quina aproximacio sumar

            DesglosarPremi(array_PremisSecundaris);
            //funcio per mostrar els premis obtinguts al usuari

            System.out.println(Utilities.LlegirLineaConcreta(5, PathIdioma));
            //Tens un altre numero?
            if (scan.next().equals("No")) {
                //tanquem el bucle depenent de la entrada del usuari
                AltreNumero = false;
            }
        }
        scan.close(); //deixem descansar al nostre estimat escaner <3
    }

    /**
     *
     * @param numeroUsuari
     * @param array_NumerosPremiats
     * @param array_PremisSecundaris
     * @param UltimesDosXifresGordo
     * @param UltimaXifraGordo
     * @return
     */
    public static void TresXifresGordo(int numeroUsuari, int[] array_NumerosPremiats, PremiSecundari[] array_PremisSecundaris,
            boolean UltimesDosXifresGordo, boolean UltimaXifraGordo) {
        //com aquests premis no son compatibles, revisem desde el que te mes prioritat per assignar algun d'ells, cridan a les funcions que el donen
        if (numeroUsuari != array_NumerosPremiats[0]) {
            
            boolean PrimeresTresXifresGordo = PrimeresTresXifresGordo(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
            if (PrimeresTresXifresGordo == false) {
                
                UltimesDosXifresGordo = UltimesDosXifresGordo(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
                if (UltimesDosXifresGordo == false) {
                    
                    UltimaXifraGordo = UltimaXifraGordo(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
                }
            }
        }
    }

    /**
     *
     * @param numeroUsuari
     * @param array_NumerosPremiats
     * @param array_PremisSecundaris
     * @param PrimerTresXifresTercer
     * @param PrimerTresXifresQuart1
     * @param PrimerTresXifresQuart2
     * @return
     */
    public static void PrimTresXifSegon(int numeroUsuari, int[] array_NumerosPremiats, PremiSecundari[] array_PremisSecundaris,
            boolean PrimerTresXifresTercer, boolean PrimerTresXifresQuart1, boolean PrimerTresXifresQuart2) {
        //com aquests premis no son compatibles, revisem desde el que te mes prioritat per assignar algun d'ells, cridan a les funcions que el donen
        if (numeroUsuari != array_NumerosPremiats[1]) {
            
            boolean PrimeresTresXifresSegon = PrimeresTresXifresSegonPremi(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
            
            if (PrimeresTresXifresSegon == false && numeroUsuari != array_NumerosPremiats[2]) {
                PrimerTresXifresTercer = PrimeresTresXifresTercerPremi(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
                
                if (PrimerTresXifresTercer == false && numeroUsuari != array_NumerosPremiats[3]) {
                    PrimerTresXifresQuart1 = PrimeresTresXifresQuartPremi1(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
                    
                    if (PrimerTresXifresQuart1 == false && numeroUsuari != array_NumerosPremiats[4]) {
                        PrimerTresXifresQuart2 = PrimeresTresXifresQuartPremi2(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
                    }
                }
            }

        }
    }

    /**
     *
     * @param numeroUsuari
     * @param array_NumerosPremiats
     * @param array_PremisSecundaris
     * @param AproxSeg
     * @param AproxTerc
     * @return
     */
    public static void AproxPrim(int numeroUsuari, int[] array_NumerosPremiats, PremiSecundari[] array_PremisSecundaris, boolean AproxSeg, boolean AproxTerc) {
        //com aquests premis no son compatibles, revisem desde el que te mes prioritat per assignar algun d'ells, cridan a les funcions que el donen
        boolean AproxPrim = AproximacioPrimerPremi(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
       
        if (AproxPrim == false) {
            AproxSeg = AproximacioSegonPremi(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
            
            if (AproxSeg == false) {
                AproxTerc = AproximacioTercerPremi(numeroUsuari, array_NumerosPremiats, array_PremisSecundaris);
            }
        }

    }

    /**
     *
     * @param numeros_premiats
     */
    //Funcio que genera un array de numeros possibles premiats
    public static void NumeroPremiat(int numeros_premiats[]) {
        final int LIMITNUMEROPREMIAT = 100000;
      
        for (int i = 0; i != numeros_premiats.length; i++) {
            /*While per a què els números s'afegeixen mentre no estiguin repetits. Per a això,
            utilizem un boolean per saber si és repetit o no i recorrem l'array de premis*/
            boolean repetit = false;
          
            int numero_a_afegir = rndm.nextInt(LIMITNUMEROPREMIAT);
           
            for (int j = 0; j < numeros_premiats.length; j++) {
                if (numeros_premiats[j] == numero_a_afegir) {
                    repetit = true;
                }
            }
            while (repetit == true) {
                numero_a_afegir = rndm.nextInt(LIMITNUMEROPREMIAT);
                repetit = false;
              
                for (int j = 0; j < numeros_premiats.length; j++) {
                    if (numeros_premiats[j] == numero_a_afegir) {
                        repetit = true;
                    }
                }
            }
            if (i < 10) {
                String.format("%05d", i);
            } else if (i < 100) {
                String.format("%04d", i);
            } else if (i < 1000) {
                String.format("%03d", i);
            } else if (i < 10000) {
                String.format("%02d", i);
            }
            numeros_premiats[i] = numero_a_afegir;
        }
    }

    /**
     *
     * @param numeros_premiats
     */
    public static void NumeroPremiatAmanyat(int numeros_premiats[]) {
        //Fiquem tots els premis amanyats en ordre, així és més fàcil
        for (int i = 0; i < numeros_premiats.length; i++) {

            numeros_premiats[i] = i * 10;

            if (numeros_premiats[i] < 10) {
                String.format("%05d", numeros_premiats[i]);
            } else if (numeros_premiats[i] < 100) {
                String.format("%04d", numeros_premiats[i]);
            } else if (numeros_premiats[i] < 1000) {
                String.format("%03d", numeros_premiats[i]);
            } else if (numeros_premiats[i] < 10000) {
                String.format("%02d", numeros_premiats[i]);
            }

        }
        numeros_premiats[0] = 18060;
        numeros_premiats[1] = 18050;
        numeros_premiats[2] = 18040;
        numeros_premiats[3] = 18030;
        numeros_premiats[4] = 18020;
    }

    /**
     *
     * @param array_PremisPrincipals
     */
    //Funcio que assigna el valor a l'array de premis 
    public static void CompletarPremisPrincipals(int[] array_PremisPrincipals) {

        final int CINQUE_PREMI = 6000;
        final int PEDREA_PREMI = 1000;
        final int PRIMERPREMI = 400000;
        final int SEGONPREMI = 125000;
        final int TERCERPREMI = 50000;
        final int QUARTICINQUEPREMI = 20000;
        final int PARTICIOPREMISGORDOS = 5;
        final int PARTICIOPREMISPEDREA = 13;

        array_PremisPrincipals[0] = PRIMERPREMI;
        array_PremisPrincipals[1] = SEGONPREMI;
        array_PremisPrincipals[2] = TERCERPREMI;
        array_PremisPrincipals[3] = QUARTICINQUEPREMI;
        array_PremisPrincipals[4] = QUARTICINQUEPREMI;

        for (int i = 5; i < TOTALPREMIS; i++) { //bucle per assignar els premis quue es repeteixen en molts numeros
            if (i >= 13) {
                array_PremisPrincipals[i] = PEDREA_PREMI;
            } else {
                array_PremisPrincipals[i] = CINQUE_PREMI;
            }
        }

    }

    public static void CompletarPremisSecundaris(PremiSecundari[] array_PremisSecundaris) throws IOException {
        //Primerament recorrem l'array de premis secundaris per crear cada objecte de la classe
        for (int i = 0; i < array_PremisSecundaris.length; i++) {
            array_PremisSecundaris[i] = new PremiSecundari();
        }
        //Ara afegim tots els registres dels premis secundaris
        array_PremisSecundaris[0].nom = Utilities.LlegirLineaConcreta(6, PathIdioma);
        //AproximacioPrimerPremi
        array_PremisSecundaris[0].missatge_premi = Utilities.LlegirLineaConcreta(7, PathIdioma);
        //Per aconseguir l'aproximacio del gordo has aconseguir 2000€
        array_PremisSecundaris[1].nom = Utilities.LlegirLineaConcreta(8, PathIdioma);
        //AproximacioSegonPremi
        array_PremisSecundaris[1].missatge_premi = Utilities.LlegirLineaConcreta(9, PathIdioma);
        //Per aconseguir l'aproximacio del segon premi has aconseguir 1250€
        array_PremisSecundaris[2].nom = Utilities.LlegirLineaConcreta(10, PathIdioma);
        //AproximacioTercerPremi
        array_PremisSecundaris[2].missatge_premi = Utilities.LlegirLineaConcreta(11, PathIdioma);
        //Per aconseguir l'aproximacio del tercer premi has aconseguir 960€
        array_PremisSecundaris[3].nom = Utilities.LlegirLineaConcreta(12, PathIdioma);
        //PrimeresTresXifresGordo
        array_PremisSecundaris[3].missatge_premi = Utilities.LlegirLineaConcreta(13, PathIdioma);
        //Per aconseguir les tres primeres xifres del gordo has aconseguit 100€
        array_PremisSecundaris[4].nom = Utilities.LlegirLineaConcreta(14, PathIdioma);
        //PrimeresTresXifresSegonPremi
        array_PremisSecundaris[4].missatge_premi = Utilities.LlegirLineaConcreta(15, PathIdioma);

        array_PremisSecundaris[5].nom = Utilities.LlegirLineaConcreta(16, PathIdioma);

        //Per aconseguir les tres primeres xifres del segon premi has aconseguit 100€
        array_PremisSecundaris[5].nom = Utilities.LlegirLineaConcreta(16, PathIdioma);
        //PrimeresTresXifresTercerPremi
        array_PremisSecundaris[5].missatge_premi = Utilities.LlegirLineaConcreta(17, PathIdioma);
        //Per aconseguir les tres primeres xifres del tercer premi has aconseguit 100€
        array_PremisSecundaris[6].nom = Utilities.LlegirLineaConcreta(18, PathIdioma);
        //PrimeresTresXifresQuartPremi1
        array_PremisSecundaris[6].missatge_premi = Utilities.LlegirLineaConcreta(19, PathIdioma);
        //Per aconseguir les tres primeres xifres d'un quart premi has aconseguit 100€
        array_PremisSecundaris[7].nom = Utilities.LlegirLineaConcreta(20, PathIdioma);
        //PrimeresTresXifresQuartPremi2
        array_PremisSecundaris[7].missatge_premi = Utilities.LlegirLineaConcreta(21, PathIdioma);
        //Per aconseguir les tres primeres xifres d'un quart premi has aconseguit 100€
        array_PremisSecundaris[8].nom = Utilities.LlegirLineaConcreta(22, PathIdioma);
        //UltimesDosXifresGordo
        array_PremisSecundaris[8].missatge_premi = Utilities.LlegirLineaConcreta(23, PathIdioma);
        //Per aconseguir les dues ultimes xifres del gordo has aconseguit 100€
        array_PremisSecundaris[9].nom = Utilities.LlegirLineaConcreta(24, PathIdioma);
        //UltimesDosXifresSegonPremi
        array_PremisSecundaris[9].missatge_premi = Utilities.LlegirLineaConcreta(25, PathIdioma);
        //Per aconseguir les dues ultimes xifres del segon premi has aconseguit 100€
        array_PremisSecundaris[10].nom = Utilities.LlegirLineaConcreta(26, PathIdioma);
        //UltimesDosXifresTercerPremi
        array_PremisSecundaris[10].missatge_premi = Utilities.LlegirLineaConcreta(27, PathIdioma);
        array_PremisSecundaris[11].nom = Utilities.LlegirLineaConcreta(28, PathIdioma);

        //Per aconseguir les dues ultimes xifres del tercer premi has aconseguit 100€
        array_PremisSecundaris[11].nom =Utilities.LlegirLineaConcreta(28, PathIdioma);
        //UltimaXifraGordo
        array_PremisSecundaris[11].missatge_premi = Utilities.LlegirLineaConcreta(29, PathIdioma);
        //Per aconseguir l'ultima xifra del gordo has aconseguit el reintegrament del teu numero (20€)
        
        //Ara recorrem la porció de l'array entre PrimeresTresXifresGordo i UltimesDosXifresTercer premi per afegir el premi de 100€
    }

    /**
     *
     * @param indexnummatch
     * @param premis
     * @return
     */
    //Funcio que troba el premi corresponent al numero premiat
    public static int TrobarPremi(int indexnummatch, int[] premis) {
        //amb l'index del numeroPremiat del usuari, busquem el premi corresponent
        int valorPremi = premis[indexnummatch];
        PREMI_ACUMULAT += valorPremi;
        return valorPremi;
    }

    /**
     *
     * @param array_NumerosPremiats
     * @param array_Premis
     * @param numeroUsuari
     */
    //Funcio que busca si el numero de l'usuari correspon a un dels numeros premiats
    public static boolean TrobarNumeroPremiat(int array_NumerosPremiats[], int array_Premis[], int numeroUsuari) {
        boolean result = false;
        int i = 0;
       
        while (result == false && i < TOTALPREMIS) {
            if (array_NumerosPremiats[i] == numeroUsuari) {
                result = true;
                indexnummatch = i;
            }
            i++;
        }
        //Si result segueix sent false vol dir que no ha trobat cap premi principal

        return result;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si les tres ultimes xifres del numero de l'usuari son iguals a les del gordo
    public static boolean PrimeresTresXifresGordo(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifresIguals = false;
        final int PREMI = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);
        //transformem el numero del usuari en un array per poder fer le seguents operacions mes facils
        int[] primerPremi = Utilities.intToArray(premis[0]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT += PREMI;
            array_PremisSecundaris[3].toca = true;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si les tres ultimes xifres del numero de l'usuari son iguals a les del segon premi
    public static boolean PrimeresTresXifresSegonPremi(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifresIguals = false;
        final int PREMI = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);
        //transformem el numero del usuari en un array per poder fer le seguents operacions mes facils
        int[] primerPremi = Utilities.intToArray(premis[1]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT += PREMI;
            array_PremisSecundaris[4].toca = true;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si les tres ultimes xifres del numero de l'usuari son iguals a les del segon premi
    public static boolean PrimeresTresXifresTercerPremi(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifresIguals = false;
        final int PREMI = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);
        //transformem el numero del usuari en un array per poder fer le seguents operacions mes facils
        int[] primerPremi = Utilities.intToArray(premis[2]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT += PREMI;
            array_PremisSecundaris[5].toca = true;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si les tres ultimes xifres del numero de l'usuari son iguals a les del segon premi
    public static boolean PrimeresTresXifresQuartPremi1(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifresIguals = false;
        final int PREMI = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);
        //transformem el numero del usuari en un array per poder fer le seguents operacions mes facils
        int[] primerPremi = Utilities.intToArray(premis[3]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT += PREMI;
            array_PremisSecundaris[6].toca = true;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si les tres ultimes xifres del numero de l'usuari son iguals a les del segon premi
    public static boolean PrimeresTresXifresQuartPremi2(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifresIguals = false;
        final int PREMI = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);
        //transformem el numero del usuari en un array per poder fer le seguents operacions mes facils
        int[] primerPremi = Utilities.intToArray(premis[4]);

        if (ArrayUsuari[0] == primerPremi[0]
                && ArrayUsuari[1] == primerPremi[1]
                && ArrayUsuari[2] == primerPremi[2]) {
            xifresIguals = true;
            PREMI_ACUMULAT += PREMI;
            array_PremisSecundaris[6].toca = true;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si les dues ultimes xifres del numero de l'usuari son iguals a les del gordo
    public static boolean UltimesDosXifresGordo(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifresIguals = false;
        final int PREMI = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);
        //transformem el numero del usuari en un array per poder fer le seguents operacions mes facils
        int[] primerPremi = Utilities.intToArray(premis[0]);

        if (ArrayUsuari[4] == primerPremi[4] && ArrayUsuari[3] == primerPremi[3]) {
            xifresIguals = true;
            PREMI_ACUMULAT += PREMI;
            array_PremisSecundaris[7].toca = true;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si les dues ultimes xifres del numero de l'usuari son iguals a les del gordo
    public static boolean UltimesDosXifresSegonPremi(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifresIguals = false;
        final int PREMI = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);
        //transformem el numero del usuari en un array per poder fer le seguents operacions mes facils
        int[] primerPremi = Utilities.intToArray(premis[1]);

        if (ArrayUsuari[4] == primerPremi[4] && ArrayUsuari[3] == primerPremi[3]) {
            xifresIguals = true;
            PREMI_ACUMULAT += PREMI;
            array_PremisSecundaris[8].toca = true;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si les dues ultimes xifres del numero de l'usuari son iguals a les del gordo
    public static boolean UltimesDosXifresTercerPremi(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifresIguals = false;
        final int PREMI = 100;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);
        //transformem el numero del usuari en un array per poder fer le seguents operacions mes facils
        int[] primerPremi = Utilities.intToArray(premis[2]);

        if (ArrayUsuari[4] == primerPremi[4] && ArrayUsuari[3] == primerPremi[3]) {
            xifresIguals = true;
            PREMI_ACUMULAT += PREMI;
            array_PremisSecundaris[9].toca = true;
        }

        return xifresIguals;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comprova si l'ultima xifra del numero de l'usuari es igual al del gordo
    public static boolean UltimaXifraGordo(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean xifraIgual = false;
        final int REINTEGRAMENT = 20;
        int[] ArrayUsuari = Utilities.intToArray(numeroUsuari);
        //transformem el numero del usuari en un array per poder fer le seguents operacions mes facils
        int[] primerPremi = Utilities.intToArray(premis[0]);

        if (ArrayUsuari[4] == primerPremi[4]) {
            xifraIgual = true;
            PREMI_ACUMULAT += REINTEGRAMENT;
            array_PremisSecundaris[10].toca = true;
        }

        return xifraIgual;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comproba si el numero del usuari es aproximacio al primer premi (+-1)
    public static boolean AproximacioPrimerPremi(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean result = false;
        final int PREMIAPROX = 2000;
        final int APROXSUPERIOR = 00000;
        final int APROXINFERIOR = 99999;
        int aproximacio_primer_premi_per_sota = premis[0] - 1;
        int aproximacio_primer_premi_per_sobra = premis[0] + 1;
        
        if (premis[0] == 00000) {
            aproximacio_primer_premi_per_sota = 99999;
            //en els cassos extrems, tanquem la volta de numeros manualment
        } else if (premis[0] == 99999) {
            aproximacio_primer_premi_per_sobra = 00000;
        }

        if (numeroUsuari == aproximacio_primer_premi_per_sota || numeroUsuari == aproximacio_primer_premi_per_sobra) {
            result = true;
            PREMI_ACUMULAT += PREMIAPROX;
            array_PremisSecundaris[0].toca = true;
        }

        return result;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comproba si el numero del usuari es aproximacio al segon premi (+-1)
    public static boolean AproximacioSegonPremi(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean result = false;
        final int PREMIAPROX = 1250;
        final int APROXSUPERIOR = 00000;
        final int APROXINFERIOR = 99999;
        int aproximacio_segon_premi_per_sota = premis[1] - 1;
        int aproximacio_segon_premi_per_sobre = premis[1] + 1;
        
        if (premis[1] == 00000) {
            aproximacio_segon_premi_per_sota = 99999;
            //en els cassos extrems, tanquem la volta de numeros manualment
        } else if (premis[1] == 99999) {
            aproximacio_segon_premi_per_sobre = 00000;
        }

        if (numeroUsuari == aproximacio_segon_premi_per_sota || numeroUsuari == aproximacio_segon_premi_per_sobre) {
            result = true;
            PREMI_ACUMULAT += PREMIAPROX;
            array_PremisSecundaris[1].toca = true;
        }

        return result;
    }

    /**
     *
     * @param numeroUsuari
     * @param premis
     * @param array_PremisSecundaris
     * @return
     */
    //Funcio que comproba si el numero del usuari es aproximacio al tercer premi (+-1)
    public static boolean AproximacioTercerPremi(int numeroUsuari, int[] premis, PremiSecundari[] array_PremisSecundaris) {
        boolean result = false;
        final int PREMIAPROX = 960;
        final int APROXSUPERIOR = 00000;
        final int APROXINFERIOR = 99999;
        int aproximacio_tercer_premi_per_sota = premis[2] - 1;
        int aproximacio_tercer_premi_per_sobra = premis[2] + 1;
      
        if (premis[2] == 00000) {
            aproximacio_tercer_premi_per_sota = 99999;
            //en els cassos extrems, tanquem la volta de numeros manualment
        } else if (premis[0] == 99999) {
            aproximacio_tercer_premi_per_sobra = 00000;
        }

        if (numeroUsuari == aproximacio_tercer_premi_per_sota || numeroUsuari == aproximacio_tercer_premi_per_sobra) {
            result = true;
            PREMI_ACUMULAT += PREMIAPROX;
            array_PremisSecundaris[0].toca = true;
        }

        return result;
    }

    /**
     *
     * @param array_PremisSecundaris
     */
    
    public static void DesglosarPremi(PremiSecundari[] array_PremisSecundaris) throws IOException {
        System.out.println(Utilities.LlegirLineaConcreta(30, PathIdioma) + PREMI_ACUMULAT + "€");
        //Enhorabona, has aconseguit:
        System.out.println(Utilities.LlegirLineaConcreta(31, PathIdioma));
        //Vols desglosar el teu premi?
        boolean noSecundaris = true;
       
        if (scan.next().equals("Si")) {
            //Recorrem el array de premis secundaris i anem imprimint els premis que han tocat
           
            for (int i = 0; i < array_PremisSecundaris.length; i++) {
                if (array_PremisSecundaris[i].toca) {
                    noSecundaris = false;
                    System.out.println(array_PremisSecundaris[i].missatge_premi);
                }
            }
            if (noSecundaris) { //si no hi han premis secundaris os diem tambe
                System.out.println(Utilities.LlegirLineaConcreta(32, PathIdioma));
                //No tens ningun premi secundari
            }
        }
    }
}
