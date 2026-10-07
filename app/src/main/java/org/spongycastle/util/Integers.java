/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package org.spongycastle.util;

/**
 * Utility methods for ints.
 */
public class Integers
{
    public static int rotateLeft(int i, int distance)
    {
        return Integer.rotateLeft(i, distance);
    }

    public static int rotateRight(int i, int distance)
    {
        return Integer.rotateRight(i, distance);
    }

    public static Integer valueOf(int value)
    {
        return Integer.valueOf(value);
    }
}
