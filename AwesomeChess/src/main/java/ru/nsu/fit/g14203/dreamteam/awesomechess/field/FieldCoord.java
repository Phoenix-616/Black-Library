package ru.nsu.fit.g14203.dreamteam.awesomechess.field;

/**
 *
 * @author phoenix
 */
public class FieldCoord {
    public final int x;
    public final int y;
    
    public FieldCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public FieldCoord(FieldCoord o) {
        x = o.x;
        y = o.y;
    }
    
    public boolean equalsTo(FieldCoord o){
        return (this.x == o.x) && (this.y == o.y);    
    }
}
