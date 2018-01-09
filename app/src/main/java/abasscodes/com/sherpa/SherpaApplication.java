package abasscodes.com.sherpa;

import android.app.Application;

import com.facebook.stetho.Stetho;

import abasscodes.com.sherpa.data.BookRepositoryComponent;
import abasscodes.com.sherpa.data.DaggerBookRepositoryComponent;

public class SherpaApplication extends Application {

    private BookRepositoryComponent dataComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDependencies();
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

    private void initializeDependencies() {
        dataComponent = DaggerBookRepositoryComponent.builder()
                .appModule(new AppModule(this)).build();
    }

    public BookRepositoryComponent getRepositoryComponent() {
        return dataComponent;
    }
}
