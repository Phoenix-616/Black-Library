package ru.nsu.fit.g14203.dreamteam.awesomechess.field;

import static java.lang.Integer.min;
import java.util.List;
import java.util.Random;
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
public class Model implements IModel {

    private Cell[][] chessBoard;
    private boolean whiteTurn;

    private FieldCoord SelectedFigCoords = null;

    Random randomizer = new Random();

    public void Model() {
        chessBoard = new Cell[8][8];
        renovate();
    }

    public void renovate() {

        whiteTurn = true;

        for (int i = 0; i < 8; i++) {
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

        for (int i = 0; i < 8; i++) {
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

        //если фигура еще не была выделена и выбранная клетка не пуста, то...
        if (SelectedFigCoords == null && !cellIsEmpty(coords)) {
            FigureColor tmpCol = chessBoard[coords.X][coords.Y].getFigure().COLOR;

            //если цвет выделенной фигуры совпадает с цветом хода, фигура может быть выделена
            if ((tmpCol == FigureColor.WHITE && whiteTurn) || (tmpCol == FigureColor.BLACK && !whiteTurn)) {
                SelectedFigCoords = new FieldCoord(coords.X, coords.Y);
            }
            return;
        }

        //если дважды ткнули в одну клетку - выбор фигуры снимается
        if (SelectedFigCoords.equals(coords)) {
            SelectedFigCoords = null;
            return;
        }

        //если фигура ...TYPE может перейти из клетки с координатами SelectedFigCoords в клетку с координатами coords...
        if (StepRules.canFigureGo(chessBoard[SelectedFigCoords.X][SelectedFigCoords.Y].getFigure().TYPE, SelectedFigCoords, coords, this)) {
            //и если эта клетка пуста...
            if (cellIsEmpty(coords)) {
                moveFigure(SelectedFigCoords, coords);
                newTurn();
                return;
            }

            //если не пуста - бой
        }
    }

    //проверяет, пуста ли клетка с координатами coords
    public boolean cellIsEmpty(FieldCoord coords) {
        return chessBoard[coords.X][coords.Y].getFigure() == null;
    }

    //перемещает фигуру из клетки с координатами from в клетку с координатами to
    private void moveFigure(FieldCoord from, FieldCoord to) {
        chessBoard[to.X][to.Y].setFigure(chessBoard[from.X][from.Y].getFigure());
        chessBoard[from.X][from.Y].setFigure(null);
    }

    //смена хода (черные->белые или белые->черные)
    private void newTurn() {
        whiteTurn = !whiteTurn;
    }

    private void battle(FieldCoord battlerOneCoords, FieldCoord battlerTwoCoords) {
        int oneStrength = chessBoard[battlerOneCoords.X][battlerOneCoords.Y].getFigure().getCreature().GetStrength();
        int twoStrength = chessBoard[battlerTwoCoords.X][battlerTwoCoords.Y].getFigure().getCreature().GetStrength();

        boolean firstWeaker;
        int minStrength = min(oneStrength, twoStrength);

        if (oneStrength < twoStrength) {
            minStrength = oneStrength;
            firstWeaker = true;
        }else{
            minStrength = twoStrength;
            firstWeaker = false;
        }
        
        if (randomizer.nextInt(oneStrength + twoStrength) < minStrength);

    }

    @Override
    public Cell[][] GetState() {

        Cell[][] chessBoardCopy = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
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
