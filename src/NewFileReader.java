import domain.Hand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewFileReader {


    private static final String HAND_STARTER = "PokerStars domain.Hand";
    private static final List<Hand> hands = new ArrayList<>();
    private static String buttonLine;
    private static  final List<String> seats = new ArrayList<>();
    private static String cards;
    private static final List<String> actions = new ArrayList<>();
    private static final List<String> actionClosure = new ArrayList<>();

    public static List<Hand> readFromFile() throws IOException {
        Path path = Paths.get("resources/text_file.txt");
        List<String> lines = Files.readAllLines(path);
        findOriginalDataBlocks(lines);
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
        findNeededBlocks(originalDataBlocks);

    }

    private static void findNeededBlocks(Map<Integer, List<String>> originalDataBlocks) {
        for (List<String> value : originalDataBlocks.values()) {

            for (int i = 0; i < value.size(); i++) {
                if(value.get(i).contains("is the button")) {
                    buttonLine = value.get(i);
                    int j = i + 1;
                    while(value.get(j).startsWith("Seat")) {
                        seats.add(value.get(j));
                        j++;
                    }
                }
                if(value.get(i).contains("Dealt to")) {
                    cards = value.get(i);
                    int j = i + 1;
                    while(!value.get(j).startsWith("Uncalled") && j < value.size() -1) {
                        actions.add(value.get(j));
                        j++;
                    }
                }
                if(value.get(i).startsWith("Uncalled")) {
                    int k = i;
                    while(!value.get(k).startsWith("***")) {
                        actionClosure.add(value.get(k));
                        k++;
                    }
                }

            }
        }
        System.out.println(buttonLine);
        System.out.println(seats);
        System.out.println(cards);
        System.out.println(actions);
        System.out.println(actionClosure);
    }




}
