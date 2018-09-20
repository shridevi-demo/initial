package co.tanay.demo.junit.parser;

public class ParseServiceImpl implements ParseServices {
    @Override
    public PaymentInitWrapper parseRequestMessage(String message) {
        PaymentInitWrapper paymentInitWrapper = new PaymentInitWrapper();

        // .. code logic

        return paymentInitWrapper;
    }
}
