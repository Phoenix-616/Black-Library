package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

/**
 *
 * @author ekaterina
 */
public class LKochey extends ACreature {

    public LKochey() {
        name = "Кощей Cмертный";
        description = "Ужас, летящий на крыльях ночи и убиваемый исключительно иглой из яйца из утки из зайца, что лежит в свинцом обитом сундуке";
        strenght = 9;
        imageFileName = "LKochey.jpg";
        iconFileNameBlack = "LKochey.png";
        iconFileNameWhite = "WLKochey.png";
    }

    @Override
    public ICreature getOtherForm() {
        return new HKochey();
    }
}
