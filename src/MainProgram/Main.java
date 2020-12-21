
package MainProgram;

//import Controller.Lavoro;

//import GUI.BootStrapPanel;

import Controller.CheckOffer;
import GUI.BootStrapPanel;

public class Main {

    static{
        System.out.println("Ciao sono prima del programma");
        try {
            CheckOffer checkOffer = new CheckOffer();
            Thread thread = new Thread(checkOffer, "Thread Check offer"); //si alterna al main
            thread.setPriority(Thread.NORM_PRIORITY); //nella jvm c'è un settore che decide l'ordine in cui chiamare i thread
            //senza specifiche la priorità è normal
            thread.start(); //nuovo stack dedicato al thread e lancia run()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        /*
        JobBoard offerte = new JobBoard();
        ApiController controller = new ApiController();
        String[] filtri = new String[10];
        Scanner input = new Scanner(System.in);
        System.out.print("Inserisci la location");
        filtri[0] = input.nextLine();
        parametri.add(Parameters.LOCATION);
        URL url =  ApiController.query(filtri, parametri);
        controller.fill(offerte, url);
        System.out.println(offerte);*/
        BootStrapPanel bootStrapPanel = new BootStrapPanel();
/*
        URL url = null;
        String[] param = new String[10];

        ApiController controller = new ApiController();
        HashSet<Job> offerte = new HashSet<>();
        try {
            Scanner input = new Scanner(System.in);

            do {
                System.out.print("Inserisci location: ");
                param[0] = input.nextLine();
                //System.out.print("Inserisci longitudine: ");
                //param[1] = input.nextLine();
                //System.out.print("Inserisci descrizione: ");
                //param[2] = input.nextLine();
                EnumSet<PARAMETERES> flags = EnumSet.of(PARAMETERES.LOCATION);
                url = ApiController.query(param, flags);
                break;
            }while(true);
            offerte = (ArrayList<Lavoro>) controller.parsing();
            for(Lavoro l: offerte) {
                System.out.println(l);
            }

            try {
                offerte.addAll(controller.parsing(url));
            }catch(Exception e){
            }
            for(Job l: offerte)
                System.out.println(l);
        }catch(MalformedURLException e) {
            System.out.println("Url non valido");
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Problema nella deserializzazione");
        }
*/

    }
}

