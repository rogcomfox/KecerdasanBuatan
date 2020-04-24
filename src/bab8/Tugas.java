package bab8;

import java.util.Scanner;

public class Tugas {

    int[] umur = {20, 30, 20, 25, 31, 27, 38, 29, 30, 22};
    int[] tinggi = {167, 167, 170, 170, 166, 180, 173, 168, 196, 179};
    int[] speed = {87, 80, 70, 75, 61, 78, 63, 50, 70, 64};
    int[] drible = {78, 70, 80, 55, 60, 80, 70, 60, 63, 72};
    int[] shooting = {86, 70, 65, 50, 78, 88, 68, 70, 58, 71};
    String[] kelas = {"Layak", "Layak", "Layak", "Layak", "Tidak Layak", "Layak", "Tidak Layak", "Layak", "Tidak Layak", "Layak"};

    public double RataRata(int[] x) {
        double total = 0;
        double ratarata;
        double banyakData = x.length;
        for (int value : x) {
            total = total + value;
        }
        ratarata = total / banyakData;
        return ratarata;
    }

    public double StandarDeviasi(int[] x) {

        double rata = RataRata(x);
        double[] tmp = new double[x.length];
        double total = 0;
        double akhir;
        for (int i = 0; i < x.length; i++) {
            tmp[i] = x[i] - rata;
            tmp[i] = tmp[i]*tmp[i];
        }
        for (double v : tmp) {
            total = total + v;
        }
        total = total / (tmp.length - 1);
        akhir = Math.sqrt(total);
        return akhir;
    }

    public double DistribusiNormal(int[] x, int z) {
        double to = StandarDeviasi(x);
        double rata = RataRata(x);
        double atas = 1;
        double bawah;
        double pangkat;
        double pAtas;
        double pBawah;

        final double pi = 3.14;
        final double eular = 2.71;
        double akhir;

        bawah = to * Math.sqrt((2 * pi));

        pAtas = Math.pow((z - rata), 2);

        pBawah = 2 * (to*to);

        pangkat = pAtas / pBawah;

        akhir = atas/bawah * (1 / Math.pow(eular, pangkat));
        return akhir;
    }

    public double Pecah1(int[] data, int data2) {
        int x = 0;
        for (String kelas1 : kelas) {
            if (kelas1.equalsIgnoreCase("Layak")) {
                x++;
            }
        }
        int[] data1 = new int[x];
        int z = 0;
        for (int i = 0; i < data.length; i++) {
            if (kelas[i].equalsIgnoreCase("Layak")) {
                data1[z] = data[i];
                z++;
            }
        }
        return DistribusiNormal(data1, data2);
    }

    public double Pecah2(int[] data, int data2) {
        int x = 0;
        for (String kelas1 : kelas) {
            if (kelas1.equalsIgnoreCase("Tidak Layak")) {
                x++;
            }
        }
        int[] data1 = new int[x];
        int z = 0;
        for (int i = 0; i < data.length; i++) {
            if (kelas[i].equalsIgnoreCase("Tidak Layak")) {
                data1[z] = data[i];
                z++;
            }
        }

        return DistribusiNormal(data1, data2);
    }

    public void Hitung(int um, int ti, int sp, int dr, int sh) {
        double umurL = Pecah1(umur, um);
        double umurTL = Pecah2(umur, um);
        double tinggiL = Pecah1(tinggi, ti);
        double tinggiTL = Pecah2(tinggi, ti);
        double speedL = Pecah1(speed, sp);
        double speedTL = Pecah2(speed, sp);
        double dribleL = Pecah1(drible, dr);
        double dribleTL = Pecah2(drible, dr);
        double shootingL = Pecah1(shooting, sh);
        double shootingTL = Pecah2(shooting, sh);
        double Layak = umurL * speedL * tinggiL * dribleL * shootingL;
        double TidakLayak = umurTL * speedTL * tinggiTL * dribleTL * shootingTL;
        print(Layak, TidakLayak);
    }

    public void print(double x, double y) {
        System.out.println("Posterior Layak : " + x);
        System.out.println("Posterior Tidak Layak : " + y);
        System.out.println("\nKesimpulan");
        System.out.println();
        if (x > y) {
            System.out.println("Karena Posterior Layak (" + x + ") lebih besar dari \n"
                    + "Posterior Tidak Layak (" + x + ") maka nilai yang \n"
                    + "di inputkan di kategorikan Kelas Layak\n");
        } else {
            System.out.println("Karena Posterior Tidak Layak (" + y + ") lebih besar dari \n"
                    + "Posterior Layak (" + x + ") maka nilai yang di inputkan \n"
                    + "di kategorikan Kelas Tidak Layak");
        }
    }

    public static void main(String[] args) {
        Scanner n = new Scanner(System.in);
        Tugas t = new Tugas();
        int umur;
        int tinggi;
        int speed;
        int drible;
        int shooting;
        System.out.print("Masukkan Umur : ");
        umur = n.nextInt();
        System.out.print("Masukkan Tinggi : ");
        tinggi = n.nextInt();
        System.out.print("Masukkan Speed : ");
        speed = n.nextInt();
        System.out.print("Masukkan Drible : ");
        drible = n.nextInt();
        System.out.print("Masukkan Shooting : ");
        shooting = n.nextInt();
        System.out.println("------------------------------");
        System.out.println("Hasil : ");
        t.Hitung(umur, tinggi, speed, drible, shooting);
    }
}

