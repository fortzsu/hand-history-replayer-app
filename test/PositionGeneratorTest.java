import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PositionGeneratorTest {


    private final List<String> defaultPositions = new ArrayList<>(Arrays.asList("EP1", "EP2", "EP3", "LJ", "HJ", "CO", "BU", "SB", "BB"));

    @Test
    public void testFindPosition_withTwoPlayers_success() {
        List<Player> playerList = new ArrayList<>();
        Player player1 = new Player(1,"First");
        playerList.add(player1);
        Player player2 = new Player(2,"Second");
        playerList.add(player2);
        PositionGenerator.findPosition(playerList, this.defaultPositions, 1);
        Assertions.assertEquals("BU&SB", player1.getNameOfPosition());
    }




}
