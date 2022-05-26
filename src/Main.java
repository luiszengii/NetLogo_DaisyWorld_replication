import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) throws IOException {
        CSVconverter csv = new CSVconverter();

        List<String[]> dataLines = new ArrayList<>();
        dataLines.add(new String[]
                { "John", "Doe", "38", "Comment Data\nAnother line of comment data" });
        dataLines.add(new String[]
                { "Jane", "Doe, Jr.", "19", "She said \"I'm being quoted\"" });

        csv.convert(dataLines);
    }

}
