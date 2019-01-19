import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        int row=4, col=4;
        Integer [][] arr = new Integer[row][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Integer[col];
        }

        int count = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[i][j] = count;
                System.out.format("%3d " ,count );
                ++count;
            }
            System.out.println();
        }

        System.out.println();
        IteratorGet<Integer> clockwise = new Clockwise(arr);
        IteratorGet<Integer> antiClockwise = new AntiClockwise(arr);

        Iterator<Integer> iter = clockwise.getIter();
        Iterator<Integer> antiIter = antiClockwise.getIter();

        System.out.println("Clockwise");
        while (iter.hasNext()) {
            System.out.print(iter.next()+" ");
        }


        System.out.println("\n");


        System.out.println("AntiClockwise");
        while (antiIter.hasNext())
            System.out.print(antiIter.next()+" ");


        System.out.println("\n");
        //---------------------------------- String örneği ----------------------

        row=4; col=4;
        String [][] arr2 = new String[row][];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = new String[col];
        }

        System.out.println("Clockwise String örneği");

        count = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr2[i][j] = "a"+count;
                System.out.format("%3s " ,"a"+count );
                ++count;
            }
            System.out.println();
        }
        System.out.println();

        IteratorGet<String> clockwise2 = new Clockwise(arr2);
        Iterator<String> iter3 = clockwise2.getIter();

        while (iter3.hasNext())
            System.out.print(iter3.next()+" ");

        System.out.println();

    }
}
