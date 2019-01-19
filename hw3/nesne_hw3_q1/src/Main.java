public class Main {

    public static void main(String[] args) {
        TurboPayment  payment = new Pay();
        ModernPayment payment1 = new ModernPay();

        TurboPayment adapter = new ModernPaymentAdapter(payment1);

        System.out.println("Pay in turbo");
        payment.payInTurbo("12345",61,"something","something");

        System.out.println("\nPay in modern");
        payment1.pay("12345",61,"something","something");

        System.out.println("\nPay in modern with Turbo");
        adapter.payInTurbo("12345",61,"something","something");
    }
}
