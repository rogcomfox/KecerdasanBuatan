package bab3;

import java.util.*;

class Edge {
    final double gn;
    final Node tujuan;

    public Edge(Node tujuan, double g) {
        this.gn = g;
        this.tujuan = tujuan;
    }

}

class Node {
    double f, g;
    final double h;
    String nama;
    Node pre;
    Edge[] tetangga;

    public Node(String nama, double h) {
        this.h = h;
        this.nama = nama;
        g = 0;
        f = 0;
    }

    public void setTetangga(Edge[] tetangga) {
        this.tetangga = tetangga;
    }

    @Override
    public String toString() {
        return nama;
    }

}

public class InformedSearch {
    LinkedList<Node> rute = new LinkedList<>();
    double finalCost = 0;

    public void start(Node awal, Node target) {
        LinkedList<Node> Explored = new LinkedList<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(20, Comparator.comparingDouble((Node x) -> x.f) //override compare method
        );

        queue.add(awal);
        while (!queue.isEmpty()) {

            Node curr = queue.poll();
            Explored.add(curr);
            if (curr.nama.equals(target.nama)) {
                makePath(curr);
                break;
            }

            for (Edge i : curr.tetangga) {
                Node next = i.tujuan;
                double cost = i.gn;
                double g = cost + curr.g;
                double f = g + next.h;
                System.out.println("----------------------------------------------");
                System.out.println(curr + " -> " + next + " = " + g + " + " + next.h + "\t= " + " " + f);

                if (Explored.contains(next) && f >= next.f) {
                    System.out.println();
                } else if (!queue.contains(next) || f <= next.f) {
                    next.pre = curr;
                    next.g = g;
                    next.f = f;
                    queue.remove(next);
                    queue.add(next);
                }
            }
        }
    }

    private void makePath(Node target) {
        rute.push(target);
        Node path=target;
        while (path.pre != null) {
            rute.push(path.pre);
            path = path.pre;
        }
    }

    public void getPath(){
        System.out.println("----------------------------------------------");
        Node pop;
        System.out.print("Path = [ ");
        while (!rute.getLast().equals(rute.peek())) {
            pop = rute.pop();
            System.out.print(pop + " ("+pop.f+") -> ");
        }
        pop=rute.pop();
        finalCost+=pop.f;
        System.out.print( pop+ "("+pop.f+") ]\n");
        System.out.println("Final Cost (f(n)) total = "+finalCost);
        System.out.println("----------------------------------------------");
    }
}

class Main{
    public static void main(String[] args) {
        String[] nama={"A","B","C","D","E","F","G"};
        double[] hn={78,132,35,0,27,115,69};
        Node[] n =new Node[nama.length];
        for (int i = 0; i < n.length; i++) {
            n[i]=new Node(nama[i],hn[i]);
        }
        //Untuk Kota A
        n[0].setTetangga(new Edge[]{
                new Edge(n[1], 20),     //A -> B
                new Edge(n[6], 30),     //A -> G
                new Edge(n[5], 34)});   //A -> F
        //Untuk Kota B
        n[1].setTetangga(new Edge[]{
                new Edge(n[0], 20),     //B -> A
                new Edge(n[6], 45),     //B -> G
                new Edge(n[2], 78),     //B -> C
                new Edge(n[3], 143),}); //B -> D
        //Untuk Kota C
        n[2].setTetangga( new Edge[]{
                new Edge(n[1], 78),     //C -> B
                new Edge(n[3], 45)});   //C -> D
        //Untuk Kota D
        n[3].setTetangga( new Edge[]{
                new Edge(n[2], 88),     //D -> C
                new Edge(n[4], 92),     //D -> E
                new Edge(n[1], 109),    //D -> B
                new Edge(n[5], 126)});  //D -> F
        //Untuk Kota E
        n[4].setTetangga( new Edge[]{
                new Edge(n[5], 93),     //E -> F
                new Edge(n[3], 92)});   //E -> D
        //Untuk Kota F
        n[5].setTetangga(new Edge[]{
                new Edge(n[4], 93),     //F -> E
                new Edge(n[0], 34),     //F -> A
                new Edge(n[3], 126),    //F -> D
                new Edge(n[6], 60)});   //F -> G
        //Untuk Kota G
        n[6].setTetangga(new Edge[]{
                new Edge(n[0], 30),     //G -> A
                new Edge(n[1], 45),     //G -> B
                new Edge(n[5], 60)});   //G -> F

        InformedSearch search = new InformedSearch();
        search.start(n[0], n[3]);
        search.getPath();
    }
}
