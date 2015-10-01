import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * Created by henrikmnm on 28.09.15.
 */
public class routeFinder {

    private ArrayList<ArrayList<Node>> board = new ArrayList<ArrayList<Node>>();
    private Node start;
    private Node goal;
    private ArrayList<Node> open = new ArrayList<Node>();

    private ArrayList<Node> closed = new ArrayList<Node>();

    // Empty constructor for accessing methods.
    public routeFinder(){
    }

    // Method for building shortest path from a to b.
    public void findRoute(){
        this.start.setG_cost(0);
        this.start.setF_cost();

        this.open.add(this.start);

        Node currentNode = this.start;

        while (!currentNode.getIsGoal()){
            if(this.open.size() == 0){
                throw new IllegalStateException("Open list is empty.");
            }

            currentNode = this.open.get(0);
            this.closed.add(currentNode);

            if(currentNode.getIsGoal()){
                System.out.println("Victory!");
                break;
            }


        }

    }
    //Method for finding the succesors of a node and add them to the respective node's children list.
    public void findSuccersors(Node node){

        int x = node.getX();
        int y = node.getY();

        if(x == 0){
            if(this.board.get(y).get(1).getPassable()){
                node.addChild(this.board.get(y).get(1));
            }
        }
        if(y == 0){
            if(this.board.get(1).get(x).getPassable()){
                node.addChild(this.board.get(1).get(x));
            }
        }
        if(x == this.board.get(y).size()-1){
            if(this.board.get(y).get(x-1).getPassable()){
                node.addChild(this.board.get(y).get(x-1));
            }
        }
        if(y == this.board.size()-1){
            if(this.board.get(y-1).get(x).getPassable()){
                node.addChild(this.board.get(y-1).get(x));
            }
        }
        if(x > 0 && x < this.board.get(y).size()-1){
            if(this.board.get(y).get(x-1).getPassable()){
                node.addChild(this.board.get(y).get(x-1));
            }
            if(this.board.get(y).get(x+1).getPassable()){
                node.addChild(this.board.get(y).get(x+1));
            }
        }
        if(y>0 && y < this.board.size()-1){
            if(this.board.get(y-1).get(x).getPassable()){
                node.addChild(this.board.get(y-1).get(x));
            }
            if(this.board.get(y+1).get(x).getPassable()){
                node.addChild(this.board.get(y+1).get(x));
            }
        }

    }


    // Method for reading a file and creating a scanner object on this file.
    public Scanner readFile(String url){
        try{
            Scanner scanner = new Scanner(new FileReader(url));

            return scanner;

        }catch(IOException e){
            System.out.println(e);
        }
        return null;
    }

    // Initializes the board and recognizes the start and goal nodes and places these in the corresponding variables.
    public void buildBoard(Scanner scanner){
        String currentLine = "";
        int counter = 0;
        while (scanner.hasNext()){
            currentLine = scanner.nextLine();

            ArrayList<Node> nodeList = new ArrayList<Node>();

            for (int i = 0; i < currentLine.length(); i++) {
                Node newNode = new Node(currentLine.charAt(i), i, counter);
                nodeList.add(newNode);
                if (currentLine.charAt(i) == 'A'){
                    this.start = newNode;
                }
                else if (currentLine.charAt(i) == 'B'){
                    this.goal = newNode;
                }

            }
            board.add(nodeList);
            counter++;
        }
    }

    // Simple toString-method.
    public String boardToString(){
        String boardString = "";
        for (int i = 0; i < board.size(); i++) {
            ArrayList<Node> currentList = board.get(i);
            for (int j = 0; j < currentList.size(); j++) {
                Node currentNode = currentList.get(j);
                boardString += currentNode.toString();

            }
            boardString += "\n";
        }
        return boardString;
    }

    // Assigns the Hcost of each node.
    public void setHcost(){

        for (int i = 0; i < this.board.size(); i++) {

            ArrayList<Node> currentList = board.get(i);
            for (int j = 0; j < currentList.size(); j++) {
                Node currentNode = currentList.get(j);
                int hcost = calculateH_cost(currentNode.getX(), currentNode.getY());
                currentNode.setH_cost(hcost);

            }

        }

    }

    // Calculates the h-cost of a node and returns it in a placeholder coordinates-object.
    public int calculateH_cost(int x, int y){
        int xDiff;
        int yDiff;

        xDiff = Math.abs(x - this.goal.getX());
        yDiff = Math.abs(y - this.goal.getY());

        return xDiff+yDiff;
    }







    public static void main(String args[]){
        routeFinder test = new routeFinder();
        test.buildBoard(test.readFile("/Users/olanordmann/Documents/Skole/5. Semester/AI/Øvinger/øving3/src/board-1-2.txt"));
        test.setHcost();
        System.out.println(test.boardToString());
        System.out.println(test.board.get(0).get(0).getG_cost());


    }




}
