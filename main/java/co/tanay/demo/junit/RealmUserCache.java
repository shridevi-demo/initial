package co.tanay.demo.junit;

import co.tanay.demo.junit.contracts.UserCache;
import co.tanay.demo.junit.models.User;
import io.realm.Realm;

public class RealmUserCache implements UserCache {

    private Realm realm;

    public RealmUserCache() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public Status store(final String username, final String password) {
        User user = realm.where(User.class).equalTo("username", username).findFirst();
        if (user != null) return Status.USER_ALREADY_EXISTS;
        if (password.length() < 6 || password.length() > 12) return Status.INVALID_PASSWORD;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(new User().setUsername(username).setPassword(password));
            }
        });
        return Status.SUCCESS;
    }

    @Override
    public Status fetch(String username, String password) {
        User user = realm.where(User.class).equalTo("username", username).findFirst();
        if (user == null) return Status.NOT_FOUND;
        user = realm.where(User.class).equalTo("username", username)
                .equalTo("password", password)
                .findFirst();
        return user == null ? Status.INVALID_PASSWORD : Status.SUCCESS;
    }
}
