import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            List<Hand> selectedLines = FileReader.readFromFile();
            WriteHtmlFile.printIntoHtml(selectedLines);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}