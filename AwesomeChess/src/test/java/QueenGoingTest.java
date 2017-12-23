import org.junit.Before;
import org.junit.Test;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LBabaYaga;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.FieldCoord;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Figure;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Model;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.StepRules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alena on 22.12.2017.
 */
public class QueenGoingTest extends FigureGoingTest{
    Figure figure = new Figure(new LBabaYaga(), StepRules.FigureType.QUEEN, Figure.FigureColor.WHITE);

    @Before
    public void prepare(){
        chessBoard[3][3].setFigure(figure);
        setCell();
    }

    @Test
    public void upLeftTest() {
        assertTrue(StepRules.canFigureGo(figure.type, new FieldCoord(3, 3), new FieldCoord(2, 4), model)); // вверх влево
    }

    @Test
    public void downRightTest() {
        assertTrue(StepRules.canFigureGo(figure.type, new FieldCoord(3, 3), new FieldCoord(4, 2), model)); // вниз вправо
    }

    @Test
    public void downTest() {
        assertTrue(StepRules.canFigureGo(figure.type, new FieldCoord(3, 3), new FieldCoord(3, 2), model)); // вниз
    }

    @Test
    public void rightTest() {
        assertTrue(StepRules.canFigureGo(figure.type, new FieldCoord(3, 3), new FieldCoord(7, 3), model)); // вправо
    }

}
