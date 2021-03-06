public class SystemSolution {

    private final double determinant;
    private final Matrix triangleMatrix;
    private final double[] unknowns;
    private final double[] residuals;

    public SystemSolution(double determinant, Matrix triangleMatrix, double[] unknowns, double[] residuals) {
        this.determinant = determinant;
        this.triangleMatrix = triangleMatrix;
        this.unknowns = unknowns;
        this.residuals = residuals;
    }

    public void printSolution() {
        System.out.println("\nОпределитель: " + determinant);
        if (determinant != 0) {
            System.out.println("\nТреугольная матрица:");
            triangleMatrix.print();

            System.out.println("\nСтолбец неизвестных:");
            for (int i = 0; i < unknowns.length; i++) {
                System.out.println("x" + (i + 1) + " = " + unknowns[i]);
            }

            System.out.println("\nСтолбец невязок:");
            for (int i = 0; i < residuals.length; i++) {
                System.out.println("x" + (i + 1) + ": " + residuals[i]);
            }
        } else {
            System.out.println("Система имеет бесконечное число решений / не имеет решений");
        }
    }

}
