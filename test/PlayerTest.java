
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PlayerTest {

    @Test
    public void testPlayerActionsSetter() {
        Player player = new Player();
        String originalAction = "Fayemouse: folds ";
        player.setAction(originalAction);
        Assertions.assertEquals("folds ", player.getActions().get(0));
    }

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

    @Test
    public void testAddActions_ok() {
        String originalAction_one = "Fayemouse: folds ";
        String originalAction_two = "Fayemouse: posts the ante 3";
        Player player = new Player();
        player.setAction(originalAction_one);
        player.setAction(originalAction_two);
        Assertions.assertEquals(1, player.getActions().size());
    }

}
