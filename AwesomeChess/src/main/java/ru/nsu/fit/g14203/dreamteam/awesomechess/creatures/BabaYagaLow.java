package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

import ru.nsu.fit.g14203.dreamteam.awesomechess.field.StepRules;

import java.util.List;

/**
 * Created by Alena on 08.12.2017.
 */
public class BabaYagaLow implements ICreature {

    private String name = "Яга Молодая";
    
    @Override
    public String GetName() {
        return name ;
    }

    @Override
    public String GetDescription() {
        return null;
    }

    @Override
    public int GetStrength() {
        return 0;
    }

    @Override
    public List<StepRules.StepDirectionWithLimit> GetStepAbilities() {
        return null;
    }

    @Override
    public ICreature GetOtherForm() {
        return null;
    }

    @Override
    public String GetImgFileName() {
        return null;
    }
}
