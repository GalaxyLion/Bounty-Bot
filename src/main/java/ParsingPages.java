import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.methods.send.SendMessage;


import java.io.IOException;


public class ParsingPages {

    private boolean isReturn = false;

    private String returntUrl = "";

    public void parsePage(final String urlTBA){


        Thread run = new Thread(new Runnable() {

            int prevCountTaps = 0;

            public void run() {

                while(true){
                    try {

                        int currentCountTaps = 0;
                            try {
                                Document document = Jsoup.connect(urlTBA).get();

                                Elements elements = document.select("h4");

                                for (Element element : elements) {
                                    System.out.println(element);
                                    currentCountTaps++;
                                    System.out.println(currentCountTaps);
                                }
                                if(prevCountTaps == 0){
                                    prevCountTaps = currentCountTaps;
                                }
                                if(prevCountTaps !=0 && prevCountTaps!=currentCountTaps){

                                    returntUrl = urlTBA;
                                    returnUrl();
                                    setReturn(true);
                                    prevCountTaps = currentCountTaps;
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        Thread.sleep(10000); //1000 - 1 сек
                    } catch (InterruptedException ex) {
                    }
                }
            }
        });

        run.start();

    }



    public SendMessage updateInfoUrl(long chatId, String tapsBountyAir, String url){

        SendMessage message = Messages.returnUpdatedUrl(chatId,tapsBountyAir,url);
        setReturn(false);
        return message;
    }

    public synchronized String returnUrl(){

        return returntUrl;
    }


    public synchronized boolean getReturn() {
        return isReturn;
    }

    public void setReturn(boolean aReturn) {
        isReturn = aReturn;
    }
}
