/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nsu.fit.g14203.dreamteam.awesomechess.field;

import java.util.List;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.ICreature;

/**
 *
 * @author phoenix
 */
public interface IModel {
    //Координаты нажатой модели
    public void cellClicked(FieldCoord coords);
    //Текущее состояние поля
    public Cell[][] getState();
    //Карточки для отображения, null или пустой список, если нечего отображать
    //Первая - белое существо, вторая - черное
    public List<ICreature> getSelectedCreatures();
    //Определитель хода
    public boolean whiteGoing();
    //Победа
    public boolean whiteWin();
    public boolean blackWin();
    //Текстовые логи
    public List<String> getLog();
    
}
