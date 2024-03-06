import domain.Block;

import java.util.ArrayList;
import java.util.List;

public class LineCutter {

    private static List<Block> blocks = new ArrayList<>();

    public static void findNeededLines(Integer id, List<String> originalLines) {
        Block block = new Block();
        block.setId(id);
        String uncalledLine = "";
        for (String originalLine : originalLines) {
            if (originalLine.contains("is the button")) {
                block.setButtonLine(originalLine);
            }
            if (originalLine.contains("Seat") && originalLine.contains("chips")) {
                block.addSeat(originalLine);
            }
            if (originalLine.contains("Dealt to ZombiChicken")) {
                block.setCards(originalLine);
            }
            if (originalLine.contains("posts big blind")) {
                block.setBigBlind(originalLine);
            }
            if(originalLine.contains("Uncalled")) {
                uncalledLine = originalLine;
            }
        }
        findActions(originalLines.indexOf(block.getCards()) + 1, originalLines, block);
        findActionClosures(originalLines.indexOf(uncalledLine), originalLines, block);
        blocks.add(block);
    }

    private static void findActions(int i, List<String> originalLines, Block block) {
        while (!originalLines.get(i).contains("Uncalled") && i < originalLines.size() - 1) {
            block.addAction(originalLines.get(i));
            i++;
        }
    }

    private static void findActionClosures(int i, List<String> originalLines, Block block) {
        while (!originalLines.get(i).contains("SUMMARY") && i < originalLines.size() - 1) {
            block.addActionClosure(originalLines.get(i));
            i++;
        }
    }


    public static void printer() {
        for (Block block : blocks) {
            System.out.println(block.getId());
            System.out.println(block.getButtonLine());
            System.out.println(block.getBigBlind());
            System.out.println(block.getCards());
            System.out.println(block.getActions());
            System.out.println(block.getSeats());
            System.out.println(block.getActionClosure());
        }
    }
}
