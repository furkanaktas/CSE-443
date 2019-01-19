import java.io.*;

public class DFT extends Transform {

    private ComplexNumber [][] elements;
    private ComplexNumber [][] result;
    private int size;
    boolean willPrint;

    public DFT(boolean willPrint){
        this.willPrint = willPrint; // süreyi ekrana bastırmak için true
        initialize();
    }

    public DFT(){
        willPrint = false;
        initialize();
    }

    /**
     *  constructor içinde çağrılır, variable initialize 'larını yapar.
     */
    private void initialize(){
        size =0;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File("dft")));

            String num = reader.readLine();
            while (num != null){
                ++size;
                num = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        elements = new ComplexNumber[size][];
        result = new ComplexNumber[size][];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = new ComplexNumber[2];
            result[i] = new ComplexNumber[2];
        }
    }

    /**
     *  girdileri dosyadan okur.
     */
    @Override
    public void readFile() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File("dft")));

            String num = reader.readLine();
            int index =0;
            while (num != null){
                String [] token = num.split(",");
                elements[index][0] = new ComplexNumber(Double.parseDouble(token[0]), false);
                elements[index][1] = new ComplexNumber(Double.parseDouble(token[1]), true);
                ++index;
                num = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *  sonuçları dosyaya yazar.
     */
    @Override
    public void writeFile() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File("dft_results")));
            for (ComplexNumber[] el : result) {
                //System.out.print(el[0]+","+el[1]+"\n");
                writer.write(el[0]+" "+el[1]+"\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     *  transform işlemini uygular.
     */
    @Override
    public void transform(){

        long startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            ComplexNumber real = new ComplexNumber(0, false);
            ComplexNumber complex = new ComplexNumber(0, true);;
            for (int j = 0; j <size ; j++) {
                ComplexNumber realNum = new ComplexNumber(Math.cos((2*Math.PI*i*j)/size), false);
                ComplexNumber complexNum = new ComplexNumber(-Math.sin((2*Math.PI*i*j)/size), true);

                //------------------------------------------------------------
                real = real.sum(realNum.mult(elements[j][0]));
                real = real.sum(complexNum.mult(elements[j][1]));
                                                                    // (a+ bi)*(c+ di)
                complex = complex.sum(realNum.mult(elements[j][1]));
                complex = complex.sum(complexNum.mult(elements[j][0]));
                //------------------------------------------------------------
            }
            result[i][0] = real;
            result[i][1] = complex;
        }

        long stopTime = System.nanoTime();
        long elapsedTime = stopTime - startTime;

        if (willPrint)
            System.out.println("Execution time : "+ elapsedTime + " nano second" );


    }
}
