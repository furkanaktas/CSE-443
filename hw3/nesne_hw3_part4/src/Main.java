
public class Main {

    public static void main(String[] args) {
        Transform dft = new DFT();
        Transform dct = new DCTII();

        dft.doTransform();
        dct.doTransform();
    }
}
