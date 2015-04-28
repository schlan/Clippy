package at.droelf.clippy;

import android.app.Application;

import timber.log.Timber;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(Global.INSTANCE.getLogTree());
    }
}
