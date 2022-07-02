package uploader;

import org.inventivetalent.telegrambot.TelegramUpdateListener;
import org.inventivetalent.telegrambot.api.Bot;
import org.inventivetalent.telegrambot.api.Document;
import org.inventivetalent.telegrambot.api.Update;

import java.util.List;

/**
 * Created by amir on 8/9/2016 AD.
 */
public class UploadDocument {
    TelegramUpdateListener listener;
    Bot bot;
    Document document;

    public UploadDocument(TelegramUpdateListener listener,Bot bot)
    {
        this.listener=listener;
        this.bot=bot;
    }
    public void UpDoc()
    {

        bot.addUpdateListener(listener = new TelegramUpdateListener() {

            @Override
            public void onUpdate(List<Update> list) {
                for (Update update : list) {
                    if (update.getMessage().getText().startsWith("hi")) {
                        update.getMessage().getFrom().sendMessage("helooooo");

                    }
else {
                        document = update.getMessage().getDocument();
                        update.getMessage().getFrom().sendDocument(document);
                        update.getMessage().getFrom().sendMessage("okeyeeee");
                        break;
                    }


                }
                bot.removeUpdateListener(listener);
            }


        });



    }


}
