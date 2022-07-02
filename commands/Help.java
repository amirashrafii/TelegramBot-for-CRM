package commands;

/**
 * Created by amir on 8/9/2016 AD.
 */
public class Help {
    private String description;
    String[] strings = {"/help" + "\n" + "/settings" + "\n" + "/login" + "\n" + "/services" + "\n" + "/contactus" + "\n" + "/signup" + "\n"};


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] perform()
    {
        return strings;
    }

}
