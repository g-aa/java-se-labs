package task12_readerwriterproblem;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
	// write your code here

        int rCount = 4;
        int vCount = 2;
        MyDataBase myDB = new MyDataBase(5000);

        for (int r = 0; r < rCount; r++) {
            new Thread(new MyReader(r + 1, myDB)).start();
        }

        for (int v = 0; v < vCount; v++) {
            new Thread(new MyWriter(v + 1, myDB)).start();
        }
    }

    public static void myPrinter (String string) {
        System.out.println(string);
    }
}