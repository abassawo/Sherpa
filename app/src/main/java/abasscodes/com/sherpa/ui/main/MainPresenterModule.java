package abasscodes.com.sherpa.ui.main;

import javax.inject.Singleton;

import abasscodes.com.sherpa.data.repository.BookDataSource;
import abasscodes.com.sherpa.data.repository.local.BookLocalDataSource;
import abasscodes.com.sherpa.data.repository.local.Local;
import abasscodes.com.sherpa.data.repository.remote.BookRemoteDataSource;
import abasscodes.com.sherpa.data.repository.remote.Remote;
import dagger.Module;
import dagger.Provides;

@Module
public class MainPresenterModule {

    private MainContract.View view;

    public MainPresenterModule(MainContract.View view) {
        this.view = view;
    }

    @Provides
    public MainContract.View provideView() {
        return view;
    }
}
