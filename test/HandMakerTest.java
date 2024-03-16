import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HandMakerTest {

    private Block generateBlock() {
        Block block = new Block();
        block.setId(1);
        block.setBigBlind("ThiagoKbelo: posts big blind 80");
        block.setCards("Dealt to ZombiChicken [Ac 5s]");
        block.setButtonLine("Table '3713502695 1' 9-max Seat #9 is the button");
        block.addSeat("Seat 1: ZombiChicken (47 in chips) is sitting out");
        block.addSeat("Seat 2: pericles420z (986 in chips)");
        block.addSeat("Seat 9: JlecHou90 (962 in chips)");
        block.addAction("FelipeBarca1: folds");
        block.addAction("Vietcongen: bets 80");
        block.addAction("JlecHou90: folds");
        block.addAction("FelipeBarca1: folds");
        block.addAction("enzoen: calls 80");
        block.addAction("Vietcongen: calls 80");
        return block;
    }

    @Test
    public void testMakeHandFromBlock_testBigBlind_success() {
        Block block = generateBlock();
        Assertions.assertEquals(80, HandMaker.makeHandFromBlock(block).getBigBlind());
    }



}
