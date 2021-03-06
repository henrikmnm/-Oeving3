/**
 * Created by henrikmnm on 29.09.15.
 *
 * Simple class to hold x and y coordinates.
 */
public class Coordinates {

    int x;
    int y;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString(){
        return this.x+" "+this.y;
    }
}
