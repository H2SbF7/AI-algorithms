package DecisionTree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;

public class ReadFileCSV {
    private BufferedReader br;
    private JSONObject contentJSON;
    private String[] title;
    public ReadFileCSV(String path) {
        try {
            this.br = new BufferedReader(new FileReader(path));
            String line;
            String splitBy = ",";
            contentJSON = new JSONObject();
            try {
                if ((line = br.readLine()) != null) {
                    //get title of JSON
                    title = line.split(splitBy);
                }
                while ((line = br.readLine()) != null) {
                    String[] rowContent = line.split(splitBy);
                    for (int i = 0; i < title.length; i++) {
                        String previous = contentJSON.get(title[i]) != null ? contentJSON.get(title[i]) + "," : "";
                        contentJSON.put(title[i], previous + rowContent[i]);
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("Xảy ra lỗi trong quá trình đọc tệp tin!");
            }  
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Tệp tin không tồn tại!");
        }  
    }
    public JSONObject getContentJSON() {
        return this.contentJSON;
    }
    public String[] getTitle() {
        return this.title;
    }
}

