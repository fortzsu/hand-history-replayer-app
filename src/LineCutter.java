import java.util.List;

public class LineCutter {

    public static Block findNeededLines(Integer id, List<String> originalLines) {
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
        return block;
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

}
