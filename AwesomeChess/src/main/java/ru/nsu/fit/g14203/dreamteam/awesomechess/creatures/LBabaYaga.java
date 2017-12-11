package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

import ru.nsu.fit.g14203.dreamteam.awesomechess.field.StepRules;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alena on 08.12.2017.
 */
public class LBabaYaga extends ACreature {

    public LBabaYaga(){
        name = "Баба Яга";
        description = "Старая горбатая ведьма. Живет в избушке на курьих ножках, летает в ступе и ест детей.";
        strenght = 6;
        imageFileName = "LBabaYaga.jpg";
        iconFileName = "";
    }

    @Override
    public ICreature GetOtherForm() {
        return null;
    }

    }
