package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

/**
 * Created by Alena on 11.12.2017.
 */
public class LVolkolak extends ACreature{

    public LVolkolak(){
        name = "Волколак Солдат";
        description = "Глупый и бронебойный. Сносит все на своем пути и идет дальше кушать супчик из зеленых мухоморчиков.";
        strenght = 8;
        imageFileName = "LVolkolak.jpg";
        iconFileNameBlack = "LVolkolak.png";
        iconFileNameWhite = "WLVolkolak.png";
    }


    @Override
    public ICreature getOtherForm() {
        return new HVolkolak();
    }
}
