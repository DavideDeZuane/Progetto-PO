package Model;


import org.jsoup.Jsoup;

import java.net.URL;

/**
 * Job class
 */
public class Job {
    private String id;
    private String type;
    private URL url;
    private String created_at;
    private String company;
    private URL company_url;
    private String location;
    private String title;
    private String description;
    private String how_to_apply;
    private URL company_logo;

    /**
     * default constructor
     */
    public Job() {}

    /**
     *
     * @param id is the id of the Job
     * @param type is the type of the Job (for example Full Time or Part Time)
     * @param url is the url of the Job
     * @param created_at is the date when the job was created
     * @param company name of the company that offers the Job
     * @param company_url is the url of the company website
     * @param location is the location where the job takes place
     * @param title is the title of the Job
     * @param description describes what the Job is like
     * @param how_to_apply are instructions to apply for the Job
     * @param company_logo is the company logo
     */
    public Job(String id, String type, URL url, String created_at, String company, URL company_url, String location, String title, String description, String how_to_apply, URL company_logo) {
        this.id = id;
        this.type = type;
        this.url = url;
        this.created_at = created_at;
        this.company = company;
        this.company_url = company_url;
        this.location = location;
        this.title = title;
        this.description = description;
        this.how_to_apply = how_to_apply;
        this.company_logo = company_logo;
    }

    /**
     * this method gets the Job id
     * @return the id of the Job
     */
    public String getId() {
        return id;
    }

    /**
     * this method gets the type of the Job
     * @return the type of the Job
     */
    public String getType() {
        return type;
    }

    /**
     * this method gets the url of the offer
     * @return the url of the offer
     */
    public URL getUrl() {
        return url;
    }

    /**
     * this method gets the date of creation of the offer
     * @return the date of creation of the offer
     */
    public String getCreated_at() {
        return created_at;
    }

    /**
     * this method gets the company name
     * @return the company name
     */
    public String getCompany() {
        return company;
    }

    /**
     * this method gets the company url
     * @return the company url
     */
    public URL getCompany_url() {
        return company_url;
    }

    /**
     * this method gets the Job location
     * @return the Job location
     */
    public String getLocation() {
        return location;
    }

    /**
     * this method gets the title of the Job
     * @return the title of the Job
     */
    public String getTitle() {
        return title;
    }

    /**
     * this method gets the Job description
     * @return the Job description
     */
    public String getDescription() {
        return description;
    }

    /**
     * this method gets the instructions to apply for the Job
     * @return the instructions to apply for the Job
     */
    public String getHow_to_apply() {
        return Jsoup.parse(how_to_apply).text();
    }

    /**
     * this method gets the company logo
     * @return the company logo
     */
    public URL getCompany_logo() {
        return company_logo;
    }



    /**
     * this method sets the id of the Job
     * @param id id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * this method sets the type of the Job
     * @param type type to set (es. Full Time)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * this method sets the url of the Job
     * @param url url to set
     */
    public void setUrl(URL url) {
        this.url = url;
    }

    /**
     * this method sets the date of creation of the Job
     * @param created_at date of creation to set
     */
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    /**
     * this method sets the company name of the Job
     * @param company company name to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * this method sets the company url of the Job
     * @param company_url company url of the Job to set
     */
    public void setCompany_url(URL company_url) {
        this.company_url = company_url;
    }

    /**
     * this method sets the location of the Job
     * @param location location of the Job to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * this method sets the title of the Job
     * @param title title of the Job to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * this method sets sets the Job description of the Job
     * @param description description of the Job to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * this method sets the instructions to apply for the Job
     * @param how_to_apply instructions to apply for the Job to set
     */
    public void setHow_to_apply(String how_to_apply) {
        this.how_to_apply = how_to_apply;
    }

    /**
     * this method sets the company logo of the Job
     * @param company_logo company logo to set
     */
    public void setCompany_logo(URL company_logo) {
        this.company_logo = company_logo;
    }

    /**
     * this method prints out some attributes of the class Job
     * @return the type, the company, the location, the created_at and the description attributes of a Job
     */
    @Override
    public String toString() {
        return "Job{" +
                ", type='" + type + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", created_At='" + created_at + '\'' +
                ", Description='" + description + '\'' +
                '}';
    }


}