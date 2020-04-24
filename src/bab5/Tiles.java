package bab5;

public class Tiles {
    int safe = 0;
    int wump = 0;
    int pit = 0;
    int doubt_pit = 0;
    int doubt_wump = 0;
    String env;
    int num;
    int br = 0;
    int bl = 0;
    int bu = 0;
    int bd = 0;
    int visited = 0;
    int l, r, u, d;
    String back = "";

    public Tiles(String s, int n) {
        env = s;
        num = n;
        l = r = u = d = 0;
        if (n == 9 || n == 5)   bl = 1;
        if (n == 8 || n == 12)  br = 1;
        if (n == 1){
            bu = 1;
            bl = 1;
        }
        if (n == 13){
            bd = 1;
            bl = 1;
        }
        if (n == 4){
            bu = 1;
            br = 1;
        }
        if (n == 16){
            bd = 1;
            br = 1;
        }
    }
    public int sense() {
        if (env.contains("B"))  return 1;
        else {
            if (env.contains("S"))  return 2;
            else {
                if (env.contains("G"))  return 3;
            }
        }
        if (env.contains("W"))  return 4;
        else    return 0;
    }
}
