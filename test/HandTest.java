import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HandTest {

    @Test
    public void testSetCurrentButton_ok() {
        Hand hand = new Hand();
        hand.setCurrentButton("Table '3713502695 1' 9-max Seat #9 is the button");
        Assertions.assertEquals(9, hand.getCurrentButton());
    }
    @Test
    public void testSetCurrentButton_withNoValidButton() {
        Hand hand = new Hand();
        hand.setCurrentButton("Table '3713502695 1' 9-max Seat # is the button");
        Assertions.assertEquals(0, hand.getCurrentButton());
    }

    @Test
    public void testSetCurrentButton_withOtherPosition() {
        Hand hand = new Hand();
        hand.setCurrentButton("Table #9 '3713502695 1' 9-max Seat #9 is the button");
        Assertions.assertEquals(9, hand.getCurrentButton());
    }
}
