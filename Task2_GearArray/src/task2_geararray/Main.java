/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2_geararray;

import java.util.Scanner;

/**
 *
 * @author Andrey
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    
    // распечатать 2D массив:
    private static void print2DIntArray(int[][] inArray)
    {
        if(inArray != null)
        {
            for (int r = 0; r < inArray.length; r++)
            {
                System.out.print("row[" + r + "]:\t");
                for (int col : inArray[r])
                {
                    System.out.print(col + "\t");
                }
                System.out.println();
            }
        }
    }


    // поиск ближайшего числа к value в 2D массиву array:
    private static void nearest10value(int[][] inArray, int value) {
        if (inArray != null)
        {
            int resRow = 0;
            int resCol = 0;
            int dRes = Integer.MAX_VALUE;
            int outRes = Integer.MAX_VALUE;;
            for (int r = 0; r < inArray.length; r++)
            {
                for (int c = 0; c < inArray[r].length; c++)
                {
                    if (Math.abs(inArray[r][c] - value) == 0)
                    {
                        System.out.println("Ближайшее значение к " + value + " в массиве:\t[" + r + "][" + c + "](" + inArray[r][c] + ")");
                        return;
                    }
                    else if(Math.abs(inArray[r][c] - value) < dRes)
                    {
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
    
    public static int MyReadConsoleInt(String message, Scanner scanner) {
        int k = 0;
        while (true) {
            System.out.print(message);
            try {
                k = Integer.parseInt(scanner.nextLine().trim());;
                break;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return k;
    }
}
