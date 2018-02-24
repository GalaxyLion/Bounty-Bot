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

    public void parsePage(final String urlFBA){


        Thread run = new Thread(new Runnable() {

            int prevCountTags = 0;

            public void run() {

                while(true){
                    try {

                        int currentCountTags = 0;
                            try {
                                Document document = Jsoup.connect(urlFBA).get();

                                Elements elements = document.select("h4");

                                for (Element element : elements) {
                                    System.out.println(element);
                                    currentCountTags++;
                                    System.out.println(currentCountTags);
                                }
                                if(prevCountTags == 0){
                                    prevCountTags = currentCountTags;
                                }
                                if(prevCountTags !=0 && prevCountTags!=currentCountTags){

                                    returntUrl = urlFBA;
                                    returnUrl();
                                    setReturn(true);
                                    prevCountTags = currentCountTags;
                                    System.out.println("Updated");
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



    public SendMessage updateInfoUrl(long chatId, String faucetBountyAir, String url){

        SendMessage message = Messages.returnUpdatedUrl(chatId,faucetBountyAir,url);
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
