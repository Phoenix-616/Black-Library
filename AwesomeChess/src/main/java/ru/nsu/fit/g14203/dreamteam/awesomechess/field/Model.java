package ru.nsu.fit.g14203.dreamteam.awesomechess.field;

import java.util.List;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.ICreature;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LDomovoy;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LVolkolak;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Figure.FigureColor;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.StepRules.FigureType;

/**
 *
 * @author ekaterina
 */
public class Model implements IModel{
        
    private Cell[][] field;

    private boolean whiteTurn;
    
    
    private void Model(){
        field = new Cell[8][8];
        whiteTurn = true;
    }
    
    public void renovate(){
        
        whiteTurn = true;
        
        for(int i = 0; i < 8; i++){
            field[i][1] = new Cell(new Figure(new LDomovoy(), FigureType.PAWN, FigureColor.WHITE));
        }
        
        field[0][0] = new Cell(new Figure(new LVolkolak(), FigureType.ROOK, FigureColor.WHITE));
        
        for(int i = 0; i < 8; i++){
            field[i][6] = new Cell(new Figure(new LDomovoy(), FigureType.PAWN, FigureColor.BLACK));
        }
        
        field[0][0] = new Cell(new Figure(new LVolkolak(), FigureType.ROOK, FigureColor.WHITE));
        
        
    
    
    }
    
    @Override
    public void CellClicked(FieldCoord coords) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public boolean cellIsEmpty(FieldCoord coords){
        return false;
    }
    
    @Override
    public Cell[][] GetState() {
        return field;
    }

    @Override
    public List<ICreature> GetSelectedCreatures() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean WhiteGoing() {
        return whiteTurn;
    }

    @Override
    public boolean WhiteWin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean BlackWin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
