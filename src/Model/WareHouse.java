package Model;

import Controller.ApiController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;

public class WareHouse extends JobBoard{

    private ApiController controller;

    public HashSet<Job> getJobs() {
        return jobs;
    }

    @Override
    public String toString() {
        return "WareHouse{" +
                "jobs=" + jobs +
                '}';
    }

    public WareHouse() throws MalformedURLException {
        numOfJobs = 0;
        //controller = new ApiController("https://jobs.github.com/positions.json/");
    }

    public int fill() throws IOException {
        if(this.jobs!=null)
        {
            this.jobs.clear();
        }
        //this.jobs= (HashSet<Job>) this.controller.parsing();
        numOfJobs = jobs.size();
        return numOfJobs;
    }
}
