package uploader;

import org.inventivetalent.telegrambot.TelegramUpdateListener;
import org.inventivetalent.telegrambot.api.Bot;
import org.inventivetalent.telegrambot.api.Document;
import org.inventivetalent.telegrambot.api.Update;

import java.util.List;

/**
 * Created by amir on 8/9/2016 AD.
 */
public class UploadPhoto {
    Document photo;
    TelegramUpdateListener listener;
    Bot bot;

    public UploadPhoto(TelegramUpdateListener listener, Bot bot) {
        this.listener = listener;
        this.bot = bot;
    }

    public void UpPhoto() {
        bot.addUpdateListener(listener = new TelegramUpdateListener() {


            @Override
            public void onUpdate(List<Update> list) {
                for (Update update : list) {
                    photo = update.getMessage().getDocument();
                    update.getMessage().getFrom().sendMessage("okey");
                    update.getMessage().getFrom().sendDocument(photo);
                    break;

                }

                bot.removeUpdateListener(listener);
            }


        });

    }
}
