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
        routefinder.buildBoard(routefinder.readFile("/Users/olanordmann/Documents/Skole/5. Semester/AI/Øvinger/øving3/src/board-2-1.txt"));
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
            case '.': newLabel.setStyle("-fx-background-color: white");
                break;
            case '#': newLabel.setStyle("-fx-background-color: black");
                break;
            case 'A': newLabel.setStyle("-fx-background-color: red"); newLabel.setText("A"); newLabel.setTextAlignment(TextAlignment.CENTER);
                break;
            case 'B': newLabel.setStyle("-fx-background-color: turquoise"); newLabel.setText("B"); newLabel.setTextAlignment(TextAlignment.CENTER);
                break;
            case 'w': newLabel.setStyle("-fx-background-color: dodgerblue");
                break;
            case 'm': newLabel.setStyle("-fx-background-color: grey");
                break;
            case 'f': newLabel.setStyle("-fx-background-color: darkgreen");
                break;
            case 'g': newLabel.setStyle("-fx-background-color: lightgreen");
                break;
            case 'r': newLabel.setStyle("-fx-background-color: brown");
                break;

        }

        return newLabel;
    }

    public void setRoute(GridPane grid){
        routefinder.findRoute(1);
        routefinder.returnShortestPath(routefinder.getGoal());

        ArrayList<ArrayList<mapNode>> board = routefinder.getBoard();

        for (int i = 0; i < board.size(); i++) {

            ArrayList<mapNode> currentList = board.get(i);

            for (int j = 0; j < currentList.size(); j++) {

                mapNode currentNode = currentList.get(j);

                switch (currentNode.getBoardchar()){
                    case '$': Node node = getNodeFromGridPane(grid, currentNode.getX(), currentNode.getY());
                }
            }
        }
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

        primaryStage.setTitle("Board Visualization.");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
}
