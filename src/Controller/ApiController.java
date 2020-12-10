package Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

//questa classe si occuperà di effettuare ed elaborare le chiamate all'Api
public class ApiController {
    private ObjectMapper mapper;
    private URL url;

    public ApiController(String url) throws MalformedURLException{
        mapper = new ObjectMapper();
        setUrl(url);

    }
    public void setUrl(String url) throws MalformedURLException {
        if(isValidUrl(url))
            this.url = new URL(url);
        else
            System.out.println("Vi sono errori di sintassi nell'URL");
    }

    public Lavoro parsing( URL url) throws IOException {
         return mapper.readValue(url, Lavoro.class);
    }

    public boolean isValidUrl(String url){
        try{
            URL obj = new URL(url);
            obj.toURI(); //converte l'URL in URI, se l'URL non è nel formato specificato generano un errore se convertiti in URI
            return true;
        }catch(MalformedURLException e){
            return false;
        }catch(URISyntaxException e){
            return false;
        }

    }
    public URL getUrl(){ return url;}
}
