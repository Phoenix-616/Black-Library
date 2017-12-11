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
    private ICreature creature;
    public FigureType TYPE;
    
    public Figure(ICreature cr, FigureType type) {
        TYPE = type;
        creature = cr;
    }
    
    
}
