/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

/**
 *
 * @author phoenix
 */
public interface ICreature {
    //Получить имя
    String getName();
    //Получить описание
    String getDescription();
    //Получить силу
    int getStrength();
    //Получить объект альтернативной формы
    ICreature getOtherForm();
    //Получить имя файла изображения
    String getImgFileName();
    //Получить имя иконки
    String getIconFileName();
}
