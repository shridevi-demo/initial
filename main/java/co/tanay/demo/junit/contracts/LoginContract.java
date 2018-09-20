package co.tanay.demo.junit.contracts;

public interface LoginContract {

    interface View {
        void onInvalidUsername();
        void onInvalidPassword();
        void onInvalidCredential();
        void onNoUser();
        void onDuplicateUser();
        void onRegisterSuccessfull();
        void onLoginSuccessfull();
    }

    interface Presenter {
        void attach(View view);
        void dettach();
        void login(String username, String password);
        void signup(String username, String password);
    }
}
