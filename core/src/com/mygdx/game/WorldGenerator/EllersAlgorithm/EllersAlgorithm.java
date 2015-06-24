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
    private final Cell[] cells;

    /** Constructs object with width = 10 and height = 10 */
    public EllersAlgorithm() {
        this(10, 10);
    }

    /** Constructs object with given width and height
     * @param w count of cells in row
     * @param h count of cells in column */
    public EllersAlgorithm(final int w, final int h) {
        this.w = w;
        this.h = h;
        cells = new Cell[w];

                /* Create the first row. No cells will be members of any set */
        for(int i = 0; i < w; i++)
            cells[i] = new Cell(i, 0);
    }

    /** Generates next row of labyrinth */
    public Cell[] step(final int position) {
        Cell[] link = null;
                /* Join any cells not members of a set to their own unique set */
        fill(cells);
                /* Create right-walls, moving from left to right */
        merge(cells);
                /* Create bottom-walls, moving from left to right */
        floor(cells);
        if(h - position != 1) {
                        /* 5.A */
            cells[cells.length - 1].setRight(true);
            link = copy();
            next(cells);
        }
        else {
                        /* 5.B */
            end(cells);
            link = copy();
        }
        return link;
    }

    private void end(final Cell[] row) {
        int length = row.length;
        for(int i = 1; i < length; i++) {
            final Cell current = row[i - 1];
            final Cell next = row[i];
            current.setDown(true);
            if(Cell.isContainsInList(current.getList(), next) == -1) {
                current.setRight(false);
                merge(current, next);
            }
        }
        row[length - 1].setDown(true);
        row[length - 1].setRight(true);
    }

    private void fill(final Cell[] row) {
        for(int index = 0; index < row.length; ) {
            Cell cell = row[index++];
            if(cell.getList() == null) {
                List<Cell> list = new ArrayList<Cell>();
                list.add(cell);
                cell.setList(list);
            }
        }
    }

    private void merge(final Cell[] row) {
        for(int i = 1; i < row.length; i++) {
            final Cell current = row[i - 1];
            final Cell next = row[i];

            if(Cell.isContainsInList(current.getList(), next) != -1) {
                current.setRight(true);
                continue;
            }

            if(randomizer.nextBoolean())
                current.setRight(true);
            else
                merge(current, next);
        }
    }

    private void floor(final Cell[] row) {
        Cell.queryCallback(new Cell.Callback() {

            @Override
            public void action(final List<Cell> set) {
                int size = set.size();
                int count = 0;
                while(true) {
                    if(count == size - 1) break;
                    count++;
                    if(randomizer.nextBoolean()) continue;
                    set.get(randomizer.nextInt(set.size())).setDown(true);
                }
            }
        }, row);
    }

    private void next(final Cell[] previousCells) {
        for(int index = 0; index < previousCells.length; ) {
            Cell cell = previousCells[index++];
            cell.setRight(false);
            cell.setY(cell.getY()+1);
            if(cell.isDown()) {
                cell.getList().remove(Cell.isContainsInList(cell.getList(), cell));
                cell.setList(null);
                cell.setDown(false);
            }
        }
    }

    private void merge(final Cell current, final Cell next) {
        final List<Cell> currentList = current.getList();
        final List<Cell> nextList = next.getList();
        for(final Cell nCell : nextList) {
            currentList.add(nCell);
            nCell.setList(currentList);
        }
    }

    private Cell[] copy() {
        final Cell[] copy = new Cell[w];
        for(int index = 0; index < w; index++) copy[index] = cells[index].copy();
        return copy;
    }


    /** Prints labyrinth to stdout */
    public static void print(Cell[] cells, int w, int h) {
        final StringBuilder builder = new StringBuilder();
        for(int i = 0; i < w; i++) builder.append(" _");
        builder.append('\n');
        for(int i = 0; i < h; i++) {
            builder.append('|');
            for(int j = 0; j < w; j++) builder.append(cells[i * w + j]);
            builder.append('\n');
        }
    }

    public int[][] generator(){
        int count =0;
        int [][] map = new int[Algo.FIELD_SIZE*2][Algo.FIELD_SIZE*2];
        String [][]gmap =new String[Algo.FIELD_SIZE][Algo.FIELD_SIZE];
        Cell[] result = new Cell[w * h];
        EllersAlgorithm algo = new EllersAlgorithm(w, h);
        for(int index = 0; index < h; index++) {
            Cell[] row = algo.step(index);
            for(int i = 0; i < w; i++) {result[index * w + i] = row[i]; //System.out.print(result[index * w + i]);
                //  System.out.println("\n");
            }

        }
        //EllersAlgorithm.print(result, w, h);
        for(int s=0;s<w;s++)
            for(int f=0;f<h;f++){
                gmap[s][f]=result[count].toString();

                count++;
            }
 ///ось туут пропишіть нормальне розсталвення нулів і одиниць і все компілите зразу
//і краще реалізуйте ейллера це потужніший алгоритм
        for(int s=0;s<w;s++){
            for(int f=0;f<h;f++){
                if("_ ".equals(gmap[s][f])){map[s][f]=0; map[s][f+1]=0; map[s+1][f]=1; map[s+1][f+1]=0; }
                if(" |".equals(gmap[s][f])){map[s][f]=0; map[s][f+1]=1; map[s+1][f]=0; map[s+1][f+1]=1; }
                if("  ".equals(gmap[s][f])){map[s][f]=0; map[s][f+1]=0; map[s+1][f]=0; map[s+1][f+1]=0; }
                if("_|".equals(gmap[s][f])){map[s][f]=0; map[s][f+1]=1; map[s+1][f]=1; map[s+1][f+1]=1; }
                if("||".equals(gmap[s][f])){map[s][f]=1; map[s][f+1]=1; map[s+1][f]=1; map[s+1][f+1]=1; }
                if(" _".equals(gmap[s][f])){map[s][f]=0; map[s][f+1]=0; map[s+1][f]=0; map[s+1][f+1]=1; }
                if("| ".equals(gmap[s][f])){map[s][f]=1; map[s][f+1]=0; map[s+1][f]=1; map[s+1][f+1]=0; }
            }
        }
        return map;
    }

}
