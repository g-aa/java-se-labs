package task2_geararray;

import java.util.Scanner;

public class GearArray {

    // print 2D array:
    public static void print2DIntArray(int[][] inArray) {
        if(inArray != null) {
            for (int r = 0; r < inArray.length; r++) {
                System.out.print("row[" + r + "]:\t");
                for (int col : inArray[r]) {
                    System.out.print(col + "\t");
                }
                System.out.println();
            }
        }
    }

    // поиск ближайшего числа к value в array_2D:
    public static void nearestValue(int[][] inArray, int value) {
        if (inArray != null) {
            int resRow = 0;
            int resCol = 0;
            int dRes = Integer.MAX_VALUE;
            int outRes = Integer.MAX_VALUE;
            for (int r = 0; r < inArray.length; r++) {
                for (int c = 0; c < inArray[r].length; c++) {
                    if (Math.abs(inArray[r][c] - value) == 0) {
                        System.out.println("Ближайшее значение к " + value + " в массиве:\t[" + r + "][" + c + "](" + inArray[r][c] + ")");
                        return;
                    }
                    else if(Math.abs(inArray[r][c] - value) < dRes) {
                        dRes = Math.abs(inArray[r][c] - value);
                        outRes = inArray[r][c];
                        resRow = r;
                        resCol = c;
                    }
                }
            }
            System.out.println("Ближайшее значение к " + value + " в массиве:\t[" + resRow + "][" + resCol + "](" + outRes + ")");
        }
    }

    // Генерация треугольной двумерной матрицы:
    public static int[][] triangularMatrix(int size, Scanner scanner) {
        int[][] array = new int[Math.max(size, 1)][];
        for (int row = 0; row < size; row++) {
            array[row] = new int[row + 1];
            for (int col = 0; col < row + 1; col++) {
                array[row][col] = Main.MyReadConsoleInt("Value array[" + row + "][" + col + "]:\t", scanner);
            }
        }
        return array;
    }
}
