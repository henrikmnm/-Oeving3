import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by henrikmnm on 28.09.15.
 */
public class routeFinder {

    ArrayList<ArrayList<Node>> board = new ArrayList<ArrayList<Node>>();
    Node start;
    Node goal;

    // Empty constructor for accessing methods.
    public routeFinder(){
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

    // Prints the coordinates of each node relative to the goal-node aka. the H-cost of each node.
    public String hCostToString(){
        String coordString= "";
        for (int i = 0; i < this.board.size(); i++) {
            coordString += "|";
            ArrayList<Node> currentList = board.get(i);
            for (int j = 0; j < currentList.size(); j++) {
                Node currentNode = currentList.get(j);
                Coordinates coordinates = returnH_cost(currentNode);
                coordString += coordinates.getX()+","+coordinates.getY();
                coordString += "|";
            }
            coordString += "\n";
        }
        return coordString;
    }

    // Calculates the h-cost of a node and returns it in a placeholder coordinates-object.
    public Coordinates returnH_cost(Node node){
        int xDiff;
        int yDiff;

        xDiff = node.getX() - this.goal.getX();
        yDiff = node.getY() - this.goal.getY();

        return new Coordinates(xDiff, yDiff);
    }




    public static void main(String args[]){
        routeFinder test = new routeFinder();
        test.buildBoard(test.readFile("/Users/olanordmann/Documents/Skole/5. Semester/AI/Øvinger/øving3/src/board-1-1.txt"));
        System.out.println(test.hCostToString());

    }




}
