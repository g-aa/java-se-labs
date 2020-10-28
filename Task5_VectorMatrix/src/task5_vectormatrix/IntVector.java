package task5_vectormatrix;

import java.lang.reflect.Array;

public class IntVector extends IntMatrix {

    public IntVector() {
        super();
    }

    public IntVector(int size) throws IllegalArgumentException {
        super(size,1);
    }

    public IntVector(int[] other) throws NullPointerException {
        if(other != null) {
            array_2D = new int[other.length][];
            for (int r = 0; r < array_2D.length; r++) {
                array_2D[r] = new int[] {other[r]};
            }
        }
        else {
            throw new NullPointerException("Other is null !");
        }
    }

    // может получить в качестве аргумента матрицу nxm в vector будет записан столбец с индексом colIdx:
    public IntVector(IntMatrix other, int colIdx) throws NullPointerException, MatrixIndexException {
        other.checkColIdx(colIdx);
        array_2D = new int[other.getCountRows()][];
        for (int r = 0; r <array_2D.length ; r++) {
            array_2D[r] = new int[]{other.getValue(r, colIdx)};
        }
    }

    // модуль длинны многомерного вектора:
    public double abs() {
        double res = 0;
        if (array_2D != null){
            for (int r = 0; r < array_2D.length; r++) {
                res += array_2D[r][0]*array_2D[r][0];
            }
        }
        return Math.sqrt(res);
    }
}