package task5_vectormatrix;

import java.io.*;
import java.util.Random;

public class IntMatrix implements IMatrix {

    private static String colsSeparator = "\t";
    protected int [][] array_2D;

    public IntMatrix() {
        array_2D = new int[0][0];
    }

    public IntMatrix(int rows, int cols) throws IllegalArgumentException {
        if (rows > 0 && cols > 0) {
            array_2D = new int[rows][];
            for (int r = 0; r < rows; r++) {
                array_2D[r] = new int[cols];
            }
        } else {
            throw new IllegalArgumentException("Matrix rows or cols < 0 !");
        }
    }

    public IntMatrix(int[][] other) throws NullPointerException, MatrixNotRectangleException {
        if(other == null) {
            throw new NullPointerException("Other is null !");
        }

        int k = other[0].length;
        for (int r = 1; r < other.length; r++) {
            if (k != other[r].length){
                throw new MatrixNotRectangleException();
            }
        }

        array_2D = new int[other.length][];
        for (int r = 0; r < other.length; r++) {
            array_2D[r] = new int[other[r].length];
            System.arraycopy(other[r], 0, array_2D[r], 0, other[r].length);
        }
    }

    public IntMatrix(IMatrix other) throws NullPointerException, MatrixIndexException {
        if(other != null) {
            array_2D = new int[other.getCountRows()][];
            for (int r = 0; r < other.getCountRows(); r++) {
                array_2D[r] = new int[other.getCountCols()];
                System.arraycopy(other.getMatrixRow(r), 0, array_2D[r], 0, other.getCountCols());
            }
        } else {
            throw new NullPointerException("Other is null !");
        }
    }

    public String toString() {
        return ("Matrix: " + array_2D.length + "x" + array_2D[0].length);
    }

    public String matrixToString() {
        StringBuilder resultStr = new StringBuilder(this.toString()).append("\n");
        if (array_2D != null) {
            for (int[] row : array_2D) {
                for (int col : row) {
                    resultStr.append(col).append(colsSeparator);
                }
                resultStr.append("\n");
            }
        }
        return resultStr.toString();
    }

    // override methods:
    @Override
    public int getValue(int rowIdx, int colIdx) throws MatrixIndexException {
        this.checkRowIdx(rowIdx);
        this.checkColIdx(colIdx);
        return array_2D[rowIdx][colIdx];
    }

    @Override
    public int setValue(int value, int rowIdx, int colIdx) throws MatrixIndexException {
        this.checkRowIdx(rowIdx);
        this.checkColIdx(colIdx);
        int oldValue = array_2D[rowIdx][colIdx];
        array_2D[rowIdx][colIdx] = value;
        return oldValue;
    }

    @Override
    public void consolePrint() {
        System.out.println(this.matrixToString());
    }

    @Override
    public boolean equals(IMatrix other) throws NullPointerException {
        if (other == null) {
            throw new NullPointerException("Other is null !");
        }
        if(!this.checkMatrixDimension(other)) {
            return false;
        }
        try {
                for (int r = 0; r < this.getCountRows() ; r++) {
                    for (int c = 0; c < this.getCountCols() ; c++) {
                        if (array_2D[r][c] != other.getValue(r, c)) {
                            return false;
                        }
                    }
                }
        }catch (MatrixIndexException ignored) {
        }
        return true;
    }

    @Override
    public IMatrix add(IMatrix other) throws NullPointerException, MatrixOperationException {
        if (other != null) {
            if(!this.checkMatrixDimension(other)) {
                throw new MatrixOperationException("Add", getCountRows(), other.getCountRows(), getCountCols(), other.getCountCols());
            }
            try {
                    IMatrix outRes = new IntMatrix(this);
                    for (int r = 0; r < this.getCountRows(); r++) {
                        for (int c = 0; c < this.getCountCols(); c++) {
                            outRes.setValue((outRes.getValue(r, c) + other.getValue(r, c)), r, c);
                            }
                        }
                    return outRes;
            } catch (MatrixIndexException ignored){
            }
        }
        throw new NullPointerException("Other is null !");
    }

    @Override
    public int getCountCols() {
        return this.array_2D[0].length;
    }

    @Override
    public int getCountRows() {
        return this.array_2D.length;
    }

    @Override
    public int[] getMatrixRow(int rowIdx) throws MatrixIndexException {
        checkRowIdx(rowIdx);
        return this.array_2D[rowIdx];
    }

    public static void fileWrite(String path, IMatrix m) throws IOException {
        try(FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(m.matrixToString().getBytes());
        }
    }

    public static IMatrix fileRead(String path) throws IOException {
        IMatrix tempMatrix = null;
        //int [][] tempArray = null;
        try(FileReader fr = new FileReader(path)) {
            BufferedReader br = new BufferedReader(fr);
            String[] subItems = br.readLine().split(" ");
            if(!"Matrix:".equals(subItems[0])) {
                throw new IOException("fileRead fault !");
            }
            int rows = Integer.parseInt(subItems[1].split("x")[0]);
            int cols = Integer.parseInt(subItems[1].split("x")[1]);

            if(rows!= 0 && cols!= 0) {
                String temp;
                int idx = 0;
                tempMatrix = new IntMatrix(rows, cols);
                while ((temp = br.readLine()) != null) {
                    subItems = temp.split(colsSeparator);
                    if(cols != subItems.length || idx >= rows) {
                        throw new IOException("fileRead fault !");
                    }
                    //tempArray[idx] = new int[cols];
                    for (int c = 0; c < cols; c++) {
                        //tempArray[idx][c] = Integer.parseInt(subItems[c]);
                        int k = Integer.parseInt(subItems[c]);
                        tempMatrix.setValue(k, idx,c);
                    }
                    idx++;
                }
            }
        }
        catch (Exception e) {
            throw new IOException("fileRead fault !");
        }
        return tempMatrix;
    }

    public void randomized(int rows, int cols) throws IllegalArgumentException {
        Random rand = new Random();
        if (rows > 0 && cols > 0) {
            this.array_2D = new int[rows][];
            for (int r = 0; r < rows; r++) {
                array_2D[r] = new int[cols];
                for (int c = 0; c < cols; c++) {
                    array_2D[r][c] = rand.nextInt(10);
                }
            }
        } else {
            throw new IllegalArgumentException("Matrix rows or cols <= 0 !");
        }
    }

    // SubMethods:
    // возвращает false если содержимое IntMatrix null или размеры матриц не совподают:
    protected boolean checkMatrixDimension(IMatrix other) {
        return this.getCountRows() == other.getCountRows() && this.getCountCols() == other.getCountCols();
    }

    // пороверка принадлежности индексов матрице:
    protected void checkRowIdx(int rowIdx) throws MatrixIndexException {
        if(!(0 <= rowIdx && rowIdx < array_2D.length)){
            throw new MatrixIndexException(rowIdx, 0, this.getCountRows());
        };
    }

    protected void checkColIdx(int colIdx) throws  MatrixIndexException {
        if(!(0 <= colIdx && colIdx < array_2D[0].length)) {
            throw new MatrixIndexException(colIdx, 0, this.getCountRows());
        }
    }
}