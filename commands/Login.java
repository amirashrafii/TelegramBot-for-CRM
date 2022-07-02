package commands;

import bot.KeyboardsBuilder;
import org.inventivetalent.telegrambot.TelegramUpdateListener;
import org.inventivetalent.telegrambot.api.Bot;
import org.inventivetalent.telegrambot.api.Update;

import java.util.List;

/**
 * Created by amir on 8/9/2016 AD.
 */
public class Login {

    private String username;
    private String password;
    Bot bot;
    KeyboardsBuilder keyboardsBuilder=new KeyboardsBuilder(bot);

    TelegramUpdateListener listener;

    //  Services services=null;

    public Login(Bot bot, TelegramUpdateListener listener)
    {
        this.bot=bot;
        this.listener=listener;

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setUsernamePassword(Update update,SignUp signUp,Services services)
    {
        update.getMessage().getFrom().sendMessage("Enter your Username :");
        bot.addUpdateListener(listener=new TelegramUpdateListener() {
            @Override
            public void onUpdate(List<Update> list) {
                for (Update update1 : list) {
                    setUsername(update1.getMessage().getText());
                    break;


                }
                bot.removeUpdateListener(listener);
                setPass(update,signUp,services);
            }
        });

//        String[] string = update.getMessage().getText().split(" ");
//        setUsername(string[0]);
//        setPassword(string[1]);
    }

    public void setPass(Update update,SignUp signUp,Services services)
    {
        update.getMessage().getFrom().sendMessage("Enter your Password :");
        bot.addUpdateListener(listener=new TelegramUpdateListener() {
            @Override
            public void onUpdate(List<Update> list) {
                for (Update update1 : list) {
                    setPassword(update1.getMessage().getText());
                    break;
                }
                bot.removeUpdateListener(listener);
               // update.getMessage().getFrom().sendMessage("checking...",keyboardsBuilder.MainMenuKeyboard());
                try {
                    update.getMessage().getFrom().sendMessage(checkUser(services,signUp));
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });



    }

    public String checkUser(Services services,SignUp signUp)throws Exception{
        if (signUp.getHashMap().containsKey(getPassword())) {


            if (signUp.getHashMap().containsValue(getUsername())) {

                String s= "your logged in";
                services.setLogin(true);

                return s;
            }
            else {
                String s3 = "password is wrong";
                return s3;
            }
        } else {
            String s4 = "the username is not exist"+"\n"+"you must sign up first";
            return s4;

        }
    }

}
