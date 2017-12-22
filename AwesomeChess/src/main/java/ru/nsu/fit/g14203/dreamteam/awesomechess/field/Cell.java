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
    
    public Cell(Cell o) {
        if (o != null) {
            this.figure = o.figure;
        }
    }
    
    public Figure getFigure() {
        return figure;
    }
    
    public void setFigure(Figure figure) {
        this.figure = figure;
    }
    
    
}
