package co.tanay.demo.junit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.tanay.demo.junit.contracts.LoginContract;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    LoginContract.Presenter presenter;

    public void signup(View view) {
        presenter.signup(username.getText().toString(), password.getText().toString());
    }

    public void signin(View view) {
        presenter.login(username.getText().toString(), password.getText().toString());
    }

    @Override
    public void onInvalidUsername() {
        Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInvalidPassword() {
        Toast.makeText(this, "Password should be in between 6 to 12 chars", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInvalidCredential() {
        Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoUser() {
        Toast.makeText(this, "No such user", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDuplicateUser() {
        Toast.makeText(this, "User id already exists", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterSuccessfull() {
        Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginSuccessfull() {
        Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(new RealmUserCache());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        presenter.dettach();
        super.onDestroy();
    }
}
