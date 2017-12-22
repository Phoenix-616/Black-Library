package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

/**
 * Created by Alena on 09.12.2017.
 */

public abstract class ACreature implements ICreature {
    protected String name;
    protected String description;
    protected int strenght;
    protected String imageFileName;
    protected String iconFileNameBlack;
    protected String iconFileNameWhite;

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
    public String getIconFileNameBlack(){return iconFileNameBlack; }
    
    @Override
    public String getIconFileNameWhite(){return iconFileNameWhite; }

    @Override
    public String getImgFileName() {
        return imageFileName;
    }
}
