package commands;

import org.inventivetalent.telegrambot.api.Update;

/**
 * Created by amir on 8/9/2016 AD.
 */
public class Services {
    private String description;
    private boolean login;





    public String getDescription () {
        return description;
    }
    public void setDescription (String description){
        this.description = description;
    }
    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }




    public boolean checkLog() {
        if (isLogin()) {
            return true;
        }
        else
        {
            return false;
        }
    }




    public String loginToSystem(Services services, Login login, Update update, SignUp signUp) throws Exception
    {
        login.setUsernamePassword(update,signUp,services);
        return  login.checkUser(services,signUp);
    }

}
