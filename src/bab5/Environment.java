package bab5;

import java.util.Scanner;

public class Environment {
    Scanner input = new Scanner(System.in);
    int np;
    int wp, gp;
    int[] pos;
    int[] b_pos = new int[20];
    int[] s_pos = new int[20];
    String[][] w;
    int sp;

    public Environment(int row, int column){
        w = new String[5][5];
        sp = 13;
        for (int i = 0; i < 20; ++i) {
            b_pos[i] = -1;
            s_pos[i] = -1;
        }
        for (int i = 0; i < 5; ++i)
            for (int j = 0; j < 5; ++j)
                w[i][j] = "";
    }

    public void createProblem() {
        display(0);
        System.out.println("\nAgent akan mulai dari posisi "+sp);
        w[4][1] = "A";

        System.out.print("Masukkan jumlah pits yang diinginkan: ");
        np = input.nextInt();
        pos = new int[np];

        System.out.println("Catatan: Posisi dari pit, gold dan wumpus tidak boleh overlap");
        System.out.println("Masukkan letak posisi pits: ");
        for (int i = 0; i < np; ++i) {
            System.out.print("Pit ke-"+(i+1)+"\t: ");
            pos[i] = input.nextInt();
            showSense(pos[i], 1);
        }

        System.out.print("Masukkan letak posisi wumpus: ");
        wp = input.nextInt();
        showSense(wp, 2);

        System.out.print("Masukkan letak posisi emas: ");
        gp = input.nextInt();
        System.out.println();
        insert();
    }

    public void insert() {
        int temp;
        int count;
        int flag1 = 0, flag2 = 0;
        for (int i = 0; i < np; ++i) {
            temp = pos[i];
            count = 0;
            for (int j = 1; j <= 4; ++j) {
                for (int k = 1; k <= 4; ++k) {
                    ++count;
                    if (count == temp) {
                        w[j][k] += "P";
                    } else {
                        if (count == gp && flag1 == 0) {
                            w[j][k] += "G";
                            flag1 = 1;
                        } else {
                            if (count == wp && flag2 == 0) {
                                w[j][k] += "W";
                                flag2 = 1;
                            }}}}}}
        display(1);
    }

    public void showSense(int a, int b) {
        int left, right, bottom, up;
        left = a - 1;
        right = a + 1;
        bottom = a + 4;
        up = a - 4;

        if (a == 5 || a == 9)   left = 0;
        if (a == 8 || a == 12)  right = 0;
        if (a == 4)             right = 0;
        if (a == 13)            left = 0;
        if (bottom > 16)        bottom = 0;
        if (bottom < 0)         up = 0;

        if (b == 1) {
            b_pos[0] = left;
            b_pos[1] = right;
            b_pos[2] = bottom;
            b_pos[3] = up;
        } else {
            if (b == 2) {
                s_pos[0] = left;
                s_pos[1] = right;
                s_pos[2] = bottom;
                s_pos[3] = up;
            }}

        int temp1, count;
        for (int i = 0; i < 4; ++i) {
            if (b == 1)     temp1 = b_pos[i];
            else    temp1 = s_pos[i];
            count = 0;
            for (int j = 1; j <= 4; ++j) {
                for (int k = 1; k <= 4; ++k) {
                    ++count;
                    if (count == temp1 && b == 1 && !w[j][k].contains("B"))
                        w[j][k] += "B";
                    else {
                        if (count == temp1 && b == 2 && !w[j][k].contains("S"))
                            w[j][k] += "S";
                    }}}}

    }

    public void display(int status) {
        int count = 1;
        if(status==0)   System.out.println("\nPosisi masih dalam keadaan default");
        else    System.out.println("Lingkungan dari wumpus world");
        System.out.println("+===============================================================+");
        for (int i = 1; i <= 4; ++i) {
            System.out.print("|\t");
            for (int j = 1; j <= 4; ++j) {
                if(status==0)
                    System.out.print((count++) + "\t|\t");
                else    System.out.print(w[i][j] + "\t|\t");
            }System.out.println();
            if(i < 4){
                for(int x = 0; x <= 64; x++){
                    if(x==0||x==16||x==32||x==48||x==64)
                        System.out.print("|");
                    else    System.out.print("=");
                }System.out.println();
            }}
        System.out.println("+===============================================================+");
    }

}
