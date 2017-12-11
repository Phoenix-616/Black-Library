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
        imageFileName = "LKochey.jpeg";
    }

    @Override
    public ICreature GetOtherForm() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
