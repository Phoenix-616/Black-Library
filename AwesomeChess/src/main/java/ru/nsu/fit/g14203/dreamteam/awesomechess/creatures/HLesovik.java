package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

/**
 * Created by Alena on 11.12.2017.
 */
public class HLesovik extends ACreature {

    public HLesovik(){
        name = "Лихо Лесное";
        description = "Ходит, бродит по лесу своему, детей пугает, девиц привечает, селян не боится и готов с ними биться. " +
                "Бойся путник взгляда его - не останется в твоей душе ничего... ";
        strenght = 10;
        imageFileName = "HLesovik.jpg";
        iconFileNameBlack = "HLesovik.png";
        iconFileNameWhite = "WHLesovik.png";
    }

    @Override
    public ICreature getOtherForm() {
        return new LLesovik();
    }
}
