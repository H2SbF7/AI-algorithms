/*
    Nguyen Tuan Dang
    Faculty of Information Technology, Saigon University
    dangnt@sgu.edu.vn
*/

package TwoWaterJugsProblem;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
        
public class BFS4 {
    public static int MAX_JUG1, MAX_JUG2, GOAL;
    
    public static Queue<Vertex> queue = new LinkedList<>();
    
    public static Set<Vertex> visited = new HashSet<Vertex>(){
        public boolean contains(Object obj) {
            Vertex vertex = (Vertex) obj;
        
            for (Vertex v : this) {
                if (vertex.equals(v)) {
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
        
	Vertex initialVertex = new Vertex(new State(0,0));
        queue.add(initialVertex);      
        visited.add(initialVertex);
        int count = 0;
        boolean flag = false;
        System.out.println("Breadth-First Search");
        System.out.printf("%-12s%-12s%-24s%-24s\n","Step","X","Open","Close");       
        System.out.printf("%-12d", count);	
        System.out.printf("%-12s", "[]");
        System.out.print(initialVertex);	
        System.out.printf("%24s\n", "[]");
	while(!queue.isEmpty()){
            Vertex currentVertex = queue.poll();                                                          
            count++;
            if(currentVertex.getState().getJug1() == GOAL || currentVertex.getState().getJug2() == GOAL){
            	System.out.println("------------------------------------------------------");
            	System.out.println("GOAL");            	
                System.out.println("Step: "+ count);
            	currentVertex.tracePath().printPath();                                                   
            	System.out.println("------------------------------------------------------");
            	flag = true;
                continue;
            }            
            
            ArrayList<Vertex> newVertices = new ArrayList<>();
            
            newVertices.add(currentVertex.full_jug1());
            newVertices.add(currentVertex.full_jug2());
            newVertices.add(currentVertex.empty_jug1()); 
            newVertices.add(currentVertex.empty_jug2());                        
            newVertices.add(currentVertex.pour_jug1_jug2());
            newVertices.add(currentVertex.pour_jug2_jug1());
            System.out.printf("%-12d", count);
            System.out.print(currentVertex);
            System.out.printf("%5s"," ");
            for (Vertex newVertex : newVertices){                                    
                if(!currentVertex.tracePath().getPath().contains(newVertex)){
                    newVertex.setParent(currentVertex);
                    
                    if (!visited.contains(newVertex)){
                        queue.add(newVertex);                   
                        visited.add(newVertex);       
                    }
                }                                         
            }  
            System.out.print(queue);
        	System.out.printf("%-10s"," ");
        	System.out.println(visited);
	}
	if (flag == false)
		System.out.println("Not found a solution!");
    }        
}
