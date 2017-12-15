package ru.nsu.fit.g14203.dreamteam.awesomechess.field;

import static java.lang.Integer.min;
import java.util.LinkedList;
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
    
    LinkedList<ICreature> selectedCreatures = new LinkedList<>();
    LinkedList<String> log = new LinkedList<>();

    Random randomizer = new Random();
    
    int whitesAlive = 12;
    int blacksAlive = 12;

    public void Model() {
        chessBoard = new Cell[8][8];
        renovate();
    }

    private void renovate() {

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
                selectedCreatures = new LinkedList<>();
                selectedCreatures.add(chessBoard[coords.X][coords.Y].getFigure().getCreature());
            }
            return;
        }

        //если дважды ткнули в одну клетку - выбор фигуры снимается
        if (SelectedFigCoords.equals(coords)) {
            SelectedFigCoords = null;
            selectedCreatures = new LinkedList<>();
            return;
        }

        if(whiteTurn){
            selectedCreatures.addLast(chessBoard[coords.X][coords.Y].getFigure().getCreature());
        }else{
            selectedCreatures.addFirst(chessBoard[coords.X][coords.Y].getFigure().getCreature());
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
            Figure firstBattler = chessBoard[SelectedFigCoords.X][SelectedFigCoords.Y].getFigure(), 
                   secondBattler = chessBoard[coords.X][coords.Y].getFigure(), 
                   winner;
            
            winner = battle(firstBattler, secondBattler);
            
            //атакующую фигуру убираем со старого места и...
            chessBoard[SelectedFigCoords.X][SelectedFigCoords.Y].setFigure(null);
            //победителя помещаем на место атаки
            chessBoard[coords.X][coords.Y].setFigure(winner);
            
            newTurn();
            
            if(winner.COLOR == FigureColor.WHITE){
                blacksAlive--;
                if(blacksAlive == 0){
                    log.add("Яркий свет полуденного солнца освещает мир вокруг, а его жители как ни в чем небывало "
                            + "продолжают заниматься своими делами, даже не подозревая о великой битве, что совсем недавно прогремела.");
                    log.add("== Победа светлых ==");
                }
            }
            else{
                whitesAlive--;
                if(whitesAlive == 0){
                    log.add("Мир вокруг окутывает кромешная тьма, со всех сторон слышен тихий, зловещий смех...");
                    log.add("== Победа тёмных ==");
                }
            }
            
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

    //бой
    private Figure battle(Figure firstBattler, Figure secondBattler) {

        int fistStrength = firstBattler.getCreature().GetStrength();
        int secondStrength = secondBattler.getCreature().GetStrength();

        int minStrength = min(fistStrength, secondStrength);
        Figure weakerBattler;
        Figure strongerBattler;

        if (fistStrength < secondStrength) {
            minStrength = fistStrength;
            weakerBattler = firstBattler;
            strongerBattler = secondBattler;
        } else {
            minStrength = secondStrength;
            weakerBattler = secondBattler;
            strongerBattler = firstBattler;
        }

        if (randomizer.nextInt(fistStrength + secondStrength) < minStrength) {
            return weakerBattler;
        }
        return strongerBattler;
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
        return selectedCreatures;
    }

    @Override
    public boolean WhiteGoing() {
        return whiteTurn;
    }

    @Override
    public boolean WhiteWin() {
        return(blacksAlive == 0);
    }

    @Override
    public boolean BlackWin() {
        return(whitesAlive == 0);
    }

}
