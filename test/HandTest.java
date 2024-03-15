import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HandTest {

    @Test
    public void addNewPlayer_success() {

    }

    @Test
    public void addNewPlayer_addedAgain_returnsNull() {

    }

    @Test
    public void addNewPlayer_success_testChipCount() {

    }

    @Test
    public void testSetBigBlind_wrongInput() {
        assertThrows(NumberFormatException.class,
                () -> {
                    Hand hand = new Hand();
                    hand.setBigBlind("ThiagoKbelo");
                });
    }

    @Test
    public void testSetBigBlind_NoInput() {
        assertThrows(NumberFormatException.class,
                () -> {
                    Hand hand = new Hand();
                    hand.setBigBlind("");
                });
    }

    @Test
    public void testSetBigBlind_success() {
        Hand hand = new Hand();
        hand.setBigBlind("ThiagoKbelo: posts big blind 200");
        Assertions.assertEquals(200, hand.getBigBlind());
    }

    @Test
    public void testSetCurrentButton_success() {
        Hand hand = new Hand();
        hand.setCurrentButton("Table '3713502695 1' 9-max Seat #9 is the button");
        Assertions.assertEquals(9, hand.getCurrentButton());
    }

    @Test
    public void testSetCurrentButton_withNoValidButton() {
        assertThrows(NumberFormatException.class,
                () -> {
                    Hand hand = new Hand();
                    hand.setCurrentButton("Table '3713502695 1' 9-max Seat # is the button");
                });
    }

    @Test
    public void testSetCurrentButton_withOtherPosition() {
        Hand hand = new Hand();
        hand.setCurrentButton("Table #9 '3713502695 1' 9-max Seat #9 is the button");
        Assertions.assertEquals(9, hand.getCurrentButton());
    }
}
