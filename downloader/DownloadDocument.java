package downloader;

import org.inventivetalent.telegrambot.api.Update;

//import java.io.File;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.inventivetalent.telegrambot.api.File;
import org.inventivetalent.telegrambot.message.TelegramFile;
import org.inventivetalent.telegrambot.message.TelegramInputFile;

/**
 * Created by amir on 8/9/2016 AD.
 */
public class DownloadDocument {


    TelegramInputFile telegramFile;


    public DownloadDocument() throws FileNotFoundException {
        TelegramInputFile.FileType fileType= TelegramInputFile.FileType.DOCUMENT;
        java.io.File file=new java.io.File("/Users/amir/Documents/report1.docx");
        this.telegramFile=new TelegramInputFile(fileType,file);

    }

    public void DownloadDoc(Update update)
    {

        update.getMessage().getFrom().sendDocument(telegramFile);
    }
}
