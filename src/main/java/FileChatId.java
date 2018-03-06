import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class FileChatId {

    public void writeChatIds(long chatId){
        File file = new File("users.baf");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file,true);
            fw.write(Long.toString(chatId) + "\n");
            fw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public HashSet<Long> readChatIds(){
        HashSet<Long> chatId = new HashSet<Long>();
        try {
            File file = new File("users.baf");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку

            String line = reader.readLine();
            while (line != null) {
                chatId.add(Long.parseLong(line));
               // System.out.println(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
            fr.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chatId;
    }

    public void updateIdsFile(HashSet<Long> ids){

        File file = new File("users.baf");
        FileWriter fr = null;

        try {
            fr = new FileWriter(file);
            Iterator<Long> iterator = ids.iterator();
            while (iterator.hasNext()) {
                fr.write(iterator.next().toString() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
