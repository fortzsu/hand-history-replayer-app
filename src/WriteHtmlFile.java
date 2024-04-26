import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WriteHtmlFile {

    private static final String PLAYER_NAME = "ZombiChicken";

    public static void printIntoHtml(List<Hand> hands) {
        try {
            File file = new File("cards.html");
            PrintWriter writer = new PrintWriter(file);
            String header = "<head>" +
                    "<link rel=\"stylesheet\" href=\"style.css\">" +
                    "</head>";
            writer.println(header);
            iterateOverHands(hands, writer);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void iterateOverHands(List<Hand> hands, PrintWriter writer) {
        for (Hand hand : hands) {
            String firstImgSource = findCard(hand.getCards().get(0));
            String secondImgSource = findCard(hand.getCards().get(1));
            String chipsInBigBlind = hand.getChosenPlayersChipsInBigBlind(PLAYER_NAME);
            String actualPosition = "";
            String playerAction = "";
            List<String> actions = new ArrayList<>();
            for (Map.Entry<Map<Integer, String>, String> entry : hand.getAllPlayerActions().entrySet()) {
                for (Map.Entry<Integer, String> stringEntry : entry.getKey().entrySet()) {
                    if (stringEntry.getValue().equals(PLAYER_NAME)) {
                        actions.add(entry.getValue());
                        playerAction = stringEntry.getValue() + ": " + entry.getValue();
                        break;
                    } else {
                        actions.add(entry.getValue());
                    }
                }
            }
            for (Player player : hand.getPlayers()) {
                if (player.getPlayerName().equals(PLAYER_NAME)) {
                    actualPosition = player.getNameOfPosition();
                }
            }
            writer.println(fillHTMLData(firstImgSource, secondImgSource,
                    chipsInBigBlind, playerAction, actions, actualPosition));
        }
    }


    public static String fillHTMLData(String firstImgSource,
                                      String secondImgSource, String chipsInBigBlind, String playerAction,
                                      List<String> actions, String actualPosition) {
        return "<div class=\"holder-div\">" +
                "<div>" + chipsInBigBlind + " </div>" +
                "<div>" + actualPosition + " </div>" +
                "<img src=" + firstImgSource + ">" +
                "<img src=" + secondImgSource + ">" +
                "<br>" +
                "<span>" + addActions(actions) + " </span>" +
                "<br>" +
                "<span class=\"player-action\">" + playerAction + " </span>" +
                " </div>";
    }

    private static String addActions(List<String> actions) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < actions.size(); i++) {
            if (i < actions.size() - 1) {
                sb.append(actions.get(i)).append(" - ");
            } else {
                sb.append(actions.get(i));
            }
        }
        return String.valueOf(sb);
    }

    private static String findCard(String line) {
        return "resources/cards_jpg/" + line + ".png";
    }


}
