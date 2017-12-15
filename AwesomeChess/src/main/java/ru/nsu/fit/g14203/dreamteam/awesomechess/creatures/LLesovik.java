package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

/**
 * Created by Alena on 11.12.2017.
 */
public class LLesovik extends ACreature {

    public LLesovik(){
        name = "Леший";
        description = "Маленький, удаленький. За лесами следит, за ручьями глядит, птенчиоков ловит, лисичек гладит. " +
                "Добрым путникам помогает, злых обкидывает мухоморами и снежком";
        strenght = 7;
        imageFileName = "LLesovik.jpg";
        iconFileName = "";
    }

    @Override
    public ICreature GetOtherForm() {
        return new HLesovik();
    }
}
