import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
