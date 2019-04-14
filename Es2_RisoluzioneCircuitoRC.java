import java.util.*;

public class Es2_RisoluzioneCircuitoRC {

    public static void main(String[] args) {
        premessa();
        pressEnter();

        // INPUT:

        double vg = inputSelezione("Vuoi inserire il valore del generatore massimo [rispondere \"Vmax\"] oppure il suo valore efficace [rispondere \"Veff\"]: ",
                "Inserire il valore massimo del generatore [Vmax]: ", "Inserire il valore efficace del generatore [Veff]: ", "Vmax", "Veff", "V");
        double f = input("Inserire il valore della frequenza del generatore [f]: ", "f", "Hz");
        double r = input("Inserire il valore della resistenza [R]: ", "R", "Ohm");
        double c = input("Inserire il valore del condensatore [C]: ", "C", "F");
        System.out.println();

        System.out.println("\\-----------------------------------------INPUT-----------------------------------------/");
        recapInput("Tensione del generatore [Veff]: ", vg, "V");
        recapInput("Frequenza del generatore [f]: ", f, "Hz");
        recapInput("Resistenza [R]: ", r, "Ohm");
        recapInput("Condensatore [C]: ", c, "F");
        System.out.println("\\---------------------------------------------------------------------------------------/");
        System.out.println();

        pressEnter();

        // CALCOLI:

        double xc = 1 / ((2 * Math.PI) * f * c);
        double xr = r;
        double ztot = Math.sqrt((r * r) + (xc * xc));
        double itot = vg / ztot;
        double vr = itot * xr;
        double vc = itot * xc;

        // OUTPUT:

        System.out.println();
        System.out.println("\\-----------------------------------------OUTPUT-----------------------------------------/");
        output("Impedenza totale [Ztot]: ", ztot, "Ohm");
        output("Impedenza del condensatore [Xc]: ", xc, "Ohm");
        output("Impedenza reistiva [Xr]: ", xr, "Ohm");
        output("Corrente efficace totale [Itot]: ", itot, "A");
        output("Tensione efficace resistiva [VR]: ", vr, "V");
        output("Tensione efficace del condensatore [VC]: ", vc, "V");
        System.out.println("\\----------------------------------------------------------------------------------------/");
    }

    public static void premessa() {
        System.out.println("\\-----------------------------------------------------------------------------------------------------------/");
        System.out.println("Questo programma supporta i multipli [T, G, M, k, h, da] e i sottomultipli [d, c, m, u, n, p]");
        delay(1500);
        System.out.println("[T]-> Tera = 10^12");
        System.out.println("[G]-> Giga = 10^9");
        System.out.println("[M]-> Mega = 10^6");
        System.out.println("[k]-> Kilo = 10^3");
        System.out.println("[h]-> Hecto = 10^2");
        System.out.println("[da]-> Deca = 10^1");
        System.out.println("[d]-> Deci = 10^-1");
        System.out.println("[c]-> Centi = 10^-2");
        System.out.println("[m]-> Milli = 10^-3");
        System.out.println("[u]-> Micro = 10^-6");
        System.out.println("[n]-> Nano = 10^-9");
        System.out.println("[p]-> Pico = 10^-12");
        delay(1500);
        System.out.println("Attenzione alle maiuscole/minuscole per il Mega/Milli [M/m] e per Hecto/Hertz [h/Hz]," +
                "\ngli altri possono essere inseriti ugualmente.");
        System.out.println("\\-----------------------------------------------------------------------------------------------------------/");
    }

    public static double input(String richiesta, String dato, String unita) {
        Scanner in = new Scanner(System.in);
        System.out.print(richiesta);
        String input = in.nextLine();
        if (!(input.contains(" "))) {
            System.err.printf("\"%s\" non puo' essere il valore di %s, riprova lasciando lo spazio tra la parte numerica e l'unita' di misura.\n", input, dato);
            double inpConv = input(richiesta, dato, unita);
            return inpConv;
        }
        if (input.contains(",")) {
            System.err.printf("\"%s\" non puo' essere il valore di %s, riprova utilizzando il punto come separatore decimale.\n", input, dato);
            double inpConv = input(richiesta, dato, unita);
            return inpConv;
        }
        String[] parti = input.split(" ");
        String parteLett = parti[1];
        double parteNum = Double.parseDouble(parti[0]);

        double inpConv = -1;


        if ("T".equals(parteLett.substring(0, 1))) {
            inpConv = parteNum * Math.pow(10, 12);
        } else if ("G".equalsIgnoreCase(parteLett.substring(0, 1))) {
            inpConv = parteNum * Math.pow(10, 9);
        } else if ("M".equals(parteLett.substring(0, 1))) {
            inpConv = parteNum * Math.pow(10, 6);
        } else if ("k".equalsIgnoreCase(parteLett.substring(0, 1))) {
            inpConv = parteNum * Math.pow(10, 3);
        } else if ("h".equals(parteLett.substring(0, 1))) {
            inpConv = parteNum * Math.pow(10, 2);
        } else if ("d".equalsIgnoreCase(parteLett.substring(0, 1))) {
            if ("a".equals(parteLett.substring(1, 2))) {
                inpConv = parteNum * Math.pow(10, 1);
            } else {
                inpConv = parteNum * Math.pow(10, -1);
            }
        } else if ("c".equalsIgnoreCase(parteLett.substring(0, 1))) {
            inpConv = parteNum * Math.pow(10, -2);
        } else if ("m".equals(parteLett.substring(0, 1))) {
            inpConv = parteNum * Math.pow(10, -3);
        } else if ("u".equalsIgnoreCase(parteLett.substring(0, 1))) {
            inpConv = parteNum * Math.pow(10, -6);
        } else if ("n".equalsIgnoreCase(parteLett.substring(0, 1))) {
            inpConv = parteNum * Math.pow(10, -9);
        } else if ("p".equalsIgnoreCase(parteLett.substring(0, 1))) {
            inpConv = parteNum * Math.pow(10, -12);
        } else if ((unita.substring(0, 1)).equalsIgnoreCase(parteLett.substring(0, 1))) {
            inpConv = parteNum;
        } else {
            System.err.printf("\"%s\" non contiene il multiplo o l'unita' di misura, riprova inserendo il multiplo [T, G, M, k, h, da, d, c, m, u, n, p] o l'unita' di misura [%s].\n", input, unita);
            inpConv = input(richiesta, dato, unita);
            return inpConv;
        }

        System.out.printf("Hai appena inserito %s = %s confermi? [\"ok\" per confermare // qualsiasi altra stringa per rifiutare.]\n", dato, input);
        String conferma = in.next();
        if (!(conferma.equalsIgnoreCase("ok"))) {
            System.err.println("Hai annullato l'input, riprova.");
            inpConv = input(richiesta, dato, unita);
            return inpConv;
        }
        return inpConv;
    }

    public static double inputSelezione(String selezione, String richiesta1, String richiesta2, String dato1, String dato2, String unita) {
        Scanner in = new Scanner(System.in);
        System.out.print(selezione);
        String scelta = in.nextLine();

        if (scelta.equalsIgnoreCase(dato1)) {
            System.out.print(richiesta1);
            String input = in.nextLine();
            if (!(input.contains(" "))) {
                System.err.printf("\"%s\" non puo' essere il valore di %s, riprova lasciando lo spazio tra la parte numerica e l'unita' di misura.\n", input, dato1);
                double inpConv = input(richiesta1, dato1, unita);
                return inpConv;
            }
            if (input.contains(",")) {
                System.err.printf("\"%s\" non puo' essere il valore di %s, riprova utilizzando il punto come separatore decimale.\n", input, dato1);
                double inpConv = input(richiesta1, dato1, unita);
                return inpConv;
            }
            String[] parti = input.split(" ");
            String parteLett = parti[1];
            double parteNum = Double.parseDouble(parti[0]);

            double inpConv = -1;


            if ("T".equalsIgnoreCase(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, 12);
            } else if ("G".equalsIgnoreCase(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, 9);
            } else if ("M".equals(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, 6);
            } else if ("k".equalsIgnoreCase(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, 3);
            } else if ("h".equals(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, 2);
            } else if ("d".equalsIgnoreCase(parteLett.substring(0, 1))) {
                if ("a".equals(parteLett.substring(1, 2))) {
                    inpConv = parteNum * Math.pow(10, 1);
                } else {
                    inpConv = parteNum * Math.pow(10, -1);
                }
            } else if ("c".equalsIgnoreCase(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, -2);
            } else if ("m".equals(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, -3);
            } else if ("u".equalsIgnoreCase(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, -6);
            } else if ("n".equalsIgnoreCase(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, -9);
            } else if ("p".equalsIgnoreCase(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, -12);
            } else if ((unita.substring(0, 1)).equalsIgnoreCase(parteLett.substring(0, 1))) {
                inpConv = parteNum;
            } else {
                System.err.printf("\"%s\" non contiene il multiplo o l'unita' di misura, riprova inserendo il multiplo [T, G, M, k, h, da, d, c, m, u, n, p] o l'unita' di misura [%s].\n", input, unita);
                inpConv = input(richiesta1, dato1, unita);
                return inpConv;
            }

            // CONVERSIONE DA VMAX A VEFF

            double inp2 = inpConv / Math.sqrt(2);

            System.out.printf("Hai appena inserito %s = %s confermi? [\"ok\" per confermare // qualsiasi altra stringa per rifiutare.]\n", dato1, input);
            String conferma = in.next();
            if (!(conferma.equalsIgnoreCase("ok"))) {
                System.err.println("Hai annullato l'input, riprova.");
                inp2 = input(richiesta1, dato1, unita);
                return inp2;
            }
            return inp2;

        } else if (scelta.equalsIgnoreCase(dato2)) {
            System.out.print(richiesta2);
            String input = in.nextLine();
            if (!(input.contains(" "))) {
                System.err.printf("\"%s\" non puo' essere il valore di %s, riprova lasciando lo spazio tra la parte numerica e l'unita' di misura.\n", input, dato2);
                double inpConv = input(richiesta2, dato2, unita);
                return inpConv;
            }
            if (input.contains(",")) {
                System.err.printf("\"%s\" non puo' essere il valore di %s, riprova utilizzando il punto come separatore decimale.\n", input, dato2);
                double inpConv = input(richiesta2, dato2, unita);
                return inpConv;
            }
            String[] parti = input.split(" ");
            String parteLett = parti[1];
            double parteNum = Double.parseDouble(parti[0]);

            double inpConv = -1;


            if ("T".equals(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, 12);
            } else if ("G".equals(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, 9);
            } else if ("M".equals(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, 6);
            } else if ("k".equals(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, 3);
            } else if ("h".equals(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, 2);
            } else if ("d".equals(parteLett.substring(0, 1))) {
                if ("a".equals(parteLett.substring(1, 2))) {
                    inpConv = parteNum * Math.pow(10, 1);
                } else {
                    inpConv = parteNum * Math.pow(10, -1);
                }
            } else if ("c".equals(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, -2);
            } else if ("m".equals(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, -3);
            } else if ("u".equals(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, -6);
            } else if ("n".equals(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, -9);
            } else if ("p".equals(parteLett.substring(0, 1))) {
                inpConv = parteNum * Math.pow(10, -12);
            } else if ((unita.substring(0, 1)).equalsIgnoreCase(parteLett.substring(0, 1))) {
                inpConv = parteNum;
            } else {
                System.err.printf("\"%s\" non contiene il multiplo o l'unita' di misura, riprova inserendo il multiplo [T, G, M, k, h, da, d, c, m, u, n, p] o l'unita' di misura [%s].\n", input, unita);
                inpConv = input(richiesta2, dato2, unita);
                return inpConv;
            }

            System.out.printf("Hai appena inserito %s = %s confermi? [\"ok\" per confermare // qualsiasi altra stringa per rifiutare.]\n", dato2, input);
            String conferma = in.next();
            if (!(conferma.equalsIgnoreCase("ok"))) {
                System.err.println("Hai annullato l'input, riprova.");
                inpConv = input(richiesta2, dato2, unita);
                return inpConv;
            }
            return inpConv;
        } else {
            System.err.printf("Risposta non valida, rispondi %s o %s, riprova.\n", dato1, dato2);
            double inp = inputSelezione(selezione, richiesta1, richiesta2, dato1, dato2, unita);
            return inp;
        }

    }

    public static void recapInput(String richiesta, double val, String unita) {
        int i;
        for (i = 0; val < 1; i++) {
            val *= Math.pow(10,3);
        }
        if (val >= Math.pow(10,3) && val < Math.pow(10,6)) {
            val /= Math.pow(10,3);
            System.out.printf("%s %.3f k%s\n", richiesta, val, unita);
        } else if (val >= Math.pow(10,6) && val < Math.pow(10,9)) {
            val /= Math.pow(10,6);
            System.out.printf("%s %.3f M%s\n", richiesta, val, unita);
        } else if (val >= Math.pow(10,9) && val < Math.pow(10,12)) {
            val /= Math.pow(10,9);
            System.out.printf("%s %.3f G%s\n", richiesta, val, unita);
        } else if (val >= Math.pow(10,12)) {
            val /= Math.pow(10,12);
            System.out.printf("%s %.3f T%s\n", richiesta, val, unita);
        }else if (i == 1) {
            System.out.printf("%s %.3f m%s\n", richiesta, val, unita);
        } else if (i == 2) {
            System.out.printf("%s %.3f u%s\n", richiesta, val, unita);
        } else if (i == 3) {
            System.out.printf("%s %.3f n%s\n", richiesta, val, unita);
        } else if (i == 4) {
            System.out.printf("%s %.3f p%s\n", richiesta, val, unita);
        } else {
            System.out.printf("%s %.3f %s\n", richiesta, val, unita);
        }
    }

    public static void output(String richiesta, double val, String unita) {
        int i;
        for (i = 0; val < 1; i++) {
            val *= Math.pow(10,3);
        }
        if (val >= Math.pow(10,3) && val < Math.pow(10,6)) {
            val /= Math.pow(10,3);
            System.out.printf("%s %.3f k%s\n", richiesta, val, unita);
        } else if (val >= Math.pow(10,6) && val < Math.pow(10,9)) {
            val /= Math.pow(10,6);
            System.out.printf("%s %.3f M%s\n", richiesta, val, unita);
        } else if (val >= Math.pow(10,9) && val < Math.pow(10,12)) {
            val /= Math.pow(10,9);
            System.out.printf("%s %.3f G%s\n", richiesta, val, unita);
        } else if (val >= Math.pow(10,12)) {
            val /= Math.pow(10,12);
            System.out.printf("%s %.3f T%s\n", richiesta, val, unita);
        }else if (i == 1) {
            System.out.printf("%s %.3f m%s\n", richiesta, val, unita);
        } else if (i == 2) {
            System.out.printf("%s %.3f u%s\n", richiesta, val, unita);
        } else if (i == 3) {
            System.out.printf("%s %.3f n%s\n", richiesta, val, unita);
        } else if (i == 4) {
            System.out.printf("%s %.3f p%s\n", richiesta, val, unita);
        } else {
            System.out.printf("%s %.3f %s\n", richiesta, val, unita);
        }
    }

    public static void delay(long tempo) {
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException ignored) {
        }
    }

    public static void pressEnter() {
        Scanner keyIn = new Scanner(System.in);
        System.out.print("Premere INVIO per continuare");
        keyIn.nextLine();
    }

}