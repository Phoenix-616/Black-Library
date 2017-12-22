package ru.nsu.fit.g14203.dreamteam.awesomechess.field;

/**
 *
 * @author phoenix
 */
public class FieldCoord {
    public final int X;
    public final int Y;
    
    public FieldCoord(int x, int y) {
        X = x;
        Y = y;
    }
    
    public FieldCoord(FieldCoord o) {
        X = o.X;
        Y = o.Y;
    }
    
    public boolean equalsTo(FieldCoord o){
        return (this.X == o.X) && (this.Y == o.Y);    
    }
}
