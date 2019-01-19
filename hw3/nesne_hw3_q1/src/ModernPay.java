public class ModernPay implements ModernPayment {

    /**
     *  modernPayment ödeme methodu
     *
     * @param cardNo
     * @param amount
     * @param destination
     * @param installments
     * @return ödeme miktarı
     */
    @Override
    public int pay(String cardNo, float amount, String destination, String installments) {
        System.out.println("modern card no     : "+ cardNo +"\n"+
                           "modern Amount      : "+ amount +"\n"+
                           "modern destination : "+ destination +"\n"+
                           "modern install     : "+ installments);
        return 0;
    }
}
