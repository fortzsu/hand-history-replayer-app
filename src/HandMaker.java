import domain.Hand;

import java.util.List;
import java.util.Map;

public class HandMaker {

    private static final String PLAYER_CARDS = "Dealt to ZombiChicken";
    private static final String BIG_BLIND = "posts big blind";
    private static final String CHIP = "chips";
    private static final String SEAT = "Seat ";
    private static final String TABLE = "Table ";

    public static void fillHandsWithData(Map<Integer, List<String>> originalDataBlocks, List<Hand> hands) {
        for (Map.Entry<Integer, List<String>> entry : originalDataBlocks.entrySet()) {
            Hand hand = new Hand();
            for (String originalDataLines : entry.getValue()) {
                hand.setId(entry.getKey());
                if (originalDataLines.contains(PLAYER_CARDS)) {
                    hand.setCards(originalDataLines);
                }
                createPlayers(originalDataLines, hand);
            }
            hands.add(hand);
        }
    }

    private static void createPlayers(String originalDataLines, Hand hand) {
        findBigBlind(originalDataLines, hand);
        findPlayers(originalDataLines, hand);
        if (originalDataLines.contains(SEAT) && originalDataLines.contains(TABLE)) {
            hand.setCurrentButton(originalDataLines);
        }

    }

    private static void findPlayers(String originalDataLines, Hand hand) {
        if (originalDataLines.contains(SEAT) && originalDataLines.contains(CHIP)) {
            setPlayerFields(originalDataLines, hand);
        }
    }

    private static void findBigBlind(String originalDataLines, Hand hand) {
        if (originalDataLines.contains(BIG_BLIND)) {
            hand.setBigBlind(originalDataLines);
        }
    }

    private static void setPlayerFields(String originalDataLines, Hand hand) {
        int index = originalDataLines.indexOf(":");
        String number = originalDataLines.substring(index - 1, index);
        Integer seat = Integer.parseInt(number);
        int playerIndex = originalDataLines.indexOf(" ", index + 2);
        String player = originalDataLines.substring(index + 2, playerIndex);
        String betweenParenthesis = originalDataLines.substring(originalDataLines.indexOf("("), originalDataLines.indexOf(")") + 1);
        double chipCount = Double.parseDouble(betweenParenthesis.substring(1, betweenParenthesis.indexOf(" ")));
        hand.checkIfPlayersPositionIsTakenAndAddNew(seat, player, chipCount);
        hand.findCurrentPlayerPosition();
    }

}
