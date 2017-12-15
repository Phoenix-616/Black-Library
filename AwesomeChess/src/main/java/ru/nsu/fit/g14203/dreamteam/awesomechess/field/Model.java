package ru.nsu.fit.g14203.dreamteam.awesomechess.field;

import java.util.List;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.ICreature;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LBabaYaga;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LDomovoy;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LKochey;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LLesovik;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LRusalka;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.LVolkolak;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Figure.FigureColor;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.StepRules.FigureType;

/**
 *
 * @author ekaterina
 */
public class Model implements IModel{
    
    private Cell[][] chessBoard;
    private boolean whiteTurn;
    
    private FieldCoord firstSelectedCellCoords = null;
    
    
    public void Model(){
        chessBoard = new Cell[8][8];
        renovate();
    }
    
    public void renovate(){
        
        whiteTurn = true;
        
        for(int i = 0; i < 8; i++){
            chessBoard[i][1] = new Cell(new Figure(new LDomovoy(), FigureType.PAWN, FigureColor.WHITE));
        }
        
        chessBoard[0][0] = new Cell(new Figure(new LVolkolak(), FigureType.ROOK, FigureColor.WHITE));
        chessBoard[7][0] = new Cell(new Figure(new LVolkolak(), FigureType.ROOK, FigureColor.WHITE));
        
        chessBoard[1][0] = new Cell(new Figure(new LRusalka(), FigureType.KNIGHT, FigureColor.WHITE));
        chessBoard[6][0] = new Cell(new Figure(new LRusalka(), FigureType.KNIGHT, FigureColor.WHITE));
        
        chessBoard[2][0] = new Cell(new Figure(new LLesovik(), FigureType.BISHOP, FigureColor.WHITE));
        chessBoard[5][0] = new Cell(new Figure(new LLesovik(), FigureType.BISHOP, FigureColor.WHITE));
        
        chessBoard[3][0] = new Cell(new Figure(new LBabaYaga(), FigureType.QUEEN, FigureColor.WHITE));
        chessBoard[4][0] = new Cell(new Figure(new LKochey(), FigureType.KING, FigureColor.WHITE));
        
        for(int i = 0; i < 8; i++){
            chessBoard[i][6] = new Cell(new Figure(new LDomovoy(), FigureType.PAWN, FigureColor.BLACK));
        }
        
        chessBoard[7][7] = new Cell(new Figure(new LVolkolak(), FigureType.ROOK, FigureColor.BLACK));
        chessBoard[0][7] = new Cell(new Figure(new LVolkolak(), FigureType.ROOK, FigureColor.BLACK));
        
        chessBoard[1][7] = new Cell(new Figure(new LRusalka(), FigureType.KNIGHT, FigureColor.BLACK));
        chessBoard[6][7] = new Cell(new Figure(new LRusalka(), FigureType.KNIGHT, FigureColor.BLACK));
        
        chessBoard[2][7] = new Cell(new Figure(new LLesovik(), FigureType.BISHOP, FigureColor.BLACK));
        chessBoard[5][7] = new Cell(new Figure(new LLesovik(), FigureType.BISHOP, FigureColor.BLACK));
        
        chessBoard[4][0] = new Cell(new Figure(new LBabaYaga(), FigureType.QUEEN, FigureColor.BLACK));
        chessBoard[3][0] = new Cell(new Figure(new LKochey(), FigureType.KING, FigureColor.BLACK));
    }
    
    @Override
    public void CellClicked(FieldCoord coords) {
        if (firstSelectedCellCoords == null){
            if(!cellIsEmpty(coords))
                firstSelectedCellCoords = new FieldCoord(coords.X,coords.Y);
            return;
        }
        
        
    
        
    
    }

    
    public boolean cellIsEmpty(FieldCoord coords){
        return chessBoard[coords.X][coords.Y].getFigure() == null;
    }
    
    @Override
    public Cell[][] GetState() {
        
        Cell[][] chessBoardCopy = new Cell[8][8];
        for(int i = 0; i< 8; i++){
            for(int j = 0; j <8; j++){
                chessBoardCopy[i][j] = new Cell(chessBoard[i][j]);
            }
        }
        
        return chessBoardCopy;
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
