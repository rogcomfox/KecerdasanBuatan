package bab5;

import java.util.LinkedList;
import java.util.Objects;

class Area {
    Area pre;
    String nama, warna;
    Edge[] tetangga;
    int h, level;

    public Area(String nama, String warna) {
        this.nama = nama;
        this.warna = warna;
    }

    public void setTetangga(Edge[] tetangga) {
        this.tetangga = tetangga;
        h = this.tetangga.length;
    }

    @Override
    public String toString() {
        return String.format("Nama\t: %-20sWarna: %s", nama, warna);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Area other = (Area) obj;
        return nama.equalsIgnoreCase(other.nama) && warna.equalsIgnoreCase(other.warna);
    }

}

class Edge {
    final Area target;

    public Edge(Area tujuan) {
        this.target = tujuan;
    }

}

public class ConstraintSatisfactionProblem {
    LinkedList<Area> rute = new LinkedList<>();

    public void start(Area[] data, String[] warna, String wAwal) {
        LinkedList<Area> Dup = new LinkedList<>();
        LinkedList<Area> Stack = new LinkedList<>();
        boolean selesai = false;
        int i = 0;
        Area awal = nextMRV(data, i);
        Objects.requireNonNull(awal).warna = wAwal;
        awal.level = i;
        Stack.add(awal);
        Dup.add(awal);
        while (!Stack.isEmpty() && !selesai) {
            Area curr = Stack.pop();
            boolean able = true;
            i = curr.level + 1;
            Area next = nextMRV(data, i);
            if (next != null) {
                String nextWarna = warna[0];
                for (Edge tetangga : next.tetangga) {
                    for (int j = 0; j < warna.length; j++) {
                        if (!tetangga.target.warna.equalsIgnoreCase(nextWarna)) {
                            able = true;
                        } else {
                            able = false;
                            if (j + 1 < warna.length) {
                                nextWarna = warna[j + 1];
                            } else {
                                nextWarna = warna[0];
                            }
                        }
                    }
                }
                if (able) {
                    next.warna = nextWarna;
                    next.pre = curr;
                    next.level = i;
                    checkDup(Stack, Dup, next);
                }
            } else {
                selesai = true;
                makePath(curr);
            }
        }
        if (!selesai) {
            System.out.println("Tidak dapat diselsaikan dengan " + warna.length + " warna");
        }
    }

    private void checkDup(LinkedList<Area> asli, LinkedList<Area> Dup, Area cek) {
        if (!Dup.contains(cek)) {
            asli.push(cek);
            Dup.push(cek);
        }
    }

    private void makePath(Area goal) {
        rute.push(goal);
        Area path = goal;
        while (path.pre != null) {
            rute.push(path.pre);
            path = path.pre;
        }
    }

    public void getPath() {
        if (!rute.isEmpty()) {
            System.out.println("----------------------------------------------------------");
            System.out.println("Urutan Solusi Langkah");
            System.out.println("----------------------------------------------------------");
            while (!rute.isEmpty()) {
                System.out.println(rute.pop());
            }
            System.out.println("----------------------------------------------------------");
        }
    }

    private Area nextMRV(Area[] data, int i) {
        try {
            for (int j = i + 1; j < data.length; j++) {
                if (data[i].h < data[j].h) {
                    Area temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
            return data[i];
        } catch (Exception e) {
            return null;
        }
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println("----------------------------------------------------------");
        System.out.println("                       CSP PROBLEM");
        System.out.println("----------------------------------------------------------");
        String[] nama = {"Lampung", "Sumatera Selatan", "Bengkulu", "Jambi", "Sumatera Barat", "Riau", "Sumatera Utara", "Aceh"};
        Area[] n = new Area[nama.length];
        for (int i = 0; i < n.length; i++) {
            n[i] = new Area(nama[i], "");
        }

        n[0].setTetangga(new Edge[]{
                new Edge(n[1])});
        n[1].setTetangga(new Edge[]{
                new Edge(n[2]),
                new Edge(n[3]),
                new Edge(n[0])});
        n[2].setTetangga(new Edge[]{
                new Edge(n[1]),
                new Edge(n[3]),
                new Edge(n[4])});
        n[3].setTetangga(new Edge[]{
                new Edge(n[1]),
                new Edge(n[2]),
                new Edge(n[4]),
                new Edge(n[5])});
        n[4].setTetangga(new Edge[]{
                new Edge(n[2]),
                new Edge(n[3]),
                new Edge(n[5]),
                new Edge(n[6])});
        n[5].setTetangga(new Edge[]{
                new Edge(n[3]),
                new Edge(n[4]),
                new Edge(n[6])});
        n[6].setTetangga(new Edge[]{
                new Edge(n[4]),
                new Edge(n[5]),
                new Edge(n[7])});
        n[7].setTetangga(new Edge[]{
                new Edge(n[6])});
        System.out.println("Problem    : Mewarnai Peta");
        System.out.println("Constraint : Area yang bertetangga tidak bewarna sama");
        String[] warna = {"Merah", "Hijau", "Kuning"};
        ConstraintSatisfactionProblem test = new ConstraintSatisfactionProblem();
        test.start(n, warna, warna[0]);
        test.getPath();
    }
}
