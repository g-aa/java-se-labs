package task5_vectormatrix;

public class MatrixNotRectangleException extends Exception {
    public MatrixNotRectangleException() {
        super("Матрица не прямоугольная!");
    }
}
