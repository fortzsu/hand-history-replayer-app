import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionGenerator {

    public static Map<Integer, String> findPosition(List<Player> players, List<String> defaultPositions,
                                                    Integer currentButton) {
        Map<Integer, String> orderOfCurrentPositions = new HashMap<>();
        if (players.size() == 2) {
            addPositionWhenThereAreTwoPlayers(orderOfCurrentPositions, currentButton, players);
        } else {
            addPositionWhenThereAreMorePlayers(orderOfCurrentPositions, currentButton, defaultPositions, players);
        }
        return orderOfCurrentPositions;
    }


    private static void addPositionWhenThereAreMorePlayers(Map<Integer, String> orderOfCurrentPositions, Integer currentButton,
                                                           List<String> defaultPositions, List<Player> players) {
        List<Integer> current = fillCurrentPlayersList(players);
        int currentButtonIndex = current.indexOf(currentButton);
        int defaultButtonIndex = 6;
        for (int i = 0, j = defaultButtonIndex, k = currentButtonIndex; i < current.size(); i++) {
            k++;
            if (k == current.size()) {
                k = 0;
            }
            j++;
            if (j == defaultPositions.size()) {
                j = defaultPositions.size() - current.size();
            }
            orderOfCurrentPositions.put(current.get(k), defaultPositions.get(j));
        }
    }

    private static void addPositionWhenThereAreTwoPlayers(Map<Integer, String> orderOfCurrentPositions,
                                                          Integer currentButton, List<Player> players) {
        List<Integer> current = fillCurrentPlayersList(players);
        for (Integer integer : current) {
            if (integer.equals(currentButton)) {
                orderOfCurrentPositions.put(integer, "BU&SB");
            } else {
                orderOfCurrentPositions.put(integer, "BB");
            }
        }
    }

    private static List<Integer> fillCurrentPlayersList(List<Player> players) {
        List<Integer> resultList = new ArrayList<>();
        for (Player player : players) {
            resultList.add(player.getSeatNumber());
        }
        return resultList;
    }
}
