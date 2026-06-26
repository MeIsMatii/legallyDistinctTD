package map.levels.util;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.MouseInfo;

import static java.lang.Math.*;

/**
 * @author mati
 * @Purpose: class to help add Paths to maps easily
 */

public class MapCoordinatesUtilGuy extends Actor {
    private int[][] clickLocations = new int[200][2]; //should be long enough
    private int count = 0;
    private int tickCounter = 0;

    public MapCoordinatesUtilGuy() {}
    public void act() {
        tickCounter++;
        if (tickCounter < 20) return;  // wait 20 ticks before allowing another add


        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if(mouseInfo == null) {
            return;
        }
        if(mouseInfo.getButton() == 2) //MMB
        {
            tickCounter = 0;
            System.out.printf("x: %d, y: %d\n", mouseInfo.getX(), mouseInfo.getY());
            addCoordinates(mouseInfo.getX(), mouseInfo.getY());
        }
        else if(mouseInfo.getButton() == 1) { //LMB
            tickCounter = 0;
            printArray(clickLocations);
        }
    }

    /**
     * Prints an array in a format that can be copy-pasted into the editor inside a map subclass.
     * @param arr the array to print
     */
    public void printArray(int[][] arr) {
        int[][] roundedArr = roundCoordinates(arr);
        System.out.print("int[][] pathLocations = {");
        for (int i = 0; i<roundedArr.length; i++) {
            int[] ints = roundedArr[i];
            if(i == roundedArr.length-1) {
                System.out.printf("{%d,%d}", ints[0], ints[1]);
            } else {
                System.out.printf("{%d,%d},", ints[0], ints[1]);
            }
        }
        System.out.print("};\nsuper.addPath(pathLocations);\n");
    }

    /**
     * adds the given points to the "clicklocations" array.
     * @param x the x value
     * @param y the y value
     */
    public void addCoordinates(int x, int y) {
        for (int i = 0; i < clickLocations.length; i++) {
            if (clickLocations[i][0] == 0 && clickLocations[i][1] == 0) {
                clickLocations[i][0] = x;
                clickLocations[i][1] = y;
                count++;
                return;
            }
        }
    }

    /**
     * Rounds the given coordinates to be valid path points.
     * @param arr the array to round.
     * @return the rounded array.
     */
    public int[][] roundCoordinates(int[][] arr) {
        for (int i = 1; i<arr.length;i++) {
            if (arr[i][0] == 0 && arr[i][1] == 0) {
                int[][] newArr = new int[i][2];

                for (int j = 0; j < newArr.length; j++) {
                    newArr[j] = arr[j];
                }

                return newArr;
            }
            int[] prevLocation =  arr[i];
            int[] currLocation = arr[i-1];

            int dx = abs(prevLocation[0] - currLocation[0]);
            int dy = abs(prevLocation[1] - currLocation[1]);

            if(dx < dy) {
                int num = toIntExact((prevLocation[0] + currLocation[0]) /2);
                arr[i-1][0] = num;
                arr[i][0] =  num;
            } else {
                int num = (prevLocation[1] + currLocation[1]) /2;
                arr[i-1][1] = num;
                arr[i][1] = num;
            }
        }
        return arr;
    }
}