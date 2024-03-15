import java.util.*;

public class Hand {

    private Integer id;
    private List<String> cards;
    private Double bigBlind;
    private final List<Player> players = new ArrayList<>();
    private Integer currentButton;
    private final List<String> defaultPositions = new ArrayList<>(Arrays.asList("EP1", "EP2", "EP3", "LJ", "HJ", "CO", "BU", "SB", "BB"));
    private final Map<String, List<String>> allPlayerActions = new HashMap<>();

    public void setCurrentButton(String originalDataHands) throws NumberFormatException{
        int index = originalDataHands.indexOf("#");
        String button = originalDataHands.substring(index + 1, index + 2);
        this.currentButton = Integer.valueOf(button);
    }

    public void setBigBlind(String line) throws NumberFormatException{
        List<String> lineString = List.of(line.split(" "));
        String value = lineString.get(lineString.size() - 1);
        this.bigBlind = Double.parseDouble(value);
    }

    public Player addNewPlayer(Integer seatNumber, String playerName, Double chipCount) {
        Player tempPlayer = new Player(seatNumber, playerName);
        if (checkIfPlayersPositionIsTaken(tempPlayer)) {
            this.players.add(tempPlayer);
            setChipCount(tempPlayer, chipCount);
        }
        return tempPlayer;
    }




    private void setChipCount(Player tempPlayer, Double chipCount) {
        tempPlayer.setChipCount(this.bigBlind, chipCount);
    }

    private boolean checkIfPlayersPositionIsTaken(Player tempPlayer) {
        return !this.players.contains(tempPlayer);
    }

    public void fillPlayerActions() {
        for (Player player : this.players) {
            if (!player.getPlayerName().equals("ZombiChicken")) {
                this.allPlayerActions.put(player.getPlayerName(), player.getActions());
            } else {
                this.allPlayerActions.put(player.getPlayerName(), player.getActions());
                break;
            }
        }
    }

    public void findCurrentPlayerPosition() {
        addPlayerHisPosition(PositionGenerator.findPosition(players, defaultPositions, currentButton));
    }

    private void addPlayerHisPosition(Map<Integer, String> orderOfCurrentPositions) {
        for (Player player : players) {
            player.setNameOfPosition(orderOfCurrentPositions.get(player.getSeatNumber()));
        }
    }


    public void setCards(String line) {
        List<String> cards = new ArrayList<>();
        String first = line.substring(line.length() - 6, line.length() - 4);
        String second = line.substring(line.length() - 3, line.length() - 1);
        cards.add(first);
        cards.add(second);
        this.cards = cards;
    }


    public String getChosenPlayersChipsInBigBlind(String playerName) {
        String result = "";
        for (Player player : players) {
            if (player.getPlayerName().equals(playerName)) {
                result = player.getChipsInBigBlind();
            }
        }
        return result;
    }

    public Double getBigBlind() {
        return bigBlind;
    }

    public List<String> getCards() {
        return cards;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurrentButton() {
        return currentButton;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getDefaultPositions() {
        return defaultPositions;
    }

    public Map<String, List<String>> getAllPlayerActions() {
        return allPlayerActions;
    }
}
