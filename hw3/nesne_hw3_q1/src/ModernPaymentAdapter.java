public class ModernPaymentAdapter implements TurboPayment {
    private ModernPayment payment;

    public ModernPaymentAdapter(ModernPayment payment) {
        this.payment = payment;
    }

    /**
     *  Turbo override edilip, modern payment ile ödeme yapılmış oldu.
     *
     * @param cardNo
     * @param amount
     * @param destination
     * @param installments
     * @return ödeme miktarı
     */
    @Override
    public int payInTurbo(String cardNo, float amount, String destination, String installments) {
        // translate the methods appropriately
        return payment.pay(cardNo, amount, destination, installments);
    }
}
