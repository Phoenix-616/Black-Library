package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

/**
 *
 * @author ekaterina
 */
public class LDomovoy extends ACreature {

    public LDomovoy() {
        name = "Домовой";
        description = "Незаметный, но незаменимый боец домашнего фронта";
        strenght = 2;
        imageFileName = "LDomovoy.jpg";
        iconFileName = "LDomovoy.png";
    }

    @Override
    public ICreature GetOtherForm() {
        return new HDomovoy();
    }
}
