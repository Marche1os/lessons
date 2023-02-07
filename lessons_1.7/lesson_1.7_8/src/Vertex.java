class Vertex {
    public int Value;

    public Vertex(int val) {
        Value = val;
    }
}

class SimpleGraph {
    Vertex[] vertex;
    int[][] m_adjacency;
    int max_vertex;
    int countVertex;

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
        countVertex = 0;
    }

    public void AddVertex(int value) {
        if (countVertex == max_vertex)
            return;

        for (int i = 0; i < max_vertex; i++) {
            if (vertex[i] == null) {
                vertex[i] = new Vertex(value);
                countVertex++;
                return;
            }
        }
    }

    public void RemoveVertex(int v) {
        if (v >= max_vertex)
            return;

        vertex[v] = null;

        for (int i = 0; i < max_vertex; i++)
            m_adjacency[i][v] = 0;

        for (int j = 0; j < max_vertex; j++)
            m_adjacency[v][j] = 0;

        countVertex--;
    }

    public boolean IsEdge(int v1, int v2) {
        if (v1 >= max_vertex || v2 >= max_vertex)
            return false;

        boolean isEdgeFromV1toV2 = m_adjacency[v1][v2] == 1;
        boolean isEdgeFromV2toV1 = m_adjacency[v2][v1] == 1;

        return isEdgeFromV1toV2 && isEdgeFromV2toV1;
    }

    public void AddEdge(int v1, int v2) {
        if (v1 >= max_vertex || v2 >= max_vertex)
            return;

        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge(int v1, int v2) {
        if (v1 >= max_vertex || v2 >= max_vertex)
            return;

        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
    }
}