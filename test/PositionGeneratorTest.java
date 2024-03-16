import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PositionGeneratorTest {



    @Test
    public void testFindPosition_withTwoPlayers_buttonIsFirst_success() {
        Hand hand = new Hand();
        hand.setBigBlind("ThiagoKbelo: posts big blind 200");
        hand.addNewPlayer(1, "First", 1500.0);
        hand.addNewPlayer(2, "Second", 1500.0);
        hand.setCurrentButton("#1");
        hand.findCurrentPlayerPosition();
        Assertions.assertEquals("BU&SB", hand.getPlayers().get(0).getNameOfPosition());
    }

    @Test
    public void testFindPosition_withTwoPlayers_buttonIsSecond_success() {
        Hand hand = new Hand();
        hand.setBigBlind("ThiagoKbelo: posts big blind 200");
        hand.addNewPlayer(1, "First", 1500.0);
        hand.addNewPlayer(2, "Second", 1500.0);
        hand.setCurrentButton("#2");
        hand.findCurrentPlayerPosition();
        Assertions.assertEquals("BB", hand.getPlayers().get(0).getNameOfPosition());
    }

    @Test
    public void testFindPosition_withMaxPlayers_buttonIsThird_success() {
        Hand hand = new Hand();
        hand.setBigBlind("ThiagoKbelo: posts big blind 200");
        hand.addNewPlayer(1, "Player", 1500.0);
        hand.addNewPlayer(2, "Player", 1500.0);
        hand.addNewPlayer(3, "Player", 1500.0);
        hand.addNewPlayer(4, "Player", 1500.0);
        hand.addNewPlayer(5, "Player", 1500.0);
        hand.addNewPlayer(6, "Player", 1500.0);
        hand.addNewPlayer(7, "Player", 1500.0);
        hand.addNewPlayer(8, "Player", 1500.0);
        hand.addNewPlayer(9, "Player", 1500.0);
        hand.setCurrentButton("#3");
        hand.findCurrentPlayerPosition();
        Assertions.assertEquals("BU", hand.getPlayers().get(2).getNameOfPosition());
        Assertions.assertEquals("BB", hand.getPlayers().get(4).getNameOfPosition());
        Assertions.assertEquals("EP3", hand.getPlayers().get(7).getNameOfPosition());
        Assertions.assertEquals("HJ", hand.getPlayers().get(0).getNameOfPosition());
    }

    @Test
    public void testFindPosition_withFivePlayers_buttonIsSecond_success() {
        Hand hand = new Hand();
        hand.setBigBlind("ThiagoKbelo: posts big blind 200");
        hand.addNewPlayer(1, "Player", 1500.0);
        hand.addNewPlayer(4, "Player", 1500.0);
        hand.addNewPlayer(5, "Player", 1500.0);
        hand.addNewPlayer(7, "Player", 1500.0);
        hand.addNewPlayer(9, "Player", 1500.0);
        hand.setCurrentButton("#1");
        hand.findCurrentPlayerPosition();
        Assertions.assertEquals("BU", hand.getPlayers().get(0).getNameOfPosition());
        Assertions.assertEquals("SB", hand.getPlayers().get(1).getNameOfPosition());
        Assertions.assertEquals("BB", hand.getPlayers().get(2).getNameOfPosition());
        Assertions.assertEquals("HJ", hand.getPlayers().get(3).getNameOfPosition());
        Assertions.assertEquals("CO", hand.getPlayers().get(4).getNameOfPosition());
    }


}
