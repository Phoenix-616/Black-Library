import org.junit.Test;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LBabaYaga;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LVolkolak;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.FieldCoord;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Figure;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Model;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.StepRules;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alena on 22.12.2017.
 */
public class RookGoingTest {
    Figure figure = new Figure(new LVolkolak(), StepRules.FigureType.ROOK, Figure.FigureColor.WHITE);
    Model model = new Model();

    @Test
    public void UpTest() {
        Model model = new Model();
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(3, 5), model)); // вверх
    }

    @Test
    public void DownTest() {
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(3, 2), model)); // вниз
    }

    @Test
    public void RightTest() {
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(7, 3), model)); // вправо
    }

    @Test
    public void LeftTest() {
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3, 3), new FieldCoord(0, 3), model)); // влево
    }

}
