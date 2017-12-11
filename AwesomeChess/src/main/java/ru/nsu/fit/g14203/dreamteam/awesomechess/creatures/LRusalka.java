package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

/**
 *
 * @author ekaterina
 */
public class LRusalka extends ACreature {

    public LRusalka() {
        name = "Русалка";
        description = "Зловредный дух, что защекотит вас насмерть или утопит в близлежащем водоёме";
        strenght = 5;
        imageFileName = "LRusalka.jpeg";
    }

    @Override
    public ICreature GetOtherForm() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
