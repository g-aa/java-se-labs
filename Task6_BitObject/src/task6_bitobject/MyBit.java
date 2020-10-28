package task6_bitobject;

import java.util.Arrays;

public class MyBit implements IBit {

    private final int SIZE = 10;
    private  int maxIndex;
    private int[] array;

    public MyBit(){
        maxIndex = SIZE*Integer.SIZE - 1;
        array = new int[SIZE];
    }

    // override methods:
    @Override
    public boolean checkBit(int n) throws Exception {
        this.checkInterval(n);
        return ((array[getArrayPosition(n)] & 1 << getBitPosition(n)) != 0);
    }

    @Override
    public boolean turnOnBit(int n) throws Exception {
        if (!checkBit(n)) {
            return this.invertBit(n);
        }
        return false;
    }

    @Override
    public boolean turnOffBit(int n) throws Exception {
        if(checkBit(n)) {
            return this.invertBit(n);
        }
        return false;
    }

    @Override
    public boolean invertBit(int n) throws Exception {
        this.checkInterval(n);
        array[getArrayPosition(n)] ^= (1 << getBitPosition(n));
        return true;
    }

    @Override
    public void turnAllBitsOn() {
        Arrays.fill(array, -1);
    }

    @Override
    public void turnAllBitsOff() {
        for (int i = 0; i < array.length; i++) {
            array[i] ^= array[i];
        }
    }

    // other methods:
    public void printMyBit() {
        StringBuilder res = new StringBuilder(String.format("MyBit, size = %s bits\n",(this.maxIndex + 1)));
        for (int item : array) {
            res.append(String.format("%32s\n", Integer.toBinaryString(item)).replaceAll(" ","0"));
        }
        System.out.println(res);
    }

    // sub methods:
    private void checkInterval(int index) throws Exception {
        if (!(0 <= index && index <= maxIndex)) {
            throw new Exception(String.format("Индекс %s не принадлежит интервалу [ 0, %s ]!", index, this.maxIndex));
        }
    }

    private int getArrayPosition(int n) {
        return (SIZE - 1) - n / Integer.SIZE;
        // return n / Integer.SIZE;
    }

    private int getBitPosition(int n) {
        //return (Integer.SIZE - 1) - n % Integer.SIZE;
        return n % Integer.SIZE;
    }
}