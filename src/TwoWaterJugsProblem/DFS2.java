package TwoWaterJugsProblem;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Stack;

public class DFS2 {
    public static int MAX_JUG1, MAX_JUG2, GOAL;
    public static Stack<Vertex> stack = new Stack<>();

    public static Set<Vertex> visited = new HashSet<Vertex>(){
        public boolean contains(Object obj) {
            Vertex vertex = (Vertex) obj;
        
            for (Vertex v : this) {
                if ((vertex.equals(v)) && (vertex.tracePath().equals(v.tracePath()))) {
                    return true;
                }
            }
        
            return false;
        }
    };

    public static void main(String[] args) {
        MAX_JUG1 = 4;
        MAX_JUG2 = 3;
        GOAL = 2;

        Vertex.setMaxJugsCapacity(MAX_JUG1, MAX_JUG2);
        
	    Vertex initialVertex = new Vertex(new State(0, 0));

        stack.add(initialVertex);
        visited.add(initialVertex);

        while (!stack.isEmpty()){
            Vertex currentVertex = stack.pop();

            if(currentVertex.getState().getJug1() == GOAL || currentVertex.getState().getJug2() == GOAL){
                
                currentVertex.tracePath().printPath(); 
                continue;
            } 

            ArrayList<Vertex> newVertices = new ArrayList<>();

            newVertices.add(currentVertex.full_jug1());
            newVertices.add(currentVertex.full_jug2());
            newVertices.add(currentVertex.empty_jug1());
            newVertices.add(currentVertex.empty_jug2());
            newVertices.add(currentVertex.pour_jug1_jug2());
            newVertices.add(currentVertex.pour_jug2_jug1());

            for (Vertex newVertex : newVertices){  
                
                if(!currentVertex.tracePath().getPath().contains(newVertex)){                    
                    newVertex.setParent(currentVertex);
                     
                    if (!visited.contains(newVertex)){
                        stack.add(newVertex);                   
                        visited.add(newVertex);       
                    }
                } 
            }
        }
    }
}
