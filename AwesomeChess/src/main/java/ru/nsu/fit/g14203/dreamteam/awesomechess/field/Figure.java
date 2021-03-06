/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nsu.fit.g14203.dreamteam.awesomechess.field;

import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.ICreature;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.StepRules.FigureType;

/**
 *
 * @author phoenix
 */
public class Figure {

    public enum FigureColor {
        WHITE, BLACK
    }

    private ICreature creature;
    public final FigureType type;
    public final FigureColor color;

    public Figure(ICreature cr, FigureType type, FigureColor color) {
        this.color = color;
        this.type = type;
        creature = cr;
    }

    public ICreature getCreature() {
        return creature;
    }

    public String getIconFileName() {
        return (color == FigureColor.WHITE) ? creature.getIconFileNameWhite() : creature.getIconFileNameBlack();

    }

    public void setCreature(ICreature cr) {
        creature = cr;
    }

}
