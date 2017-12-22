import org.junit.Before;
import org.junit.Test;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LDomovoy;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LKochey;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.FieldCoord;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Figure;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Model;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.StepRules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alena on 22.12.2017.
 */
public class PawnGoingTest extends FigureGoingTest{
    Figure figure = new Figure(new LDomovoy(), StepRules.FigureType.PAWN, Figure.FigureColor.WHITE);

    @Before
    public void prepare(){
        chessBoard[3][3].setFigure(figure);
        chessBoard[3][5].setFigure(figure);
        setCell();
    }

    @Test
    public void stepTest() {
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(3, 4), model)); // вверх если свободно
    }

    @Test
    public void upRightTest() {
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 5), new FieldCoord(4, 6), model)); // вправо по диагонали
    }

    @Test
    public void upLeftTest() {
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 5), new FieldCoord(2, 6), model)); // влево по диагонали
    }

    @Test
    public void stepBackTest() {
        assertFalse(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(3, 2), model)); // шаг назад неверный
    }

    @Test
    public void bootomRightTest() {
        assertFalse(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(2, 2), model)); // вправо назад по диагонали
    }

    @Test
    public void bootomLeftTest() {
        assertFalse(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(4, 2), model)); // вправо назад по диагонали
    }


    @Test
    public void stepBackTest1() {
        assertFalse(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(3, 0), model)); // шаг назад неверный
    }

    @Test
    public void stepTest1() {
        assertFalse(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(3, 7), model)); // шаг вперед неверный
    }

    @Test
    public void bootomRightTest1() {
        assertFalse(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(2, 2), model)); // вправо назад по диагонали
    }

    @Test
    public void bootomLeftTest1() {
        assertFalse(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(4, 2), model)); // вправо назад по диагонали
    }

}
