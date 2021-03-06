import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MatrixGetter {

    private static int dimensions = 0;

    private static InputStream getSource() {
        Scanner in = new Scanner(System.in);
        System.out.print("How do you want to input your system( keyboard/file ): ");
        String inputType = in.nextLine().trim();

        if (inputType.equals("file") || inputType.equals("f")) {
            System.out.print("Path to your file: ");
            String filePath = in.nextLine().trim();
            InputStream srcStream = null;
            try {
                srcStream = new FileInputStream(
                        new File(filePath));
                return srcStream;
            } catch (FileNotFoundException e) {
                System.out.println("File on the path has not been found");
                System.exit(-1);
            }
        } else if (inputType.equals("keyboard") || inputType.equals("k")) {
            System.out.println("Write your system's coefficients:");
            return System.in;
        } else {
            System.out.println("There is no such command. Check your spelling");
            System.exit(-1);
        }
        return null;
    }

    private static Double[] getRow(BufferedReader reader) throws IOException {
        String stringOfValues = reader.readLine();
        if (stringOfValues == null) {
            System.out.println("Not enough data");
            System.exit(-1);
        }
        String[] values = stringOfValues.trim().split(" +");
        if (values.length < 2) {
            System.out.println("Not enough values");
            System.exit(-1);
        }
        if (values.length == dimensions + 1 || dimensions == 0) {
            Double[] row = new Double[values.length];
            for (int i = 0; i < values.length; i++) {
                if (values[i].matches("-?\\d*(,\\d+)?")) {
                    row[i] = Double.parseDouble(values[i].replace(",", "."));
                } else {
                    System.out.println("Incorrect value received. Check if you use ',' instead of '.' in fractions");
                    System.exit(-1);
                }
            }

            return row;
        } else {
            System.out.println("Incorrect row length");
            System.exit(-1);
        }

        return null;
    }

    public static Matrix getMatrix() {
        List<Double[]> matrixRows = null;
        try {
            InputStream srcStream = getSource();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(srcStream));
            matrixRows = new ArrayList<>();
            Double[] firstRow = getRow(reader);
            dimensions = firstRow.length - 1;
            matrixRows.add(firstRow);

            for (int i = 1; i < dimensions; i++) {
                matrixRows.add(getRow(reader));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        dimensions = 0;
        return new Matrix(matrixRows);
    }

    public static Matrix getRandomMatrix(int rows) {
        List<Double[]> matrix = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            Double[] row = new Double[rows + 1];
            for (int j = 0; j < rows + 1; j++) {
                row[j] = Math.random() * 100 - 50;
            }
            matrix.add(row);
        }
        return new Matrix(matrix);
    }

}
