package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;



/**
 * Created by Alena on 08.12.2017.
 */
public class BabaYagaLow extends ACreature {


    public BabaYagaLow(){
        name = "Яга Молодая";
        description = "Яга Молодая";
        strenght = 10;
        imageFileName = "LBabaYaga.jpeg";
    }

    @Override
    public ICreature GetOtherForm() {
        return null;
    }

    }
