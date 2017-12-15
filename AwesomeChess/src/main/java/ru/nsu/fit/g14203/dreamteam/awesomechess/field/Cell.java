package ru.nsu.fit.g14203.dreamteam.awesomechess.field;

import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.ICreature;

/**
 *
 * @author phoenix
 */
public class Cell {
    
    private Figure figure = null;
    
    public Cell() { }
    
    public Cell(Figure figure) {
        this.figure = figure;
    }
    
    Figure getFigure() {
        return null;
    }
    
    void setFigure(Figure figure) {
        this.figure = figure;
    }
}
