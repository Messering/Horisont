package com.mygdx.game.WorldGenerator.Recursive_Backtracking;

/**
 * Created by HP PAVILION on 14.06.2015.
 * це не рухаєм поки що не працюча реалізація алгоритму
 */
public class Directions
{
    public static final Directions N = new Directions(1);
    public static final Directions S = new Directions(2);
    public static final Directions E = new Directions(4);
    public static final Directions W = new Directions(8);

    private int intValue;
    private static java.util.HashMap<Integer, Directions> mappings;
    private static java.util.HashMap<Integer, Directions> getMappings()
    {
        if (mappings == null)
        {
            synchronized (Directions.class)
            {
                if (mappings == null)
                {
                    mappings = new java.util.HashMap<Integer, Directions>();
                }
            }
        }
        return mappings;
    }

    private Directions(int value)
    {
        intValue = value;
        synchronized (Directions.class)
        {
            getMappings().put(value, this);
        }
    }

    public int getValue()
    {
        return intValue;
    }

    public static Directions forValue(int value)
    {
        synchronized (Directions.class)
        {
            Directions enumObj = getMappings().get(value);
            if (enumObj == null)
            {
                return new Directions(value);
            }
            else
            {
                return enumObj;
            }
        }
    }
}