import org.junit.Before;
import org.junit.Test;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LKochey;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LRusalka;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.FieldCoord;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Figure;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Model;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.StepRules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alena on 22.12.2017.
 */
public class KnightGoingTest extends FigureGoingTest {
    Figure figure = new Figure(new LRusalka(), StepRules.FigureType.KNIGHT, Figure.FigureColor.WHITE);

    @Before
    public void prepare(){
        chessBoard[3][3].setFigure(figure);
        setCell();
    }

    @Test
    public void upRightTest() {
        assertTrue(StepRules.canFigureGo(figure.type, new FieldCoord(3, 3), new FieldCoord(2, 5), model)); // верхняя г
    }

    @Test
    public void upLeftTest() {
        assertTrue(StepRules.canFigureGo(figure.type, new FieldCoord(3, 3), new FieldCoord(4, 5), model)); // верхняя г
    }

    @Test
    public void downRightTest() {
        assertFalse(StepRules.canFigureGo(figure.type, new FieldCoord(3, 3), new FieldCoord(2, 1), model)); // нижняя г, не рубит свои фигуры
    }

    @Test
    public void downLeftTest() {
        assertFalse(StepRules.canFigureGo(figure.type, new FieldCoord(3, 3), new FieldCoord(4, 1), model)); // нижняя г, е рубит свои фигуры
    }

    @Test
    public void rightUpTest() {
        assertTrue(StepRules.canFigureGo(figure.type, new FieldCoord(3, 3), new FieldCoord(5, 4), model)); // правая г
    }

    @Test
    public void rightDownTest() {
        assertTrue(StepRules.canFigureGo(figure.type, new FieldCoord(3, 3), new FieldCoord(5, 2), model)); // правая г
    }

    @Test
    public void leftUpTest() {
        assertTrue(StepRules.canFigureGo(figure.type, new FieldCoord(3, 3), new FieldCoord(1, 2), model)); // левая г
    }

    @Test
    public void leftDownTest() {
        assertTrue(StepRules.canFigureGo(figure.type, new FieldCoord(3, 3), new FieldCoord(1, 4), model)); // левая г
    }

    @Test
    public void falseWayTest() {
        assertFalse(StepRules.canFigureGo(figure.type, new FieldCoord(3, 3), new FieldCoord(3, 0), model)); // ход неверный
    }

    @Test
    public void falseWayTest2() {
        assertFalse(StepRules.canFigureGo(figure.type, new FieldCoord(3, 3), new FieldCoord(6, 1), model)); // ход неверный
    }

    @Test
    public void falseWayTest3() {
        assertFalse(StepRules.canFigureGo(figure.type, new FieldCoord(3, 3), new FieldCoord(1, 5), model)); // ход неверный
    }
}
