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
}
