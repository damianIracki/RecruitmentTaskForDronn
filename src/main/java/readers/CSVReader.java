package readers;

import converters.StringToDoubleConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CSVReader {

    public void printCSVResults (String path){
        try {
            List<String> csvDataList = readCSVFile(path);
            HashMap<String, Double> resultsMap = convertCSVDAtaToJobSalaryMap(csvDataList);
            System.out.println("Results from csv file:");
            resultsMap.forEach((key, value) -> System.out.printf(key + ": %.2f \n", value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, Double> convertCSVDAtaToJobSalaryMap(List<String> records){
        StringToDoubleConverter stringToDoubleConverter = new StringToDoubleConverter();
        HashMap<String, Double>jobSalaryMap = new HashMap<String, Double>();
        int titleRecordIndex = 0;

        List<String>titleRecord = Arrays.asList(records.get(titleRecordIndex).split(";"));
        int jobIndex = titleRecord.indexOf("job");
        int salaryIndex = titleRecord.indexOf("salary");
        records.remove(titleRecordIndex);
        for (String record : records) {
            String[] values = record.split(";");
            for (int i = 0; i < values.length; i++) {
                values[i] = values[i].replaceAll("[\"]|[ ]", "");
            }
            String job = values[jobIndex];
            double salary = stringToDoubleConverter.convertStringToDouble(values[salaryIndex]);
            if(!jobSalaryMap.containsKey(job)){
                jobSalaryMap.put(job, salary);
            } else {
                salary += jobSalaryMap.get(job);
                jobSalaryMap.replace(job, salary);
            }
        }
        return jobSalaryMap;
    }


    private List<String> readCSVFile (String path) throws IOException {
        List<String> records = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while((line = br.readLine()) != null) {
            records.add(line);
        }
        br.close();
        return records;
    }
}
