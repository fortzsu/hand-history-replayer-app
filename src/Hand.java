import java.util.*;

public class Hand {

    private Integer id;
    private final List<String> cards = new ArrayList<>();
    private Double bigBlind;
    private final List<Player> players = new ArrayList<>();
    private Integer currentButton;
    private final List<String> defaultPositions =
            new ArrayList<>(Arrays.asList("EP1", "EP2", "EP3", "LJ", "HJ", "CO", "BU", "SB", "BB"));
    private final Map<String, String> allPlayerActions = new TreeMap<>();

    public void addPlayerActions(String originalLineOfAction) {
        int splitIndex = originalLineOfAction.indexOf(':');
        if (splitIndex > -1) {
            String name = originalLineOfAction.substring(0, splitIndex);
            String action = originalLineOfAction.substring(splitIndex + 2);
            if (allPlayerActions.containsKey(name)) {
                this.allPlayerActions.put(name + "_", action);
            } else {
                this.allPlayerActions.put(name, action);
            }
        }
    }

    public void setCurrentButton(String originalDataHands) throws NumberFormatException {
        int index = originalDataHands.indexOf("#");
        String button = originalDataHands.substring(index + 1, index + 2);
        this.currentButton = Integer.valueOf(button);
    }

    public void setBigBlind(String line) throws NumberFormatException {
        List<String> lineString = List.of(line.split(" "));
        String value = lineString.get(lineString.size() - 1);
        this.bigBlind = Double.parseDouble(value);
    }

    public Player addNewPlayer(Integer seatNumber, String playerName, Double chipCount) {
        Player tempPlayer = null;
        if (checkIfPlayersPositionIsTaken(seatNumber)) {
            tempPlayer = new Player(seatNumber, playerName);
            this.players.add(tempPlayer);
            setChipCount(tempPlayer, chipCount);
        }
        return tempPlayer;
    }

    private boolean checkIfPlayersPositionIsTaken(Integer seatNumber) {
        for (Player player : this.players) {
            if (player.getSeatNumber().equals(seatNumber)) {
                return false;
            }
        }
        return true;
    }

    private void setChipCount(Player tempPlayer, Double chipCount) {
        tempPlayer.setChipCount(this.bigBlind, chipCount);
    }

    public String getChosenPlayersChipsInBigBlind(String playerName) throws NoSuchElementException {
        String result = "";
        for (Player player : players) {
            if (player.getPlayerName().equals(playerName)) {
                result = player.getChipsInBigBlind();
            }
        }
        return result;
    }

    public void setCards(String line) {
        this.cards.add(line.substring(line.length() - 6, line.length() - 4));
        this.cards.add(line.substring(line.length() - 3, line.length() - 1));
    }

    public void findCurrentPlayerPosition() {
        addPlayerPosition(PositionGenerator.findPosition(players, defaultPositions, currentButton));
    }

    private void addPlayerPosition(Map<Integer, String> orderOfCurrentPositions) {
        for (Player player : players) {
            player.setNameOfPosition(orderOfCurrentPositions.get(player.getSeatNumber()));
        }
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

    public Map<String, String> getAllPlayerActions() {
        return allPlayerActions;
    }
}
