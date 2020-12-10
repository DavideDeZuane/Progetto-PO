import Controller.ApiController;
import Controller.Lavoro;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "";
        ApiController controller = null;
        try {
            Scanner input = new Scanner(System.in);

            do {
                 System.out.println("Inserisci l'url");
                 url = input.nextLine();
                 controller = new ApiController(url);

            }while(!controller.isValidUrl(url));
        }catch(MalformedURLException e){
            System.out.println("Url non valido");
            e.printStackTrace();
        }

        try {
             Lavoro offerta = controller.parsing(controller.getUrl());
             System.out.println(offerta);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Problema nella deserializzazione");
        }


    }
}
