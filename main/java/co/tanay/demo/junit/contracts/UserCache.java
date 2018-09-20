package co.tanay.demo.junit.contracts;

public interface UserCache {

    enum Status {
        USER_ALREADY_EXISTS,
        INVALID_PASSWORD,
        NOT_FOUND,
        SUCCESS
    }

    Status store(String username, String password);
    Status fetch(String username, String password);
}
