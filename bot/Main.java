package bot;

import com.sun.deploy.net.HttpResponse;
import commands.*;
import downloader.DownloadAudio;
import downloader.DownloadDocument;
import downloader.DownloadVideo;
import org.inventivetalent.telegrambot.TelegramBotAPI;
import org.inventivetalent.telegrambot.TelegramUpdateListener;
import org.inventivetalent.telegrambot.api.Bot;
import org.inventivetalent.telegrambot.api.File;
import org.inventivetalent.telegrambot.api.Update;
import uploader.UploadDocument;
import uploader.UploadLocation;
import uploader.UploadPhoto;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;

/**
 * Created by amir on 8/9/2016 AD.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, SQLException {


//               Class.forName("com.mysql.jdbc.Driver");
//        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/myDatabase", "amirash", "730");
//        final PreparedStatement[] preparedStatement = {null};
//        //  System.out.println("hi");


        String token = "208124029:AAFtXafBhTenxpcbobtzQW8ti92j8aIxlxo";
        Help help=new Help();
        help.setDescription("there are some tips that you can use");
        Setting setting=new Setting();
        setting.setDescription("some tools");

        Services services=new Services();
        services.setDescription("some tools for you");
        ContactUs contactUs =new ContactUs();
        contactUs.setPhoneNumber(123456789);
        contactUs.setAddress("vanak,negar");
        contactUs.setFaxNumber(121212);



        DownloadDocument downloadDocument=new DownloadDocument();
        DownloadAudio downloadAudio=new DownloadAudio();
        DownloadVideo downloadVideo=new DownloadVideo();

        //  LoginTimer loginTimer=new LoginTimer();

        Bot bot= TelegramBotAPI.getBot(token);

        KeyboardsBuilder keyboardsBuilder=new KeyboardsBuilder(bot);




        bot.setUpdateDelay(2000);










        TelegramUpdateListener[] listener =new TelegramUpdateListener[12];
        SignUp signUp=new SignUp(bot,listener[1],listener[2]);
        Login login=new Login(bot,listener[11]);
        bot.addUpdateListener(listener[0]= new TelegramUpdateListener() {

            @Override
            public void onUpdate(List<Update> updates) {
                System.out.println(updates);


                for (Update update : updates) {

                    //  System.out.println(update.getMessage().getChat());

                    if (update.getMessage().getText().toLowerCase().startsWith("/start")) {
                        update.getMessage().getFrom().sendMessage("welcome to bot", keyboardsBuilder.MainMenuKeyboard());
                    }

                    if (update.getMessage().getText() != null) {


                        if (update.getMessage().getText().toLowerCase().startsWith("/help") || update.getMessage().getText().startsWith("Help")) {
                            keyboardsBuilder.MainMenuKeyboard().setSelective(true);


                            update.getMessage().getFrom().sendMessage("here are some help", keyboardsBuilder.HelpMenu());
                            // update.getMessage().getChat().sendMessage(String.valueOf(help.perform()));
                            bot.addUpdateListener(listener[3] = new TelegramUpdateListener() {
                                @Override
                                public void onUpdate(List<Update> list) {

                                    for (Update update1 : list) {

                                        if (update1.getMessage().getText().startsWith("Download document")) {
                                            downloadDocument.DownloadDoc(update1);

                                        }
                                        if (update1.getMessage().getText().startsWith("download audio")) {
                                            downloadAudio.DownloadAdo(update1);
                                        }
                                        if (update1.getMessage().getText().startsWith("download video")) {
                                            downloadVideo.DownloadVdo(update1);
                                        }
                                        if (update1.getMessage().getText().startsWith("Return to main menu")) {
                                            update1.getMessage().getFrom().sendMessage("return to main menu", keyboardsBuilder.MainMenuKeyboard());
                                            bot.removeUpdateListener(listener[3]);
                                        }

                                    }


                                }
                            });


                        }
                        if (update.getMessage().getText().toLowerCase().startsWith("/setting") || update.getMessage().getText().startsWith("Setting")) {
                            keyboardsBuilder.MainMenuKeyboard().setSelective(true);
                            update.getMessage().getFrom().sendMessage(setting.getDescription());

                        }

                        if (update.getMessage().getText().toLowerCase().startsWith("/signup") || update.getMessage().getText().startsWith("SignUp")) {

                            keyboardsBuilder.MainMenuKeyboard().setSelective(true);



                            signUp.RegisterUsername(update);
                            //System.out.println(signUp.getHashMap());

//                            update.getMessage().getFrom().sendMessage("please Enter Username :\n");
//
//
//                            bot.addUpdateListener(listener[2] = new TelegramUpdateListener() {
//                                @Override
//                                public void onUpdate(List<Update> list1) {
//
//                                    for (Update update1 : list1) {
//
//
//                                        update1.getMessage().getFrom().sendMessage();
////                                        try {
////                                            signUp.AddToDatabase(connection, preparedStatement[0],services);
////                                        } catch (SQLException e) {
////                                            e.printStackTrace();
////                                        }
////
//
//
//                                        break;
//                                    }
//
//
//                                    bot.removeUpdateListener(listener[2]);
//
//                                }
//
//
//                            });


                        }


                        if (update.getMessage().getText().toLowerCase().startsWith("/login") || update.getMessage().getText().startsWith("Login")) {
                            keyboardsBuilder.MainMenuKeyboard().setSelective(true);
                            //                         try {
                          //  update.getMessage().getFrom().sendMessage("please Enter Username and password\n");
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }

                                        //                                       try {
//                                            preparedStatement[0] =connection.prepareStatement("SELECT username FROM LogedInPerson WHERE personID="+update.getMessage().getFrom().getId());
//                                            preparedStatement[0].execute();
//                                             resultSet=preparedStatement[0].getResultSet();
//                                        } catch (SQLException e) {
//                                            e.printStackTrace();
//                                        }

//                                        try {
//                                            if(update1.getMessage().getText().startsWith(resultSet.getString("username")))
//                                            {
//                                                login.setUsernamePassword(update1);
//                                            }


                            login.setUsernamePassword(update,signUp,services);
//                                        } catch (SQLException e) {
//                                            e.printStackTrace();
//                                        }













                        }
                    }


                    if (update.getMessage().getText().toLowerCase().startsWith("/services") || update.getMessage().getText().startsWith("Services")) {
                        keyboardsBuilder.MainMenuKeyboard().setSelective(true);
                        if (services.checkLog())
                            update.getMessage().getFrom().sendMessage("these are some services:", keyboardsBuilder.MainMenuKeyboard());
                        else {
                            update.getMessage().getFrom().sendMessage("Please enter your username and password :");
                            bot.addUpdateListener(listener[5] = new TelegramUpdateListener() {
                                @Override
                                public void onUpdate(List<Update> list) {
                                    for (Update update1 : list) {
                                        try {

                                            update1.getMessage().getFrom().sendMessage(services.loginToSystem(services, login, update1, signUp));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    }
                                    bot.removeUpdateListener(listener[5]);
                                }
                            });


                        }

                    }


                    if (update.getMessage().getText().toLowerCase().startsWith("/contactus") || update.getMessage().getText().startsWith("Contact Us")) {
                        keyboardsBuilder.MainMenuKeyboard().setSelective(true);
                        update.getMessage().getFrom().sendMessage(contactUs.getPhoneNumber() + "\n" + contactUs.getFaxNumber() + "\n" + contactUs.getAddress());
                    }

                    UploadPhoto uploadPhoto = new UploadPhoto(listener[7], bot);
                    UploadDocument uploadDocument = new UploadDocument(listener[8], bot);
                    UploadLocation uploadLocation = new UploadLocation(listener[9], bot);
                    if (update.getMessage().getText().startsWith("Upload Document")) {
                        update.getMessage().getFrom().sendMessage(("نوع مدرک مورد نظر را برای آپلود انتخاب کنید: "), keyboardsBuilder.UploadDoc());
                        bot.addUpdateListener(listener[6] = new TelegramUpdateListener() {
                            @Override
                            public void onUpdate(List<Update> list) {
                                for (Update update1 : list) {
                                    if (update1.getMessage().getText().toLowerCase().startsWith("کارت ملی")) {
                                        // Document document;
                                        update1.getMessage().getFrom().sendMessage("تصویر کارت ملی خود را آپلود کنید:");
                                        uploadDocument.UpDoc();


                                    }


                                    //  update1.getMessage().getChat().sendMessage("دریافت شد");


                                    if (update1.getMessage().getText().toLowerCase().startsWith("عکس پرسونلی")) {
                                        update1.getMessage().getFrom().sendMessage("عکس ۳*۴ خود را آپلود کنید :");


                                        uploadPhoto.UpPhoto();


                                        //update1.getMessage().getChat().sendMessage("دریافت شد");

                                    }
                                    if (update1.getMessage().getText().toLowerCase().startsWith("موقعیت")) {
                                        update1.getMessage().getFrom().sendMessage("مورقعیت خود را ارسال کنید :");
                                        uploadLocation.UpLocation();
                                        //update1.getMessage().getChat().sendMessage("دریافت شد");

                                    }
                                    if (update1.getMessage().getText().toLowerCase().startsWith("بازگشت به منوی اصلی")) {
                                        update1.getMessage().getFrom().sendMessage("منوی اصلی :", keyboardsBuilder.MainMenuKeyboard());
                                        bot.removeUpdateListener(listener[6]);
                                    }
                                }
                            }
                        });

                    }


                }

               // bot.removeUpdateListener(listener[0]);


            }







        });



    }
}
