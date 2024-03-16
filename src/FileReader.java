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
        List<Hand> hands = findOriginalDataBlocks(lines);
        for (Hand hand : hands) {
//            System.out.println("************************");
//            System.out.println(hand.getId());
//            System.out.println(hand.getBigBlind());
//            System.out.println(hand.getCurrentButton());
//            System.out.println(hand.getCards());
//            System.out.println(hand.getPlayers().size());
//            System.out.println(hand.getChosenPlayersChipsInBigBlind("ZombiChicken"));
//            System.out.println(hand.getAllPlayerActions());
//            System.out.println("************************");
        }
        return hands;
    }

    private static List<Hand> findOriginalDataBlocks(List<String> lines) {
        Map<Integer, List<String>> originalDataBlocks = new HashMap<>();
        int counter = 0;
        for (String line : lines) {
            List<String> blockLines = new ArrayList<>();
            if (line.contains("PokerStars Hand")) {
                counter++;
                blockLines.add(line);
                originalDataBlocks.put(counter, blockLines);
            } else {
                originalDataBlocks.get(counter).add(line);
            }
        }
        return fillHands(originalDataBlocks);
    }

    private static List<Hand> fillHands(Map<Integer, List<String>> originalDataBlocks){
        List<Hand> hands = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : originalDataBlocks.entrySet()) {
            hands.add(HandMaker.fillHands(entry.getKey(), entry.getValue()));
        }
        return hands;
    }



}
