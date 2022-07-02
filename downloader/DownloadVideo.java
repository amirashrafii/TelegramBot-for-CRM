package downloader;

import org.inventivetalent.telegrambot.api.Update;
import org.inventivetalent.telegrambot.message.TelegramInputFile;
import org.inventivetalent.telegrambot.message.TelegramVideo;

import java.io.File;

/**
 * Created by amir on 8/9/2016 AD.
 */
public class DownloadVideo {
    TelegramInputFile telegramVideo;
    public DownloadVideo()
    {
        TelegramInputFile.FileType fileType= TelegramInputFile.FileType.VIDEO;
        File file=new File("/Users/amir/Downloads/big_buck_bunny_720p_1mb.mp4");
        this.telegramVideo= new TelegramInputFile(fileType,file);
    }
    public void DownloadVdo(Update update)
    {
        update.getMessage().getFrom().sendVideo(telegramVideo);
    }
}
