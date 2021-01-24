package Test.Test.java.Controller;


import Controller.ApiController;
import Controller.*;
import Model.Job;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.EnumSet;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class TestController {

    ApiController apiController;
    EnumSet<Parameters> flag;
    String[] tmp = {"java", "Berlino"};
    String urlsbagliato;
    HashSet<Job> jobs;
    URL url;

    @BeforeEach
    void setUp() throws MalformedURLException {
        apiController = new ApiController();
        flag = EnumSet.of(Parameters.LOCATION, Parameters.TYPE, Parameters.DESCRIPTION);
        urlsbagliato = "jobs.github.com/Qualcosa.json"; //url non formattato correttamente
        jobs = new HashSet<>();
        url = new URL("https://jobs.github.com/positions/b1691d49-ee1d-4793-9c1d-7a10a9b8b84a.json");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test: verifica validità url")
    void test1(){
        assertThrows(MalformedURLException.class, ()->ApiController.createUrl(urlsbagliato)); //con questo metodo forzo l'eccezione
        //poichè l'URL non è ben formattato (manca lo schema) genera un'eccezione
    }

    @Test
    @DisplayName("Test: lettura file config.properties")
    void test2() throws IOException {
        //assertThrows(IOException.class, ()->Controller.readProp());
        assertDoesNotThrow(()->Controller.readProp()); //controllo se trova il file readConfig, se lo trova
        //non deve generare l'eccezione
    }

    @Test
    @DisplayName("Test: verifica proprietà file config.properties")
    void test3()
    {
        assertEquals(Controller.baseUrl, Controller.getProp().getProperty("url")); //verifica se legge correttamente i campi del file config
    }

    @Test
    @DisplayName("Test: verifica parsing json")
    void test4()
    {
        assertThrows(MismatchedInputException.class, ()->jobs.addAll(apiController.getMapper().readValue(url, new TypeReference<HashSet<Job>>() {})));
        //abbiamo verificato che l'eccezione è stata lanciata in quanto si cerca di leggere un json object come un json array
        //mi aspetto un eccezione perchè tento di leggere un oggetto come fosse un json array per mezzo della collection hashset
        //vedi apicontoller
    }
}