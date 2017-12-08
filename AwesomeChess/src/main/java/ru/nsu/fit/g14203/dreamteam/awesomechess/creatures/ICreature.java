/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nsu.fit.g14203.dreamteam.awesomechess.creatures;

import java.util.List;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.StepRules.StepDirectionWithLimit;

/**
 *
 * @author phoenix
 */
public interface ICreature {
    //Получить имя
    String GetName();
    //Получить описание
    String GetDescription();
    //Получить силу
    int GetStrength();
    //Получить направления возможного хода
    List<StepDirectionWithLimit> GetStepAbilities();
    //Получить объект альтернативной формы
    ICreature GetOtherForm();
    //Получить имя файла изображения
    String GetImgFileName();
}
