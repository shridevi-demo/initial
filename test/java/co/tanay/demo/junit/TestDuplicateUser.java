package co.tanay.demo.junit;

import org.junit.Before;
import org.junit.Test;

import co.tanay.demo.junit.contracts.LoginContract;
import co.tanay.demo.junit.contracts.UserCache;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestDuplicateUser {

    UserCache cache;
    LoginContract.View view;
    LoginContract.Presenter presenter;

    @Before
    public void setup() {
        cache = mock(UserCache.class);
        view = mock(LoginContract.View.class);
        presenter = new LoginPresenter(cache);
        presenter.attach(view);
    }

    @Test
    public void assert_duplicate_user() {
        when(cache.store("tanay", "12345678")).thenReturn(UserCache.Status.USER_ALREADY_EXISTS);
        presenter.signup("tanay", "12345678");
        verify(view, times(1)).onDuplicateUser();
    }

    @Test
    public void assert_duplicate_user_failure() {
        when(cache.store("tanay", "12345678")).thenReturn(UserCache.Status.SUCCESS);
        presenter.signup("tanay", "12345678");
        verify(view, times(0)).onDuplicateUser();
    }
}
