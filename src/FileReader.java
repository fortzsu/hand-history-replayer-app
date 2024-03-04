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

    private static final String HAND_STARTER = "PokerStars domain.Hand";
    private static final List<Hand> hands = new ArrayList<>();

    public static List<Hand> readFromFile() throws IOException {
        Path path = Paths.get("resources/text_file.txt");
        List<String> lines = Files.readAllLines(path);
        findOriginalDataBlocks(lines);
//        for (Hand hand : hands) {
//            System.out.println("ID: " + hand.getId());
//            System.out.println(hand.getBigBlind());
//            for (Player player : hand.getPlayers()) {
//                System.out.println(player.getPlayerName() + " - " + player.getSeatNumber() + " . " + player.getNameOfPosition());
//            }
//            System.out.println(hand.getChipsInBigBlind());
//            System.out.println("*****************************************");
//        }
        return hands;
    }

    private static void findOriginalDataBlocks(List<String> lines) {
        Map<Integer, List<String>> originalDataBlocks = new HashMap<>();
        int counter = 0;
        for (String line : lines) {
            List<String> blocks = new ArrayList<>();
            if (line.contains(HAND_STARTER)) {
                counter++;
                blocks.add(line);
                originalDataBlocks.put(counter, blocks);
            } else {
                originalDataBlocks.get(counter).add(line);
            }
        }
        HandMaker.fillHandsWithData(originalDataBlocks, hands);
    }


}
