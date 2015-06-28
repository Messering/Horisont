package com.mygdx.game.WorldGenerator;


import java.util.ArrayList;
import java.util.Random;
/**
 * Created by HP PAVILION on 28.06.2015.
 */
public class MyMaz {

    private int dimensionX, dimensionY;
    private int gridDimensionX, gridDimensionY;
    private int[][] mazeGrid;
    private Cel[][] cells;
    private Random random = new Random();


    public MyMaz(int xDimension, int yDimension) {
        dimensionX = xDimension;
        dimensionY = yDimension;
        gridDimensionX = xDimension * 2 + 1;
        gridDimensionY = yDimension * 2 + 1;
        mazeGrid = new int[gridDimensionX][gridDimensionY];
        init();
        generateMaze();
    }

     private void init() {
         cells = new Cel[dimensionX][dimensionY];
         for (int x = 0; x < dimensionX; x++) {
             for (int y = 0; y < dimensionY; y++) {
               cells[x][y] = new Cel(x, y, false);
             }
         }
     }

private class Cel{
   private  int x, y;
   private ArrayList<Cel> neighbors;
   private boolean wall = true;
   private boolean open = true;
    Cel(int x, int y) {
        this(x, y, true);
    }
    Cel(int x, int y, boolean isWall) {
        this.x = x;
        this.y = y;
        this.wall = isWall;
        neighbors = new ArrayList<Cel>();
    }
    void addNeighbor(Cel other) {
        if (!this.neighbors.contains(other)) {
            this.neighbors.add(other);
        }
        if (!other.neighbors.contains(this)) {
            other.neighbors.add(this);
        }
    }
    boolean isCellBelowNeighbor() {
        return this.neighbors.contains(new Cel(this.x, this.y + 1));
    }
    boolean isCellRightNeighbor() {
        return this.neighbors.contains(new Cel(this.x + 1, this.y));
    }

    public boolean equals(Object other) {
        if (!(other instanceof Cel)) return false;
        Cel otherCell = (Cel) other;
        return (this.x == otherCell.x && this.y == otherCell.y);
    }

    public int hashCode() {
        return this.x + this.y * 256;
    }

}

    private void generateMaze() {
        generateMaze(0, 0);
    }

    private void generateMaze(int x, int y) {
        generateMaze(getCell(x, y));
    }
    private void generateMaze(Cel startAt) {

        if (startAt == null) return;
        startAt.open = false;
        ArrayList<Cel> cellsList = new ArrayList<Cel>();
        cellsList.add(startAt);

        while (!cellsList.isEmpty()) {
            Cel cell;

            if (random.nextInt(10)==0)
                cell = cellsList.remove(random.nextInt(cellsList.size()));
            else cell = cellsList.remove(cellsList.size() - 1);

            ArrayList<Cel> neighbors = new ArrayList<Cel>();

            Cel[] potentialNeighbors = new Cel[]{
                    getCell(cell.x + 1, cell.y),
                    getCell(cell.x, cell.y + 1),
                    getCell(cell.x - 1, cell.y),
                    getCell(cell.x, cell.y - 1)
            };
            for (Cel other : potentialNeighbors) {

                if (other==null || other.wall || !other.open) continue;
                neighbors.add(other);
            }
            if (neighbors.isEmpty()) continue;

            Cel selected = neighbors.get(random.nextInt(neighbors.size()));

            selected.open = false;
            cell.addNeighbor(selected);
            cellsList.add(cell);
            cellsList.add(selected);
        }
        updateGrid();
    }

    public Cel getCell(int x, int y) {
        try {
            return cells[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }



}

    public void updateGrid() {
        int backChar = 0, wallChar = 1, cellChar = 0;

        for (int x = 0; x < gridDimensionX; x++) {
            for (int y = 0; y < gridDimensionY; y++) {
                mazeGrid[x][y] = backChar;
            }
        }

        for (int x = 0; x < gridDimensionX; x++) {
            for (int y = 0; y < gridDimensionY; y++) {
                if (x % 2 == 0 || y % 2 == 0)
                    mazeGrid[x][y] = wallChar;
            }
        }

        for (int x = 0; x < dimensionX; x++) {
            for (int y = 0; y < dimensionY; y++) {
                Cel current = getCell(x, y);
                int gridX = x * 2 + 1, gridY = y * 2 + 1;
                mazeGrid[gridX][gridY] = cellChar;
                if (current.isCellBelowNeighbor()) {
                    mazeGrid[gridX][gridY + 1] = cellChar;
                }
                if (current.isCellRightNeighbor()) {
                    mazeGrid[gridX + 1][gridY] = cellChar;
                }
            }
        }


    }
   public int[][] gen(int h, int w) {
       MyMaz f = new MyMaz(h, w);
       return mazeGrid;
   }

    }

