import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HandTest {

    @Test
    public void addNewPlayer_success() {
        Hand hand = new Hand();
        hand.setBigBlind("ThiagoKbelo: posts big blind 200");
        Assertions.assertNotNull(hand.addNewPlayer(1, "First_Player", 1500.0));
    }

    @Test
    public void addNewPlayer_withTwoPlayers_success() {
        Hand hand = new Hand();
        hand.setBigBlind("ThiagoKbelo: posts big blind 200");
        hand.addNewPlayer(1, "First_Player", 1500.0);
        hand.addNewPlayer(2, "Second_Player", 1500.0);
        Assertions.assertEquals(2, hand.getPlayers().size());
    }

    @Test
    public void addNewPlayer_addedAgain_returnsNull() {
        Hand hand = new Hand();
        hand.setBigBlind("ThiagoKbelo: posts big blind 200");
        hand.addNewPlayer(1, "First_Player", 1500.0);
        Assertions.assertNull(hand.addNewPlayer(1, "First_Player", 1500.0));
    }

    @Test
    public void addNewPlayer_success_testBigBlind() {
        Hand hand = new Hand();
        hand.setBigBlind("ThiagoKbelo: posts big blind 200");
        hand.addNewPlayer(1, "First_Player", 1500.0);
        Assertions.assertEquals(200, hand.getBigBlind());
    }

    @Test
    public void addNewPlayer_success_testPlayers_Chips() {
        Hand hand = new Hand();
        hand.setBigBlind("ThiagoKbelo: posts big blind 200");
        hand.addNewPlayer(1, "First_Player", 1500.0);
        Assertions.assertEquals("7,50", hand.getPlayers().get(0).getChipsInBigBlind());
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
