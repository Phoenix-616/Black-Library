
import org.junit.*;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LKochey;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.FieldCoord;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Figure;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Model;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.StepRules;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alena on 22.12.2017.
 */
public class KingTest{
    Figure figure = new Figure(new LKochey(), StepRules.FigureType.KING, Figure.FigureColor.WHITE);
    Model model = new Model();

    @Test
    public void trueTest(){
        assertTrue(StepRules.canFigureGo(figure.TYPE, new FieldCoord(3,4), new FieldCoord(3,5), model));
    }
}
