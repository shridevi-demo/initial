package co.tanay.demo.junit;

import co.tanay.demo.junit.contracts.LoginContract;
import co.tanay.demo.junit.contracts.UserCache;

public class LoginPresenter implements LoginContract.Presenter {


    private UserCache cache;
    private LoginContract.View view;

    public LoginPresenter(UserCache cache) {
        this.cache = cache;
    }

    @Override
    public void attach(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void dettach() {
        this.view = null;
    }

    @Override
    public void login(String username, String password) {

        if (username == null || "".equals(username.trim())) {
            view.onInvalidUsername();
            return;
        }

        if (password == null || "".equals(password.trim())) {
            view.onInvalidPassword();
            return;
        }

        UserCache.Status status = cache.fetch(username, password);
        switch (status) {
            case SUCCESS:
                view.onLoginSuccessfull();
                break;

            case INVALID_PASSWORD:
                view.onInvalidCredential();
                break;

            case NOT_FOUND:
                view.onNoUser();
                break;
        }
    }

    @Override
    public void signup(String username, String password) {

        if (username == null || "".equals(username.trim())) {
            view.onInvalidUsername();
            return;
        }

        if (password == null || "".equals(password.trim())) {
            view.onInvalidPassword();
            return;
        }

        UserCache.Status status = cache.store(username, password);
        switch (status) {
            case SUCCESS:
                view.onRegisterSuccessfull();
                break;

            case INVALID_PASSWORD:
                view.onInvalidPassword();
                break;

            case USER_ALREADY_EXISTS:
                view.onDuplicateUser();
                break;
        }
    }
}
