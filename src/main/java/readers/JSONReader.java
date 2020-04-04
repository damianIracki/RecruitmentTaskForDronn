package readers;

import converters.StringToDoubleConverter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;



public class JSONReader {

    public void printJSONResults (String path){
        try {
            JSONArray jsonArray = readJSONFile(path);
            HashMap<String, Double> resultsMap = convertJSONArrayToJobSalaryMap(jsonArray);
            System.out.println("Results from json file:");
            resultsMap.forEach((key, value) ->System.out.printf(key + ": %.2f \n", value));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, Double> convertJSONArrayToJobSalaryMap(JSONArray jsonObjectsList){
        StringToDoubleConverter stringToDoubleConverter = new StringToDoubleConverter();
        HashMap<String, Double>jobSalaryMap = new HashMap<String, Double>();
        for(int i =0; i < jsonObjectsList.size(); i++){
            JSONObject employeeJsonObject =(JSONObject) jsonObjectsList.get(i);
            String job = (String)employeeJsonObject.get("job");
            double salary = stringToDoubleConverter.convertStringToDouble((String)employeeJsonObject.get("salary"));
            if(!jobSalaryMap.containsKey(job)){
                jobSalaryMap.put(job, salary);
            } else {
                salary += jobSalaryMap.get(job);
                jobSalaryMap.replace(job, salary);
            }
        }
        return jobSalaryMap;
    }

    private JSONArray readJSONFile (String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(path));
        JSONArray jsonList = (JSONArray) jsonObject.get("employees");
        return jsonList;
    }

}
