import java.util.ArrayList;
import java.util.List;

public class Player {

    private final Integer seatNumber;
    private final String playerName;
    private String nameOfPosition;
    private Double chipCount;
    private String chipsInBigBlind;
    private final List<String> actions = new ArrayList<>();

    public Player(Integer seatNumber, String player) {
        this.seatNumber = seatNumber;
        this.playerName = player;
    }

    public void setAction(String original) {
        this.addAction(original.substring(original.indexOf(":") + 2));
    }

    public void setChipCount( Double bigBlind, Double chipCount) {
        this.chipCount = chipCount;
        setChipsInBigBlind(bigBlind);
    }

    public void setChipsInBigBlind(Double bigBlindFromHand) {
        if (bigBlindFromHand != null && this.chipCount != null) {
            this.chipsInBigBlind = String.format("%.2f", this.chipCount / bigBlindFromHand);
        }
    }

    private void addAction(String action) {
        if (!action.equals("posts the ante 3")) {
            this.actions.add(action);
        }
    }


    public String getChipsInBigBlind() {
        return chipsInBigBlind;
    }

    public Double getChipCount() {
        return chipCount;
    }

    public void setNameOfPosition(String nameOfPosition) {
        this.nameOfPosition = nameOfPosition;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getNameOfPosition() {
        return nameOfPosition;
    }

    public List<String> getActions() {
        return actions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return getSeatNumber() != null ? getSeatNumber().equals(player.getSeatNumber()) : player.getSeatNumber() == null;
    }

}
