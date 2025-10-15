package no.uib.inf101.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import no.uib.inf101.tetris.model.TetrisBoard;

public class TetrisBoardTest {
    
    @Test
    public void prettyStringTest() {
    TetrisBoard board = new TetrisBoard(3, 4);
        board.set(new CellPosition(0, 0), 'r'); // Rød i øvre venstre hjørne
        board.set(new CellPosition(board.rows()-1, 0), 'b'); // Blå i nedre venstre hjørne
        board.set(new CellPosition(0, board.cols()-1), 'y'); // Gul i øvre høyre hjørne
        board.set(new CellPosition(board.rows()-1, board.cols()-1), 'w');   // Hvit i nedre høyre hjørne
    
    String expected = String.join("\n", new String[] {
        "r--y",
        "----",
        "b--w"
    });
    assertEquals(expected, board.prettyString());
    }


}
