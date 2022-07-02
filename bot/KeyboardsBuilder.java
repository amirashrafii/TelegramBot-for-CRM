package bot;

import com.vdurmont.emoji.EmojiParser;
import org.inventivetalent.telegrambot.api.Bot;
import org.inventivetalent.telegrambot.api.Update;
import org.inventivetalent.telegrambot.chat.keyboard.TelegramMarkupBuilder;
import org.inventivetalent.telegrambot.chat.keyboard.TelegramReplyKeyboardMarkup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amir on 8/9/2016 AD.
 */
public class KeyboardsBuilder {
    Bot bot;
    TelegramMarkupBuilder telegramMarkupBuilder=new TelegramMarkupBuilder(bot);


    public   KeyboardsBuilder(Bot bot)
    {
        this.bot=bot;
    }

    public TelegramReplyKeyboardMarkup MainMenuKeyboard()
    {
        EmojiParser[] emojiParser=new EmojiParser[7];
        String[][] keyboard={
                {emojiParser[0].parseToUnicode("Login :hourglass:"),emojiParser[1].parseToUnicode("SignUp \uD83D\uDDD2")}
                ,{emojiParser[2].parseToUnicode("Services :package:")}
                ,{emojiParser[6].parseToUnicode("Upload Document:outbox_tray:")}
                , {emojiParser[3].parseToUnicode("Setting :wrench:")}
                ,{emojiParser[4].parseToUnicode("Help :globe_with_meridians:"),emojiParser[5].parseToUnicode("Contact Us :telephone:")}};
        return telegramMarkupBuilder.buildKeyboardMarkup(keyboard);
    }

    public TelegramReplyKeyboardMarkup HelpMenu()
    {
        String[][] keyboard={{"Download document","download video"},{"download audio","Return to main menu"}};
        return telegramMarkupBuilder.buildKeyboardMarkup(keyboard);
    }

    public TelegramReplyKeyboardMarkup UploadDoc()
    {
        String[][] keyboard={{"کارت ملی"},{"عکس پرسونلی"},{"موقعیت"},{"بازگشت به منوی اصلی"}};
        return telegramMarkupBuilder.buildKeyboardMarkup(keyboard);
    }
    public TelegramReplyKeyboardMarkup LoginMenu(Connection connection, PreparedStatement preparedStatement, Update update) throws SQLException {
        preparedStatement=connection.prepareStatement("SELECT username FROM LogedInPerson WHERE personID="+update.getMessage().getFrom().getId());
        preparedStatement.execute();
        ResultSet resultSet=preparedStatement.getResultSet();
        int i=0;
        String[][] username=new String[i][0];
        while (resultSet.next())
        {
             username[i][1]=resultSet.getString("username");
             i++;

        }

        return telegramMarkupBuilder.buildKeyboardMarkup(username);


    }

}
