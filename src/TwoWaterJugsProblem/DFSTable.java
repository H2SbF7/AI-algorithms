package TwoWaterJugsProblem;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Stack;

public class DFSTable {
    public static int MAX_JUG1, MAX_JUG2, GOAL;
    public static Stack<Vertex> stack = new Stack<>();

    public static Set<Vertex> visited = new HashSet<Vertex>(){
        public boolean contains(Object obj) {
            Vertex vertex = (Vertex) obj;
        
            for (Vertex v : this) {
                if ((vertex.equals(v))) {
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
        int index = 0;

        Vertex.setMaxJugsCapacity(MAX_JUG1, MAX_JUG2);
        
	    Vertex initialVertex = new Vertex(new State(0, 0));

        stack.add(initialVertex);
        visited.add(initialVertex);

        System.out.println("Two Water Jugs Problem Using Depth-First Search:");
        System.out.print("Step \t    X");
        System.out.format("\t  %-22s  %-20s", "Open", "Close");
        System.out.println();
        System.out.print("0 \t|  ");
        System.out.format("\t| %-22s| %-20s", initialVertex, "[]");
        System.out.println();

        while (!stack.isEmpty()){
            Vertex currentVertex = stack.pop();
            index++;

            if(currentVertex.getState().getJug1() == GOAL || currentVertex.getState().getJug2() == GOAL){
                System.out.println("At " + index + ": GOAL REACHED."); 
                currentVertex.tracePath().printPath(); 
                continue;
            } 

            ArrayList<Vertex> newVertices = new ArrayList<>();

            System.out.print(index + "\t| " +currentVertex);

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
            System.out.format("\t| %-22s| %-20s", stack, visited);
            System.out.println();
        }

       
    }
}
