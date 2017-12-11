package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

import ru.nsu.fit.g14203.dreamteam.awesomechess.field.StepRules;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alena on 11.12.2017.
 */
public class HBabaYaga extends ACreature {

    public HBabaYaga(){
        name = "Лесная Ведьма Ягда";
        description = "Молодая красивая ведьма. Управляет драконами и змеями. Заманивает мужчин в чащу и убивает их";
        strenght = 9;
        imageFileName = "HBabaYaga.jpg";
        iconFileName = "";
    }

    @Override
    public ICreature GetOtherForm() {
        return null;
    }
}
