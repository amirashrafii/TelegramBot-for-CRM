package downloader;

import org.inventivetalent.telegrambot.api.Update;
import org.inventivetalent.telegrambot.message.TelegramAudio;
import org.inventivetalent.telegrambot.message.TelegramInputFile;

import java.io.File;

/**
 * Created by amir on 8/9/2016 AD.
 */
public class DownloadAudio {
    TelegramInputFile telegramAudio;
    public DownloadAudio()
    {
        TelegramInputFile.FileType fileType= TelegramInputFile.FileType.DOCUMENT;
        File file=new File("/Users/amir/Downloads/PutYourHeadOnMyShoulder.mp3");
        this.telegramAudio= new TelegramInputFile(fileType,file);

    }
    public void DownloadAdo(Update update)
    {
        update.getMessage().getChat().sendDocument(telegramAudio);
    }

}
