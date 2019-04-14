import java.util.*;

public class Es2_NoMultipli {

    public static void main(String[] args) {

        double vg = inputSelezione("Vuoi inserire il valore del generatore massimo [rispondere \"Vmax\"] oppure il suo valore efficace [rispondere \"Veff\"]: ",
                "Inserire il valore massimo del generatore [Vmax]: ", "Inserire il valore efficace del generatore [Veff]: ", "Vmax", "Veff");
        double f = input("Inserire il valore della frequenza del generatore [f]: ", "f");
        double r = input("Inserire il valore della resistenza [R]: ", "R");
        double c = input("Inserire il valore del condensatore [C]: ", "C");
        System.out.println();

        System.out.println("INPUT:");
        System.out.println("Tensione del generatore [Veff]: "+ vg+ "V");
        System.out.println("Frequenza del generatore [f]: "+ f +"Hz");
        System.out.println("Resistenza [R]: "+ r+ "Ohm");
        System.out.println("Condensatore [C]: "+ c+ "F");
        System.out.println();

        double xc = 1 / ((2 * Math.PI) * f * c);
        double xr = r;
        double ztot = Math.sqrt((r * r) + (xc * xc));
        double itot = vg / ztot;
        double vr = itot * xr;
        double vc = itot * xc;

        System.out.println();
        System.out.println("OUTPUT:");
        System.out.println("Impedenza totale [Ztot]: "+ ztot+ " Ohm");
        System.out.println("Impedenza del condensatore [Xc]: "+ xc+ " Ohm");
        System.out.println("Impedenza reistiva [Xr]: "+ xr+ " Ohm");
        System.out.println("Corrente efficace totale [Itot]: "+ itot+ " A");
        System.out.println("Tensione efficace resistiva [VR]: "+ vr+ " V");
        System.out.println("Tensione efficace del condensatore [VC]: "+ vc+ " V");
    }

    public static double input(String richiesta, String dato) {
        Scanner in = new Scanner(System.in);
        System.out.print(richiesta);
        double inp = in.nextDouble();

        System.out.printf("%s = %f confermi? [\"ok\" per confermare]\n", dato, inp);
        String conferma = in.next();
        if (!(conferma.equalsIgnoreCase("ok"))) {
            System.err.println("Hai annullato l'input, riavvia il programma.");
            System.exit(-1);
        }
        return inp;
    }

    public static double inputSelezione(String selezione, String richiesta1, String richiesta2, String dato1, String dato2) {
        Scanner in = new Scanner(System.in);
        System.out.print(selezione);
        String scelta = in.nextLine();

        if (scelta.equalsIgnoreCase(dato1)) {
            System.out.print(richiesta1);
            double inp = in.nextDouble();

            // CONVERSIONE DA VMAX A VEFF

            double inp2 = inp / Math.sqrt(2);

            System.out.printf("%s = %f confermi? [\"ok\" per confermare]\n", dato1, inp);
            String conferma = in.next();
            if (!(conferma.equalsIgnoreCase("ok"))) {
                System.err.println("Hai annullato l'input, riavvia il programma.");
                System.exit(-1);
            }
            return inp2;

        } else if (scelta.equalsIgnoreCase(dato2)) {
            System.out.print(richiesta2);
            double inp = in.nextDouble();

            System.out.printf("%s = %s confermi? [\"ok\" per confermare]\n", dato2, inp);
            String conferma = in.next();
            if (!(conferma.equalsIgnoreCase("ok"))) {
                System.err.println("Hai annullato l'input, riavvia il programma.");
                System.exit(-1);
            }
            return inp;
        } else {
            System.err.printf("Risposta non valida, riavvia il programma");
            System.exit(-1);
        }
        return -1;
    }

}