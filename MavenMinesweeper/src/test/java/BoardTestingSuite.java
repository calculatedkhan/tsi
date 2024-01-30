import org.example.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTestingSuite {

    @Test
    public void revealingCellCheck() {
        Board board = new Board(5,5);
        board.revealCell(1,1);
        Assertions.assertTrue(board.cellRevealedStatus(1,1), "Tiles not revealed");
    }

    @Test
    public void hasWonCheck() {
        Board board = new Board(5,5);
        board.placeMines(0);
        board.revealCell(1,1);
        Assertions.assertTrue(board.checkWin(), "Win Checker failed, player should've won");
    }

    @Test
    public void gameOverCheck() {
        Board board = new Board(5,5);
        board.setGameOver(true);
        Assertions.assertTrue(board.isGameOver(), "Game wasn't terminated after player won");;
    }

    @Test
    public void flagCellCheck() {
        Board board = new Board(5,5);
        board.flagCell(1,1);
        Assertions.assertTrue(board.cellFlaggedStatus(1,1), "Cell flagged by user hasn't been flagged in game");
    }

    @Test
    public void  minePlacingCheck() {
         
    }
}
