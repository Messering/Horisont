//Check if maze has exit
package com.mygdx.game.Maze_Search;

/**
 * Created by Ollko_000 on 6/25/2015.
 */
import com.mygdx.game.Main.Algo;

public class Recursive {

    private int h,w;
    private int[][] maze;
    public Recursive(int[][] m, final int hi, final int we){
        this.h = hi; this.w = we;
        maze = m;
    }
    public void InMazeSearch()
    {
        if (findPath(maze, h, w)) System.out.println("Path exists!");
        else System.out.println("There's no exit in this maze! Someone, help me!!!");
    }
    private int GetH(){return this.h;}
    private int GetW(){return this.w;}
    private static boolean findPath(int[][] maze, int i, int j)
    {
        if (maze[i][j] == 0)
        {
            maze[i][j] = 2;
            if(i == maze.length-1 || j == maze.length-1) return true;
            if ((i+1 < maze.length && maze[i+1][j] == 0 && findPath(maze, i+1, j)))
            {
                return true;
            }
            if ((j+1 < maze[i].length) && maze[i][j+1] == 0 && findPath(maze, i, j+1))
            {
                return true;
            }
            if (i>=1 && maze[i-1][j] == 0 && findPath(maze, i-1,j))
            {
                return true;
            }
            if(j>=1 && maze[i][j-1] == 0 && findPath(maze, i,j-1))
            {
                return true;
            }
        }
        return false;
    }
}
