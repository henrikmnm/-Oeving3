/**
 * Created by henrikmnm on 02.10.15.
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;

public class boardVisualization extends Application {

    private routeFinder routefinder = new routeFinder();



    public GridPane buildGrid(){
        routefinder.buildBoard(routefinder.readFile("/Users/olanordmann/Documents/Skole/5. Semester/AI/Øvinger/øving3/src/board-2-1.txt"));
        ArrayList<ArrayList<Node>> board = routefinder.getBoard();

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);


        for (int i = 0; i < board.size(); i++) {
            ArrayList<Node> currentList = board.get(i);
            grid.addRow(i);

            for (int j = 0; j < currentList.size(); j++) {

                grid.addColumn(j);
                Node currentNode = currentList.get(j);
                Label currentLabel = createLabel(currentNode);
                grid.add(currentLabel, currentNode.getX(), currentNode.getY());


            }

        }

        grid.setStyle("-fx-stroke: black");
        grid.setStyle("-fx-stroke-width: 2");

        return grid;

    }

    public Label createLabel(Node node){
        Label newLabel = new Label();
        newLabel.setPrefSize(20,20);
        newLabel.setStyle("-fx-background-color: black");
        newLabel.setStyle("-fx-border-width: 1");

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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = buildGrid();
        System.out.println(grid.toString());
        grid.setGridLinesVisible(true);
        grid.setStyle("-fx-stroke: black");
        Group root = new Group(grid);
        Scene scene = new Scene(root);
        grid.setAlignment(Pos.CENTER);

        primaryStage.setTitle("Board Visualization.");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
