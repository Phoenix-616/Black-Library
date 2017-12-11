/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nsu.fit.g14203.dreamteam.awesomechess.field;

import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.ICreature;

/**
 *
 * @author phoenix
 */
public class Cell {
    
    private ICreature creature = null;
    
    public Cell() { }
    
    public Cell(ICreature creature) {
        this.creature = creature;
    }
    
    ICreature GetCreature() {
        return null;
    }
}
