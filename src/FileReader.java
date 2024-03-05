import domain.Hand;
import domain.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReader {

    public static List<Hand> readFromFile() throws IOException {
        Path path = Paths.get("resources/text_file.txt");
        List<String> lines = Files.readAllLines(path);
        return findOriginalDataBlocks(lines);
    }

    private static List<Hand> findOriginalDataBlocks(List<String> lines) {
        Map<Integer, List<String>> originalDataBlocks = new HashMap<>();
        int counter = 0;
        for (String line : lines) {
            List<String> blocks = new ArrayList<>();
            if (line.contains("PokerStars domain.Hand")) {
                counter++;
                blocks.add(line);
                originalDataBlocks.put(counter, blocks);
            } else {
                originalDataBlocks.get(counter).add(line);
            }
        }
        return FileCutter.findNeededBlocks(originalDataBlocks);
    }


}
