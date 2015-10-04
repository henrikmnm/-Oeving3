import java.util.ArrayList;

/**
 * Created by henrikmnm on 25.09.15.
 *
 *
 * Class to provide a mapNode-interface.
 */
public class mapNode {

    private boolean passable = true;
    private boolean isStart = false;
    private boolean isGoal = false;
    private int f_cost;
    private int g_cost;
    private int h_cost;
    private int moveCost;
    private int x;
    private int y;
    private char boardchar;
    private mapNode parent;
    private ArrayList<mapNode> children = new ArrayList<mapNode>();

    public mapNode(char c, int x, int y){

        this.boardchar = c;
        this.x = x;
        this.y = y;

        switch (c){
            case '.':case 'r': this.moveCost = 1;
                break;

            case '#': this.passable = false;
                break;

            case 'A': this.isStart = true;
                break;

            case 'B': this.isGoal = true;
                break;

            case 'w': this.moveCost = 100;
                break;

            case 'm': this.moveCost = 50;
                break;

            case 'f': this.moveCost = 10;
                break;

            case 'g': this.moveCost = 5;
                break;


        }

    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
    public boolean getIsStart(){
        return this.isStart;
    }

    public void setF_cost() {
        this.f_cost = this.h_cost + this.g_cost;
    }

    public boolean getPassable(){
        return this.passable;
    }

    public mapNode getParent() {
        return parent;
    }

    public void setParent(mapNode parent) {
        this.parent = parent;
    }

    public String toString(){
        return this.boardchar+"";
    }

    public int getMoveCost() {
        return moveCost;
    }

    public void setMoveCost(int moveCost) {
        this.moveCost = moveCost;
    }

    public ArrayList<mapNode> getChildren() {
        return children;
    }

    public void setBoardchar(char c){
        this.boardchar = c;
    }

    public char getBoardchar(){
        return this.boardchar;
    }

    public boolean getIsGoal(){
        return this.isGoal;
    }


    public void addChild(mapNode mapNode){
        this.children.add(mapNode);
    }





}
