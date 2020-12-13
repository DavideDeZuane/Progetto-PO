import Controller.ApiController;
import Controller.Lavoro;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String url = "";
        ApiController controller = null;
        HashSet<Lavoro> offerte = new HashSet<>();
        try {
            Scanner input = new Scanner(System.in);

            do {
                 System.out.println("Inserisci l'url");
                 url = input.nextLine();
                 controller = new ApiController(url);
            }while(!controller.isValidUrl(url));
            /*offerte = (ArrayList<Lavoro>) controller.parsing();
            for(Lavoro l: offerte) {
                System.out.println(l);
            }
            */
            offerte.addAll((HashSet)controller.parsing());
            for(Lavoro l: offerte)
                System.out.println(l);
        }catch(MalformedURLException e) {
            System.out.println("Url non valido");
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Problema nella deserializzazione");
        }


    }
}
