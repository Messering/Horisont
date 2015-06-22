package com.mygdx.game.WorldGenerator.EllersAlgorithm;

/**
 * Created by HP PAVILION on 20.06.2015.
 */
import com.mygdx.game.Main.Algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Eller's maze-generation algorithm
 * For more information see www.neocomputer.org/projects/eller.html
 * */
public class EllersAlgorithm {

    private int w, h;
    private final Random randomizer = new Random();
    private final Cel[] cells;
    String [][] maps=new String[w][h];
    int [][] gmap = new int [w*2][h*2];


    public EllersAlgorithm() {
        this(Algo.FIELD_SIZE, Algo.FIELD_SIZE);
    }

    /**
     * Constructs object with given width and height
     *
     * @param w count of cells in row
     * @param h count of cells in column
     */
    public EllersAlgorithm(final int w, final int h) {
        this.w = w;
        this.h = h;
        cells = new Cel[w];

                /* Create the first row. No cells will be members of any set */
        for (int i = 0; i < w; i++)
            cells[i] = new Cel(i, 0);
    }

    /**
     * Generates next row of labyrinth
     */
    public Cel[] step(final int position) {
        Cel[] link = null;
                /* Join any cells not members of a set to their own unique set */
        fill(cells);
                /* Create right-walls, moving from left to right */
        merge(cells);
                /* Create bottom-walls, moving from left to right */
        floor(cells);
        if (h - position != 1) {
                        /* 5.A */
            cells[cells.length - 1].setRight(true);
            link = copy();
            next(cells);
        } else {
                        /* 5.B */
            end(cells);
            link = copy();
        }
        return link;
    }

    private void end(final Cel[] row) {
        int length = row.length;
        for (int i = 1; i < length; i++) {
            final Cel current = row[i - 1];
            final Cel next = row[i];
            current.setDown(true);
            if (Cel.isContainsInList(current.getList(), next) == -1) {
                current.setRight(false);
                merge(current, next);
            }
        }
        row[length - 1].setDown(true);
        row[length - 1].setRight(true);
    }

    private void fill(final Cel[] row) {
        for (int index = 0; index < row.length; ) {
            Cel cell = row[index++];
            if (cell.getList() == null) {
                List<Cel> list = new ArrayList<Cel>();
                list.add(cell);
                cell.setList(list);
            }
        }
    }

    private void merge(final Cel[] row) {
        for (int i = 1; i < row.length; i++) {
            final Cel current = row[i - 1];
            final Cel next = row[i];

            if (Cel.isContainsInList(current.getList(), next) != -1) {
                current.setRight(true);
                continue;
            }

            if (randomizer.nextBoolean())
                current.setRight(true);
            else
                merge(current, next);
        }
    }

    private void floor(final Cel[] row) {
        Cel.queryCallback(new Cel.Callback() {

            @Override
            public void action(final List<Cel> set) {
                int size = set.size();
                int count = 0;
                while (true) {
                    if (count == size - 1) break;
                    count++;
                    if (randomizer.nextBoolean()) continue;
                    set.get(randomizer.nextInt(set.size())).setDown(true);
                }
            }
        }, row);
    }

    private void next(final Cel[] previousCells) {
        for (int index = 0; index < previousCells.length; ) {
            Cel cell = previousCells[index++];
            cell.setRight(false);
            cell.setY(cell.getY() + 1);
            if (cell.isDown()) {
                cell.getList().remove(Cel.isContainsInList(cell.getList(), cell));
                cell.setList(null);
                cell.setDown(false);
            }
        }
    }

    private void merge(final Cel current, final Cel next) {
        final List<Cel> currentList = current.getList();
        final List<Cel> nextList = next.getList();
        for (final Cel nCell : nextList) {
            currentList.add(nCell);
            nCell.setList(currentList);
        }
    }

    private Cel[] copy() {
        final Cel[] copy = new Cel[w];
        for (int index = 0; index < w; index++) copy[index] = cells[index].copy();
        return copy;
    }

    private int[][] mass(){
    Cel[] result = new Cel[w * h];
        int count = 0;
    EllersAlgorithm algo = new EllersAlgorithm(w, h);
    for(int index = 0; index < h; index++) {
        Cel[] row = algo.step(index);
        for(int i = 0; i < w; i++) {
            result[index * w + i] = row[i];
        }
        for(int s=0;s<w;s++)
            for(int f=0;f<h;f++){
                maps[s][f]=result[count].toString();
                count++;
            }
}

   return gmap;
    }
}
