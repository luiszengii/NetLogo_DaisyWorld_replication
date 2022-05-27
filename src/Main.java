import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) throws IOException {
        List<String[]> dataLines = new ArrayList<>();

        // initialize a board with patches of random daisies
        Board board = new Board();
        board.initPatches();

        // build the head of the table
        StringBuilder sb = new StringBuilder();
        sb.append("tick" + ",")
                .append("Popularity of white daisy" + ",")
                .append("Popularity of grey daisy" + ",")
                .append("Popularity of black daisy" + ",")
                .append("Global temperature" + "\n");

        sb = board.getData(sb);


        // adding data to the string
        int totalTick = 200;
        for (int i = 0; i < totalTick; i++){
            board.updateBoard();
            sb = board.getData(sb);
        }

        // write data to a csv file
        try(PrintWriter writer = new PrintWriter("data.csv")) {
            writer.write(sb.toString());
        }
    }

}
