/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task1_swap;

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
        
        Scanner sInput = new Scanner(System.in);
        int a, b, c;
        a = MyReadConsoleInt("Enter value \"a\":\t", sInput);
        b = MyReadConsoleInt("Enter value \"b\":\t", sInput);
        c = MyReadConsoleInt("Enter value \"c\":\t", sInput);
        System.out.println("Input values: \"a\" = " + a + ", \"b\" = " + b + ", \"c\" = " + c);

        if (a < b) {
            a += (b - (b = a));
        }
        if (b < c) {
            b += (c - (c = b));
        }
        if (c < a) {
            c += (a - (a = c));
        }
        System.out.println("Result: \"a\" = " + a + ", \"b\" = " + b + ", \"c\" = " + c);
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
