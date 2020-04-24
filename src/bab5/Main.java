package bab5;

public class Main {
    public static void main(String[] args) {
        Environment e = new Environment(5,5);
        e.createProblem();
        Wumpus x = new Wumpus(e);
        x.findSolution();
        x.showTruthTable();
    }
}
