import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Main {
    public static void main(String args[])
    {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi= new TelegramBotsApi();
        Botm botm=new Botm();
        try{
            telegramBotsApi.registerBot(botm);
        }
        catch(TelegramApiRequestException e){
            e.printStackTrace();
        }

    }
}
