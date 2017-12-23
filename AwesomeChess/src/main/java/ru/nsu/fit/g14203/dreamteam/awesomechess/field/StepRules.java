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
    private StepRules() {
    }

    //Тип фигуры (По порядку: король, ферзь, ладья, конь, слон, пешка)
    public enum FigureType {
        KING, QUEEN, ROOK, KNIGHT, BISHOP, PAWN
    }

    public static boolean canFigureGo(FigureType figureType, FieldCoord figureCoord, FieldCoord targetCoord, Model model) {
        if(model.getState()[targetCoord.x][targetCoord.y].getFigure() != null) {
            if (!checkColor(figureCoord, targetCoord, model)) {
                return false;
            }
        }
        if (figureType == FigureType.KING) {
            return canKingGo(figureCoord, targetCoord);
        } else if (figureType == FigureType.QUEEN) {
            return canQueenGo(figureCoord, targetCoord, model);
        } else if (figureType == FigureType.ROOK) {
            return canRookGo(figureCoord, targetCoord, model);
        } else if (figureType == FigureType.KNIGHT) {
            return canKnightGo(figureCoord, targetCoord);
        } else if (figureType == FigureType.BISHOP) {
            return canBishopGo(figureCoord, targetCoord, model);
        } else if (figureType == FigureType.PAWN) {
            return canPawnGo(figureCoord, targetCoord, model);
        }
        return false;
    }

    private static boolean checkColor(FieldCoord figureCoord, FieldCoord targetCoord, Model model) {
        return (model.getState()[figureCoord.x][figureCoord.y].getFigure().color != model.getState()[targetCoord.x][targetCoord.y].getFigure().color);
    }

    private static boolean canKingGo(FieldCoord figureCoord, FieldCoord targetCoord) {

        if(targetCoord.y - figureCoord.y == 1){ //проверяем верхнюю или нижнюю строку для хода короля
            if(targetCoord.x == figureCoord.x || targetCoord.x - figureCoord.x == 1 || targetCoord.x - figureCoord.x == -1){
                return true;
            }
        }
        else if (targetCoord.y - figureCoord.y == -1){ //проверяем боковые ходы
            if(targetCoord.x == figureCoord.x || targetCoord.x - figureCoord.x == 1 || targetCoord.x - figureCoord.x == -1){
                return true;
            }
        }
        else if (targetCoord.y == figureCoord.y){ //проверяем ходы вбок
            if(targetCoord.x - figureCoord.x == 1 || targetCoord.x - figureCoord.x == -1){
                return true;
            }
        }
        return false;
    }

    private static boolean canQueenGo(FieldCoord figureCoord, FieldCoord targetCoord, Model model) {
        if (canRookGo(figureCoord, targetCoord, model)) { //проверяем, может ли пойти как ладья
            return true;
        } else return canBishopGo(figureCoord, targetCoord, model); //проверяем, может ли пойти как слон
    }

    private static boolean canRookGo(FieldCoord figureCoord, FieldCoord targetCoord, Model model) {
        if (figureCoord.x == targetCoord.x) {  //проверяем вертикальные ходы
            return canGoVertical(figureCoord, targetCoord, model);
        }
        else if (figureCoord.y == targetCoord.y) { //проверяем горизонтальные ходы
           return canGoHorizontal(figureCoord, targetCoord, model);
        }
        return false;
    }

    private static boolean canGoVertical(FieldCoord figureCoord, FieldCoord targetCoord, Model model){
        int y;
        if (figureCoord.y < targetCoord.y) { //проверяем ход вправо вверх
            y = figureCoord.y + 1;
            while (y < targetCoord.y) {
                if (!model.cellIsEmpty(new FieldCoord(figureCoord.x, y))) {
                    return false;
                }
                y++;
            }
            return true;
        }
        if (figureCoord.y > targetCoord.y) { //проверяем ход вниз
            y = figureCoord.y - 1;
            while (y > targetCoord.y) {
                if (!model.cellIsEmpty(new FieldCoord(figureCoord.x, y))) {
                    return false;
                }
                y--;
            }
            return true;
        }
        return false;
    }

    private static boolean canGoHorizontal(FieldCoord figureCoord, FieldCoord targetCoord, Model model){
        int x;
        if (figureCoord.x < targetCoord.x) { //проверяе ход вправо
            x = figureCoord.x + 1;
            while (x < targetCoord.x) {
                if (!model.cellIsEmpty(new FieldCoord(x, figureCoord.y))) {
                    return false;
                }
                x++;
            }
            return true;
        }
        if (figureCoord.x > targetCoord.x) { //проверяем ход влево
            x = figureCoord.x - 1;
            while (x > targetCoord.x) {
                if (!model.cellIsEmpty(new FieldCoord(x, figureCoord.y))) {
                    return false;
                }
                x--;
            }
            return true;
        }
        return false;
    }

    private static boolean canKnightGo(FieldCoord figureCoord, FieldCoord targetCoord) {
        return(
           targetCoord.x - figureCoord.x == 2 && (targetCoord.y - figureCoord.y == 1 || targetCoord.y - figureCoord.y == -1) ||  //проверили правые Гшки
           targetCoord.x - figureCoord.x == -2 && (targetCoord.y - figureCoord.y == 1 || targetCoord.y - figureCoord.y == -1) || //проверили левые Гшки
           targetCoord.y - figureCoord.y == 2 && (targetCoord.x - figureCoord.x == 1 || targetCoord.x - figureCoord.x == -1) || //проверили верхние Гшки
           targetCoord.y - figureCoord.y == -2 && (targetCoord.x - figureCoord.x == 1 || targetCoord.x - figureCoord.x == -1) //проверили нижние Гшки
        );
    }

    private static boolean canBishopGo(FieldCoord figureCoord, FieldCoord targetCoord, Model model) {
        if (figureCoord.y < targetCoord.y) { //проверяем верхние квадраты
            return canGoTopDiagonals(figureCoord, targetCoord, model);
        } else { //проверяем нижние квадраты
            return canGoBottomDiagonals(figureCoord, targetCoord, model);
        }
    }

    private static boolean canGoTopDiagonals(FieldCoord figureCoord, FieldCoord targetCoord, Model model){
        int x = 0;
        int y = 0;
        if (figureCoord.x < targetCoord.x) { //проверяем правый верхний квадрат
            x = figureCoord.x + 1;
            y = figureCoord.y + 1;
            while (y < targetCoord.y && x < targetCoord.x) {
                if (!model.cellIsEmpty(new FieldCoord(x, y))) {
                    return false;
                }
                x++;
                y++;
            }
            return (x == targetCoord.x && y == targetCoord.y);
        } else { //проверяем левый верхний квадрат
            x = figureCoord.x - 1;
            y = figureCoord.y + 1;
            while (y < targetCoord.y && x > targetCoord.x) {
                if (!model.cellIsEmpty(new FieldCoord(x, y))) {
                    return false;
                }
                x--;
                y++;
            }
            return (x == targetCoord.x && y == targetCoord.y);
        }
    }
    
    private static boolean canGoBottomDiagonals(FieldCoord figureCoord, FieldCoord targetCoord, Model model) {
        int x;
        int y;
        if (figureCoord.x < targetCoord.x) {//проверяем нижний правый квадрат
            x = figureCoord.x + 1;
            y = figureCoord.y - 1;
            while (y > targetCoord.y && x < targetCoord.x) {
                if (!model.cellIsEmpty(new FieldCoord(x, y))) {
                    return false;
                }
                x++;
                y--;
            }
            return (x == targetCoord.x && y == targetCoord.y);
        } else { //проверяем нижний левый квадрат
            x = figureCoord.x - 1;
            y = figureCoord.y - 1;
            while (y > targetCoord.y && x > targetCoord.x) {
                if (!model.cellIsEmpty(new FieldCoord(x, y))) {
                    return false;
                }
                x--;
                y--;
            }
            return (x == targetCoord.x && y == targetCoord.y);
        }
    }

    private static boolean canPawnGo(FieldCoord figureCoord, FieldCoord targetCoord, Model model) {
        int y0;  //y0 - координата той фигуры, которая стоит ниже
        int y1;
        if (model.whiteGoing()) {
            y0 = figureCoord.y;
            y1 = targetCoord.y;
        } else {
            y0 = targetCoord.y;
            y1 = figureCoord.y;
        }
        if (y1 - y0 == 1) {
            if (figureCoord.x == targetCoord.x) { //если пешка идет на 1 вперед
                return model.cellIsEmpty(new FieldCoord(targetCoord.x, targetCoord.y));
            } else if (figureCoord.x - targetCoord.x == 1 || figureCoord.x - targetCoord.x == -1) { //если будет рубить по диагонали
                return !model.cellIsEmpty(new FieldCoord(targetCoord.x, targetCoord.y));
            }
        }
        return false;
    }


}
