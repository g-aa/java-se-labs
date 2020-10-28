/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task6_bitobject;

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
        MyBit mb = new MyBit();
        boolean b = false;
        try {
            mb.turnAllBitsOn();
            mb.printMyBit();
            b = mb.checkBit(5);

            mb.turnAllBitsOff();
            mb.printMyBit();
            b = mb.checkBit(5);

            mb.turnOnBit(32);
            mb.printMyBit();

            mb.turnOnBit(31);
            mb.printMyBit();

            mb.turnOnBit(1);
            mb.printMyBit();

            mb.turnOffBit(1);
            mb.printMyBit();

            mb.invertBit(0);
            mb.printMyBit();

            mb.turnOnBit(17);
            mb.printMyBit();

            mb.turnOnBit(16);
            mb.printMyBit();

            mb.turnOnBit(15);
            mb.printMyBit();

            mb.turnOnBit(14);
            mb.printMyBit();

            mb.turnOnBit(13);
            mb.printMyBit();
        } 
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
