package sample;

public class MatrixInversion implements LinearSolver {
    /*
    @return nx1 sonuç matrix'i
    */
    @Override
    public double [] makeSolution(double [][] matrix) {
        int row = matrix[0].length-1;

        double [][] bMatrix = new double[row][];
        double [][] inverseMatrix = new double[row][];
        for (int i = 0; i < row; i++) {
            bMatrix[i] = new double[1];
            inverseMatrix [i] = new double[row];
        }


        for (int i = 0; i < row ; i++) {
            bMatrix[i][0] = matrix[i][row]; // initializing bMatrix
            for (int j = 0; j < row ; j++) {
                if (i == j){
                    inverseMatrix[i][j] = 1;
                }
                else{
                    inverseMatrix[i][j] = 0;
                }
            }
        } // birim matrix elde edildi

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (i == j){
                    double pivot = matrix[i][j];
                    for (int k = 0; k < row; k++) {     // satır pivot'a bölündü (köşegen matris teki eleman 1 oldu)
                        matrix[i][k] /= pivot;
                        inverseMatrix[i][k] /= pivot;
                    }

                    for (int k = 0; k < row; k++) {
                        if (k != i){
                            pivot = matrix[k][j];
                            for (int l = 0; l < row; l++) {
                                matrix[k][l] -= pivot*matrix[i][l];  // pivot satır hariç diğer satırlardaki ilgili sütun değerleri 0' landı
                                inverseMatrix[k][l] -= pivot*inverseMatrix[i][l];
                            }
                        }
                    }
                }
            }
        }   // inverseMatrix elde edildi



        double [] result = new double[row];
        for (int i = 0; i < row; i++) {
            result[i] =0;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row ; j++) {
                result[i] += inverseMatrix[i][j] * bMatrix[j][0];       // A^-1 * b = X
            }
        }

        for (int i = 0; i < row ; i++) {
            result[i] = Math.round(result[i]* 100.0)/100.0;     // üste yuvarlandı
        }
        
        return result;
    }
}
