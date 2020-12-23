package Model;


import org.jsoup.Jsoup;

import java.net.URL;


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
     *
     * @return the id of the Job
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return the type of the Job
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return the url of the offer
     */
    public URL getUrl() {
        return url;
    }

    /**
     *
     * @return the date of creation of the offer
     */
    public String getCreated_at() {
        return created_at;
    }

    /**
     *
     * @return the name of the company
     */
    public String getCompany() {
        return company;
    }

    /**
     *
     * @return the url of the company
     */
    public URL getCompany_url() {
        return company_url;
    }

    /**
     *
     * @return the location of the Job
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @return the title of the offer
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return the offer description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return the instructions to apply for the Job
     */
    public String getHow_to_apply() {
        return Jsoup.parse(how_to_apply).text();
    }

    /**
     *
     * @return the company logo
     */
    public URL getCompany_logo() {
        return company_logo;
    }



    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @param url
     */
    public void setUrl(URL url) {
        this.url = url;
    }

    /**
     *
     * @param created_at
     */
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    /**
     *
     * @param company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     *
     * @param company_url
     */
    public void setCompany_url(URL company_url) {
        this.company_url = company_url;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @param how_to_apply
     */
    public void setHow_to_apply(String how_to_apply) {
        this.how_to_apply = how_to_apply;
    }

    /**
     *
     * @param company_logo
     */
    public void setCompany_logo(URL company_logo) {
        this.company_logo = company_logo;
    }

    /**
     *
     * @return
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