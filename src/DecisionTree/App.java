package DecisionTree;

import org.json.simple.JSONObject;
public class App {
    public static void main(String[] args) throws Exception {
        ReadFileCSV readFileCSV = new ReadFileCSV("src/DecisionTree/sunburn.csv");
        JSONObject contentJSON = readFileCSV.getContentJSON();
        ID3 id = new ID3(contentJSON, readFileCSV.getTitle());
        System.out.println(id.getRule());
    }
}
