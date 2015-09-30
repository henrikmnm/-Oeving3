import java.util.ArrayList;

/**
 * Created by henrikmnm on 25.09.15.
 */
public class Node {

    private boolean passable;
    private boolean isStart = false;
    private boolean isGoal = false;
    private int f_cost;
    private int g_cost;
    private int h_cost;
    private int x;
    private int y;
    private char boardchar;
    Node parent;


    public boolean isStart() {
        return isStart;
    }

    public void setIsStart(boolean isStart) {
        this.isStart = isStart;
    }

    public boolean isGoal() {
        return isGoal;
    }

    public void setIsGoal(boolean isGoal) {
        this.isGoal = isGoal;
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

    public Node (char c, int x, int y){
        this.boardchar = c;
        this.x = x;
        this.y = y;

        if (c == '.'){
            this.passable = true;
        }
        else if (c == 'A'){
            this.isStart = true;
        }
        else if (c == 'B'){
            this.isGoal = true;
        }
        else if (c == '#'){
            this.passable = false;
        }

    }

    public void setPassable(boolean passable){
        this.passable = passable;
    }

    public int getH_cost() {
        return h_cost;
    }

    public void setH_cost(int h_cost) {
        this.h_cost = h_cost;
    }

    public int getG_cost() {
        return g_cost;
    }

    public void setG_cost(int g_cost) {
        this.g_cost = g_cost;
    }

    public int getF_cost() {

        return f_cost;
    }

    public void setF_cost(int f_cost) {
        this.f_cost = f_cost;
    }

    public boolean getPassable(){
        return this.passable;
    }
    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    public String toString(){
        return this.boardchar+"";
    }
}
