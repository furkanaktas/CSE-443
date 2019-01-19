package sample;

public class GaussianElimination implements LinearSolver {
    /*
    @return nx1 sonuç matrix'i
    */
    @Override
    public double [] makeSolution(double [][] matrix) {
        int n = matrix[0].length-1;
        double scaled[] = new double[n];

        for(int i=0; i< n;++i){

            double max = Math.abs(matrix[i][0]);
            for (int k=0;k<n;++k){

                if(max < Math.abs(matrix[i][k]))            // scaled  matris belir lendi , S = (....)
                    max= Math.abs(matrix[i][k]);
            }
            scaled[i]=max;
        }

        for (int i=0; i< scaled.length; ++i){
            if (scaled[i] == 0) {
                throw new RuntimeException("Matrix is singular or nearly singular");
            }
        }

        int index;
        for(int p=0; p< n;++p) {
            double max= Math.abs(matrix[p][p]/scaled[p]);
            index=p;
            for (int i=p;i<n; ++i)
            {
                if(max < Math.abs(matrix[i][p]/scaled[i]) ) {       // scaled la oranlandı  ve  en büyük oran index
                    max = Math.abs(matrix[i][p] / scaled[i]);
                    index = i;
                }
            }

            double temp[][]= new double[n][];

            for(int i=0; i<n; ++i){
                temp[i] = new double[n+1];
            }

            for(int i=0;i<n;++i) {
                for (int j = 0; j < n+1; ++j)
                    temp[i][j]=matrix[i][j];
            }

            for (int j = 0; j < n+1; ++j){
                matrix[p][j] = temp[index][j];
                matrix[index][j] = temp[p][j];
            }

            double temp2 = scaled[p];
            scaled[p] = scaled[index];
            scaled[index] = temp2;


            if (Math.abs(matrix[p][p]) <= 0.001) {
                throw new RuntimeException("Matrix is singular or nearly singular");
            }

            for (int i = p + 1; i < n; i++) {                   // elimination
                double alpha = matrix[i][p] / matrix[p][p];
                matrix[i][n] -= alpha * matrix[p][n];
                for (int j = p; j < n; j++) {
                    matrix[i][j] -= alpha * matrix[p][j];
                }
            }
        }

        // back substitution
        double[] result = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += matrix[i][j] * result[j];
            }
            result[i] = Math.round((matrix[i][n] - sum) / matrix[i][i]* 100.0)/100.0;
        }

        return result;
    }

}
