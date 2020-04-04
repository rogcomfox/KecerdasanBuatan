package utp;

import java.util.*;

class Node {

    int[][] position;
    int gn, fn;
    int hn;
    Node parent;

    Node(int[][] pos) {
        this.position = pos;
    }

    int h(Node goal) {
        hn = 0;
        int start;
        int i;
        int j;
        for (start = 0; start < 9; start++) {
            int u1 = 0;
            int u2 = 0;
            int v1 = 0;
            int v2 = 0;

            for (i = 0; i < 3; i++) {
                for (j = 0; j < 3; j++) {
                    if (this.position[i][j] == start) {
                        u1 = i;
                        v1 = j;
                    }
                    if (goal.position[i][j] == start) {
                        u2 = i;
                        v2 = j;
                    }
                }
            }
            hn += Math.abs(u1 - u2) + Math.abs(v1 - v2);
        }
        return hn;
    }

    int f(Node goal) {
        return this.gn + h(goal);
    }

    void move(int x1, int y1, int x2, int y2) {
        this.position[x1][y1] = this.position[x2][y2];
        this.position[x2][y2] = 0;
    }

    void setParent(Node parent) {
        this.parent = parent;
    }

    boolean isSameState(int[][] A, int[][] B) {
        boolean hasil = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (A[i][j] != B[i][j]) {
                    hasil = false;
                    break;
                }
            }
        }
        return hasil;
    }

    int[][] copyState() {
        int[][] copy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(this.position[i], 0, copy[i], 0, 3);
        }
        return copy;
    }
}

class Search {

    ArrayList<Node> generateChild(Node curr) {
        ArrayList<Node> childs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (curr.position[i][j] == 0) {
                    if (j + 1 < 3) {
                        int[][] state = curr.copyState();
                        Node b = new Node(state);
                        b.move(i, j, i, j + 1);
                        childs.add(b);
                    }
                    if (j - 1 > -1) {
                        int[][] state = curr.copyState();
                        Node c = new Node(state);
                        c.move(i, j, i, j - 1);
                        childs.add(c);
                    }
                    if (i + 1 < 3) {
                        int[][] state = curr.copyState();
                        Node d = new Node(state);
                        d.move(i, j, i + 1, j);
                        childs.add(d);
                    }
                    if (i - 1 > -1) {
                        int[][] state = curr.copyState();
                        Node e = new Node(state);
                        e.move(i, j, i - 1, j);
                        childs.add(e);
                    }
                    break;
                }
            }
        }
        return childs;
    }

    List<Node> aStarFinding(Node init, Node goal) {
        List<Node> pilihan = new ArrayList<>();
        List<Node> cariLuas = new ArrayList<>();
        List<Node> buka = new ArrayList<>();
        List<Node> tutup = new ArrayList<>();
        init.h(goal);
        buka.add(init);
        System.out.println("Pilih Alur :\n");
        while (!buka.isEmpty()) {
            int fn = 10000;
            Node awal = init;
            for (Node node : buka) {
                if (node.fn < fn) {
                    fn = node.fn;
                    awal = node;
                }
            }
            buka.remove(awal);
            tutup.add(awal);
            if (awal.isSameState(awal.position, goal.position)) {
                Node sekarang = awal;
                do {
                    pilihan.add(sekarang);
                    sekarang = sekarang.parent;
                } while (sekarang != null && sekarang != init);
                pilihan.add(sekarang);
                break;
            }
            boolean status = true;
            for (Node n : cariLuas) {
                if (n.isSameState(awal.position, n.position)) {
                    status = false;
                }
            }
            if (status) {
                cariLuas.add(awal);
            }
            ArrayList<Node> child = generateChild(awal);
            for (Node n : child) {
                n.setParent(awal);
                if (tutup.contains(n))
                    continue;
                n.gn = awal.gn + 1;
                n.fn = n.f(goal);
                if (buka.contains(n))
                    continue;
                buka.add(n);
            }
        }
        return pilihan;
    }

    public void printState(int[][] position) {
        for (int i = 0; i < 3; i++) {
            System.out.print("\n|");
            for (int j = 0; j < 3; j++) {
                String str = (position[i][j] != 0) ? Integer.toString(position[i][j]) : " ";
                System.out.print(str + "|");
            }
        }
    }

    void printPath(Node init, Node goal) {
        List<Node> path = aStarFinding(init, goal);
        Collections.reverse(path);
        for (Node n : path) {
            printState(n.position);
            System.out.println();
        }
        System.out.println("SELESAI YOOOOWWW");
    }
}

public class Puzzle {

    public static void main(String[] args) {
        // 0 MERUPAKAN TANDA TIDAK ADA ANGKA DI TILE TERSEBUT
        int[][] init = {{2, 4, 3}, {1, 5, 6}, {7, 0, 8}};
        int[][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Node Initial = new Node(init);
        Node Goal = new Node(goal);
        Search find = new Search();
        find.printPath(Initial, Goal);
    }
}
