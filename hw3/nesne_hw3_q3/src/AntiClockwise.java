import java.util.Iterator;
import java.util.NoSuchElementException;

public class AntiClockwise<E> implements IteratorGet<E> {

    private E [][] array;

    public AntiClockwise(E [][] arr){
        this.array = arr;
    }

    /**
     *
     * @return true if next element exits
     */
    public Iterator<E> getIter(){
        return new AntiClockwise.MyIter(array);
    }

    private class MyIter implements Iterator<E> {
        private int count, currBound, rowSize, colSize, i, j, way, size;
        private E [][] array;

        public MyIter(E [][] arr){
            count =0; i=0; j=0; way=0;
            array = arr;
            rowSize = arr.length;
            colSize = arr[0].length;
            currBound = rowSize;
            size = colSize*rowSize;
        }

        /**
         *
         * @return true if next element exits
         */
        public boolean hasNext(){
            return count < size;
        }

        /**
         *
         * @return next element
         */
        public E next(){

            if (count < currBound && way == 0){ // yukarıdan aşağı
                E retVal = array[i][j];
                ++count;
                ++i;
                if (count == currBound){
                    currBound = currBound + colSize -1;
                    ++j;
                    i = rowSize-1;
                    way = 1;
                }
                return retVal;
            }
            else if(count < currBound && way == 1){  // soldan sağa
                E retVal = array[i][j];
                ++count;
                ++j;
                if (count == currBound){
                    currBound = currBound + rowSize -1;
                    --i;
                    j= colSize-1;
                    way = 2;
                }
                return retVal;
            }
            else if (count < currBound && way == 2){ // aşağıdan yukarı
                E retVal = array[i][j];
                ++count;
                --i;
                if (count == currBound){
                    currBound = currBound + colSize -2;
                    --j;
                    i=0;
                    way = 3;
                }
                return retVal;
            }
            else if (count < currBound && way == 3){    // sağdan sola
                E retVal = array[i][j];
                ++count;
                --j;

                return retVal;
            }


            // tur tamamlandı

            rowSize = rowSize-2;    // tur tamamlandığında karşılıklı kenarlar 2 şer azalır.
            colSize = colSize-2;
            size = rowSize*colSize;

            if (rowSize ==0|| colSize ==0 )     // tur tamamlandıktan sonra row yada col 0 ise daha gezilecek eleman kalmamıştır
                throw new NoSuchElementException();
            else
            {
                E [][] tempArr = (E[][]) new Object[rowSize][];
                for (int k = 0; k < rowSize; k++) {
                    tempArr[k] = (E[])new Object[colSize];
                }
                for (int k = 0; k < rowSize; k++) {
                    for (int l = 0; l < colSize ; l++) {    // gezilecek yeni array(içteki) oluşturuldu
                        tempArr[k][l] = array[1+k][1+l];
                    }
                }

                array = tempArr;

                E retVal;
                i=0; j=0; count=0;
                if(rowSize == 1)        // row 1 ise , soldan sağa case'i ayarlandı
                {
                    retVal = array[i][j];
                    way=1;
                    currBound= colSize;
                    ++count;
                    ++j;
                    if (count == currBound){
                        currBound = currBound + rowSize -1;
                        --i;
                        way = 2;
                    }
                    return retVal;
                }
                else    // değilse yukarıdan aşağı inme durumunun case'leri ayarlandı
                {
                    retVal = array[i][j];
                    way=0;
                    currBound= rowSize;
                    ++count;
                    ++i;
                    if (count == currBound){
                        currBound = currBound + colSize -1;
                        ++j;
                        way = 1;
                    }
                    return retVal;
                }

            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
