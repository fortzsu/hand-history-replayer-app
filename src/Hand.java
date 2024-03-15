import java.util.*;

public class Hand {

    private Integer id;
    private final List<String> cards = new ArrayList<>();
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

    public Player addNewPlayer(Integer seatNumber, String playerName, Double chipCount){
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
            } else {
                throw new NoSuchElementException();
            }
        }
        return result;
    }

    public void setCards(String line) {
        this.cards.add(line.substring(line.length() - 6, line.length() - 4));
        this.cards.add(line.substring(line.length() - 3, line.length() - 1));
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
