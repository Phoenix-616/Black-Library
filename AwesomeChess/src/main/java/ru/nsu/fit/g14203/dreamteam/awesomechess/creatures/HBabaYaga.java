package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

/**
 * Created by Alena on 11.12.2017.
 */
public class HBabaYaga extends ACreature {

    public HBabaYaga(){
        name = "Лесная Ведьма Ягда";
        description = "Молодая красивая ведьма. Управляет драконами и змеями. Заманивает мужчин в чащу и убивает их";
        strenght = 9;
        imageFileName = "HBabaYaga.jpg";
        iconFileNameBlack = "HBabaYaga.png";
    }

    @Override
    public ICreature getOtherForm() {
        return new LBabaYaga();
    }
}
