package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

/**
 *
 * @author ekaterina
 */
public class HKochey extends ACreature {

    public HKochey() {
        name = "Кощей Бессмертный";
        description = "Против такого не поможет даже игла из яйца из утки из зайца, что лежит в свинцом обитом сундуке...";
        strenght = 13;
        imageFileName = "HKochey.jpeg";
    }

    @Override
    public ICreature GetOtherForm() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
