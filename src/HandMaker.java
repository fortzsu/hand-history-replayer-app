import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HandMaker {

    public static Hand makeHandFromBlock(Block block) {
        Hand hand = new Hand();
        hand.setId(block.getId());
        hand.setBigBlind(block.getBigBlind());
        hand.setCards(block.getCards());
        hand.setCurrentButton(block.getButtonLine());
        for (String seat : block.getSeats()) {
            setPlayerFields(seat, hand);
        }
        return hand;
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
