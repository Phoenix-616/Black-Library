package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

/**
 *
 * @author ekaterina
 */
public class HDomovoy extends ACreature {

    public HDomovoy() {
        name = "Хозяин Дома";
        description = "Говорят, что настоящий хозяин дома не человек, а его кот. Боюсь, у нас для него плохие новости...";
        strenght = 4;
        imageFileName = "HDomovoy.jpg";
        iconFileNameBlack = "HDomovoy.png";
    }

    @Override
    public ICreature getOtherForm() {
        return new LDomovoy();
    }
}
