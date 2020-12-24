package Test.Test.java.Model;


import Model.Job;
import Model.StatsJobBoard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class TestStats {

    StatsJobBoard statsJobBoard;
    HashSet<Job>jobs;
    Job j;
    Double [] d1;
    Double [] d2;

    @BeforeEach
    void setUp() throws MalformedURLException {
        statsJobBoard = new StatsJobBoard();
        jobs = new HashSet<>();
        j = new Job( "ddfb088a-92d4-4d82-aad3-87de06b3d999", "Full Time", new URL("https://jobs.github.com/positions/ddfb088a-92d4-4d82-aad3-87de06b3d999"),
                "Thu Dec 17 13:55:57 UTC 2020", "ITS Berlin GmbH", new URL("https://de.dpdhl.jobs/itsgermany"), "Berlin", "(Senior) DevOps and IT Operations Engineer (m/w/d)",
                "<p>Was machen wir genau? bliche Erfahrung ciao","https://itsberlin.join.com/jobs/1656912-senior-devops-and-it-operations-engineer-m-w-d?pid=71fbd6149a82b0b128ee&utm_source=stackoverflow&utm_medium=paid&utm_campaign=stackoverflow&utm_content=senior%2Bdevops%2Band%2Bit%2Boperations%2Bengineer%2Bm%2Bw%2Bd",
                null);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    @DisplayName("Test: testiamo il metodo dateOfCreation")
    void Test1() throws IOException {
        //assertEquals(statsJobBoard.dateOfCreation(3),0);;
        jobs.add(j);
        statsJobBoard.setJobs(jobs);
        assertAll("STATS1",
                ()->assertEquals(0, statsJobBoard.dateOfCreation(3)),
                ()-> assertEquals(0, statsJobBoard.dateOfCreation(5)),
                ()-> assertEquals(1, statsJobBoard.dateOfCreation(20)));
        //il test è passato
    }

    @Test
    @DisplayName("Test: testiamo il metodo calculatePercentage")
    void Test2()
    {
        jobs.add(j);
        statsJobBoard.setJobs(jobs);
        assertAll("STATS2",
                ()->assertEquals(statsJobBoard.calculatePercentage(), 100));
    }

    @Test
    @DisplayName("Test: testiamo il metodo keyWords")
    void Test3()
    {
        jobs.add(j);
        statsJobBoard.setJobs(jobs);
        //statsJobBoard.keyWords("ciao");
        assertEquals(1, statsJobBoard.keyWords("ciao"));
    }
}