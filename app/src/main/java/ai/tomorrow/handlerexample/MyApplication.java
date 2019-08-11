package ai.tomorrow.handlerexample;

import android.app.Application;
import android.graphics.Bitmap;

import ai.tomorrow.base.BaseManager;

public class MyApplication extends Application {

    private BaseManager baseManager;
    @Override
    public void onCreate() {
        super.onCreate();

//        // Configure Realm for the application
//        Realm.init(this);
//        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
////        Realm.deleteRealm(realmConfiguration); // Clean slate
//        Realm.setDefaultConfiguration(realmConfiguration); // Make this Realm the default


        baseManager = new BaseManager(this);

    }

    public BaseManager getBaseManager() {
        return baseManager;
    }
}