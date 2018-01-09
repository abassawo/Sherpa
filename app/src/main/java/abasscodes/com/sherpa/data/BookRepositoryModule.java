package abasscodes.com.sherpa.data;

import javax.inject.Singleton;

import abasscodes.com.sherpa.data.repository.BookDataSource;
import abasscodes.com.sherpa.data.repository.local.BookLocalDataSource;
import abasscodes.com.sherpa.data.repository.local.Local;
import abasscodes.com.sherpa.data.repository.remote.BookRemoteDataSource;
import abasscodes.com.sherpa.data.repository.remote.Remote;
import dagger.Module;
import dagger.Provides;

@Module
public class BookRepositoryModule {
    @Provides
    @Local
    @Singleton
    public BookDataSource provideLocalDataSource(BookLocalDataSource bookLocalDataSource) {
        return bookLocalDataSource;
    }

    @Provides
    @Remote
    @Singleton
    public BookDataSource provideRemoteDataSource(BookRemoteDataSource bookRemoteDataSource) {
        return bookRemoteDataSource;
    }

}