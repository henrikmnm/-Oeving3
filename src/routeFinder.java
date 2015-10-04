import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * Created by henrikmnm on 28.09.15.
 */
public class routeFinder implements Comparator<mapNode>{

    private ArrayList<ArrayList<mapNode>> board = new ArrayList<ArrayList<mapNode>>();
    private mapNode start;
    private mapNode goal;
    private ArrayList<mapNode> open = new ArrayList<mapNode>();
    private int totalWeight;

    private ArrayList<mapNode> closed = new ArrayList<mapNode>();

    // Empty constructor for accessing methods.
    public routeFinder(){
    }

    public mapNode getGoal(){
        return this.goal;
    }

    // Method for building shortest path from a to b.
    public void findRoute(int algorithm){
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

            mapNode currentMapNode = this.open.remove(0);
            if(currentMapNode.getX()==18 && currentMapNode.getY()==3){

            }

            this.closed.add(currentMapNode);

            if(currentMapNode.getIsGoal()){
                finished = true;
                System.out.println("Victory!");


            }
            findSuccersors(currentMapNode);



            for(mapNode c: currentMapNode.getChildren()){

                //currentMapNode.addChild(c);

                if (!this.open.contains(c) && !this.closed.contains(c)){
                    c.setParent(currentMapNode);
                    c.setG_cost(currentMapNode.getG_cost()+c.getMoveCost());
                    c.setF_cost();

                    this.open.add(c);
                    if(algorithm == 1)
                        Collections.sort(this.open, compareF);
                    if(algorithm == 3)
                        Collections.sort(this.open, compareG);
                }
                // Have to change when different g-costs on nodes.
                else if(currentMapNode.getG_cost() + c.getMoveCost() < c.getG_cost()){
                    c.setParent(currentMapNode);
                    // Change this when different g-gosts.
                    c.setG_cost(currentMapNode.getG_cost()+c.getMoveCost());
                    c.setF_cost();
                    if(closed.contains(c)){
                        ppi(c);
                    }
                }
            }



        }


    }

    // Method for updating g-costs on child-nodes.
    public void ppi(mapNode mapNode){
        for (mapNode c: mapNode.getChildren()){
            // Need to change for weighted g-costs in nodes.
            if (mapNode.getG_cost() + c.getMoveCost() < c.getG_cost()){
                c.setParent(mapNode);
                // Need to change
                c.setG_cost(mapNode.getG_cost()+c.getMoveCost());
                c.setF_cost();
                // Recursive call to update every mapNode's children.
                ppi(c);
            }
        }
    }

    //Method for finding the succesors of a mapNode and add them to the respective mapNode's children list.
    public void findSuccersors(mapNode mapNode){

        int x = mapNode.getX();
        int y = mapNode.getY();

        if(x == 0){
            if(this.board.get(y).get(1).getPassable()){
                mapNode.addChild(this.board.get(y).get(1));
            }
        }
        if(y == 0){
            if(this.board.get(1).get(x).getPassable()){
                mapNode.addChild(this.board.get(1).get(x));
            }
        }
        if(x == this.board.get(y).size()-1){
            if(this.board.get(y).get(x-1).getPassable()){
                mapNode.addChild(this.board.get(y).get(x-1));
            }
        }
        if(y == this.board.size()-1){
            if(this.board.get(y-1).get(x).getPassable()){
                mapNode.addChild(this.board.get(y-1).get(x));
            }
        }
        if(x > 0 && x < this.board.get(y).size()-1){
            if(this.board.get(y).get(x-1).getPassable()){
                mapNode.addChild(this.board.get(y).get(x-1));
            }
            if(this.board.get(y).get(x+1).getPassable()){
                mapNode.addChild(this.board.get(y).get(x+1));
            }
        }
        if(y>0 && y < this.board.size()-1){
            if(this.board.get(y-1).get(x).getPassable()){
                mapNode.addChild(this.board.get(y-1).get(x));
            }
            if(this.board.get(y+1).get(x).getPassable()){
                mapNode.addChild(this.board.get(y+1).get(x));
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

            ArrayList<mapNode> mapNodeList = new ArrayList<mapNode>();

            for (int i = 0; i < currentLine.length(); i++) {
                mapNode newMapNode = new mapNode(currentLine.charAt(i), i, counter);
                mapNodeList.add(newMapNode);
                if (currentLine.charAt(i) == 'A'){
                    this.start = newMapNode;
                }
                else if (currentLine.charAt(i) == 'B'){
                    this.goal = newMapNode;
                }

            }
            board.add(mapNodeList);
            counter++;
        }
    }

    // Simple toString-method.
    public String boardToString(){
        String boardString = "";
        for (int i = 0; i < board.size(); i++) {
            ArrayList<mapNode> currentList = board.get(i);
            for (int j = 0; j < currentList.size(); j++) {
                mapNode currentMapNode = currentList.get(j);
                boardString += currentMapNode.toString();

                }
            boardString += "\n";
        }
        return boardString;
    }

    // Assigns the Hcost of each node.
    public void setHcost(){

        for (int i = 0; i < this.board.size(); i++) {

            ArrayList<mapNode> currentList = board.get(i);
            for (int j = 0; j < currentList.size(); j++) {
                mapNode currentMapNode = currentList.get(j);
                int hcost = calculateH_cost(currentMapNode.getX(), currentMapNode.getY());
                currentMapNode.setH_cost(hcost);

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
    public void returnShortestPath(mapNode mapNode){


        if(mapNode.getIsGoal()){
            returnShortestPath(mapNode.getParent());

        }else if(mapNode.getParent().getIsStart()){
            this.totalWeight += mapNode.getMoveCost();
            mapNode.setRouteChar('$');
        }else{
            mapNode.setRouteChar('$');
            this.totalWeight += mapNode.getMoveCost();
            returnShortestPath(mapNode.getParent());

        }


    }

    public ArrayList<mapNode> getOpen() {
        return open;
    }

    public void setOpen(ArrayList<mapNode> open) {
        this.open = open;
    }

    public ArrayList<mapNode> getClosed() {
        return closed;
    }

    public void setClosed(ArrayList<mapNode> closed) {
        this.closed = closed;
    }

    public ArrayList<ArrayList<mapNode>> getBoard(){
        return this.board;
    }


    @Override
    public int compare(mapNode o1, mapNode o2) {
        return 0;
    }

    Comparator<mapNode> compareF = new Comparator<mapNode>() {
        @Override
        public int compare(mapNode o1, mapNode o2){
            return o1.getF_cost() - o2.getF_cost();
        }

    };

    Comparator<mapNode> compareG = new Comparator<mapNode>() {
        @Override
        public int compare(mapNode o1, mapNode o2){
            return o1.getG_cost() - o2.getG_cost();
        }

    };

    public int getTotalWeight(){
        return this.totalWeight;
    }


/*    public static void main(String args[]){
        routeFinder test = new routeFinder();
        test.buildBoard(test.readFile("/Users/olanordmann/Documents/Skole/5. Semester/AI/Øvinger/øving3/src/board-2-4.txt"));
        test.setHcost();
        test.findRoute(1);
        test.returnShortestPath(test.getGoal());
        System.out.println(test.boardToString());


    }*/




}
