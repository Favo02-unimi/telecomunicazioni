import java.util.*;

public class Es1_ConvertitoreLetteraleNumerico {

    //INCOMPLETO

    public static void main(String[] args) {
        Scanner in =  new Scanner (System.in);
        System.out.print("Vuoi convertire da un valore decimale o da un valore con parte letterale [DECIMALE / LETTERALE]: ");
        String risposta = in.nextLine();

        if (risposta.equalsIgnoreCase("decimale")){
            decimale();
        } else if (risposta.equalsIgnoreCase("letterale")){
            letterale();
        } else {
            System.err.println("Devi inserire se vuoi convertire da un valore decimale o con parte letterale [rispondere: LETTERALE / DECIMALE]");
        }
    }

    public static void decimale(){
        Scanner in =  new Scanner (System.in);
        System.out.print("Inserire il valore del condensatore: ");
        double condensatore = in.nextDouble();

        if (condensatore < 0) {
            condensatore *= Math.pow(10,3);
            System.out.printf("Il condensatore equivale a %.2f mF\n", condensatore);
        } else if (condensatore < Math.pow(10,-3)) {
            condensatore *= Math.pow(10,6);
            System.out.printf("Il condensatore equivale a %.2f uF\n", condensatore);
        } else if (condensatore < Math.pow(10,-6)) {
            condensatore *= Math.pow(10,9);
            System.out.printf("Il condensatore equivale a %.2f nF\n", condensatore);
        } else if (condensatore < Math.pow(10,-9)) {
            condensatore *= Math.pow(10,12);
            System.out.printf("Il condensatore equivale a %.2f pF\n", condensatore);
        }
    }

    public static void letterale(){
        Scanner in = new Scanner(System.in);
        System.out.print("Inserire il valore del condensatore: ");
        String input = in.nextLine();
        if (!(input.contains(" "))){
            System.err.printf("\"%s\" non puo' essere il valore del condensatore, riprova lasciando lo spazio tra la parte numerica e l'unita' di misura.\n", input);
            letterale();
            return;
        }
        if (input.contains(",")){
            System.err.printf("\"%s\" non puo' essere il valore del condensatore, riprova utilizzando il punto come separatore decimale.\n", input);
            letterale();
            return;
        }
        String[] parti = input.split(" ");
        String parteLett = parti[1];
        double parteNum = Double.parseDouble(parti[0]);

        if ((parteLett.substring(0,1)).equalsIgnoreCase("m")) {
            double base = parteNum*Math.pow(10,-3);
            System.out.printf("%s = %f TF\n", input, (base*Math.pow(10,-12)));
            System.out.printf("%s = %f F\n", input, base);
        } else if ((parteLett.substring(0,1)).equalsIgnoreCase("u")) {
            double base = parteNum*Math.pow(10,-6);
        } else if ((parteLett.substring(0,1)).equalsIgnoreCase("n")) {
            double base = parteNum*Math.pow(10,-9);
        } else if ((parteLett.substring(0,1)).equalsIgnoreCase("p")) {
            double base = parteNum*Math.pow(10,-12);
        }
    }
}