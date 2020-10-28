package task5_vectormatrix;

public class MatrixIndexException extends Exception {
    private int faultIdx;
    private int firstIdx;
    private int lastIdx;

    public MatrixIndexException(int faultIdx, int firstIdx, int lastIdx) {
        super("Out of range matrix index = " + faultIdx + ", matrix index [" + firstIdx + ", " + lastIdx + "]!");
        this.faultIdx = faultIdx;
        this.firstIdx = firstIdx;
        this.lastIdx = lastIdx;
    }

    public int getFaultIdx() {
        return this.faultIdx;
    }

    public int getFirstIdx() {
        return this.firstIdx;
    }

    public int getLastIdx(){
        return this.lastIdx;
    }
}
