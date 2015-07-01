//Check if maze has exit
package com.mygdx.game.Maze_Search;

/**
 * Created by Ollko_000 on 6/25/2015.
 */
import com.mygdx.game.Main.Algo;

public class Recursive {

    private int h,w;
    int count=0;
    private String[][] maze;
    int jl;
    public Recursive(String[][] m, final int hi, final int we){
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

    private boolean findPath(String[][] maze, int i, int j) {
        //OUTPUT
        System.out.println("Recursive: i = " + i + ", j = " + j);
        for (int k = 0; k < maze.length; k++) {
            for (int p = 0; p < maze.length; p++)
                System.out.print(maze[k][p]);
            System.out.println();
        }
        //END OUTPUT

        //if(i == maze.length-2 || j == maze.length-2) return true;
        //if outside maze
        if(i<1 || i>=maze.length-1 || j<1 || j>=maze.length-1)    return false;

        //if goal
        if (i == 10 && j == 10) return true;

       /*if (!(i == 1 || j == 1)) {
            if (maze[i][j].contains("w") && maze[i][j].contains("e") && maze[i][j].contains("s")) return false;
            else if (maze[i][j].contains("w") && maze[i][j].contains("n") && maze[i][j].contains("s")) return false;
            else if (maze[i][j].contains("w") && maze[i][j].contains("n") && maze[i][j].contains("e")) return false;
            else if (maze[i][j].contains("n") && maze[i][j].contains("e") && maze[i][j].contains("s")) return false;
        }*/

        if (maze[i][j].contains("1")) return false;
        if (!maze[i][j].contains("1"))  maze[i][j] += "1";

        if(!maze[i][j].contains("n")) {
            //if (!maze[i][j].contains("1")) maze[i][j] += '1';
            if (findPath(maze, i, j + 1)) return true;
        }
        if(!maze[i][j].contains("e")) {
           // if(!maze[i][j].contains("1")) maze[i][j] +='1';
            if(findPath(maze,i+1,j)) return true;
        }
        if(!maze[i][j].contains("s")) {
           // if(!maze[i][j].contains("1")) maze[i][j] +='1';
            if(findPath(maze,i,j-1)) return true;
        }
        if(!maze[i][j].contains("w")) {
            // if(!maze[i][j].contains("1") && Direction != 1) maze[i][j] +='1';
            if (findPath(maze, i-1, j)) return true;
        }
/*
            if (i+1 < maze.length-1 &&!maze[i][j].contains("e")&&jl!=1){jl=0; if(findPath(maze, i+1, j))
            {
                return true;
            }}
            if ((j+1 < maze[i].length-1)&&!maze[i][j].contains("n")&&jl!=2){jl=3;if(findPath(maze, i, j+1))
            {
                return true;
            }}
            if(j>=1&&!maze[i][j].contains("s")&&jl!=3){jl=2;if( findPath(maze, i,j-1))
            {
            return true;
             }}
            if (i>=1 &&!maze[i][j].contains("w")&&jl!=0){jl=1;if(findPath(maze, i-1,j))
            {
                return true;
            }}
*/
       // maze[i][j].replace("1","");
        return false;
    }
}
