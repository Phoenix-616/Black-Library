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
        imageFileName = "LRusalka.jpg";
        iconFileNameBlack = "LRusalka.png";
    }

    @Override
    public ICreature getOtherForm() {
        return new HRusalka();
    }
}
