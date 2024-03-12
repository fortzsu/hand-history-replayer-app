import java.util.ArrayList;
import java.util.List;

public class HandMaker {

    public static Hand makeHandFromBlock(Block block) {
        Hand hand = new Hand();
        hand.setId(block.getId());
        hand.setBigBlind(block.getBigBlind());
        hand.setCards(block.getCards());
        hand.setCurrentButton(block.getButtonLine());
        List<Player> playersList = new ArrayList<>();
        Player player;
        for (String seat : block.getSeats()) {
            player = setPlayerFields(seat, hand);
            playersList.add(player);
        }
        setPlayerActions(playersList, block.getActions());
        return hand;
    }

    private static void setPlayerActions(List<Player> playersList, List<String> actions) {
        for (Player player : playersList) {
            for(String action : actions) {
                if(action.contains(player.getPlayerName())) {
                    player.setAction(action);
                }
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
        Player createdPlayer = hand.checkIfPlayersPositionIsTakenAndAddNew(seat, player, chipCount);
        hand.findCurrentPlayerPosition();
        return createdPlayer;
    }
}
