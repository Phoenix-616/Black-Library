/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nsu.fit.g14203.dreamteam.awesomechess.field;

import com.sun.beans.util.Cache;

/**
 *
 * @author phoenix
 */
public class StepRules {
    
    //Нельзя создать экземпляр класса
    private StepRules(){}
    
    //Тип фигуры (По порядку: король, ферзь, ладья, конь, слон, пешка)
    public static enum FigureType { KING, QUEEN, ROOK, KNIGHT, BISHOP, PAWN}

    public boolean canFigureGo(FigureType figureType, FieldCoord figureCoord, FieldCoord targetCoord){
        if (figureCoord.equals(targetCoord)){
            return false;
        }
        else if (figureType == FigureType.KING){
            return canKingGo(figureCoord, targetCoord);
        }
        else if (figureType == FigureType.QUEEN){
            return canQueenGo(figureCoord, targetCoord);
        }
        else if (figureType == FigureType.ROOK){
            return canRookGo(figureCoord, targetCoord);
        }
        else if (figureType == FigureType.KNIGHT){
            return canKnightGo(figureCoord, targetCoord);
        }
        else if (figureType == FigureType.BISHOP){
            return canBishopGo(figureCoord, targetCoord);
        }
        else if (figureType == FigureType.PAWN){
            return canPawnGo(figureCoord, targetCoord);
        }
        return false;
    }

    private boolean canKingGo(FieldCoord figureCoord, FieldCoord targetCoord){

    }

    private boolean canQueenGo(FieldCoord figureCoord, FieldCoord targetCoord){
        if(figureCoord.X == targetCoord.X){

        }
    }

    private boolean canRookGo(FieldCoord figureCoord, FieldCoord targetCoord){

    }

    private boolean canKnightGo(FieldCoord figureCoord, FieldCoord targetCoord){

    }

    private boolean canBishopGo(FieldCoord figureCoord, FieldCoord targetCoord){

    }

    private boolean canPawnGo(FieldCoord figureCoord, FieldCoord targetCoord){

    }


    /*//Направление хода с ограничением. Например, пешки ходят только на 1-2 клетки
    public static class StepDirectionWithLimit {
        public final FigureType direction;
        public final int limit;
        public final int firstStepLimit;
        
        public StepDirectionWithLimit(FigureType dir, int lim, int fLim) {
            direction = dir;
            limit = lim;
            firstStepLimit = fLim;
        }
    }
    
    //В зависимости от направления и текущих координат выдается следующая клетка и проверяется моделью на валидность, если клетки нет null
    public static FieldCoord GetNextFieldInDir(FigureType dir, FieldCoord curCoord) {
        return null;
    }*/
}
