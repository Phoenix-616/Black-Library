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
public class QueenGoingTest {
    Figure figure = new Figure(new LBabaYaga(), StepRules.FigureType.QUEEN, Figure.FigureColor.WHITE);
    Model model = new Model();
/*
    @Test
    public void trueTest() {
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(3, 5), model)); // вверх
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(5, 5), model)); // вверх-вправо
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(7, 3), model)); // вправо
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(5, 1), model)); // вниз-вправо
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(3, 0), model)); // вниз
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(1, 1), model)); // вниз-влево
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(2, 3), model)); // влево
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(1, 5), model)); // влево-вверх
    }

    @Test
    public void falseTest() {
        assertFalse(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(0, 4), model));
        assertFalse(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(2, 0), model));
        assertFalse(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(5, 7), model));
        assertFalse(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(7, 2), model));
    }
*/
}
