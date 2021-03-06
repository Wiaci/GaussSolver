import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Matrix implements Cloneable {

    private final List<Double[]> rows;
    private final int numOfRows;

    public Matrix(List<Double[]> rows) {
        this.rows = rows;
        numOfRows = rows.size();
    }

    public Matrix getSquareMatrix() {
        int dimensions = rows.size();
        LinkedList<Double[]> sqMatrixRows = new LinkedList<>();
        for (Double[] row : rows) {
            Double[] newRow = Arrays.copyOf(row, dimensions);
            sqMatrixRows.add(newRow);
        }
        return new Matrix(sqMatrixRows);
    }

    public Matrix getMinor(int row, int col) {
        List<Double[]> minorRows = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            if (i == row) continue;

            Double[] minorRow = new Double[rows.get(0).length - 1];
            for (int j = 0; j < rows.get(0).length; j++) {
                if (j == col) continue;
                if (j < col) {
                    minorRow[j] = rows.get(i)[j];
                } else {
                    minorRow[j - 1] = rows.get(i)[j];
                }
            }
            minorRows.add(minorRow);
        }
        return new Matrix(minorRows);
    }

    public void swapRows(int row1, int row2) {
        Double[] temp = rows.get(row1);
        rows.set(row1, rows.get(row2));
        rows.set(row2, temp);
    }

    public void multiplyRowByNumber(int row, double number) {
        Double[] multipliedRow = new Double[rows.get(0).length];
        Double[] currentRow = rows.get(row);
        for (int i = 0; i < multipliedRow.length; i++) {
            multipliedRow[i] = currentRow[i] * number;
        }
        rows.set(row, multipliedRow);
    }

    public void subtractMultipliedRowFromRow(int rowToSubtract, double multiplier, int row) {
        Double[] multipliedRow = new Double[rows.get(0).length];
        Double[] currentRow = rows.get(rowToSubtract);
        for (int i = 0; i < multipliedRow.length; i++) {
            multipliedRow[i] = currentRow[i] * multiplier;
        }
        for (int i = 0; i < numOfRows + 1; i++) {
            rows.get(row)[i] -= multipliedRow[i];
        }
    }

    public double getElement(int row, int col) {
        return rows.get(row)[col];
    }

    public void print() {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfRows + 1; j++) {
                System.out.printf("%7.3f ", rows.get(i)[j]);
            }
            System.out.println();
        }
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    @Override
    protected Object clone() {
        try { return super.clone(); }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return this;
    }
}
