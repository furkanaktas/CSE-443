package sample;

import javafx.scene.control.TextArea;

import java.io.*;
import java.util.Arrays;

public class SolutionProcess {
    private LinearSolver solver;
    private File file;
    private int rowNumber;
    private TextArea matrixArea;

    public SolutionProcess(LinearSolver solver, File file, int rowNumber, TextArea matrixArea){
        this.solver = solver;
        this.file = file;
        this.rowNumber = rowNumber;
        this.matrixArea = matrixArea;
    }

    /*
        solution'u gerçekler, "solver" gauss veya inverse olabilir
        @return nx1 sonuç matrix'i
    */
    public double[] makeSolution(){
        double [][]matrix = getMatrix();
        if (matrix == null)
            return null;
        else
            return solver.makeSolution(matrix);
    }

    /*
        Dosyadan matrix'i okur ve double [][] tipinde return eder.
        @return matrix'i çift boyulu array olarak döner
    */
    private double [][] getMatrix(){
        try {

            int row = rowNumber;
            int col = row+1;

            double matrix[][] = new double[row][];

            for(int i=0; i<row; ++i){
                matrix[i] = new double[col];
            }

            File file = this.file;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();    // satır okundu
            String matrixText ="";
            for (int i = 0; i < row; i++) {
                matrixText += line+"\n";
                String tokens[] = line.split(",");
                for (int j = 0; j < col; j++) {
                    matrix[i][j] = Double.parseDouble(tokens[j]);
                }
                line = reader.readLine();
            }
            matrixArea.setText(matrixText);
            //  veriler dosyadan matrix'e alındı.
            return matrix;
        }
        catch (IndexOutOfBoundsException e)
        {
            matrixArea.setStyle("-fx-text-fill: #ff0000; ");    // hata için renk kırmızı yapıldı
            matrixArea.setText("Matrix fault !!");
        }
        catch (FileNotFoundException e){
            matrixArea.setStyle("-fx-text-fill: #ff0000; ");
            matrixArea.setText("Matrix is not found !!");
        }
        catch (IOException e){
            matrixArea.setStyle("-fx-text-fill: #ff0000; ");
            matrixArea.setText("File is not opened !!");
        }
        return null;
    }
}
