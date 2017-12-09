/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nsu.fit.g14203.dreamteam.awesomechess.field;

/**
 *
 * @author phoenix
 */
public class StepRules {
    
    //Нельзя создать экземпляр класса
    private StepRules(){}
    
    //Тип фигуры (По порядку: король, ферзь, ладья, конь, слон, пешка)
    public static enum FigureTypes { KING, QUEEN, ROOK, KNIGHT, BISHOP, PAWN}
    
    /*//Направление хода с ограничением. Например, пешки ходят только на 1-2 клетки
    public static class StepDirectionWithLimit {
        public final FigureTypes direction;
        public final int limit;
        public final int firstStepLimit;
        
        public StepDirectionWithLimit(FigureTypes dir, int lim, int fLim) {
            direction = dir;
            limit = lim;
            firstStepLimit = fLim;
        }
    }
    
    //В зависимости от направления и текущих координат выдается следующая клетка и проверяется моделью на валидность, если клетки нет null
    public static FieldCoord GetNextFieldInDir(FigureTypes dir, FieldCoord curCoord) {
        return null;
    }*/
}
