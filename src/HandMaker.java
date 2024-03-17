import java.util.ArrayList;
import java.util.List;

public class HandMaker {

    public static Hand fillHands(Integer id, List<String> originalLines) {
        Hand hand = new Hand();
        hand.setId(id);
        String cards = "";
        for (String originalLine : originalLines) {
            if (originalLine.contains("posts big blind")) {
                hand.setBigBlind(originalLine);
            }
            if (originalLine.contains("is the button")) {
                hand.setCurrentButton(originalLine);
            }
            if (originalLine.contains("Dealt to ZombiChicken")) {
                cards = originalLine;
                hand.setCards(originalLine);
            }
        }
        fillPlayers(originalLines, hand);

        List<String> allOriginalActionLines = findActionsFromOriginalLines(originalLines.indexOf(cards) + 1, originalLines);
        fillHandsPlayerActions(allOriginalActionLines, hand);
        System.out.println(hand.getAllPlayerActions());

        hand.findCurrentPlayerPosition();
        return hand;
    }

    private static void fillHandsPlayerActions(List<String> allOriginalActionLines, Hand hand) {
        for (String actionLine : allOriginalActionLines) {
            hand.addPlayerActions(actionLine);
        }
    }

    private static List<String> findActionsFromOriginalLines(int i, List<String> originalLines) {
        List<String> allActions = new ArrayList<>();
        while (!originalLines.get(i).contains("Uncalled") && !originalLines.get(i).contains("*") && i < originalLines.size() - 1) {
            allActions.add(originalLines.get(i));
            i++;
        }
        return allActions;
    }

    private static void fillPlayers(List<String> originalLines, Hand hand) {
        for (String originalLine : originalLines) {
            if (originalLine.contains("Seat") && originalLine.contains("chips")) {
                setPlayerFields(originalLine, hand);
            }
        }
    }

    private static Player setPlayerFields(String originalDataLines, Hand hand) {
        int index = originalDataLines.indexOf(":");
        String number = originalDataLines.substring(index - 1, index);
        Integer seat = Integer.parseInt(number);
        int playerIndex = originalDataLines.indexOf(" ", index + 2);
        String player = originalDataLines.substring(index + 2, playerIndex);
        String betweenParenthesis = originalDataLines.substring(originalDataLines.indexOf("("), originalDataLines.indexOf(")") + 1);
        double chipCount = Double.parseDouble(betweenParenthesis.substring(1, betweenParenthesis.indexOf(" ")));
        return hand.addNewPlayer(seat, player, chipCount);
    }

}
