package com.mygdx.game.Maze_Search;

/**
 * Created by Ollko_000 on 6/26/2015.
 */
public class RightHandedRule {

    private int h,w,Direction,i,j;
    private int[][] maze;
    public RightHandedRule(int[][] m, final int hi, final int we){
        this.h = i = hi; this.w = j = we; Direction = 1;
        maze = m;
    }
    public void InMazeSearch()
    {
        if (findPath()) System.out.println("Path exists!");
        else System.out.println("There's no exit in this maze! Someone, help me!!!");
    }
    /*
    * If there is wall on right - move forward, else - move right */
    private boolean findPath()
    {
        j++;
        while(i != maze.length-1 && j != maze.length-1 && i != 0 && j != 0) {
            maze[i][j] = 3;
            System.out.println("FindPath implementation");
            if (RightWallExist()) MoveForward();
            else {
                if(Direction >=3) Direction = 0; else Direction++;
                MoveForward();}
        }
        return true;
    }
    private boolean RightWallExist()
    {
        if(Direction == 0 && maze[i][j+1] == 1) return true;
        else if(Direction == 1 && maze[i+1][j] == 1) return true;
        else if(Direction == 2 && maze[i][j-1] == 1) return true;
        else if(Direction == 3 && maze[i-1][j] == 1) return true;
        else return false;
    }
    private void MoveForward()
    {
        if(Direction == 0) {
            if(maze[i--][j] != 1) i--; else Direction = 3; MoveForward();}
        else if(Direction == 1) {
            if(maze[i][j++] != 1)j++; else Direction = 0; MoveForward();}
        else if(Direction == 2) {
            if(maze[i++][j] != 1) i++; else Direction = 1; MoveForward();}
        else if(Direction == 3) {
            if(maze[i][j--] != 1) j--; else Direction = 2; MoveForward();}
        else System.out.println("Direction got unexpected value.");
    }
}
