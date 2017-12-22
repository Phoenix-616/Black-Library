package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

/**
 * Created by Alena on 09.12.2017.
 */

public abstract class ACreature implements ICreature {
    protected String name;
    protected String description;
    protected int strenght;
    protected String imageFileName;
    protected String iconFileName;

    @Override
    public String getName() {
        return name ;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getStrength() {
        return strenght;
    }

    @Override
    public String getIconFileName(){return iconFileName; }

    @Override
    public abstract ICreature getOtherForm();

    @Override
    public String getImgFileName() {
        return imageFileName;
    }
}
