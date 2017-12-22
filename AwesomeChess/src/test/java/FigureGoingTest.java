import org.junit.Before;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.*;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Cell;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Figure;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Model;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.StepRules;

/**
 * Created by Alena on 22.12.2017.
 */
public class FigureGoingTest {
    protected Cell[][] chessBoard;
    protected Model model;


    @Before
    public void createModel() {
        chessBoard = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 2; j < 6; j++) {
                chessBoard[i][j] = new Cell();
            }
        }

        for (int i = 0; i < 8; i++) {
            chessBoard[i][1] = new Cell(new Figure(new LDomovoy(), StepRules.FigureType.PAWN, Figure.FigureColor.WHITE));
        }

        chessBoard[0][0] = new Cell(new Figure(new LVolkolak(), StepRules.FigureType.ROOK, Figure.FigureColor.WHITE));
        chessBoard[7][0] = new Cell(new Figure(new LVolkolak(), StepRules.FigureType.ROOK, Figure.FigureColor.WHITE));

        chessBoard[1][0] = new Cell(new Figure(new LRusalka(), StepRules.FigureType.KNIGHT, Figure.FigureColor.WHITE));
        chessBoard[6][0] = new Cell(new Figure(new LRusalka(), StepRules.FigureType.KNIGHT, Figure.FigureColor.WHITE));

        chessBoard[2][0] = new Cell(new Figure(new LLesovik(), StepRules.FigureType.BISHOP, Figure.FigureColor.WHITE));
        chessBoard[5][0] = new Cell(new Figure(new LLesovik(), StepRules.FigureType.BISHOP, Figure.FigureColor.WHITE));

        chessBoard[3][0] = new Cell(new Figure(new LBabaYaga(), StepRules.FigureType.QUEEN, Figure.FigureColor.WHITE));
        chessBoard[4][0] = new Cell(new Figure(new LKochey(), StepRules.FigureType.KING, Figure.FigureColor.WHITE));

        for (int i = 0; i < 8; i++) {
            chessBoard[i][6] = new Cell(new Figure(new LDomovoy(), StepRules.FigureType.PAWN, Figure.FigureColor.BLACK));
        }

        chessBoard[7][7] = new Cell(new Figure(new LVolkolak(), StepRules.FigureType.ROOK, Figure.FigureColor.BLACK));
        chessBoard[0][7] = new Cell(new Figure(new LVolkolak(), StepRules.FigureType.ROOK, Figure.FigureColor.BLACK));

        chessBoard[1][7] = new Cell(new Figure(new LRusalka(), StepRules.FigureType.KNIGHT, Figure.FigureColor.BLACK));
        chessBoard[6][7] = new Cell(new Figure(new LRusalka(), StepRules.FigureType.KNIGHT, Figure.FigureColor.BLACK));

        chessBoard[2][7] = new Cell(new Figure(new LLesovik(), StepRules.FigureType.BISHOP, Figure.FigureColor.BLACK));
        chessBoard[5][7] = new Cell(new Figure(new LLesovik(), StepRules.FigureType.BISHOP, Figure.FigureColor.BLACK));

        chessBoard[4][7] = new Cell(new Figure(new LBabaYaga(), StepRules.FigureType.QUEEN, Figure.FigureColor.BLACK));
        chessBoard[3][7] = new Cell(new Figure(new LKochey(), StepRules.FigureType.KING, Figure.FigureColor.BLACK));
    }

    public void setCell(){
        model = new Model(chessBoard);
    }
}
