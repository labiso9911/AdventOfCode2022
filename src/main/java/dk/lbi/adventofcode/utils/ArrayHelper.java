package dk.lbi.adventofcode.utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayHelper {
    // Function that returns all adjacent elements
    static List<Integer> getAdjacent(List<List<Integer>> arr, int i, int j) {
// Size of given 2d array
        int n = arr.size();
        int m = arr.get(0).size();

// Initialising a array list where adjacent element
// will be stored
        List<Integer> v = new ArrayList<>();

// Checking for all the possible adjacent positions
        if (isValidPos(i - 1, j, n, m)) {
            v.add(arr.get(i - 1).get(j));
        }
        if (isValidPos(i, j - 1, n, m)) {
            v.add(arr.get(i).get(j - 1));
        }
        if (isValidPos(i, j + 1, n, m)) {
            v.add(arr.get(i).get(j + 1));
        }
        if (isValidPos(i + 1, j, n, m)) {
            v.add(arr.get(i + 1).get(j));
        }

// Returning the arraylist
        return v;
    }

    // Function to check whether position is valid or not
    static boolean isValidPos(int i, int j, int n, int m) {
        if (i < 0 || j < 0 || i > n - 1 || j > m - 1) {
            return false;
        }
        return true;
    }
}
