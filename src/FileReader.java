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
            if (line.contains("PokerStars Hand")) {
                counter++;
                blocks.add(line);
                originalDataBlocks.put(counter, blocks);
            } else {
                originalDataBlocks.get(counter).add(line);
            }
        }

        return findHandsOfBlocks(turnDataLinesIntoBlocks(originalDataBlocks));
    }

    private static List<Hand> findHandsOfBlocks(List<Block> blocks) {
        List<Hand> hands = new ArrayList<>();
        for (Block block : blocks) {
            hands.add(block.getHand());
        }
        return hands;
    }

    private static List<Block> turnDataLinesIntoBlocks(Map<Integer, List<String>> originalDataBlocks) {
        List<Block> blocks = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : originalDataBlocks.entrySet()) {
            Block tempBlock = LineCutter.findNeededLines(entry.getKey(), entry.getValue());
            blocks.add(tempBlock);
        }
        return blocks;
    }


}
