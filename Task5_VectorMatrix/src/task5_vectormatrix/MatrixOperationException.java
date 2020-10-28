package task5_vectormatrix;

public class MatrixOperationException extends Exception {
    private String operation;

    private int rows_M1;
    private int cols_M1;

    private int rows_M2;
    private int cols_M2;

    public MatrixOperationException(String operation, int rows_M1, int cols_M1, int rows_M2, int cols_M2) {
        super(operation + " fault, matrix 1 [" + rows_M1 + "x" + cols_M1 + "], matrix 2 dim[" + rows_M2 + "x" + cols_M2 +"]");
    }

    public int getRows_M1() {
        return rows_M1;
    }

    public int getCols_M1() {
        return cols_M1;
    }

    public int getRows_M2() {
        return rows_M2;
    }

    public int getCols_M2() {
        return cols_M2;
    }
}
