/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nsu.fit.g14203.dreamteam.awesomechess.field.Actions;

import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Cell;

/**
 *
 * @author phoenix
 */
public abstract class AAction {
    
    protected Cell targetCell = null;
    
    public AAction(Cell tarCell) {
        targetCell = tarCell;
    }
    
    public abstract void DoAction();
}