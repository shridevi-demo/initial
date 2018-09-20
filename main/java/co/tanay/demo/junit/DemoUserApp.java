package co.tanay.demo.junit;

import android.app.Application;

import io.realm.Realm;

public class DemoUserApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
