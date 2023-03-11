import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleGraphTest {
    private final static int SIZE = 5;
    private SimpleGraph graph;

    @BeforeEach
    void generateTestData() {
        graph = new SimpleGraph(SIZE);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
    }

    @Test
    void depthFirstSearch() {
        graph.AddEdge(0 , 1);
        graph.AddEdge(1 , 2);
        graph.AddEdge(2 , 3);
        graph.AddEdge(3 , 4);

        graph.AddEdge(1 , 0);
        graph.AddEdge(2 , 1);
        graph.AddEdge(3 , 2);
        graph.AddEdge(4 , 3);

        ArrayList<Vertex> path = graph.DepthFirstSearch(1, 2);

        assertEquals(3, path.size());
    }

    @Test
    void breadthFirstSearch() {
        graph.AddEdge(0 , 1);
        graph.AddEdge(1 , 2);
        graph.AddEdge(2 , 3);
        graph.AddEdge(3 , 4);

        graph.AddEdge(1 , 0);
        graph.AddEdge(2 , 1);
        graph.AddEdge(3 , 2);
        graph.AddEdge(4 , 3);

        ArrayList<Vertex> path = graph.BreadthFirstSearch(1, 2);
        assertEquals(2, path.size());
    }

    @Test
    void weakVertices() {
        final SimpleGraph simpleGraph = new SimpleGraph(9);
        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        simpleGraph.AddVertex(3);
        simpleGraph.AddVertex(4);
        simpleGraph.AddVertex(5);
        simpleGraph.AddVertex(5);
        simpleGraph.AddVertex(6);
        simpleGraph.AddVertex(7);
        simpleGraph.AddVertex(8);

        simpleGraph.AddEdge(0 , 1);
        simpleGraph.AddEdge(0 , 2);
        simpleGraph.AddEdge(1 , 2);
        simpleGraph.AddEdge(1 , 3);
        simpleGraph.AddEdge(2 , 3);
        simpleGraph.AddEdge(3 , 4);
        simpleGraph.AddEdge(4, 5);
        simpleGraph.AddEdge(5, 6);
        simpleGraph.AddEdge(5, 7);
        simpleGraph.AddEdge(6, 7);
        simpleGraph.AddEdge(7, 8);


        ArrayList<Vertex> path = simpleGraph.WeakVertices();
        assertEquals(2, path.size());
    }

    @Test
    void addVertex() {
        int currentValue = 1;
        for (Vertex item : graph.vertex) {
            assertEquals(currentValue++, item.Value);
        }
    }

    @Test
    void addEdge() {
        int i = 0;
        int j = 2;
        assertEquals(0, graph.m_adjacency[i][j]);

        graph.AddEdge(i, j);

        assertEquals(1, graph.m_adjacency[i][j]);
        assertEquals(1, graph.m_adjacency[j][i]);

        assertTrue(graph.IsEdge(i, j));
        assertTrue(graph.IsEdge(j, i));
    }

    @Test
    void deleteEdgeBetweenVertexes() {
        int i = 0;
        int j = 2;

        assertFalse(graph.IsEdge(i, j));
        assertFalse(graph.IsEdge(j, i));

        graph.AddEdge(i, j);

        assertTrue(graph.IsEdge(i, j));
        assertTrue(graph.IsEdge(j, i));

        graph.RemoveEdge(i, j);

        assertEquals(1, graph.vertex[i].Value);
        assertEquals(3, graph.vertex[j].Value);

        assertFalse(graph.IsEdge(i, j));
        assertFalse(graph.IsEdge(j, i));
    }

    @Test
    void deleteVertexWithEdges() {
        int i = 0;
        int j = 2;

        assertFalse(graph.IsEdge(i, j));
        assertFalse(graph.IsEdge(j, i));

        graph.AddEdge(i, j);

        assertTrue(graph.IsEdge(i, j));
        assertTrue(graph.IsEdge(j, i));

        graph.RemoveVertex(i);

        assertNull(graph.vertex[i]);

        assertFalse(graph.IsEdge(i, j));
        assertFalse(graph.IsEdge(j, i));
    }
}
