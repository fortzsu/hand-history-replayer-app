import domain.Hand;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            List<Hand> selectedLines = FileReader.readFromFile();
//            for (int i = 0; i < selectedLines.size(); i++) {
//                System.out.println(selectedLines + " : " + i);
//            }
            WriteHtmlFile.printIntoHtml(selectedLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}