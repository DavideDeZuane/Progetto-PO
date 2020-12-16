package MainProgram;

import Controller.ApiController;
//import Controller.Lavoro;

import GUI.BootStrapPanel;

public class Main {
/*
    static{
        System.out.println("Ciao sono prima del programma");
        try {
            ApiController.verifyOffer("968d2ec9-80d6-4902-90fb-79478aeb1271");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) throws Exception{
        BootStrapPanel bootstrapPanel = null;
        bootstrapPanel = new BootStrapPanel();
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
