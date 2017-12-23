
import org.junit.*;
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
public class KingGoingTest extends FigureGoingTest{
    Figure figure = new Figure(new LKochey(), StepRules.FigureType.KING, Figure.FigureColor.WHITE);

    @Before
    public void prepare(){
        chessBoard[3][3].setFigure(figure);
        setCell();
    }


    @Test
    public void trueTest(){
        assertTrue(StepRules.canFigureGo(figure.type, new FieldCoord(3,3), new FieldCoord(4,4), model)); //начиная с верхнего правого угла иду по часовой стрелке
        assertTrue(StepRules.canFigureGo(figure.type, new FieldCoord(3,3), new FieldCoord(4,3), model)); //начиная с верхнего правого угла иду по часовой стрелке
        assertTrue(StepRules.canFigureGo(figure.type, new FieldCoord(3,3), new FieldCoord(4,2), model)); //начиная с верхнего правого угла иду по часовой стрелке
        assertTrue(StepRules.canFigureGo(figure.type, new FieldCoord(3,3), new FieldCoord(3,2), model)); //начиная с верхнего правого угла иду по часовой стрелке
        assertTrue(StepRules.canFigureGo(figure.type, new FieldCoord(3,3), new FieldCoord(2,2), model)); //начиная с верхнего правого угла иду по часовой стрелке
        assertTrue(StepRules.canFigureGo(figure.type, new FieldCoord(3,3), new FieldCoord(2,3), model)); //начиная с верхнего правого угла иду по часовой стрелке
        assertTrue(StepRules.canFigureGo(figure.type, new FieldCoord(3,3), new FieldCoord(2,4), model)); //начиная с верхнего правого угла иду по часовой стрелке
    }

    @Test
    public void falseTest() {
        assertFalse(StepRules.canFigureGo(figure.type, new FieldCoord(3, 3), new FieldCoord(5, 4), model)); //начиная с верхнего правого угла иду по часовой стрелке
        assertFalse(StepRules.canFigureGo(figure.type, new FieldCoord(3, 3), new FieldCoord(0, 0), model)); //начиная с верхнего правого угла иду по часовой стрелке
    }
}
