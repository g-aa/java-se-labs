/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task5_vectormatrix;

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
        
        try {
            IntMatrix M1 = new IntMatrix(10, 10);
            System.out.println(M1.matrixToString());

            M1.randomized(10,10);
            System.out.println(M1.matrixToString());

            IntMatrix M4 = new IntMatrix();
            M4.randomized(10,10);
            System.out.println(M4.matrixToString());

            IMatrix fg = M1.add(M4);
            System.out.println(fg.matrixToString());


            IntMatrix.fileWrite("D:\\MyRepository\\Java\\JavaSE\\Labs\\NetBeansProjects\\Data\\matrix_for_task5.txt", M1);
            IMatrix M2 = IntMatrix.fileRead("D:\\MyRepository\\Java\\JavaSE\\Labs\\NetBeansProjects\\Data\\matrix_for_task5.txt");

            IntVector V1 = new IntVector(M1, 2);
            System.out.println(V1.matrixToString());
            System.out.println("Vector length: " + V1.abs());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
