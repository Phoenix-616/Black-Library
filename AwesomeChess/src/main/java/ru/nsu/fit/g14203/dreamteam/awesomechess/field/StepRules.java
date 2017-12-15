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

    public static boolean canFigureGo(FigureType figureType, FieldCoord figureCoord, FieldCoord targetCoord, Model model){
        if (figureType == FigureType.KING){
            return canKingGo(figureCoord, targetCoord,model);
        }
        else if (figureType == FigureType.QUEEN){
            return canQueenGo(figureCoord, targetCoord, model);
        }
        else if (figureType == FigureType.ROOK){
            return canRookGo(figureCoord, targetCoord, model);
        }
        else if (figureType == FigureType.KNIGHT){
            return canKnightGo(figureCoord, targetCoord, model);
        }
        else if (figureType == FigureType.BISHOP){
            return canBishopGo(figureCoord, targetCoord, model);
        }
        else if (figureType == FigureType.PAWN){
            return canPawnGo(figureCoord, targetCoord, model);
        }
        return false;
    }

    private static boolean canKingGo(FieldCoord figureCoord, FieldCoord targetCoord, Model model){
    return false;
    }

    private static boolean canQueenGo(FieldCoord figureCoord, FieldCoord targetCoord, Model model){
        if(canRookGo(figureCoord, targetCoord, model)){
            return true;
        }
        else return canBishopGo(figureCoord, targetCoord, model);
    }

    private static boolean canRookGo(FieldCoord figureCoord, FieldCoord targetCoord, Model model){
        int y=0, x=0;
        if(figureCoord.X == targetCoord.X){
            if(figureCoord.Y < targetCoord.Y){
                y = figureCoord.Y+1;
                while(y < targetCoord.Y){
                    if( !model.cellIsEmpty(new FieldCoord(figureCoord.X, y))){
                        return false;
                    }
                    y++;
                }
                return true;
            }
            if(figureCoord.Y > targetCoord.Y){
                y = figureCoord.Y-1;
                while(y > targetCoord.Y){
                    if( !model.cellIsEmpty(new FieldCoord(figureCoord.X, y))){
                        return false;
                    }
                    y--;
                }
                return true;
            }
        }
        else if(figureCoord.Y == targetCoord.Y){
            if(figureCoord.X < targetCoord.X){
                x = figureCoord.X+1;
                while(x < targetCoord.X){
                    if( !model.cellIsEmpty(new FieldCoord(x, figureCoord.Y))){
                        return false;
                    }
                    x++;
                }
                return true;
            }
            if(figureCoord.X > targetCoord.X){
                x = figureCoord.X-1;
                while(x > targetCoord.X){
                    if( !model.cellIsEmpty(new FieldCoord(x, figureCoord.Y))){
                        return false;
                    }
                    x--;
                }
                return true;
            }
        }
        return false;
    }

    private static boolean canKnightGo(FieldCoord figureCoord, FieldCoord targetCoord, Model model){
        return false;
    }

    private static boolean canBishopGo(FieldCoord figureCoord, FieldCoord targetCoord, Model model){
        int x=0, y=0;
        if(figureCoord.Y < targetCoord.Y){
            if(figureCoord.X < targetCoord.X){
                x=figureCoord.X+1;
                y=figureCoord.Y+1;
                while (y < targetCoord.Y && x < targetCoord.X){
                    if(!model.cellIsEmpty(new FieldCoord(x,y))){
                        return false;
                    }
                    x++;
                    y++;
                }
                return true;
            }
            else{
                x=figureCoord.X-1;
                y=figureCoord.Y+1;
                while (y < targetCoord.Y && x > targetCoord.X){
                    if(!model.cellIsEmpty(new FieldCoord(x,y))){
                        return false;
                    }
                    x--;
                    y++;
                }
                return true;
            }
        }
        else{
            if(figureCoord.X < targetCoord.X){
                x=figureCoord.X+1;
                y=figureCoord.Y-1;
                while (y > targetCoord.Y && x < targetCoord.X){
                    if(!model.cellIsEmpty(new FieldCoord(x,y))){
                        return false;
                    }
                    x++;
                    y--;
                }
                return true;
            }
            else{
                x=figureCoord.X-1;
                y=figureCoord.Y-1;
                while (y > targetCoord.Y && x > targetCoord.X){
                    if(!model.cellIsEmpty(new FieldCoord(x,y))){
                        return false;
                    }
                    x--;
                    y--;
                }
                return true;
            }
        }
    }

    private static boolean canPawnGo(FieldCoord figureCoord, FieldCoord targetCoord, Model model){
        int y0, y1;  //y0 - координата той фигуры, которая стоит ниже
        if (model.WhiteGoing()){
            y0 = figureCoord.Y;
            y1 = targetCoord.Y;
        }
        else{
            y0 = targetCoord.Y;
            y1 = figureCoord.Y;
        }
        if(figureCoord.X == targetCoord.X && y1-y0 == 1){ //если пешка идет на 1 вперед
            return false;
            //return model.cellIsEmpty(new FieldCoord(figureCoord.X, y1))
        }
        else{

        }
        return false;
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
