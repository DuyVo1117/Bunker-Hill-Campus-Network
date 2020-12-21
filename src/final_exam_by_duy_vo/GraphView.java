package final_exam_by_duy_vo;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class GraphView extends Pane {

    private Graph<? extends Displayable> graph;

    public GraphView(Graph<? extends Displayable> graph) {
        this.graph = graph;

        // Draw vertices
        java.util.List<? extends Displayable> vertices = graph.getVertices();

        for (int i = 0; i < graph.getSize(); i++) {
            int x = vertices.get(i).getX();
            int y = vertices.get(i).getY();
            String name = vertices.get(i).getName();

            getChildren().add(new Circle(x, y, 16)); // Display a vertex
            getChildren().add(new Text(x - 8, y - 18, name));
        }

        // Draw edges for pairs of vertices
        for (int i = 0; i < graph.getSize(); i++) {
            java.util.List<Integer> neighbors = graph.getNeighbors(i);
            int x1 = graph.getVertex(i).getX();
            int y1 = graph.getVertex(i).getY();
            for (int v : neighbors) {
                int x2 = graph.getVertex(v).getX();
                int y2 = graph.getVertex(v).getY();

                // Draw an edge for (i, v) 
                Line line = new Line(x1, y1, x2, y2);
                line.setStrokeWidth(5);
                getChildren().add(line);
            }
        }
    }

    public GraphView(Graph<? extends Displayable> graph, int s, int e) {

        this.graph = graph;

        UnweightedGraph.SearchTree tree = graph.bfs(e); // get shortest path 
        java.util.List<? extends Displayable> path = tree.getPath(s); // get shortest path from starting campus to ending campus and put to the list

        // Draw vertices
        java.util.List<? extends Displayable> vertices = graph.getVertices();

        for (int i = 0; i < graph.getSize(); i++) {
            int x = vertices.get(i).getX();
            int y = vertices.get(i).getY();
            String name = vertices.get(i).getName();

            getChildren().add(new Circle(x, y, 16)); // Display a vertex
            getChildren().add(new Text(x - 8, y - 18, name));

        }

        // Draw edges for pairs of vertices
        for (int i = 0; i < graph.getSize(); i++) {
            java.util.List<Integer> neighbors = graph.getNeighbors(i);
            int x1 = graph.getVertex(i).getX();
            int y1 = graph.getVertex(i).getY();
            for (int v : neighbors) {
                int x2 = graph.getVertex(v).getX();
                int y2 = graph.getVertex(v).getY();

                // Draw an edge for (i, v)
                Line line = new Line(x1, y1, x2, y2);
                line.setStrokeWidth(5);
                getChildren().add(line);
            }
        }

        for (Displayable n : path) {
            boolean flag = true;
            int i = 0;
            while (flag) {
                int x1 = path.get(i).getX();
                int y1 = path.get(i).getY();;
                int x2 = path.get(i + 1).getX();
                int y2 = path.get(i + 1).getY();

                Line line = new Line(x1, y1, x2, y2);
                line.setStrokeWidth(5);
                line.setFill(Color.RED);
                line.setStroke(Color.RED);
                getChildren().add(line);

                i++;

                if (i == path.size() - 1) {
                    flag = false;
                }
            }
        }

    }
}
