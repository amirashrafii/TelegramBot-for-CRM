package commands;

import bot.KeyboardsBuilder;
import org.inventivetalent.telegrambot.TelegramUpdateListener;
import org.inventivetalent.telegrambot.api.Bot;
import org.inventivetalent.telegrambot.api.Update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amir on 8/9/2016 AD.
 */
public class SignUp {
    private HashMap hashMap;
    private String username;
    private String password;
    private Integer perssonId;
    Bot bot;
    TelegramUpdateListener listener1;
    KeyboardsBuilder keyboardsBuilder=new KeyboardsBuilder(bot);
    TelegramUpdateListener listener2;

    public SignUp(Bot bot,TelegramUpdateListener listener1,TelegramUpdateListener listener2)
    {
        this.bot=bot;
        this.listener1=listener1;
        this.listener2=listener2;

    }


//    public static Connection connection;
//    PreparedStatement preparedStatement;


    public void RegisterUsername(Update update)
    {
       // setPerssonId(update.getMessage().getChat().getId());
        update.getMessage().getFrom().sendMessage("please Enter Username :");
        bot.addUpdateListener(listener1=new TelegramUpdateListener() {
            @Override
            public void onUpdate(List<Update> list) {

                for (Update update1 : list) {
                    String string = update1.getMessage().getText();

                   setUsername(string);
                    break;
                }

                bot.removeUpdateListener(listener1);
                //update.getMessage().getFrom().sendMessage("done1");
                RegisterPassword(update);

            }
        });

//        String[] string = update.getMessage().getText().split(" ");
//        setUsername(string[0]);
//        setPassword(string[1]);
//        setHashMap(getUsername(),getPassword());
//        return "register done";

    }
    public void RegisterPassword(Update update)
    {
        update.getMessage().getFrom().sendMessage("please Enter Password:");
        bot.addUpdateListener(listener2=new TelegramUpdateListener() {
            @Override
            public void onUpdate(List<Update> list) {

                for (Update update1 : list) {
                    String string = update1.getMessage().getText();
                    setPassword(string);

                    break;
                }
                bot.removeUpdateListener(listener2);
                HashMap hashMap=new HashMap();
                hashMap.put(getPassword(),getUsername());
                setHashMap(hashMap);
               // update.getMessage().getFrom().sendMessage("done2");
                update.getMessage().getFrom().sendMessage("Register Done",keyboardsBuilder.MainMenuKeyboard());

            }
        });
    }

//    public void AddToDatabase(Connection connection,PreparedStatement preparedStatement,Services services) throws SQLException {
//        preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO LogedInPerson VALUE(?,?,?)");
//        preparedStatement.setInt(1, perssonId);
//        preparedStatement.setString(2,username);
//        preparedStatement.setString(3,password);
//        preparedStatement.executeUpdate();
//        services.setLogin(true);
//
//
//
//    }



    public HashMap getHashMap() {
        return hashMap;
    }

//    public void setHashMap(String username,String password) {
//        this.hashMap.put(username,password);
//    }


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

    public Integer getPerssonId() {
        return perssonId;
    }

    public void setPerssonId(Integer perssonId) {
        this.perssonId = perssonId;
    }

    public void setHashMap(HashMap hashMap) {
        this.hashMap = hashMap;
    }
}
