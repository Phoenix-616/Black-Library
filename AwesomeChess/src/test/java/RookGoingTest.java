import org.junit.Before;
import org.junit.Test;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LBabaYaga;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LVolkolak;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.FieldCoord;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Figure;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Model;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.StepRules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alena on 22.12.2017.
 */
public class RookGoingTest extends FigureGoingTest{
    Figure figure = new Figure(new LVolkolak(), StepRules.FigureType.ROOK, Figure.FigureColor.WHITE);

    @Before
    public void prepare(){
        chessBoard[3][3].setFigure(figure);
        setCell();
    }


    @Test
    public void upTest() {
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(3, 5), model)); // вверх
    }

    @Test
    public void downTest() {
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(3, 2), model)); // вниз
    }

    @Test
    public void rightTest() {
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(7, 3), model)); // вправо
    }

    @Test
    public void leftTest() {
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(0, 3), model)); // влево
    }

    @Test
    public void figureOnWayTest() {
        assertFalse(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(3, 0), model)); // на пути фигура
    }

    @Test
    public void falseWayTest() {
        assertFalse(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(4, 4), model)); // по диагонали
    }

}
