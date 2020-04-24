package bab5;

public class Wumpus {
    static int scream = 0;
    static int score = 0;
    static int complete = 0;
    Environment e;

    public Wumpus(Environment e){
        this.e = e;
    }

    public boolean check(Tiles t) {
        int temp = t.sense();
        return !(temp == 1 || temp == 2);
    }

    public void findSolution(){
        Tiles[] t = new Tiles[17];
        int c = 1;
        out:
        for (int i = 1; i < 5; ++i) {
            for (int j = 1; j < 5; ++j) {
                if (c > 16) {
                    break out;
                }
                t[c] = new Tiles(e.w[i][j], c);
                ++c;
            }
        }

        t[13].safe = 1;
        t[13].visited = 1;

        int pos = 13;
        int condition;
        int limit = 0;
        String temp1;

        System.out.println("\nMulai dari posisi\t\t: "+e.sp);

        do {
            ++limit;

            if (t[pos].env.contains("G")) {
                complete = 1;
                System.out.println("Pencarian selesai, Emas telah ditemukan!!\n");
                break;
            }

            if (t[pos].br != 1 && t[pos].r != 1 && t[pos + 1].doubt_pit < 1 && t[pos + 1].doubt_wump < 1 && t[pos + 1].pit != 1 && t[pos + 1].wump != 1 && !(t[pos].back.contains("r") && (t[pos].l != 1 || t[pos].u != 1 || t[pos].d != 1) && check(t[pos]))) {
                temp1 = "l";
                t[pos].r = 1;
                ++pos;
                System.out.println("→ Geser ke kanan, posisi\t: " + pos);
                ++score;
                t[pos].back += temp1;
                condition = t[pos].sense();
                if (condition == 3) {
                    complete = 1;
                    break;
                } else {
                    if (condition == 1 && t[pos].visited == 0) {
                        if (t[pos].br != 1 && t[pos + 1].safe != 1) {
                            t[pos + 1].doubt_pit += 1;
                        }
                        if (t[pos].bu != 1 && (pos - 4) >= 1 && t[pos - 4].safe != 1) {
                            t[pos - 4].doubt_pit += 1;
                        }
                        if (t[pos].bl != 1 && t[pos - 1].safe != 1) {
                            t[pos - 1].doubt_pit += 1;
                        }
                        if (t[pos].bd != 1 && (pos + 4) <= 16 && t[pos + 4].safe != 1) {
                            t[pos + 4].doubt_pit += 1;
                        }

                        t[pos].safe = 1;
                    } else {
                        if (condition == 2 && t[pos].visited == 0) {
                            if (t[pos].br != 1 && t[pos + 1].safe != 1) {
                                t[pos + 1].doubt_wump += 1;
                            }
                            if (t[pos].bu != 1 && (pos - 4) >= 1 && t[pos - 4].safe != 1) {
                                t[pos - 4].doubt_wump += 1;
                            }
                            if (t[pos].bl != 1 && t[pos - 1].safe != 1) {
                                t[pos - 1].doubt_wump += 1;
                            }
                            if (t[pos].bd != 1 && (pos + 4) <= 16 && t[pos + 4].safe != 1) {
                                t[pos + 4].doubt_wump += 1;
                            }

                            t[pos].safe = 1;
                        } else {
                            if (condition == 0) {
                                t[pos].safe = 1;
                            }
                        }
                    }
                }

                t[pos].visited = 1;
            } else {
                if (t[pos].bl != 1 && t[pos].l != 1 && t[pos - 1].doubt_pit < 1 && t[pos - 1].doubt_wump < 1 && t[pos - 1].pit != 1 && t[pos - 1].wump != 1 && !(t[pos].back.contains("l") && (t[pos].r != 1 || t[pos].u != 1 || t[pos].d != 1) && check(t[pos]))) {
                    temp1 = "r";
                    t[pos].l = 1;
                    pos = pos - 1;
                    System.out.println("← Geser ke kiri, posisi\t\t: " + pos);
                    ++score;
                    t[pos].back += temp1;
                    condition = t[pos].sense();
                    if (condition == 3) {
                        complete = 1;
                        break;
                    } else {
                        if (condition == 1 && t[pos].visited == 0) {
                            if (t[pos].br != 1 && t[pos + 1].safe != 1) {
                                t[pos + 1].doubt_pit += 1;
                            }
                            if (t[pos].bu != 1 && (pos - 4) >= 1 && t[pos - 4].safe != 1) {
                                t[pos - 4].doubt_pit += 1;
                            }
                            if (t[pos].bl != 1 && t[pos - 1].safe != 1) {
                                t[pos - 1].doubt_pit += 1;
                            }
                            if (t[pos].bd != 1 && (pos + 4) <= 16 && t[pos + 4].safe != 1) {
                                t[pos + 4].doubt_pit += 1;
                            }

                            t[pos].safe = 1;
                        } else {
                            if (condition == 2 && t[pos].visited == 0) {
                                if (t[pos].br != 1 && t[pos + 1].safe != 1) {
                                    t[pos + 1].doubt_wump += 1;
                                }
                                if (t[pos].bu != 1 && (pos - 4) >= 1 && t[pos - 4].safe != 1) {
                                    t[pos - 4].doubt_wump += 1;
                                }
                                if (t[pos].bl != 1 && t[pos - 1].safe != 1) {
                                    t[pos - 1].doubt_wump += 1;
                                }
                                if (t[pos].bd != 1 && (pos + 4) <= 16 && t[pos + 4].safe != 1) {
                                    t[pos + 4].doubt_wump += 1;
                                }

                                t[pos].safe = 1;
                            } else {
                                if (condition == 0) {
                                    t[pos].safe = 1;
                                }
                            }
                        }
                    }

                    t[pos].visited = 1;

                } else {
                    if (t[pos].bu != 1 && t[pos].u != 1 && (pos - 4) >= 1 && t[pos - 4].doubt_pit < 1 && t[pos - 4].doubt_wump < 1 && t[pos - 4].pit != 1 && t[pos - 1].wump != 1 && !(t[pos].back.contains("u") && (t[pos].l != 1 || t[pos].r != 1 || t[pos].d != 1) && check(t[pos]))) {
                        temp1 = "d";
                        t[pos].u = 1;
                        pos = pos - 4;
                        System.out.println("↑ Naik ke atas, posisi\t\t: " + pos);
                        ++score;
                        t[pos].back += temp1;
                        condition = t[pos].sense();
                        if (condition == 3) {
                            complete = 1;
                            break;
                        } else {
                            if (condition == 1 && t[pos].visited == 0) {
                                if (t[pos].br != 1 && t[pos + 1].safe != 1) {
                                    t[pos + 1].doubt_pit += 1;
                                }
                                if (t[pos].bu != 1 && (pos - 4) >= 1 && t[pos - 4].safe != 1) {
                                    t[pos - 4].doubt_pit += 1;
                                }
                                if (t[pos].bl != 1 && t[pos - 1].safe != 1) {
                                    t[pos - 1].doubt_pit += 1;
                                }
                                if (t[pos].bd != 1 && (pos + 4) <= 16 && t[pos + 4].safe != 1) {
                                    t[pos + 4].doubt_pit += 1;
                                }

                                t[pos].safe = 1;
                            } else {
                                if (condition == 2 && t[pos].visited == 0) {
                                    if (t[pos].br != 1 && t[pos + 1].safe != 1) {
                                        t[pos + 1].doubt_wump += 1;
                                    }
                                    if (t[pos].bu != 1 && (pos - 4) >= 1 && t[pos - 4].safe != 1) {
                                        t[pos - 4].doubt_wump += 1;
                                    }
                                    if (t[pos].bl != 1 && t[pos - 1].safe != 1) {
                                        t[pos - 1].doubt_wump += 1;
                                    }
                                    if (t[pos].bd != 1 && (pos + 4) <= 16 && t[pos + 4].safe != 1) {
                                        t[pos + 4].doubt_wump += 1;
                                    }

                                    t[pos].safe = 1;
                                } else {
                                    if (condition == 0) {
                                        t[pos].safe = 1;
                                    }
                                }
                            }
                        }

                        t[pos].visited = 1;
                    } else {
                        if (t[pos].bd != 1 && t[pos].d != 1 && (pos + 4) <= 16 && t[pos + 4].doubt_pit < 1 && t[pos + 4].doubt_wump < 1 && t[pos + 4].pit != 1 && t[pos + 4].wump != 1) {
                            temp1 = "u";

                            t[pos].d = 1;
                            pos = pos + 4;
                            System.out.println("↓ Turun ke bawah, posisi\t: " + pos);
                            ++score;

                            t[pos].back += temp1;
                            condition = t[pos].sense();
                            if (condition == 3) {
                                complete = 1;
                                break;
                            } else {
                                if (condition == 1 && t[pos].visited == 0) {
                                    if (t[pos].br != 1 && t[pos + 1].safe != 1) {
                                        t[pos + 1].doubt_pit += 1;
                                    }
                                    if (t[pos].bu != 1 && (pos - 4) >= 1 && t[pos - 4].safe != 1) {
                                        t[pos - 4].doubt_pit += 1;
                                    }
                                    if (t[pos].bl != 1 && t[pos - 1].safe != 1) {
                                        t[pos - 1].doubt_pit += 1;
                                    }
                                    if (t[pos].bd != 1 && (pos + 4) <= 16 && t[pos + 4].safe != 1) {
                                        t[pos + 4].doubt_pit += 1;
                                    }

                                    t[pos].safe = 1;
                                } else {
                                    if (condition == 2 && t[pos].visited == 0) {
                                        if (t[pos].br != 1 && t[pos + 1].safe != 1) {
                                            t[pos + 1].doubt_wump += 1;
                                        }
                                        if (t[pos].bu != 1 && (pos - 4) >= 1 && t[pos - 4].safe != 1) {
                                            t[pos - 4].doubt_wump += 1;
                                        }
                                        if (t[pos].bl != 1 && t[pos - 1].safe != 1) {
                                            t[pos - 1].doubt_wump += 1;
                                        }
                                        if (t[pos].bd != 1 && (pos + 4) <= 16 && t[pos + 4].safe != 1) {
                                            t[pos + 4].doubt_wump += 1;
                                        }

                                        t[pos].safe = 1;
                                    } else {
                                        if (condition == 0) {
                                            t[pos].safe = 1;
                                        }
                                    }
                                }
                            }

                            t[pos].visited = 1;
                        } else {
                            if (limit > 50) {
                                int temp3 = pos;
                                int flag_1 = 0, flag2 = 0, flag3 = 0, flag4 = 0;

                                System.out.println("\nPosisi saat ini " + temp3);

                                while (t[pos].visited == 1 && t[pos].br != 1) {
                                    ++pos;
                                    ++score;
                                }

                                if (t[pos].pit == 1 || t[pos].wump == 1 || (t[pos].br == 1 && t[pos].visited == 1 && t[pos].safe != 1)) {
                                    pos = temp3;
                                    flag_1 = 1;
                                }

                                if (flag_1 == 0) {
                                    t[pos].back += "l";
                                }
                                while (pos + 4 >= 1 && t[pos].bu != 1 && t[pos].visited == 1) {
                                    pos -= 4;
                                    ++score;
                                }

                                if (t[pos].pit == 1 || t[pos].wump == 1 || (t[pos].bu == 1 && t[pos].visited == 1 && t[pos].safe != 1)) {
                                    pos = temp3;
                                    flag3 = 1;
                                }
                                if (flag3 == 0) {
                                    t[pos].back += "d";
                                }

                                while (t[pos].visited == 1 && t[pos].bl != 1) {
                                    --pos;
                                    ++score;
                                }
                                if (t[pos].pit == 1 || t[pos].wump == 1 || (t[pos].bl == 1 && t[pos].visited == 1 && t[pos].safe != 1)) {
                                    pos = temp3;
                                    flag2 = 1;
                                }

                                if (flag2 == 0) {
                                    t[pos].back += "r";
                                }
                                while (pos + 4 <= 16 && t[pos].bd != 1 && t[pos].visited == 1) {
                                    pos += 4;
                                    ++score;
                                }

                                if (t[pos].pit == 1 || t[pos].wump == 1 || (t[pos].bd == 1 && t[pos].visited == 1 && t[pos].safe != 1)) {
                                    pos = temp3;
                                    flag4 = 1;
                                }

                                if (flag4 == 0) {
                                    t[pos].back += "u";
                                }

                                t[pos].safe = 1;
                                t[pos].visited = 1;
                                System.out.println("Mencapai posisi " + pos);
                                limit = 0;
                            }
                        }
                    }
                }
            }
            if (t[pos].env.contains("W") && scream != 1) {
                score += 100;
                scream = 1;
                t[pos].safe = 1;
                System.out.println("\n\nWumpus mati >--0-->");
                t[pos].env.replace("W", " ");
                for (int l = 1; l <= 16; ++l) {
                    t[l].doubt_wump = 0;
                    t[l].env.replace("S", " ");
                }
            }

            if (t[pos].env.contains("P")) {
                score += 50;
                t[pos].pit = 1;
                System.out.println("\n\nTerjebak di posisi pit " + pos + ".");
            }

            for (int k = 1; k <= 16; ++k) {
                if (t[k].doubt_pit == 1 && t[k].doubt_wump == 1) {
                    t[k].doubt_pit = 0;
                    t[k].doubt_wump = 0;
                    t[k].safe = 1;
                }
            }
            for (int y = 1; y <= 16; ++y) {
                if (t[y].doubt_wump > 1) {
                    t[y].wump = 1;
                    for (int h = 1; h <= 16; ++h) {
                        if (h != y) {
                            t[h].doubt_wump = 0;
                            t[h].env.replace("S", " ");
                        }
                    }

                }

            }

            for (int y = 1; y <= 16; ++y) {
                if (t[y].doubt_pit > 1) {
                    t[y].pit = 1;
                }
            }
            try {
                Thread.sleep(100);
            } catch (Exception ignored) {
            }

        } while (complete == 0);
        if (complete == 1) {
            score *= -1;
            score += 1000;
        }
        System.out.println("Score yang di dapatkan oleh agen adalah " +
                score + ".\nSekarang dia akan kembali mengikuti jalur terbaik");
        System.out.println();

        System.out.println("Keterangan : ");
        System.out.println("A\t : Agent");
        System.out.println("B\t : Breeze");
        System.out.println("W\t : Wumpus");
        System.out.println("S\t : Stench");
        System.out.println("G\t : Gold ");
        System.out.println("P\t : Pits");
        System.out.println();
    }

    public void showTruthTable(){
        System.out.println("=============================== TABEL KEBENARAN ================================");
        int side = 3;
        int loop = (int) Math.pow(2,side);
        System.out.println("KB = (B13 V P14) Λ (P9 V ~P14) || α = B13 V P9");
        System.out.println("+-------------------------------------------------------------------------------+");
        System.out.println("|B13 \t| P9 \t| P14 \t| B13 V P14 \t| P9 V ~P14 \t| KB \t| α \t\t|");
        System.out.println("+-------------------------------------------------------------------------------+");
        int cgB13 = (loop/2), cgP9 = (loop/4), cgP14 = (loop/8);
        int countB13 = -1,countP14 = -1,countP9 = -1;
        int b13 = 0,p14 = 0,p9 = 0;
        for (int i = 0; i < loop; i++) {
            countB13++;countP14++;countP9++;
            if (countB13 == cgB13) {
                countB13 = 0;
                if (b13 == 0) {
                    b13 = 1;
                }else{
                    b13 = 0;
                }
            }
            if (countP14 == cgP14) {
                countP14 = 0;
                if (p14 == 0) {
                    p14 = 1;
                }else{
                    p14 = 0;
                }
            }
            if (countP9 == cgP9) {
                countP9 = 0;
                if (p9 == 0) {
                    p9 = 1;
                }else{
                    p9 = 0;
                }
            }
            int b13VP14 = b13 + p14;
            if (b13VP14 >1) {
                b13VP14 = 1;
            }
            int nP14;
            if (p14 == 0) {
                nP14 = 1;
            }else{
                nP14 = 0;
            }
            int p9VP14 = p9 + nP14;
            if (p9VP14 >1) {
                p9VP14 = 1;
            }
            int KB = b13VP14 * p9VP14;
            int alfa = b13 + p9;
            if (alfa >1) {
                alfa = 1;
            }
            if (KB == 1 && KB == alfa) {
                System.out.println("| " + b13+" \t| "+p9+" \t| "+p14+" \t| "+b13VP14+" \t| \t| "+p9VP14+" \t| \t| "+KB+" \t| "+alfa+" - KB|=α\t|");
            }else{
                System.out.println("| " + b13+" \t| "+p9+" \t| "+p14+" \t| "+b13VP14+" \t| \t| "+p9VP14+" \t| \t| "+KB+" \t| "+alfa + "\t\t|");
            }
        }
        System.out.println("+-------------------------------------------------------------------------------+");
    }

}
