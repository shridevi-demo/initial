package co.tanay.demo.junit.parser;

public class PaymentInitWrapper {

    String mmbId;
//    long creationTime; // 0
    Long creationTime; // null

    public String getMmbId() {
        return mmbId;
    }

    public void setMmbId(String mmbId) {
        this.mmbId = mmbId;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
