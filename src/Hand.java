import java.util.*;

public class Hand {

    private Integer id;
    private List<String> cards;
    private Double bigBlind;
    private final List<Player> players = new ArrayList<>();
    private Integer currentButton;
    private final List<String> defaultPositions = new ArrayList<>(Arrays.asList("EP1", "EP2", "EP3", "LJ", "HJ", "CO", "BU", "SB", "BB"));

    public void findCurrentPlayerPosition(){
        addPlayerHisPosition(PositionGenerator.findPosition(players, defaultPositions, currentButton));
    }

    private void addPlayerHisPosition(Map<Integer, String> orderOfCurrentPositions) {
        for (Player player : players) {
            player.setNameOfPosition(orderOfCurrentPositions.get(player.getSeatNumber()));
        }
    }

    public Player checkIfPlayersPositionIsTakenAndAddNew(Integer seatNumber, String player, Double chipCount) {
        Player tempPlayer = new Player(seatNumber, player);
        if (!this.players.contains(tempPlayer)) {
            this.players.add(tempPlayer);
        }
        tempPlayer.setChipCount(this.bigBlind, chipCount);
        return tempPlayer;
    }

    public void setCards(String line) {
        List<String> cards = new ArrayList<>();
        String first = line.substring(line.length() - 6, line.length() - 4);
        String second = line.substring(line.length() - 3, line.length() - 1);
        cards.add(first);
        cards.add(second);
        this.cards = cards;
    }

    public void setBigBlind(String line) {
        List<String> lineString = List.of(line.split(" "));
        String value = lineString.get(lineString.size() - 1);
        this.bigBlind = Double.parseDouble(value);
    }

    public void setCurrentButton(String originalDataHands) {
        int index = originalDataHands.indexOf("#");
        String button = originalDataHands.substring(index + 1, index + 2);
        this.currentButton = Integer.valueOf(button);
    }

    public String getChosenPlayersChipsInBigBlind(String playerName) {
        String result = "";
        for (Player player : players) {
            if(player.getPlayerName().equals(playerName)) {
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


}
