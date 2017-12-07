import net.sf.json.JSONObject;

import java.io.*;

public class JsonUtils {

    public static void writeJson2file(JSONObject jsonObject,String filePath){
        FileOutputStream fop = null;
        File file;
        try {
            file = new File(filePath);
            fop = new FileOutputStream(file);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // get the content in bytes
            byte[] contentInBytes = jsonObject.toString().getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readJsonfile(String filePath) throws FileNotFoundException{
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(filePath);
            // 读取文件，并且以utf-8的形式写出去
            BufferedReader bufread;
            String read;
            bufread = new BufferedReader(new FileReader(file));
            while ((read = bufread.readLine()) != null) {
                sb.append(read);
            }
            bufread.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }


    public static void main(String[] args){
        String jsonStr = null;
        try{
            jsonStr = readJsonfile("hiveMetaData.json");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        if(null != jsonStr){
            System.out.println(jsonStr);
        }
    }
}
