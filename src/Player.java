import java.util.ArrayList;
import java.util.List;

public class Player {

    private Integer seatNumber;
    private String playerName;
    private String nameOfPosition;
    private Double chipCount;
    private String chipsInBigBlind;

    public Player() {
    }

    public Player(Integer seatNumber, String player) {
        this.seatNumber = seatNumber;
        this.playerName = player;
    }

    public void setChipCount(Double bigBlind, Double chipCount) {
        this.chipCount = chipCount;
        setChipsInBigBlind(bigBlind);
    }

    public void setChipsInBigBlind(Double bigBlindFromHand) {
        if (this.chipCount != null) {
            this.chipsInBigBlind = String.format("%.2f", this.chipCount / bigBlindFromHand);
        }
    }
    public String getChipsInBigBlind() {
        return chipsInBigBlind;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return getSeatNumber() != null ? getSeatNumber().equals(player.getSeatNumber()) : player.getSeatNumber() == null;
    }

}
