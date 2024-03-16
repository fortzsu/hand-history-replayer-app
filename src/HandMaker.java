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
        List<Player> playersList = fillPlayers(originalLines, hand);
        setPlayerActions(playersList, findActions(originalLines.indexOf(cards) + 1, originalLines));
        hand.findCurrentPlayerPosition();
        hand.fillPlayerActions();
        return hand;
    }

    private static List<Player> fillPlayers(List<String> originalLines, Hand hand) {
        List<Player> playersList = new ArrayList<>();
        for (String originalLine : originalLines) {
            if (originalLine.contains("Seat") && originalLine.contains("chips")) {
                Player player = setPlayerFields(originalLine, hand);
                playersList.add(player);
            }
        }
        return playersList;
    }

    private static List<String> findActions(int i, List<String> originalLines) {
        List<String> allActions = new ArrayList<>();
        while (!originalLines.get(i).contains("Uncalled") && i < originalLines.size() - 1) {
            allActions.add(originalLines.get(i));
            i++;
        }
        return allActions;
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
        return hand.addNewPlayer(seat, player, chipCount);
    }

}
