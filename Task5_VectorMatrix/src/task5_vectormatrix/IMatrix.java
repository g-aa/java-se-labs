package task5_vectormatrix;

import java.io.IOException;


public interface IMatrix {

    public abstract IMatrix add(IMatrix other) throws NullPointerException, MatrixOperationException;

    public abstract String matrixToString();

    public abstract boolean equals(IMatrix other) throws NullPointerException;

    public abstract int getValue(int rowIdx, int colIdx) throws MatrixIndexException;

    public abstract int setValue(int value, int rowIdx, int colIdx) throws MatrixIndexException;

    public abstract void consolePrint();

    public abstract int getCountRows();

    public abstract int getCountCols();

    public abstract int[] getMatrixRow(int rowIdx) throws MatrixIndexException;

    // public abstract IMatrix fileRead(String path) throws IOException;   // Непонятно зачем они тут !!!

    // public abstract void fileWrite(String path) throws IOException;     // Непонятно зачем они тут !!!
}
