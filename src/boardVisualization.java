/**
 * Created by henrikmnm on 02.10.15.
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class boardVisualization extends Application {

    private routeFinder routefinder = new routeFinder();



    // Method for creating a grid and adding all of the board-nodes to it with the correct colour.
    public GridPane buildGrid(){
        routefinder.buildBoard(routefinder.readFile("/Users/olanordmann/Documents/Skole/5. Semester/AI/Øvinger/øving3/src/board-1-1.txt"));
        routefinder.setHcost();
        routefinder.findRoute(1);
        routefinder.returnShortestPath(routefinder.getGoal());
        ArrayList<ArrayList<mapNode>> board = routefinder.getBoard();

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);


        for (int i = 0; i < board.size(); i++) {
            ArrayList<mapNode> currentList = board.get(i);
            grid.addRow(i);

            for (int j = 0; j < currentList.size(); j++) {

                grid.addColumn(j);
                mapNode currentNode = currentList.get(j);
                Label currentLabel = createLabel(currentNode);
                grid.add(currentLabel, currentNode.getX(), currentNode.getY());


            }

        }


        return grid;

    }


    // Method that returns a label with correct background-colour and text according to what type
    // node-object that is given to it as an argument.
    public Label createLabel(mapNode node){
        Label newLabel = new Label();
        newLabel.setPrefSize(20,20);
        newLabel.setStyle("-fx-border-color: black");
        newLabel.setStyle("-fx-border-width: 2");

        switch (node.getBoardchar()){
            case '.': newLabel.setStyle("-fx-background-color: white"); if(routefinder.getOpen().contains(node))newLabel.setText("O"); if(routefinder.getClosed().contains(node))newLabel.setText("C"); if(node.getRouteChar() == '$')newLabel.setText("X");
                break;
            case '#': newLabel.setStyle("-fx-background-color: black");
                break;
            case 'A': newLabel.setStyle("-fx-background-color: red"); newLabel.setText("A"); newLabel.setTextAlignment(TextAlignment.CENTER);
                break;
            case 'B': newLabel.setStyle("-fx-background-color: turquoise"); newLabel.setText("B"); newLabel.setTextAlignment(TextAlignment.CENTER);
                break;
            case 'w': newLabel.setStyle("-fx-background-color: dodgerblue");  if(routefinder.getOpen().contains(node))newLabel.setText("O"); if(routefinder.getClosed().contains(node))newLabel.setText("C"); if(node.getRouteChar() == '$')newLabel.setText("X");
                break;
            case 'm': newLabel.setStyle("-fx-background-color: grey");  if(routefinder.getOpen().contains(node))newLabel.setText("O"); if(routefinder.getClosed().contains(node))newLabel.setText("C"); if(node.getRouteChar() == '$')newLabel.setText("X");
                break;
            case 'f': newLabel.setStyle("-fx-background-color: darkgreen");  if(routefinder.getOpen().contains(node))newLabel.setText("O"); if(routefinder.getClosed().contains(node))newLabel.setText("C"); if(node.getRouteChar() == '$')newLabel.setText("X");
                break;
            case 'g': newLabel.setStyle("-fx-background-color: lightgreen");  if(routefinder.getOpen().contains(node))newLabel.setText("O"); if(routefinder.getClosed().contains(node))newLabel.setText("C"); if(node.getRouteChar() == '$')newLabel.setText("X");
                break;
            case 'r': newLabel.setStyle("-fx-background-color: brown");  if(routefinder.getOpen().contains(node))newLabel.setText("O"); if(routefinder.getClosed().contains(node))newLabel.setText("C"); if(node.getRouteChar() == '$')newLabel.setText("X");
                break;

        }

        return newLabel;
    }





    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = buildGrid();
        System.out.println(grid.toString());
        grid.setHgap(1);
        grid.setVgap(1);

        Group root = new Group(grid);
        Scene scene = new Scene(root);
        grid.setAlignment(Pos.CENTER);


        System.out.println("Total weight: "+routefinder.getTotalWeight());
        primaryStage.setTitle("Board Visualization.");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
