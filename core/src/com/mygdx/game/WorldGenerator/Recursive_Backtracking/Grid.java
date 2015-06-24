package com.mygdx.game.WorldGenerator.Recursive_Backtracking;

import static java.util.Arrays.*;

public class Grid {
    private static final int _rowDimension = 0;
    private static final int _columnDimension = 1;

    private int MinSize;

    public final int getMinSize() {
        return MinSize;
    }

    private void setMinSize(int value) {
        MinSize = value;
    }

    private int MaxSize;

    public final int getMaxSize() {
        return MaxSize;
    }

    private void setMaxSize(int value) {
        MaxSize = value;
    }

    private int[][] Cells;

    public final int[][] getCells() {
        return Cells;
    }

    private void setCells(int[][] value) {
        Cells = value;
    }

    public Grid() {
        this(3, 3);

    }

    public Grid(int rows, int columns) {
        setMinSize(3);
        setMaxSize(10);
        setCells(Initialise(rows, columns));
    }

    public final int[][] Initialise(int rows, int columns) {
        if (rows < getMinSize()) {
            rows = getMinSize();
        }

        if (columns < getMinSize()) {
            columns = getMinSize();
        }

        if (rows > getMaxSize()) {
            rows = getMaxSize();
        }

        if (columns > getMaxSize()) {
            columns = getMaxSize();
        }

        int[][] cells = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = 0;
            }
        }

        return cells;
    }

    private java.util.HashMap<Directions, Integer> DirectionX = new java.util.HashMap<Directions, Integer>();
    private java.util.HashMap<Directions, Integer> DirectionY = new java.util.HashMap<Directions, Integer>();
    private java.util.HashMap<Directions, Directions> Opposite = new java.util.HashMap<Directions, Directions>();

    public final int[][] Generate() {
        {
            DirectionX.put(Directions.N, 0);
            DirectionX.put(Directions.S, 0);
            DirectionX.put(Directions.E, 1);
            DirectionX.put(Directions.W, -1);
        }

        {
            DirectionY.put(Directions.N, -1);
            DirectionY.put(Directions.S, 1);
            DirectionY.put(Directions.E, 0);
            DirectionY.put(Directions.W, 0);
        }

        {
            Opposite.put(Directions.N, Directions.S);
            Opposite.put(Directions.S, Directions.N);
            Opposite.put(Directions.E, Directions.W);
            Opposite.put(Directions.W, Directions.E);
        }

        int[][] cells = getCells();
        RefObject<Integer> tempRef_cells = new RefObject<Integer>(cells);
       // CarvePassagesFrom(0, 0, tempRef_cells);
        cells = tempRef_cells.argValue;

        return cells;
    }
/* це я взагалі не знаю як переробити на джаву хз виникли куча проблем
    public void CarvePassagesFrom(int currentX, int currentY, ref int[,] grid)
    {
        var directions = new List<Directions>
        {
            Directions.N,
                    Directions.S,
                    Directions.E,
                    Directions.W
        }
        .OrderBy(x => Guid.NewGuid());

        foreach (var direction in directions)
        {
            var nextX = currentX + DirectionX[direction];
            var nextY = currentY + DirectionY[direction];

            if (IsOutOfBounds(nextX, nextY, grid))
                continue;

            if (grid[nextY, nextX] != 0) // has been visited
            continue;

            grid[currentY, currentX] |= (int)direction;
            grid[nextY, nextX] |= (int)Opposite[direction];

            CarvePassagesFrom(nextX, nextY, ref grid);
        }
    }

    private bool IsOutOfBounds(int x, int y, int[,] grid)
    {
        if (x < 0 || x > grid.GetLength(_rowDimension) - 1)
            return true;

        if (y < 0 || y > grid.GetLength(_columnDimension) - 1)
            return true;

        return false;
    }*/
}