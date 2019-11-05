//import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.request.DeleteMessage;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendPhoto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
//import org.telegram.telegrambots.api.objects.File;

public class Botm extends TelegramLongPollingBot {
    int status=1;//Main-1 ,watch choose-2,
    String Msg=new String();
    long chat_id=0;
    int message_id=0;
   // private String[] watches=new String[20];

    MessageAnalysis msga=new MessageAnalysis();
    @Override
    public void onUpdateReceived(Update update) {

   // System.out.println(update.getMessage().getFrom().getFirstName()+ ":" +update.getMessage().getText());
//        SendMessage sendMessage=new SendMessage().setChatId(update.getMessage().getChatId());
       // sendMessage.setText("Hello" + update.getMessage().getFrom().getFirstName()+ "\nYour Message is:" + update.getMessage().getText());


        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            if (update.getMessage().getText().equals("/start")) {

                chat_id=update.getMessage().getChatId();
                message_id=update.getMessage().getMessageId();
                //DeleteMessage delete = new DeleteMessage(chat_id,message_id);
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("תבחר מהקטגוריוח הבאות:");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("שעונים").setCallbackData("1"));
                rowInline.add(new InlineKeyboardButton().setText("תיקים").setCallbackData("2"));
                rowInline.add(new InlineKeyboardButton().setText("נעליים").setCallbackData("3"));
                rowInline.add(new InlineKeyboardButton().setText("צמידים").setCallbackData("4"));
                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                // Add it to the message
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                try {
                    sendMessage(message); // Sending our message object to user
                    status=2;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
            }
        }
        else if (update.hasCallbackQuery()&&status==2) {

            DeleteMessage delete = new DeleteMessage(chat_id,message_id);

            String call_data = update.getCallbackQuery().getData();
            message_id = update.getCallbackQuery().getMessage().getMessageId();
            chat_id = update.getCallbackQuery().getMessage().getChatId();

            if (call_data.equals("1")) {
                String answer = "איזה מותג תרצה?";
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText(answer);
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("Rado").setCallbackData("1"));
                rowInline.add(new InlineKeyboardButton().setText("Breitling").setCallbackData("2"));
                rowInline.add(new InlineKeyboardButton().setText("Tissot").setCallbackData("3"));
                rowInline.add(new InlineKeyboardButton().setText("EDOX").setCallbackData("4"));
                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                // Add it to the message
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                status=21;
                try {
                    sendMessage(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (update.hasCallbackQuery()&&status==21) {
           String[] watches= new String[]{"Rado", "Breitling", "TISSOT", "EDOX"};
           String dir="C:'bot'watches'"+watches[Integer.parseInt(update.getCallbackQuery().getData())-1];
           String dirF=dir.replaceAll("'","\\\\");
           System.out.println(dirF);
           File folder=new File(dirF);
           List<String> fileList = new ArrayList<>();
           search(".*\\.jpg", folder, fileList);
            for (String s : fileList) {
                System.out.println(s);
            }
            for (String s:fileList) {
                String fCurr=String.join("",s);
                File p1 = new File(s);
                SendPhoto sendPhotoRequest = new SendPhoto();
                sendPhotoRequest.setChatId(chat_id);
                sendPhotoRequest.setNewPhoto(new File(s));
                //this.sendPhoto(chat_id,s);
                try {
                 //   SendPhoto message = new SendPhoto().setPhoto(chat_id, new FileInputStream(p1));
                    sendPhoto(sendPhotoRequest);
                } catch (TelegramApiException  e) {
                    e.printStackTrace();
                }

                //sendPhoto(chat_id,s);
            }
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id).setText("מה תרצה לעשות:");
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            rowInline.add(new InlineKeyboardButton().setText("<Previous Model").setCallbackData("1"));
            rowInline.add(new InlineKeyboardButton().setText("Buy:130$").setCallbackData("2"));
            rowInline.add(new InlineKeyboardButton().setText("Next Model>").setCallbackData("3"));
            // Set the keyboard to the markup
            rowsInline.add(rowInline);
            // Add it to the message
            markupInline.setKeyboard(rowsInline);
            message.setReplyMarkup(markupInline);
            try {
                sendMessage(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
         /*   msga.setTxt(update.getMessage().getText());
        Msg="";
        String[] msgts=msga.MsgToShow();
        for(int i=1;i<msgts.length;i++){
            Msg=Msg+"\n"+msgts[i];
        }
        sendMessage.setText("בחר אחת מהקטגוריות(בעזרת שליחת מס' הקט'):"+Msg);

        try {
            sendMessage(sendMessage);
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }*/
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return "959804909:AAE5mSGnInQKStjGA6-BlL12Qrex_zki6RA";
    }
    public static void search(final String pattern, final File folder, List<String> result) {
        for (final File f : folder.listFiles()) {

            if (f.isDirectory()) {
                search(pattern, f, result);
            }

            if (f.isFile()) {
                if (f.getName().matches(pattern)) {
                    result.add(f.getAbsolutePath());
                }
            }

        }
    }
}
