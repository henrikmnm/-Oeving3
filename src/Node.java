import java.util.ArrayList;

/**
 * Created by henrikmnm on 25.09.15.
 *
 *
 * Class to provide a Node-interface.
 */
public class Node implements Comparable<Node>{

    private boolean passable;
    private boolean isStart = false;
    private boolean isGoal = false;
    private int f_cost;
    private int g_cost;
    private int h_cost;
    private int x;
    private int y;
    private char boardchar;
    private Node parent;
    private ArrayList<Node> children = new ArrayList<Node>();

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

    public void setF_cost() {
        this.f_cost = this.h_cost + this.g_cost;
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

    public ArrayList<Node> getChildren() {
        return children;
    }

    public boolean getIsGoal(){
        return this.isGoal;
    }


    public void addChild(Node node){
        this.children.add(node);
    }


    @Override
    public int compareTo(Node o) {
        return this.f_cost - o.getF_cost();
    }
}
