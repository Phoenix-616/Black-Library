import org.junit.Test;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LLesovik;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LRusalka;
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
public class BishopGoingTest {
    Figure figure = new Figure(new LLesovik(), StepRules.FigureType.BISHOP, Figure.FigureColor.WHITE);
    Model model = new Model();
/*
    @Test
    public void upRightTest() {
        Model model = new Model();
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(5, 5), model)); // вверх вправо
    }

    @Test
    public void upLeftTest() {
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(3, 2), model)); // вверх влево
    }

    @Test
    public void downRightTest() {
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(7, 3), model)); // вниз вправо
    }

    @Test
    public void downLeftTest() {
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(0, 3), model)); // вниз влево
    }

    @Test
    public void figureOnWayTest() {
        assertFalse(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(3, 0), model)); // на пути фигура
    }

    @Test
    public void falseWayTest() {
        assertFalse(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(4, 4), model)); // по диагонали
    }
*/
}
