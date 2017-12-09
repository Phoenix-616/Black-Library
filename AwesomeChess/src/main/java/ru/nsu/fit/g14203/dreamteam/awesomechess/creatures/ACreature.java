package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

import ru.nsu.fit.g14203.dreamteam.awesomechess.field.StepRules;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alena on 09.12.2017.
 */

public abstract class ACreature implements ICreature {
    protected String name;
    protected String description;
    protected int strenght;
    protected String imageFileName;
    protected List<StepRules.StepDirectionWithLimit> stepAbilities = new LinkedList<>();

    @Override
    public String GetName() {
        return name ;
    }

    @Override
    public String GetDescription() {
        return description;
    }

    @Override
    public int GetStrength() {
        return strenght;
    }

    @Override
    public abstract List<StepRules.StepDirectionWithLimit> GetStepAbilities();

    @Override
    public abstract ICreature GetOtherForm();

    @Override
    public String GetImgFileName() {
        return imageFileName;
    }
}
