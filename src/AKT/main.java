package AKT;
import java.util.*;

public class main {
    public static void main(String[] args) {
        Vertex Arad = new Vertex("Arad");
        Vertex Zerind = new Vertex("Zerind");
        Vertex Oradea = new Vertex("Oradea");
        Vertex Sibiu = new Vertex("Sibiu");
        Vertex Faragas = new Vertex("Faragas");
        Vertex Timisoara = new Vertex("Timisoara");
        Vertex Lugoj = new Vertex("Lugoj");
        Vertex Mehadia = new Vertex("Mehadia");
        Vertex Drobeta = new Vertex("Drobeta");
        Vertex Craiova = new Vertex("Craiova");
        Vertex Pitesti = new Vertex("Pitesti");
        Vertex Bucharest = new Vertex("Bucharest");
        Vertex Giurgiu = new Vertex("Giurgiu");
        Vertex Urziceni = new Vertex("Urziceni");
        Vertex Neamt = new Vertex("Neamt");
        Vertex Lasi = new Vertex("Lasi");
        Vertex Vaslui = new Vertex("Vaslui");
        Vertex Hirsova = new Vertex("Hirsova");
        Vertex Eforie = new Vertex("Eforie");
        Vertex RimnicuVilcea = new Vertex("Rimnicu Vilcea");

        Arad.addAdjacentVertex(Timisoara, 118);
        Arad.addAdjacentVertex(Zerind, 75);
        Arad.addAdjacentVertex(Sibiu, 140);
        Zerind.addAdjacentVertex(Oradea, 71);
        Sibiu.addAdjacentVertex(Oradea, 151);
        Timisoara.addAdjacentVertex(Lugoj, 111);
        Lugoj.addAdjacentVertex(Mehadia, 70);
        Mehadia.addAdjacentVertex(Drobeta, 120);
        Drobeta.addAdjacentVertex(Craiova,120);
        Craiova.addAdjacentVertex(RimnicuVilcea, 146);
        Craiova.addAdjacentVertex(Pitesti, 138);
        Sibiu.addAdjacentVertex(RimnicuVilcea, 80);
        Sibiu.addAdjacentVertex(Faragas, 99);
        RimnicuVilcea.addAdjacentVertex(Pitesti, 97);
        Faragas.addAdjacentVertex(Bucharest, 211);
        Bucharest.addAdjacentVertex(Pitesti, 101);
        Bucharest.addAdjacentVertex(Giurgiu, 90);
        Bucharest.addAdjacentVertex(Urziceni, 85);
        Urziceni.addAdjacentVertex(Vaslui, 142);
        Vaslui.addAdjacentVertex(Lasi, 92);
        Lasi.addAdjacentVertex(Neamt, 87);
        Urziceni.addAdjacentVertex(Hirsova, 98);
        Hirsova.addAdjacentVertex(Eforie, 86);

        ArrayList<Vertex> goal = new ArrayList<Vertex>();
        goal.add(Bucharest);

        AKT akt = new AKT(goal);
        akt.AKTSearch(Arad); 
    }
}
