package ru.nsu.fit.g14203.dreamteam.awesomechess.field;

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
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Demiurg.Joke;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Figure.FigureColor;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.StepRules.FigureType;

/**
 *
 * @author ekaterina
 */
public class Model implements IModel {

    private static final String PlayerLogMark = "-- ";
    private static final String DemiurgLogMark = "@ ";
    private static final String W = "Белый(ая) ";
    private static final String B = "Черный(ая) ";

    private Demiurg demiurg = new Demiurg("Боженька");

    private Cell[][] chessBoard;
    private boolean whiteTurn = true;

    private FieldCoord selectedFigureCoords = null;

    LinkedList<ICreature> selectedCreatures = new LinkedList<>();
    LinkedList<String> log = new LinkedList<>();

    Random randomizer = new Random();

    int whitesAlive = 16;
    int blacksAlive = 16;

    public Model() {
        chessBoard = new Cell[8][8];
        renovate();
    }

    public Model(Cell[][] board) {
        whiteTurn = true;
        chessBoard = board;
    }

    private void renovate() {

        whiteTurn = true;

        for (int i = 0; i < 8; i++) {
            for (int j = 2; j < 6; j++) {
                chessBoard[i][j] = new Cell();
            }
        }

        for (int i = 0; i < 8; i++) {
            chessBoard[i][1] = new Cell(new Figure(new LDomovoy(), FigureType.PAWN, FigureColor.WHITE));
        }

        chessBoard[0][0] = new Cell(new Figure(new LVolkolak(), FigureType.ROOK, FigureColor.WHITE));
        chessBoard[7][0] = new Cell(new Figure(new LVolkolak(), FigureType.ROOK, FigureColor.WHITE));

        chessBoard[1][0] = new Cell(new Figure(new LRusalka(), FigureType.KNIGHT, FigureColor.WHITE));
        chessBoard[6][0] = new Cell(new Figure(new LRusalka(), FigureType.KNIGHT, FigureColor.WHITE));

        chessBoard[2][0] = new Cell(new Figure(new LLesovik(), FigureType.BISHOP, FigureColor.WHITE));
        chessBoard[5][0] = new Cell(new Figure(new LLesovik(), FigureType.BISHOP, FigureColor.WHITE));

        chessBoard[4][0] = new Cell(new Figure(new LBabaYaga(), FigureType.QUEEN, FigureColor.WHITE));
        chessBoard[3][0] = new Cell(new Figure(new LKochey(), FigureType.KING, FigureColor.WHITE));

        for (int i = 0; i < 8; i++) {
            chessBoard[i][6] = new Cell(new Figure(new LDomovoy(), FigureType.PAWN, FigureColor.BLACK));
        }

        chessBoard[7][7] = new Cell(new Figure(new LVolkolak(), FigureType.ROOK, FigureColor.BLACK));
        chessBoard[0][7] = new Cell(new Figure(new LVolkolak(), FigureType.ROOK, FigureColor.BLACK));

        chessBoard[1][7] = new Cell(new Figure(new LRusalka(), FigureType.KNIGHT, FigureColor.BLACK));
        chessBoard[6][7] = new Cell(new Figure(new LRusalka(), FigureType.KNIGHT, FigureColor.BLACK));

        chessBoard[2][7] = new Cell(new Figure(new LLesovik(), FigureType.BISHOP, FigureColor.BLACK));
        chessBoard[5][7] = new Cell(new Figure(new LLesovik(), FigureType.BISHOP, FigureColor.BLACK));

        chessBoard[4][7] = new Cell(new Figure(new LBabaYaga(), FigureType.QUEEN, FigureColor.BLACK));
        chessBoard[3][7] = new Cell(new Figure(new LKochey(), FigureType.KING, FigureColor.BLACK));
    }

    @Override
    public void cellClicked(FieldCoord coords) {

        //если фигура еще не была выделена и выбранная клетка не пуста, то...
        if (selectedFigureCoords == null) {
            selectFigure(coords);
            return;
        }

        //если дважды ткнули в одну клетку - выбор фигуры снимается
        if (selectedFigureCoords.equalsTo(coords)) {
            log.add(PlayerLogMark + (whiteTurn ? (W + selectedCreatures.getFirst().getName()) : (B + selectedCreatures.getLast().getName())) + " отправлен(а) во временное увольнение.");
            selectedFigureCoords = null;
            selectedCreatures = new LinkedList<>();
            return;
        }

        //если фигура ...type может перейти из клетки с координатами SelectedFigCoords в клетку с координатами coords...
        if (StepRules.canFigureGo(chessBoard[selectedFigureCoords.x][selectedFigureCoords.y].getFigure().type, selectedFigureCoords, coords, this)) {

            //и если эта клетка пуста...
            if (cellIsEmpty(coords)) {
                moveFigure(selectedFigureCoords, coords);
                newTurn();
                demiurgIntervention();
                selectedFigureCoords = null;
                return;
            }

            //если не пуста добавляем противника в список выделенных существ...
            if (whiteTurn) {
                selectedCreatures.set(1, chessBoard[coords.x][coords.y].getFigure().getCreature());
            } else {
                selectedCreatures.set(0, chessBoard[coords.x][coords.y].getFigure().getCreature());
            }

            //и отправляем бойцов на арену
            Figure firstBattler = chessBoard[selectedFigureCoords.x][selectedFigureCoords.y].getFigure();
            Figure secondBattler = chessBoard[coords.x][coords.y].getFigure();

            Figure winner = battle(firstBattler, secondBattler);
            //атакующую фигуру убираем со старого места и...
            chessBoard[selectedFigureCoords.x][selectedFigureCoords.y].setFigure(null);
            //победителя помещаем на место атаки
            chessBoard[coords.x][coords.y].setFigure(winner);

            log.add("== БИТВА ОКОНЧЕНА ==");

            processBattleResults(winner);

            //если победила пешка, смотрим ее расположение на поле (дошла до конца или нет)
            if (winner.type == FigureType.PAWN) {
                checkPawnTransform(coords);
            }

            selectedFigureCoords = null;
        } else {
            log.add(PlayerLogMark + (whiteTurn ? (W + selectedCreatures.getFirst().getName()) : (B + selectedCreatures.getLast().getName())) + " отправлен(а) во временное увольнение.");
            selectedFigureCoords = null;
            selectedCreatures = new LinkedList<>();
        }
    }

    private void selectFigure(FieldCoord coords) {
        if (!cellIsEmpty(coords)) {
            FigureColor tmpCol = chessBoard[coords.x][coords.y].getFigure().color;

            //если цвет выделенной фигуры совпадает с цветом хода, фигура может быть выделена
            if ((tmpCol == FigureColor.WHITE && whiteTurn) || (tmpCol == FigureColor.BLACK && !whiteTurn)) {
                selectedFigureCoords = new FieldCoord(coords);
                selectedCreatures = new LinkedList<>();
                selectedCreatures.add(chessBoard[coords.x][coords.y].getFigure().getCreature());

                log.add(PlayerLogMark + (whiteTurn ? W : B) + selectedCreatures.getFirst().getName() + " ожидает приказа главнокомандующего.");

                //строгий порядок записи белого и черного существа в список
                if (whiteTurn) {
                    selectedCreatures.addLast(null);
                } else {
                    selectedCreatures.addFirst(null);
                }
            }
        }

    }

    //проверяет, пуста ли клетка с координатами coords
    public boolean cellIsEmpty(FieldCoord coords) {
        return chessBoard[coords.x][coords.y].getFigure() == null;
    }

    //перемещает фигуру из клетки с координатами from в клетку с координатами to
    private void moveFigure(FieldCoord from, FieldCoord to) {

        String creatureName = chessBoard[from.x][from.y].getFigure().getCreature().getName();
        String figureColor = chessBoard[from.x][from.y].getFigure().color == FigureColor.WHITE ? W : B;

        chessBoard[to.x][to.y].setFigure(chessBoard[from.x][from.y].getFigure());
        chessBoard[from.x][from.y].setFigure(null);

        log.add(PlayerLogMark + figureColor + " " + creatureName + " переместился(лась) из клетки (" + from.x + "," + from.y + ") в клетку (" + to.x + "," + to.y + ").");

        if (chessBoard[to.x][to.y].getFigure().type == FigureType.PAWN) {
            checkPawnTransform(to);
        }

    }

    private void checkPawnTransform(FieldCoord coord) {

        String prevCreatureName = chessBoard[coord.x][coord.y].getFigure().getCreature().getName();

        if (chessBoard[coord.x][coord.y].getFigure().color == FigureColor.WHITE && coord.y == 7) {
            chessBoard[coord.x][coord.y].setFigure(new Figure(new LBabaYaga(), FigureType.QUEEN, FigureColor.WHITE));
            log.add(PlayerLogMark + "Предприимчивый белый " + prevCreatureName
                    + " достиг просветления. На поле новая белая " + chessBoard[coord.x][coord.y].getFigure().getCreature().getName());
            return;
        }

        if (chessBoard[coord.x][coord.y].getFigure().color == FigureColor.BLACK && coord.y == 0) {
            chessBoard[coord.x][coord.y].setFigure(new Figure(new LBabaYaga(), FigureType.QUEEN, FigureColor.BLACK));
            log.add(PlayerLogMark + "Предприимчивый черный " + prevCreatureName
                    + " достиг просветления. На поле новая черная " + chessBoard[coord.x][coord.y].getFigure().getCreature().getName());
            return;
        }

    }

    //смена хода (черные->белые или белые->черные) и шалость демиурга
    private void newTurn() {
        whiteTurn = !whiteTurn;
        log.add("== Ход переходит к " + ((whiteTurn) ? "БЕЛЫМ " : "ЧЕРНЫМ ") + " ==");
    }

    private void demiurgIntervention() {
        Joke joke = demiurg.playJoke();
        Figure targetFigure = chessBoard[joke.where.x][joke.where.y].getFigure();

        switch (joke.what) {
            case SWITCHLVL:

                if (targetFigure == null) {
                    log.add(DemiurgLogMark + demiurg.name + " махнул рукой, и на клетке (" + 
                            joke.where.x + "," + joke.where.y + ") появились зловредные маленькие пикси." + 
                            " Однако цели для своей проказы они не нашли... " + DemiurgLogMark);
                    return;
                }

                String oldCreatureName = targetFigure.getCreature().getName();

                targetFigure.setCreature(targetFigure.getCreature().getOtherForm());
                log.add(DemiurgLogMark + demiurg.name + " пошалил: на клетке (" + joke.where.x + "," + joke.where.y + "), "
                        + "где только что стоял(а) " + oldCreatureName + ", клубиться розовый туман...");
                log.add("Спустя пару минут туман рассеивается - на клетке теперь стоит " + targetFigure.getCreature().getName() + " " + DemiurgLogMark);
                return;

            case KILLKINDLY:

                if (targetFigure == null) {
                    log.add(DemiurgLogMark + demiurg.name + " шарахнул молнией в клетку (" + joke.where.x + "," + joke.where.y + "). К его величайшему сожалению она была пуста... " + DemiurgLogMark);
                    return;
                }

                String deadManName = targetFigure.getCreature().getName();
                String deadManColor = (FigureColor.WHITE == targetFigure.color ? W : B);
                chessBoard[joke.where.x][joke.where.y].setFigure(null);
                log.add(DemiurgLogMark + demiurg.name + " злобно хихикает: на клетку (" + joke.where.x + "," + joke.where.y + ") только что упал здоровенный валун");
                log.add(deadManColor + deadManName + " мертв(а)... " + DemiurgLogMark);
        }
    }

    //бой
    private Figure battle(Figure firstBattler, Figure secondBattler) {

        log.add("== БИТВА ==");
        log.add(W + selectedCreatures.getFirst().getName() + " vs " + B + selectedCreatures.getLast().getName());

        int fistStrength = firstBattler.getCreature().getStrength();
        int secondStrength = secondBattler.getCreature().getStrength();

        int minStrength;

        String strongerColor;
        String weakerColor;
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

        weakerColor = (weakerBattler.color == FigureColor.WHITE ? W : B);
        strongerColor = (strongerBattler.color == FigureColor.WHITE ? W : B);

        if (randomizer.nextInt(fistStrength + secondStrength) < minStrength) {
            log.add(strongerColor + strongerBattler.getCreature().getName() + " замешкался(лась) и потерпел(а) поражение.");
            log.add(weakerColor + weakerBattler.getCreature().getName() + " с видом победителя оглядывает поле сражения.");
            return weakerBattler;
        }
        log.add(strongerColor + strongerBattler.getCreature().getName() + " играючи одерживает победу.");
        log.add(weakerColor + weakerBattler.getCreature().getName() + " покинул(а) этот бренный мир...");

        return strongerBattler;
    }

    private void processBattleResults(Figure winner) {
        if (winner.color == FigureColor.WHITE) {
            blacksAlive--;
            if (blacksAlive == 0) {
                log.add("Яркий свет полуденного солнца освещает мир вокруг, а его жители как ни в чем небывало "
                        + "продолжают заниматься своими делами, даже не подозревая о великой битве, что совсем недавно прогремела.");
                log.add("== Победа светлых ==");
                return;
            }
        } else {
            whitesAlive--;
            if (whitesAlive == 0) {
                log.add("Мир вокруг окутывает кромешная тьма, со всех сторон слышен тихий, зловещий смех...");
                log.add("== Победа тёмных ==");
                return;
            }
        }

        newTurn();
        demiurgIntervention();
    }

    @Override
    public Cell[][] getState() {

        Cell[][] chessBoardCopy = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoardCopy[i][j] = new Cell(chessBoard[i][j]);
            }
        }

        return chessBoardCopy;
    }

    @Override
    public List<ICreature> getSelectedCreatures() {
        return selectedCreatures;
    }

    public List<String> getLog() {
        LinkedList<String> tempLog = new LinkedList<>(log);
        log = new LinkedList<>();
        return tempLog;
    }

    @Override
    public boolean whiteGoing() {
        return whiteTurn;
    }

    @Override
    public boolean whiteWin() {
        return (blacksAlive == 0);
    }

    @Override
    public boolean blackWin() {
        return (whitesAlive == 0);

    }

}
