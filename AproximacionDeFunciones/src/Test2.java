
public class Test2 {
	
    public static void main(String[] args) {
        double[] x = {0,0.5,1,1.5}, 
        		 y = { 1, 1.64872, 2.71828, 4.48169 };
        int n = x.length;

        double[][] L = matrizDeLagrange(x, n);

        System.out.println("Matriz de Lagrange:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%10.6f ", L[i][j]);
            }
            System.out.println();
        }
        double[] polinomio = new double[n];
        double acu;
        for (int i = 0; i < n; i++) {
        	acu = 0;
            for (int j = 0; j < n; j++) {
                acu += L[i][j] * y[j];
            }
            polinomio[i] = acu ;
            System.out.println("Polinomio["+i+"]: " + polinomio[i]);
        }
    }

    public static double[][] matrizDeLagrange(double[] x, int n) {
        double[][] L = new double[n][n];
        double[] Q = extraerCoef(x, n);

        for (int i = 0; i < n; i++) {
            RuffiniResult result = ruffini(Q, n, x[i]);
            double dQ = result.dQ;
            double[] C = result.C;

            for (int j = 0; j < n; j++) {
                L[j][i] = C[j + 1] / dQ;
            }
        }

        return L;
    }

    public static double[] extraerCoef(double[] x, int n) {
        double[] Q = new double[n + 1];
        Q[0] = -x[0];
        for (int i = 1; i <= n; i++) {
            Q[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 1; j--) {
                Q[j] = Q[j - 1] - Q[j] * x[i];
            }
            Q[0] = -Q[0] * x[i];
        }

        return Q;
    }

    public static RuffiniResult ruffini(double[] Q, int n, double a) {
        double[] C = new double[n + 1];
        C[n] = Q[n];
        double dQ = 0;

        for (int i = n - 1; i >= 0; i--) {
            dQ = C[i + 1] + a * dQ;
            C[i] = Q[i] + a * C[i + 1];
        }

        return new RuffiniResult(dQ, C);
    }

    static class RuffiniResult {
        double dQ;
        double[] C;

        RuffiniResult(double dQ, double[] C) {
            this.dQ = dQ;
            this.C = C;
        }
    }
}
