public class ComplexNumber {
    private Double num;
    private boolean isComplex;

    public ComplexNumber(double num, boolean isComplex){
        this.num = num;
        this.isComplex = isComplex;
    }

    /**
     *
     * @return sayı değeri
     */
    public double getNumber(){return num;}

    /**
     *
     * @return true if num is complex
     */
    public boolean isComplex(){return isComplex;}

    /**
     *  Sayının complex yada reel olma durumlarına göre çarpma işlemi yapar.
     *
     * @param o diğer sayıyı
     * @return yeni sayı (ComplexNumber)
     */
    public ComplexNumber mult(ComplexNumber o) {
        if (isComplex && o.isComplex) {
            return new ComplexNumber(-(num * o.num), false);
        } else if (!isComplex && !o.isComplex) {
            return new ComplexNumber(num * o.num, false);
        }else
            return new ComplexNumber(num * o.num, true);
    }

    /**
     *  2'side complex yada reel ise sayı değerlerini toplar ve yeni ComplexNumber return eder
     *
     * @param o diğer sayıyı
     * @return yeni sayı (ComplexNumber)
     */
    public ComplexNumber sum(ComplexNumber o) {
        if (isComplex && o.isComplex) {
            return new ComplexNumber(num + o.num, true);
        } else if (!isComplex && !o.isComplex) {
            return new ComplexNumber(num + o.num, false);
        }else
            return null;
    }

    /**
     *  complex yada reel olmasına göre string return eder
     *
     * @return string
     */
    public String toString(){
        String result ="";
        long resultNum = Math.round(num.doubleValue());
        if(isComplex){
            if(num < 0)
            {
                result = "- "+(-resultNum)+"i";
            }else
                result = "+ " +resultNum+"i";
        }else {
            result = ""+resultNum;
        }

        return result;
    }

}
