import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

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
            String chipsInBigBlind = hand.getChipsInBigBlind();
            String actualPosition = "";
            for (Player player : hand.getPlayers()) {
                if(player.getPlayerName().equals(PLAYER_NAME)) {
                    actualPosition = player.getNameOfPosition();
                }
            }
            List<String> actions = hand.getPlayers().get(0).getActions();//TODO
            writer.println(fillHTMLData(firstImgSource, secondImgSource, chipsInBigBlind, actions, actualPosition));
        }
    }


    public static String fillHTMLData(String firstImgSource,
                                      String secondImgSource, String chipsInBigBlind, List<String> actions,
                                      String actualPosition) {
        return "<div class=\"holder-div\">" +
                "<div>" + chipsInBigBlind + " </div>" +
                "<img src=" + firstImgSource + ">" +
                "<img src=" + secondImgSource + ">" +
                "<span>" + addActions(actions) + " </span>" +
                "<div>" + actualPosition + " </div>"
                + " </div>";
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
