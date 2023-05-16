package TwoWaterJugsProblem;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
        
public class BFSTable {
    public static int MAX_JUG1, MAX_JUG2, GOAL;
    
    public static Queue<Vertex> queue = new LinkedList<>();
    
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
        queue.add(initialVertex);      
        visited.add(initialVertex);

        System.out.println("Two Water Jugs Problem Using Breath-First Search:");
        System.out.print("Step \t    X");
        System.out.format("\t  %-22s  %-20s", "Open", "Close");
        System.out.println();
        System.out.print("0 \t|  ");
        System.out.format("\t| %-22s| %-20s", initialVertex, "[]");
        System.out.println();

	    while(!queue.isEmpty()){
            Vertex currentVertex = queue.poll();
            index++;

            if(currentVertex.getState().getJug1() == GOAL || currentVertex.getState().getJug2() == GOAL){
                System.out.println("At " + index + ": GOAL REACHED."); 
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

            System.out.print(index + "\t| " +currentVertex);

            for (Vertex newVertex : newVertices){   
                if(!currentVertex.tracePath().getPath().contains(newVertex)){                    
                    newVertex.setParent(currentVertex);
                    
                    if (!visited.contains(newVertex)){
                        queue.add(newVertex);                   
                        visited.add(newVertex);       
                    }
                }
            }
            System.out.format("\t| %-22s| %-20s", queue, visited);
            System.out.println();
	    }
    }        
}