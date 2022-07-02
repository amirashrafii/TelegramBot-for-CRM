package uploader;

import org.inventivetalent.telegrambot.TelegramUpdateListener;
import org.inventivetalent.telegrambot.api.Bot;
import org.inventivetalent.telegrambot.api.Location;
import org.inventivetalent.telegrambot.api.Update;

import java.util.List;

/**
 * Created by amir on 8/9/2016 AD.
 */
public class UploadLocation {
    Location location;
    TelegramUpdateListener listener;
    Bot bot;

    public  UploadLocation(TelegramUpdateListener listener,Bot bot)
    {
        this.listener=listener;
        this.bot=bot;
    }

    public void UpLocation()
    {
        bot.addUpdateListener(listener=new TelegramUpdateListener() {

            @Override
            public void onUpdate(List<Update> list) {
                for (Update update : list) {
                    location =update.getMessage().getLocation();
                    float f1=location.getLatitude();
                    float f2=location.getLongitude();
                    update.getMessage().getFrom().sendLocation(f1,f2);

                    break;
                }
                bot.removeUpdateListener(listener);
            }

    });

}
}
