package graphColoring;

public class WelshPowell {

    public static int[] colorGraph(int[][] graph, int numVertices) {
        int[] degrees = new int[numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                degrees[i] += graph[i][j];
            }
        }

        int[] vertexOrder = new int[numVertices];

        for (int i = 0; i < numVertices; i++) {
            vertexOrder[i] = i;
        }
        for (int i = 0; i < numVertices - 1; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (degrees[vertexOrder[i]] < degrees[vertexOrder[j]]) {
                    int temp = vertexOrder[i];
                    vertexOrder[i] = vertexOrder[j];
                    vertexOrder[j] = temp;
                }
            }
        }

        int[] colors = new int[numVertices];
        boolean[] usedColors = new boolean[numVertices];
        int numColors = 0;

        for (int i = 0; i < numVertices; i++) {
            int vertex = vertexOrder[i];
            if (colors[vertex] == 0) {
                numColors++;
                usedColors = new boolean[numVertices];
                for (int j = 0; j < numVertices; j++) {
                    if (graph[vertex][j] == 1 && colors[j] > 0) {
                        usedColors[colors[j] - 1] = true;
                    }
                }
                int color = 1;
                while (usedColors[color - 1]) {
                    color++;
                }
                colors[vertex] = color;
            }
        }

        return colors;
    }
    
    public static void main(String[] args) {

        int[][] graph = {
            {0,0,1,1,0,1,1,0,1,0,0,0},
            {0,0,0,0,0,0,0,1,0,0,0,1},
            {1,0,0,1,0,0,1,0,0,0,0,0},
            {1,0,1,0,0,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,1,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0},
            {1,0,1,1,0,0,0,0,0,0,0,0},
            {0,1,0,0,0,0,0,0,0,0,1,1},
            {1,0,0,0,0,1,0,0,0,0,0,0},
            {0,0,0,0,1,0,0,0,0,0,0,1},
            {0,1,0,0,0,0,0,1,0,0,0,1},
            {0,1,0,0,1,0,0,1,0,1,1,0}
        };
        int numVertices = graph.length;

        int[] colors = colorGraph(graph, numVertices);

        System.out.println("Vertex\tColor");
        for (int i = 0; i < numVertices; i++) {
            System.out.println(i + "\t" + colors[i]);
        } 
    }

}

