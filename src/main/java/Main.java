import readers.CSVReader;
import readers.JSONReader;

public class Main {
    public static void main(String[] args) {
        JSONReader jsonReader = new JSONReader();
        jsonReader.printJSONResults("src/employees.json");

        CSVReader csvReader = new CSVReader();
        csvReader.printCSVResults("src/employees.csv");
    }
}
