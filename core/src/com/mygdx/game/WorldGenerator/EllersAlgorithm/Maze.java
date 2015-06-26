package com.mygdx.game.WorldGenerator.EllersAlgorithm;

/**
 * Created by Vlad on 26.06.2015.
 */

public class Maze {
    private int N;                 // dimension of maze
    private boolean[][] north;     // is there a wall to north of cell i, j
    private boolean[][] east;
    private boolean[][] south;
    private boolean[][] west;
    private boolean[][] visited;
    private boolean done = false;

    public Maze(int N) {
        this.N = N;
        init();
        generate();
    }

    private void init() {
        // initialize border cells as already visited
        visited = new boolean[N+2][N+2];
        for (int x = 0; x < N+2; x++) visited[x][0] = visited[x][N+1] = true;
        for (int y = 0; y < N+2; y++) visited[0][y] = visited[N+1][y] = true;


        // initialze all walls as present
        north = new boolean[N+2][N+2];
        east  = new boolean[N+2][N+2];
        south = new boolean[N+2][N+2];
        west  = new boolean[N+2][N+2];
        for (int x = 0; x < N+2; x++)
            for (int y = 0; y < N+2; y++)
                north[x][y] = east[x][y] = south[x][y] = west[x][y] = true;
    }


    // generate the maze
    private void generate(int x, int y) {
        visited[x][y] = true;

        // while there is an unvisited neighbor
        while (!visited[x][y+1] || !visited[x+1][y] || !visited[x][y-1] || !visited[x-1][y]) {

            // pick random neighbor (could use Knuth's trick instead)
            while (true) {
                double r = Math.random();
                if (r < 0.25 && !visited[x][y+1]) {
                    north[x][y] = south[x][y+1] = false;
                    generate(x, y + 1);
                    break;
                }
                else if (r >= 0.25 && r < 0.50 && !visited[x+1][y]) {
                    east[x][y] = west[x+1][y] = false;
                    generate(x+1, y);
                    break;
                }
                else if (r >= 0.5 && r < 0.75 && !visited[x][y-1]) {
                    south[x][y] = north[x][y-1] = false;
                    generate(x, y-1);
                    break;
                }
                else if (r >= 0.75 && r < 1.00 && !visited[x-1][y]) {
                    west[x][y] = east[x-1][y] = false;
                    generate(x-1, y);
                    break;
                }
            }
        }
    }

    // generate the maze starting from lower left
    private void generate() {
        generate(1, 1);

/*
        // delete some random walls
        for (int i = 0; i < N; i++) {
            int x = (int) (1 + Math.random() * (N-1));
            int y = (int) (1 + Math.random() * (N-1));
            north[x][y] = south[x][y+1] = false;
        }

        // add some random walls
        for (int i = 0; i < 10; i++) {
            int x = (int) (N / 2 + Math.random() * (N / 2));
            int y = (int) (N / 2 + Math.random() * (N / 2));
            east[x][y] = west[x+1][y] = true;
        }
*/

    }



    // solve the maze using depth-first search
    private void solve(int x, int y) {
        if (x == 0 || y == 0 || x == N+1 || y == N+1) return;
        if (done || visited[x][y]) return;
        visited[x][y] = true;


        // reached middle
        if (x == N/2 && y == N/2) done = true;

        if (!north[x][y]) solve(x, y + 1);
        if (!east[x][y])  solve(x + 1, y);
        if (!south[x][y]) solve(x, y - 1);
        if (!west[x][y])  solve(x - 1, y);

        if (done) return;

    }

    // solve the maze starting from the start state
    public void solve() {
        for (int x = 1; x <= N; x++)
            for (int y = 1; y <= N; y++)
                visited[x][y] = false;
        done = false;
        solve(1, 1);
    }

    // draw the maze
   /* public void draw() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(N/2.0 + 0.5, N/2.0 + 0.5, 0.375);
        StdDraw.filledCircle(1.5, 1.5, 0.375);

        StdDraw.setPenColor(StdDraw.BLACK);
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                if (south[x][y]) StdDraw.line(x, y, x + 1, y);
                if (north[x][y]) StdDraw.line(x, y + 1, x + 1, y + 1);
                if (west[x][y])  StdDraw.line(x, y, x, y + 1);
                if (east[x][y])  StdDraw.line(x + 1, y, x + 1, y + 1);
            }
        }
        StdDraw.show(1000);
    }
*/
    public String[][] mass()
    {
        String[][] mass=new String[N+2][N+2];
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {mass[x][y]="_";}}
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                if (south[x][y])mass[x][y]+='s';
                if (north[x][y])mass[x][y]+='n';
                if (west[x][y])mass[x][y]+='w';
                if (east[x][y])mass[x][y]+='e';
            }
        }
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {System.out.print(mass[x][y]);}System.out.println();}
        return mass;
    }

    // a test client
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Maze maze = new Maze(N);
        StdDraw.show(0);
       // maze.draw();
        maze.solve();
    }
}
