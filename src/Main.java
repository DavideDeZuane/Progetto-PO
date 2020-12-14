import Controller.ApiController;
import Controller.Lavoro;
import Controller.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import Controller.*;

public class Main {
    public static void main(String[] args) {
        String url = "";
        String[] param = new String[2];

        ApiController controller = null;
        HashSet<Lavoro> offerte = new HashSet<>();
        try {
            Scanner input = new Scanner(System.in);

            do {
                 System.out.print("Inserisci la località: ");
                 param[0] = input.nextLine();
                 System.out.print("Inserisci la località: ");
                 param[1] = input.nextLine();
                 EnumSet<PARAMETERES> flags = EnumSet.of(PARAMETERES.LOCATION, PARAMETERES.TITLE);
                 url = ApiController.query(param,flags);
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
