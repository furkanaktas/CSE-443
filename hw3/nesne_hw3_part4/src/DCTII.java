import java.io.*;
import java.util.Arrays;

public class DCTII extends Transform {

    Double [] elements;
    double [] result;
    int size;

    public DCTII(){
        size =0;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File("dct")));

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

        elements = new Double[size];
        result = new double[size];
    }

    /**
     *  girdileri dosyadan okur.
     */
    @Override
    public void readFile() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File("dct")));

            String num = reader.readLine();
            int index =0;
            while (num != null){
                elements[index] = Double.parseDouble(num);
                ++index;
                num = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     *  sonuçları dosyaya yazar.
     */
    @Override
    public void writeFile() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File("dct_results")));
            for (double el : result) {
                //System.out.print(Math.round(el)+"\n");
                writer.write(Math.round(el)+"\n");
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
    public void transform() {

        for (int i = 0; i < size; i++) {
            double sum = 0;
            for (int j = 0; j <size ; j++) {
                sum += elements[j]*Math.cos((Math.PI/size)*(j+0.5)*i);
            }
            result[i] = sum;
        }
    }
}
