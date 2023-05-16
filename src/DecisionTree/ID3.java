package DecisionTree;

import java.util.ArrayList;
import java.util.Arrays;
import org.json.simple.JSONObject;

public class ID3 {
    private JSONObject rootData;
    private JSONObject validColumn;
    private String[] title;
    private ArrayList<String> validName;
    private JSONObject rule;
    private ID3 nextID3 = null; 

    public ID3(JSONObject dataJSON, String[] title) {
        if (dataJSON.size() ==  0) return;
        this.rootData = dataJSON;
        this.title = title;
        validateColumn();
        execute();
    }
    // check if a column is an attribute column or a result column
    private void validateColumn() {
        this.validColumn = new JSONObject();
        this.validName = new ArrayList<>();
        for(int i = 0; i < this.title.length; i++) {
            String[] dataColumn = this.rootData.get(title[i]).toString().split(",");
            int length = dataColumn.length;
            boolean checkUnique = true;
            for (int j = 0 ; j < length - 1; j++) {
                for (int k = j + 1; k < length; k++) {
                    if (dataColumn[j].equals(dataColumn[k])) {
                        checkUnique = false;
                        break;
                    }
                }
            }
            if (!checkUnique) {
                this.validName.add(this.title[i]);
                this.validColumn.put(this.title[i], this.rootData.get(this.title[i]));
            }
        }
    }

    private void execute() {
        float minSumAverageEntropy = 1;
        int validColumns = validName.size();
        String nameChoose = "";
        String columnChoose = "";
        String resultCol = validColumn.get(validName.get(validColumns - 1)).toString();

        System.out.println("Calculating Average Entropy of each attribute:");

        for (int i = 0; i < validColumns - 1; i++) {
            String attributeCol = this.validColumn.get(this.validName.get(i)).toString();
            float avgEntropy = calcAvgEntropy(attributeCol, resultCol);
            
            System.out.println(this.validName.get(i) + ": " + avgEntropy);
            if (minSumAverageEntropy > avgEntropy) {
                minSumAverageEntropy = avgEntropy;
                columnChoose = attributeCol;
                nameChoose = this.validName.get(i);
            }
            
        }
        System.out.println("\n Picking out the decisive attribute: " + nameChoose);
        toRuleAndNextID3(nameChoose, columnChoose, resultCol);
    }

    public JSONObject getRule() {
        return this.rule;
    }

    private void toRuleAndNextID3(String nameChoose, String attributeToRule, String resultCol) {
        this.rule = new JSONObject();
        String[] attrDataToRule = attributeToRule.split(","); 
        ArrayList<String> attrCasesToRule = this.getCases(attrDataToRule); 
        String[] resultData = resultCol.split(",");
        // JSONObject nextJSONID3 = new JSONObject();
        JSONObject currentRule = new JSONObject();
        ArrayList<String> nextTitleID3 = new ArrayList<>();
        // nextJSONID3.remove(nameColumnRemove);

        for (int i = 0; i < resultData.length; i++) {
            Object valueData = currentRule.get(attrDataToRule[i]);

            if (valueData != null) {

                if (!valueData.toString().equals(resultData[i]) 
                && !valueData.toString().equals("")) {
                    currentRule.put(attrDataToRule[i], "");
                }
            }
            else 
                currentRule.put(attrDataToRule[i], resultData[i]); 
                
        }
        // TODO: decision tree here
        // for(int i = 0; i < resultData.length; i++){
        //     System.out.println("Rule: " + currentRule.get(attrDataToRule[i]) + ", " + currentRule.get(resultData[i]));
        //     System.out.println();
        // }
        

        for(String element : attrCasesToRule) {
            currentRule.get(element).toString();
        }

        for (String titleRule : attrCasesToRule) {
            String valueCurrentTitle = currentRule.get(titleRule).toString();
            if (valueCurrentTitle.equals("")) {
                String[] dataColumnRule = attributeToRule.split(",");
                JSONObject nextJSONID3 = new JSONObject();
                for (int l = 0; l < dataColumnRule.length; l++) {
                    if (!dataColumnRule[l].equals(titleRule)) {
                        continue;
                    }
                    else {
                        int lengthValidColumn = this.validColumn.size();
                        for (int i = 0; i < lengthValidColumn; i++) {
                            String currentTitleValid = this.validName.get(i);
                            String[] currentValueValidAtTitle = this.validColumn.get(currentTitleValid).toString().split(",");
                            String content = nextJSONID3.get(currentTitleValid) == null ? currentValueValidAtTitle[l] : (nextJSONID3.get(currentTitleValid).toString() + "," + currentValueValidAtTitle[l]);
                            nextJSONID3.put(currentTitleValid, content);
                        }
                    }
                }
                Object[] arrayValidTitle = this.validName.toArray();
                String[] stringArrayValidTitle =  Arrays.copyOf(arrayValidTitle, arrayValidTitle.length, String[].class);
                ID3 theNextID3OfCurrentTitleRule = new ID3(nextJSONID3, stringArrayValidTitle) ;
                currentRule.put(titleRule, theNextID3OfCurrentTitleRule.getRule());
            }
        }
        this.rule.put(nameChoose, currentRule);
        // TODO: rule here
    }
    private float calcAvgEntropy(String attributeCol, String resultCol) {
        String[] attributeData = attributeCol.split(",");
        String[] resultData = resultCol.split(",");
        ArrayList<String> resultCases = getCases(resultData);
        ArrayList<String> attributeCases = getCases(attributeData);
        // sum(average) 
        
        int numCase = attributeCases.size();
        float sumAverageEntropy = 0;
        for (int i = 0; i < numCase; i++) {
            sumAverageEntropy += calcAvgEntropyOfOneValue(attributeCases.get(i), attributeData, resultData, resultCases);
        }
        return sumAverageEntropy;
    }
    // cases = unique list
    private ArrayList<String> getCases(String[] dataList) {
        ArrayList<String> cases = new ArrayList<>();
        for (String data : dataList)
            if (!cases.contains(data)) 
                cases.add(data);
        return cases;
    }
    
    private float calcAvgEntropyOfOneValue(String defaultValueOfAttribute, String[] attributeData, String[] resultData, ArrayList<String> resultCases) {
        int logBase = resultCases.size();               // logarit base. ex: 2 result cases => log 2
        int totalData = attributeData.length;           //nt
        int timeAppear = 0;                             // nb
        int[] timeAppearWithResult = new int[logBase];  // nbc
        for (int i = 0; i < totalData; i++)
            // ex: defaultValueOfAttribute = yellow
            if (!attributeData[i].equals(defaultValueOfAttribute)) 
                continue;
            else {
                timeAppear = timeAppear + 1;
                for (int j = 0; j < logBase; j++)
                    if (resultData[i].equals(resultCases.get(j)))
                        timeAppearWithResult[j] += 1;
            }
        // (nb / nt) * sum ((-nbc / nb) * log base(nbc / nb))
        // (timeAppear / totalData) * sum((-timeAppearWithResult[i] / timeAppear) * log base(timeAppearWithResult[i] / timeAppear))
        
        float averageOfValue = (float)timeAppear / (float)totalData;// (timeAppear / totalData)
        // sum((-timeAppearWithResult[i] / timeAppear) * log base(timeAppearWithResult[i] / timeAppear))
        float entropy = 0;
        for (int i = 0; i < logBase; i++) {
            float averageOfTimeAppear = (float)timeAppearWithResult[i] / (float)timeAppear;
            entropy += averageOfTimeAppear != 0 ? (-averageOfTimeAppear) * Util.logWithBase(logBase, averageOfTimeAppear) : 0;
        }
        return averageOfValue * entropy;
    }
}
