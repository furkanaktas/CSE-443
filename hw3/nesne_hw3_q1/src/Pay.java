public class Pay implements TurboPayment {

    /**
     *  TurboPayment ödeme methodu
     *
     * @param turboCardNo
     * @param turboAmount
     * @param destinationTurboOfCourse
     * @param installmentsButInTurbo
     * @return ödeme miktarı
     */
    @Override
    public int payInTurbo(String turboCardNo, float turboAmount, String destinationTurboOfCourse, String installmentsButInTurbo) {
        System.out.println("turbo card no     : "+ turboCardNo +"\n"+
                           "turbo Amount      : "+ turboAmount +"\n"+
                           "turbo destination : "+ destinationTurboOfCourse +"\n"+
                           "turbo install     : "+ installmentsButInTurbo);
        return 10;
    }
}
