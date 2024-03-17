
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PlayerTest {



    @Test
    public void testSetChipsInBigBlind_withNoChip_fail() {
        Player player = new Player();
        player.setChipCount(5.0, null);
        Assertions.assertNull(player.getChipsInBigBlind());
    }

    @Test
    public void testSetChipsInBigBlind_withChip_ok() {
        Player player = new Player();
        player.setChipCount(5.0, 10.00);
        Assertions.assertEquals("2,00", player.getChipsInBigBlind());
    }

    @Test
    public void testSetChipsInBigBlind_withChip_testFormatting_ok() {
        Player player = new Player();
        player.setChipCount(965.0, 215.0);
        Assertions.assertEquals("0,22", player.getChipsInBigBlind());
    }



}
