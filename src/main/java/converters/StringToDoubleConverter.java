package converters;

public class StringToDoubleConverter {

    public Double convertStringToDouble(String numberInTextFormat)  throws NumberFormatException {
        if(numberInTextFormat.contains(",")) {
            numberInTextFormat = numberInTextFormat.replaceAll(",", ".");
        }
        Double doubleFormatSalary = (Double.parseDouble(numberInTextFormat));
        return doubleFormatSalary;
    }
}
