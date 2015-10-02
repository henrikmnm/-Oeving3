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

    public Node getGoal(){
        return this.goal;
    }

    // Method for building shortest path from a to b.
    public void findRoute(){
        boolean finished = false;
        this.start.setG_cost(0);
        this.start.setF_cost();

        this.open.add(this.start);



        int counter = 0;
        while (!finished){
            counter ++;
            if(this.open.size() == 0){
                System.out.println(counter);
                throw new IllegalStateException("Open list is empty.");

            }

            Node currentNode = this.open.remove(0);
            if(currentNode.getX()==18 && currentNode.getY()==3){

            }

            this.closed.add(currentNode);

            if(currentNode.getIsGoal()){
                finished = true;
                System.out.println("Victory!");


            }
            findSuccersors(currentNode);



            for(Node c: currentNode.getChildren()){

                //currentNode.addChild(c);

                if (!this.open.contains(c) && !this.closed.contains(c)){
                    c.setParent(currentNode);
                    c.setG_cost(currentNode.getG_cost()+c.getMoveCost());
                    c.setF_cost();

                    this.open.add(c);
                    Collections.sort(this.open);
                }
                // Have to change when different g-costs on nodes.
                else if(currentNode.getG_cost() + c.getMoveCost() < c.getG_cost()){
                    c.setParent(currentNode);
                    // Change this when different g-gosts.
                    c.setG_cost(currentNode.getG_cost()+c.getMoveCost());
                    c.setF_cost();
                    if(closed.contains(c)){
                        ppi(c);
                    }
                }
            }



        }


    }
    // Method for updating g-costs on child-nodes.
    public void ppi(Node node){
        for (Node c: node.getChildren()){
            // Need to change for weighted g-costs in nodes.
            if (node.getG_cost() + c.getMoveCost() < c.getG_cost()){
                c.setParent(node);
                // Need to change
                c.setG_cost(node.getG_cost()+c.getMoveCost());
                c.setF_cost();
                // Recursive call to update every node's children.
                ppi(c);
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
    public void returnShortestPath(Node node){

        if(node.getIsGoal()){
            returnShortestPath(node.getParent());
        }else if(node.getParent().getIsStart()){
            node.setBoardchar('$');
        }else{
            node.setBoardchar('$');
            returnShortestPath(node.getParent());
        }

    }

    public ArrayList<ArrayList<Node>> getBoard(){
        return this.board;
    }





/*    public static void main(String args[]){
        routeFinder test = new routeFinder();
        test.buildBoard(test.readFile("/Users/olanordmann/Documents/Skole/5. Semester/AI/Øvinger/øving3/src/board-2-4.txt"));
        test.setHcost();
        test.findRoute();
        test.returnShortestPath(test.getGoal());
        System.out.println(test.boardToString());


    }*/




}
