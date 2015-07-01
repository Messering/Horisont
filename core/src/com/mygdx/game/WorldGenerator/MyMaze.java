/*package com.mygdx.game.WorldGenerator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by HP PAVILION on 26.06.2015.

public class MyMaze{
    private int dimensionX, dimensionY;
    private int gridDimensionX, gridDimensionY;
    private int[][] mazeGrid;
    private Cell[][] cells;
    private Random random = new Random();

    public MyMaze(int aDimension) {

        this(aDimension, aDimension);
    }

    public MyMaze(int xDimension, int yDimension) {
        dimensionX = xDimension;
        dimensionY = yDimension;
        gridDimensionX = xDimension * 2 + 1;
        gridDimensionY = yDimension * 2 + 1;
        mazeGrid = new int[gridDimensionX][gridDimensionY];
        init();
        generateMaze();
    }

    private void init() {
        cells = new Cell[dimensionX][dimensionY];
        for (int x = 0; x < dimensionX; x++) {
            for (int y = 0; y < dimensionY; y++) {
                cells[x][y] = new Cell(x, y, false);
            }
        }
    }


    private class Cell {
        int x, y;
        ArrayList<Cell> neighbors = new ArrayList<>();
        boolean wall = true;
        boolean open = true;
        Cell(int x, int y) {
            this(x, y, true);
        }
        Cell(int x, int y, boolean isWall) {
            this.x = x;
            this.y = y;
            this.wall = isWall;
        }
        void addNeighbor(Cell other) {
            if (!this.neighbors.contains(other)) {
                this.neighbors.add(other);
            }
            if (!other.neighbors.contains(this)) {
                other.neighbors.add(this);
            }
        }
        boolean isCellBelowNeighbor() {
            return this.neighbors.contains(new Cell(this.x, this.y + 1));
        }
        boolean isCellRightNeighbor() {
            return this.neighbors.contains(new Cell(this.x + 1, this.y));
        }

        public boolean equals(Object other) {
            if (!(other instanceof Cell)) return false;
            Cell otherCell = (Cell) other;
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
    private void generateMaze(Cell startAt) {

        if (startAt == null) return;
        startAt.open = false;
        ArrayList<Cell> cellsList = new ArrayList<>();
        cellsList.add(startAt);

        while (!cellsList.isEmpty()) {
            Cell cell;

            if (random.nextInt(10)==0)
                cell = cellsList.remove(random.nextInt(cellsList.size()));
            else cell = cellsList.remove(cellsList.size() - 1);

            ArrayList<Cell> neighbors = new ArrayList<>();

            Cell[] potentialNeighbors = new Cell[]{
                    getCell(cell.x + 1, cell.y),
                    getCell(cell.x, cell.y + 1),
                    getCell(cell.x - 1, cell.y),
                    getCell(cell.x, cell.y - 1)
            };
            for (Cell other : potentialNeighbors) {

                if (other==null || other.wall || !other.open) continue;
                neighbors.add(other);
            }
            if (neighbors.isEmpty()) continue;

            Cell selected = neighbors.get(random.nextInt(neighbors.size()));

            selected.open = false;
            cell.addNeighbor(selected);
            cellsList.add(cell);
            cellsList.add(selected);
        }
        updateGrid();
    }

    public Cell getCell(int x, int y) {
        try {
            return cells[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void updateGrid() {
        int backChar = 0, wallChar = 1, cellChar = 0;

        for (int x = 0; x < gridDimensionX; x ++) {
            for (int y = 0; y < gridDimensionY; y ++) {
                mazeGrid[x][y] = backChar;
            }
        }

        for (int x = 0; x < gridDimensionX; x ++) {
            for (int y = 0; y < gridDimensionY; y ++) {
                if (x % 2 == 0 || y % 2 == 0)
                    mazeGrid[x][y] = wallChar;
            }
        }

        for (int x = 0; x < dimensionX; x++) {
            for (int y = 0; y < dimensionY; y++) {
                Cell current = getCell(x, y);
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
       MyMaze f = new MyMaze(h, w);
       return mazeGrid;
   }

    }*/
