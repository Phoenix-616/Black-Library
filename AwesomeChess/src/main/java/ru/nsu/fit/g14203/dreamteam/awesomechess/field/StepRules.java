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
        if(model.getState()[targetCoord.X][targetCoord.Y].getFigure() != null) {
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
        if (model.getState()[figureCoord.X][figureCoord.Y].getFigure().COLOR != model.getState()[targetCoord.X][targetCoord.Y].getFigure().COLOR) {
            return true;
        }
        return false;
    }

    private static boolean canKingGo(FieldCoord figureCoord, FieldCoord targetCoord) {

        if(targetCoord.Y - figureCoord.Y == 1){ //проверяем верхнюю или нижнюю строку для хода короля
            if(targetCoord.X == figureCoord.X || targetCoord.X - figureCoord.X == 1 || targetCoord.X - figureCoord.X == -1){
                return true;
            }
        }
        else if (targetCoord.Y - figureCoord.Y == -1){ //проверяем боковые ходы
            if(targetCoord.X == figureCoord.X || targetCoord.X - figureCoord.X == 1 || targetCoord.X - figureCoord.X == -1){
                return true;
            }
        }
        else if (targetCoord.Y == figureCoord.Y){ //проверяем ходы вбок
            if(targetCoord.X - figureCoord.X == 1 || targetCoord.X - figureCoord.X == -1){
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
        if (figureCoord.X == targetCoord.X) {  //проверяем вертикальные ходы
            return canGoVertical(figureCoord, targetCoord, model);
        }
        else if (figureCoord.Y == targetCoord.Y) { //проверяем горизонтальные ходы
           return canGoHorizontal(figureCoord, targetCoord, model);
        }
        return false;
    }

    private static boolean canGoVertical(FieldCoord figureCoord, FieldCoord targetCoord, Model model){
        int y;
        if (figureCoord.Y < targetCoord.Y) { //проверяем ход вправо вверх
            y = figureCoord.Y + 1;
            while (y < targetCoord.Y) {
                if (!model.cellIsEmpty(new FieldCoord(figureCoord.X, y))) {
                    return false;
                }
                y++;
            }
            return true;
        }
        if (figureCoord.Y > targetCoord.Y) { //проверяем ход вниз
            y = figureCoord.Y - 1;
            while (y > targetCoord.Y) {
                if (!model.cellIsEmpty(new FieldCoord(figureCoord.X, y))) {
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
        if (figureCoord.X < targetCoord.X) { //проверяе ход вправо
            x = figureCoord.X + 1;
            while (x < targetCoord.X) {
                if (!model.cellIsEmpty(new FieldCoord(x, figureCoord.Y))) {
                    return false;
                }
                x++;
            }
            return true;
        }
        if (figureCoord.X > targetCoord.X) { //проверяем ход влево
            x = figureCoord.X - 1;
            while (x > targetCoord.X) {
                if (!model.cellIsEmpty(new FieldCoord(x, figureCoord.Y))) {
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
           targetCoord.X - figureCoord.X == 2 && (targetCoord.Y - figureCoord.Y == 1 || targetCoord.Y - figureCoord.Y == -1) ||  //проверили правые Гшки
           targetCoord.X - figureCoord.X == -2 && (targetCoord.Y - figureCoord.Y == 1 || targetCoord.Y - figureCoord.Y == -1) || //проверили левые Гшки
           targetCoord.Y - figureCoord.Y == 2 && (targetCoord.X - figureCoord.X == 1 || targetCoord.X - figureCoord.X == -1) || //проверили верхние Гшки
           targetCoord.Y - figureCoord.Y == -2 && (targetCoord.X - figureCoord.X == 1 || targetCoord.X - figureCoord.X == -1) //проверили нижние Гшки
        );
    }

    private static boolean canBishopGo(FieldCoord figureCoord, FieldCoord targetCoord, Model model) {
        if (figureCoord.Y < targetCoord.Y) { //проверяем верхние квадраты
            return canGoTopDiagonals(figureCoord, targetCoord, model);
        } else { //проверяем нижние квадраты
            return canGoBottomDiagonals(figureCoord, targetCoord, model);
        }
    }

    private static boolean canGoTopDiagonals(FieldCoord figureCoord, FieldCoord targetCoord, Model model){
        int x = 0;
        int y = 0;
        if (figureCoord.X < targetCoord.X) { //проверяем правый верхний квадрат
            x = figureCoord.X + 1;
            y = figureCoord.Y + 1;
            while (y < targetCoord.Y && x < targetCoord.X) {
                if (!model.cellIsEmpty(new FieldCoord(x, y))) {
                    return false;
                }
                x++;
                y++;
            }
            if (x == targetCoord.X && y == targetCoord.Y) {
                return true;
            } else {
                return false;
            }
        } else { //проверяем левый верхний квадрат
            x = figureCoord.X - 1;
            y = figureCoord.Y + 1;
            while (y < targetCoord.Y && x > targetCoord.X) {
                if (!model.cellIsEmpty(new FieldCoord(x, y))) {
                    return false;
                }
                x--;
                y++;
            }
            if (x == targetCoord.X && y == targetCoord.Y) {
                return true;
            } else {
                return false;
            }
        }
    }
    private static boolean canGoBottomDiagonals(FieldCoord figureCoord, FieldCoord targetCoord, Model model) {
        int x;
        int y;
        if (figureCoord.X < targetCoord.X) {//проверяем нижний правый квадрат
            x = figureCoord.X + 1;
            y = figureCoord.Y - 1;
            while (y > targetCoord.Y && x < targetCoord.X) {
                if (!model.cellIsEmpty(new FieldCoord(x, y))) {
                    return false;
                }
                x++;
                y--;
            }
            if (x == targetCoord.X && y == targetCoord.Y) {
                return true;
            } else {
                return false;
            }
        } else { //проверяем нижний левый квадрат
            x = figureCoord.X - 1;
            y = figureCoord.Y - 1;
            while (y > targetCoord.Y && x > targetCoord.X) {
                if (!model.cellIsEmpty(new FieldCoord(x, y))) {
                    return false;
                }
                x--;
                y--;
            }
            if (x == targetCoord.X && y == targetCoord.Y) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static boolean canPawnGo(FieldCoord figureCoord, FieldCoord targetCoord, Model model) {
        int y0;  //y0 - координата той фигуры, которая стоит ниже
        int y1;
        if (model.whiteGoing()) {
            y0 = figureCoord.Y;
            y1 = targetCoord.Y;
        } else {
            y0 = targetCoord.Y;
            y1 = figureCoord.Y;
        }
        if (y1 - y0 == 1) {
            if (figureCoord.X == targetCoord.X) { //если пешка идет на 1 вперед
                return model.cellIsEmpty(new FieldCoord(targetCoord.X, targetCoord.Y));
            } else if (figureCoord.X - targetCoord.X == 1 || figureCoord.X - targetCoord.X == -1) { //если будет рубить по диагонали
                return !model.cellIsEmpty(new FieldCoord(targetCoord.X, targetCoord.Y));
            }
        }
        return false;
    }


}
