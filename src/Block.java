import java.util.ArrayList;
import java.util.List;


public class Block {

    private Integer id;
    private String buttonLine;
    private String cards;
    private String bigBlind;
    private final List<String> seats = new ArrayList<>();
    private final List<String> actions = new ArrayList<>();
    private final List<String> actionClosure = new ArrayList<>();
    private Hand hand;

    public Hand getHand() {
        return hand;
    }

    private void setHand() {
        this.hand = HandMaker.makeHandFromBlock(this);
    }

    public String getBigBlind() {
        return bigBlind;
    }

    public void setBigBlind(String bigBlind) {
        this.bigBlind = bigBlind;
    }

    public void addSeat(String seatLine) {
        seats.add(seatLine);
    }

    public void addAction(String actionLine) {
        actions.add(actionLine);
    }

    public void addActionClosure(String actionClosureLine) {
        actionClosure.add(actionClosureLine);
        setHand();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getButtonLine() {
        return buttonLine;
    }

    public void setButtonLine(String buttonLine) {
        this.buttonLine = buttonLine;
    }

    public List<String> getSeats() {
        return seats;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public List<String> getActions() {
        return actions;
    }

    public List<String> getActionClosure() {
        return actionClosure;
    }
}
