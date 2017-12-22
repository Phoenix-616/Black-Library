package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

/**
 * Created by Alena on 11.12.2017.
 */
public class HVolkolak extends ACreature {

    public HVolkolak(){
        name = "Волколак Вожак";
        description = "Вожак всех волколаков. Зубастый, умелый и умный весьма. " +
                "Сражение с таким смерти подобно, а выживших можно пересчитать по пальцам одной руки. ";
        strenght = 11;
        imageFileName = "HVolkolak.jpg";
        iconFileName = "HVolkolak.png";
    }

    @Override
    public ICreature GetOtherForm() {
        return new LVolkolak();
    }
}
