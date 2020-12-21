/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_exam_by_duy_vo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author duyvo
 */
public class Final_exam_by_Duy_Vo extends Application {

    private Text text1 = new Text("");
    private Text text2 = new Text("");
    private TextField startingCampus = new TextField();
    private TextField endingCampus = new TextField();
    private Button button = new Button("Display Shortest Paths");
    private Label startingLabel = new Label("Starting Campus: ");
    private Label endingLabel = new Label("Ending campus: ");
    private HBox box = new HBox(5);
    private String starting, ending;
    private int startingIndex = -1, endingIndex = -1;

    private Campus[] vertices = {new Campus("Malden", 75, 50),
        new Campus("H_Building", 75, 150),
        new Campus("Charlestown", 75, 250),
        new Campus("Chelsea", 200, 200),
        new Campus("Chinatown", 10, 325),
        new Campus("East_Boston", 200, 325)};

    private int[][] edges = {
        {0, 1, 8}, {0, 3, 13},
        {1, 0, 8}, {1, 2, 2},
        {2, 1, 2}, {2, 4, 7}, {2, 5, 9},
        {3, 0, 13},
        {4, 2, 7},
        {5, 2, 9}
    };

    @Override
    public void start(Stage primaryStage) {

        WeightedGraph<Campus> graph = new WeightedGraph<>(vertices, edges);
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10);

        text1.setFont(font);
        text1.setTranslateX(15);
        text1.setTranslateY(125);
        text1.setFill(Color.BROWN);
        text1.maxWidth(580);
        text1.setWrappingWidth(580);
        text2.setFont(font);
        text2.setTranslateX(15);
        text2.setTranslateY(137);
        text2.setFill(Color.BROWN);
        text2.maxWidth(580);
        text2.setWrappingWidth(580);

        button.setTranslateX(250);
        button.setTranslateY(75);

        box.setPadding(new Insets(25, 5, 5, 50));
        box.getChildren().addAll(startingLabel, startingCampus, endingLabel, endingCampus);

        Group root = new Group(box, button);
        GraphView graphView = new GraphView(graph);

        BorderPane pane = new BorderPane();
        pane.setCenter(graphView);
        pane.setBottom(root);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Bunker Hill Campus Network"); // Set the stage title      
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                starting = startingCampus.getText();
                ending = endingCampus.getText();

                String wrongStartingName = null;
                String wrongEndingName = null;

                for (int i = 0; i < vertices.length; i++) {
                    if (starting.equals(vertices[i].getName())) {
                        startingIndex = i;
                        break;
                    } else {
                        wrongStartingName = starting;
                    }
                }

                for (int i = 0; i < vertices.length; i++) {
                    if (ending.equals(vertices[i].getName())) {
                        endingIndex = i;
                        break;
                    } else {
                        wrongEndingName = ending;
                    }
                }

                boolean flag = false;
                if (startingIndex == -1) {
                    flag = true;
                    text1.setText("We don't have any starting campus name: " + wrongStartingName + ". Try Again.");
                    Group root = new Group(box, button, text1);
                    GraphView graphView = new GraphView(graph);
                    BorderPane pane = new BorderPane();
                    pane.setCenter(graphView);
                    pane.setBottom(root);

                    Scene scene = new Scene(pane);
                    primaryStage.setTitle("Bunker Hill Campus Network"); // Set the stage title      
                    primaryStage.setScene(scene); // Place the scene in the stage
                    primaryStage.show(); // Display the stage
                }

                if (endingIndex == -1) {
                    text2.setText("We don't have any ending campus name: " + wrongEndingName + ". Try Again.");
                    Group root;
                    if (flag == true) {
                        root = new Group(box, button, text1, text2);
                    } else {
                        root = new Group(box, button, text2);
                    } 

                    GraphView graphView = new GraphView(graph);
                    BorderPane pane = new BorderPane();
                    pane.setCenter(graphView);
                    pane.setBottom(root);

                    Scene scene = new Scene(pane);
                    primaryStage.setTitle("Bunker Hill Campus Network"); // Set the stage title      
                    primaryStage.setScene(scene); // Place the scene in the stage
                    primaryStage.show(); // Display the stage
                }

                if (startingIndex != -1 && endingIndex != -1) {

                    Group root = new Group(box, button);
                    GraphView graphView = new GraphView(graph, startingIndex, endingIndex);
                    BorderPane pane = new BorderPane();
                    pane.setCenter(graphView);
                    pane.setBottom(root);

                    Scene scene1 = new Scene(pane);
                    primaryStage.setTitle("Bunker Hill Campus Network"); // Set the stage title      
                    primaryStage.setScene(scene1); // Place the scene in the stage
                    primaryStage.show(); // Display the stage
                }

                startingIndex = -1;
                endingIndex = -1;
            }
        });
    }

    static class Campus implements Displayable {

        private int x, y;
        private String name;

        Campus(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    /**
     * The main method is only needed for the IDE with limited JavaFX support.
     * Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
