/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

/**
 *
 * @author ekaterina
 */
public class HRusalka extends ACreature {

    public HRusalka() {
        name = "Водяная Дева";
        description = "Повелительница рек, озёр, морей и океанов. Однажды преступив ей дорогу, вы можете забыть о водоёмах.";
        strenght = 7;
        imageFileName = "HRusalka.jpg";
        iconFileNameBlack = "HRusalka.png";
        iconFileNameWhite = "HRusalka.png";
    }

    @Override
    public ICreature getOtherForm() {
        return new LRusalka();
    }
}
