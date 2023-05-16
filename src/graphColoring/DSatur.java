package graphColoring;

import java.util.*;

public class DSatur {
    
    public static int[] colorGraph(int[][] graph, int numVertices) {
        int[] saturationDegrees = new int[numVertices];
        int[] colors = new int[numVertices];
        int numColors = 0;

        while (numColors < numVertices) {
            int maxSaturationDegree = -1;
            int vertexToColor = -1;
            for (int i = 0; i < numVertices; i++) {
                if (colors[i] == 0) {
                    int saturationDegree = calculateSaturationDegree(graph, i, colors);
                    if (saturationDegree > maxSaturationDegree) {
                        maxSaturationDegree = saturationDegree;
                        vertexToColor = i;
                    }
                }
            }

            numColors++;
            colors[vertexToColor] = numColors;
            saturationDegrees[vertexToColor] = -1;

            for (int i = 0; i < numVertices; i++) {
                if (graph[vertexToColor][i] == 1) {
                    saturationDegrees[i]++;
                }
            }
        }

        return colors;
    }

    private static int calculateSaturationDegree(int[][] graph, int vertex, int[] colors) {
        Set<Integer> coloredNeighbors = new HashSet<>();
        for (int i = 0; i < graph.length; i++) {
            if (graph[vertex][i] == 1 && colors[i] != 0) {
                coloredNeighbors.add(colors[i]);
            }
        }
        return coloredNeighbors.size();
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
