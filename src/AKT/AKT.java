package AKT;

import java.util.*;
import java.util.HashSet;
import java.util.Set;
import java.util.PriorityQueue;

public class AKT {
    // heuristic function: straight-line distances to Bucharest
    private int heuristic(Vertex initialVertex){
        switch (initialVertex.getState() + "") {
            case "Arad":
                return 366;
            case "Bucharest":
                return 0;
            case "Craiova":
                return 160;
            case "Zerind":
                return 374;
            case "Timisoara":
                return 329;
            case "Sibiu":
                return 253;
            case "Faragas":
                return 176;
            case "Rimnicu Vilcea":
                return 193;
            case "Pitesti":
                return 100;
            case "Lugoj":
                return 244;
            case "Mehadia":
                return 241;
            case "Drobeta":
                return 242;
            case "Oradea":
                return 380;
            case "Eforie":
                return 161;
            case "Giurgiu":
                return 77;
            case "Hirsova":
                return 151;
            case "Iasi":
                return 226;
            case "Vaslui":
                return 199;
            case "Neamt":
                return 234;
            default: return -1;
        }
    }
    public static ArrayList<Vertex> CLOSED = new ArrayList<Vertex>();
    public static Set<Vertex> destination = new HashSet<Vertex>();

    public AKT(ArrayList<Vertex> destination){
        for(Vertex v: destination){
            this.destination.add(v);
        }
    }

    public void AKTSearch(Vertex initialVertex){
        
        int step = 0;
        PriorityQueue<Vertex> OPEN = new PriorityQueue<>();

        System.out.println("AKT in a simple roadmap of Romania: ");
        //initial state
        System.out.println("Initial state");
        initialVertex.setG(0);
        OPEN.add(initialVertex);
        initialVertex.setF(initialVertex.getG() + heuristic(initialVertex));
        System.out.println("\t" + initialVertex + ": g = " + initialVertex.getG() + ", h = " + heuristic(initialVertex) + ", f = " + initialVertex.getF());
        

        while(!OPEN.isEmpty()){
            
            System.out.println("\n-Step " + step + "-");
            System.out.println("Open: " + OPEN);
            System.out.println("Closed: " + CLOSED);

            step++;

            Vertex currentVertex = OPEN.poll();
            CLOSED.add(currentVertex);

            if(destination.contains(currentVertex)) {
                System.out.println("\nDestination found: " + currentVertex);
                System.out.print("The path is: "); currentVertex.tracePath().printPath();
                System.out.println();
                break;
            }

            System.out.println("Expanding " + currentVertex + "...");

            ArrayList<Vertex> adjacentVertices = currentVertex.getAdjacentVertices();

            for (int i = 0; i < adjacentVertices.size(); i++) {
                Vertex v = adjacentVertices.get(i);

                if((!OPEN.contains(v))) {
                    v.setG(currentVertex.getG() + v.getD());
                    v.setF(heuristic(v) + v.getG());
                    System.out.println("\t" + v + ": g = " + v.getG() + ", h = " + heuristic(v)+ ", f = " + v.getF());
                    OPEN.add(v);
                }
                
            }
        }
    }

    // public static void main(String[] args) {
        // Vertex Arad = new Vertex("Arad");
        // Vertex Zerind = new Vertex("Zerind");
        // Vertex Oradea = new Vertex("Oradea");
        // Vertex Sibiu = new Vertex("Sibiu");
        // Vertex Faragas = new Vertex("Faragas");
        // Vertex Timisoara = new Vertex("Timisoara");
        // Vertex Lugoj = new Vertex("Lugoj");
        // Vertex Mehadia = new Vertex("Mehadia");
        // Vertex Drobeta = new Vertex("Drobeta");
        // Vertex Craiova = new Vertex("Craiova");
        // Vertex Pitesti = new Vertex("Pitesti");
        // Vertex Bucharest = new Vertex("Bucharest");
        // Vertex Giurgiu = new Vertex("Giurgiu");
        // Vertex Urziceni = new Vertex("Urziceni");
        // Vertex Neamt = new Vertex("Neamt");
        // Vertex Lasi = new Vertex("Lasi");
        // Vertex Vaslui = new Vertex("Vaslui");
        // Vertex Hirsova = new Vertex("Hirsova");
        // Vertex Eforie = new Vertex("Eforie");
        // Vertex RimnicuVilcea = new Vertex("Rimnicu Vilcea");

        // Arad.addAdjacentVertex(Zerind, 75);
        // Arad.addAdjacentVertex(Timisoara, 118);
        // Arad.addAdjacentVertex(Sibiu, 140);
        // Zerind.addAdjacentVertex(Oradea, 71);
        // Oradea.addAdjacentVertex(Sibiu, 151);
        // Timisoara.addAdjacentVertex(Lugoj, 111);
        // Lugoj.addAdjacentVertex(Mehadia, 70);
        // Mehadia.addAdjacentVertex(Drobeta, 120);
        // Drobeta.addAdjacentVertex(Craiova,120);
        // Craiova.addAdjacentVertex(RimnicuVilcea, 146);
        // Craiova.addAdjacentVertex(Pitesti, 138);
        // Sibiu.addAdjacentVertex(RimnicuVilcea, 80);
        // Sibiu.addAdjacentVertex(Faragas, 99);
        // RimnicuVilcea.addAdjacentVertex(Pitesti, 97);
        // Faragas.addAdjacentVertex(Bucharest, 211);
        // Pitesti.addAdjacentVertex(Bucharest, 101);
        // Bucharest.addAdjacentVertex(Giurgiu, 90);
        // Bucharest.addAdjacentVertex(Urziceni, 85);
        // Urziceni.addAdjacentVertex(Vaslui, 142);
        // Vaslui.addAdjacentVertex(Lasi, 92);
        // Lasi.addAdjacentVertex(Neamt, 87);
        // Urziceni.addAdjacentVertex(Hirsova, 98);
        // Hirsova.addAdjacentVertex(Eforie, 86);

        // ArrayList<Vertex> goal = new ArrayList<Vertex>();
        // goal.add(Bucharest);

        // AKT akt = new AKT(goal);
        // akt.AKTSearch(Arad);
    // }
}
