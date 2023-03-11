import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

class Vertex {
    public int Value;
    public boolean Hit;

    public Vertex(int val) {
        Value = val;
        Hit = false;
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

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {
        resetVertexHits();

        final Stack<Integer> stack = new Stack<>();
        final ArrayList<Vertex> fullPath = new ArrayList<>();

        int currentIndex = VFrom;
        vertex[currentIndex].Hit = true;
        stack.push(currentIndex);

        while (!stack.isEmpty()) {
            currentIndex = stack.peek();

            if (m_adjacency[currentIndex][VTo] == 1) {
                stack.push(VTo);
                break;
            }

            int i = 0;
            for (; i < max_vertex; i++) {
                Vertex x = vertex[i];
                if (m_adjacency[currentIndex][i] == 1 && !x.Hit) {
                    x.Hit = true;
                    stack.push(i);
                    break;
                }
            }

            if (i == max_vertex)
                stack.pop();
        }

        for (Integer index : stack)
            fullPath.add(vertex[index]);

        return fullPath;
    }

    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo) {
        resetVertexHits();

        final ArrayList<Vertex> fullPath = new ArrayList<>();
        final LinkedList<Integer> queue = new LinkedList<>();
        final int[] ancestors = new int[max_vertex];

        vertex[VFrom].Hit = true;
        queue.addLast(VFrom);
        int currentIndex;

        while (!queue.isEmpty()) {
            currentIndex = queue.pop();

            if (m_adjacency[currentIndex][VTo] == 1) {
                ancestors[VTo] = currentIndex;
                break;
            }

            for (int i = 0; i < max_vertex; i++) {
                Vertex x = vertex[i];
                if (m_adjacency[currentIndex][i] == 1 && !x.Hit) {
                    x.Hit = true;
                    ancestors[i] = currentIndex;
                    queue.addLast(i);
                }
            }
        }

        int dest = VTo;
        fullPath.add(vertex[VTo]);
        while (dest != VFrom) {
            dest = ancestors[dest];
            fullPath.add(0, vertex[dest]);
        }

        return fullPath;
    }

    private void resetVertexHits() {
        for (Vertex v : vertex)
            v.Hit = false;
    }

}