public class Gauss {

    public static void main(String[] args) {
        Matrix system;
        if (args.length == 0) {
            system = MatrixGetter.getMatrix();
        } else {
            system = MatrixGetter.getRandomMatrix(Integer.parseInt(args[0]));
            system.print();
        }
        SystemSolver.solveSystem(system).printSolution();
    }
}