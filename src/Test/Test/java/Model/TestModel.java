package Test.Test.java.Model;

import Model.Job;
import Model.PickedJobs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TestModel {

    PickedJobs pickedJobs;
    Job j;

    @BeforeEach
    void setUp() throws IOException {
        pickedJobs = new PickedJobs(); //per vedere se funziona l'add con picked jobs
        j = new Job( "ddfb088a-92d4-4d82-aad3-87de06b3d999", "Full Time", new URL("https://jobs.github.com/positions/ddfb088a-92d4-4d82-aad3-87de06b3d999"),
                "Thu Dec 17 13:55:57 UTC 2020", "ITS Berlin GmbH", new URL("https://de.dpdhl.jobs/itsgermany"), "Berlin", "(Senior) DevOps and IT Operations Engineer (m/w/d)",
                "<p>Was machen wir genau? bliche Erfahrung ciao","https://itsberlin.join.com/jobs/1656912-senior-devops-and-it-operations-engineer-m-w-d?pid=71fbd6149a82b0b128ee&utm_source=stackoverflow&utm_medium=paid&utm_campaign=stackoverflow&utm_content=senior%2Bdevops%2Band%2Bit%2Boperations%2Bengineer%2Bm%2Bw%2Bd",
                null);

        //ho creato un oggetto di tipo job con campi presi da una richiesta postman
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    @DisplayName("Test: Verifichiamo se l'add funziona")
    void Test1() throws IOException {
        assertThrows(IOException.class, ()->pickedJobs.add(j)); //verifica se aggiunge il lavoro, se non funziona ho l'eccezione
    }
}
