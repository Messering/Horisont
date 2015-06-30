package com.mygdx.game.Maze_Search;

import java.util.Dictionary;

/**
 * Created by Ollko_000 on 6/26/2015.
 */
public class RightHandedRule {

    private int h,w,Direction,i,j, count;
    private String[][] maze;
    public RightHandedRule(String[][] m, final int hi, final int we){
        this.h = i = hi; this.w = j = we; Direction = 1;
        this.maze = m; this.count=0;
    }
    public void InMazeSearch()
    {
        if (findPath()) System.out.println("Path exists!");
        else System.out.println("There's no exit in this maze! Someone, help me!!!");
    }
    /*
    * If there is wall on right - move forward, else - move right */
    /*if you can go right go right
    else if you can go forward go forward
    else if you can go left go left
    else if you can go back go back
    else - oO there are walls all around you
    */
    private boolean findPath()
    {
            //first direction:
            if (!maze[i][j].contains("e")) {
                System.out.println("FirstDir=" + 1);
                Direction = 1;
            } else if (!maze[i][j].contains("n")) {
                System.out.println("FirstDir=" + 0);
                Direction = 0;
            } else if (!maze[i][j].contains("s")) {
                System.out.println("FirstDir=" + 2);
                Direction = 2;
            } else {
                System.out.println("FirstDir=" + 3);
                Direction = 3;
            }
      //  while(i != maze.length-2 && j != maze.length-2) {
        while( i != 13 || j != 8){
          // if(stop()) return true;
    //        cou = cou+1; if(cou ==10) return true;
            if(!maze[i][j].contains("1")) maze[i][j] += "1";
           // if (i == maze.length - 2 || j == maze.length - 2) return true;
            System.out.println("FindPath implementation" + ": i=" + i + ", j=" + j + ", Dir=" + Direction);
            System.out.println("RightWallExist - " + RightWallExist());

            if (RightWallExist()) MoveForward();
            else { //move right
                if (Direction >= 3) Direction = 0;
                else Direction++;
                MoveForward();
            }

            //SHIT START
            //SHIT ENDS
        }
        return true;
    }
    private boolean RightWallExist()
    {
        switch (Direction)
        {
            case 0: if(maze[i][j].contains("e")) return true;
                break;
            case 1: if(maze[i][j].contains("s")) return true;
                break;
            case 2: if(maze[i][j].contains("w")) return true;
                break;
            case 3: if(maze[i][j].contains("n")) return true;
                break;
        }
      return false;
    }
    private void MoveForward()
    {
        System.out.println("MOVEFORWARD: DIR=" + Direction);
        System.out.println(maze[i][j].contains("n"));
        System.out.println(maze[i][j].contains("e"));
        System.out.println(maze[i][j].contains("s"));
        System.out.println(maze[i][j].contains("w"));
        if(maze[i][j].contains("n") && maze[i][j].contains("s") && maze[i][j].contains("w") && maze[i][j].contains("e")) {
            System.out.println("THERE R TO MANY WALLS, I CAN'T MOVE!"); return;
        }

        switch (Direction) {
            case 0:
                if (!maze[i][j].contains("n")) {j++; return;}
                else {Direction=3; MoveForward(); return;}
            case 1:
                if (!maze[i][j].contains("e")) {i++; return;}
                else {Direction=0; MoveForward();
                return;}
            case 2:
                if (!maze[i][j].contains("s")) {j--; return;}
                else {Direction=1; MoveForward();
                return;}
            case 3:
                if (!maze[i][j].contains("w")) {i--; return;}
                else { Direction=2; MoveForward();
                return;}

            default: System.out.println("unexpected direction value, direction=" + Direction); Direction=0;
                return;
        }

        /*if(Direction==0)
                if (!maze[i][j].contains("n")) j++;
                else Direction=1; MoveForward(); return;
        if(Direction==1)
                if (!maze[i][j].contains("e")) i++;
                else Direction=2; MoveForward();
                return;
        if(Direction==2)
                if (!maze[i][j].contains("s")) j--;
                else {Direction=3; MoveForward();
                return;}
        if(Direction==3)
                if (!maze[i][j].contains("w")) i--;
                else {Direction=0; MoveForward();
                return;}
            else{
        System.out.println("unexpected direction value, direction=" + Direction);
        Direction = 0;
        return;
    }*/
    }
    private boolean stop()
    {
        count++;
        if (count<10) return true;
        else return false;
    }
}
