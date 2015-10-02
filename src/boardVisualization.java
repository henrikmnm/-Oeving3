/**
 * Created by henrikmnm on 02.10.15.
 */

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class boardVisualization extends Application {

    private routeFinder routefinder = new routeFinder();



    public void buildGrid(){
        ArrayList<ArrayList<Node>> board = routefinder.getBoard();

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);

        for (int i = 0; i < board.size(); i++) {
            ArrayList<Node> currentList = board.get(i);
            for (int j = 0; j < currentList.size(); j++) {
                Label currentLabel = new Label();


            }


            grid.addRow();
        }

    }
    public Label createLabel(Node node){
        Label newLabel = new Label();

        switch (node.getBoardchar()){
            case '.': newLabel;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
