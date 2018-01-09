package abasscodes.com.sherpa.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import abasscodes.com.sherpa.data.database.BookDao;
import abasscodes.com.sherpa.data.database.SherpaDb;
import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    private static final String DATABASE =  "database_name";

    @Provides
    @Named(DATABASE)
    String provideDatabaseName() {
        return Config.DATABASE_NAME;
    }

    @Provides
    @Singleton
    SherpaDb provideRemoteDao(Context context, @Named(DATABASE) String databaseName) {
        return Room.databaseBuilder(context, SherpaDb.class, databaseName).build();
    }

    @Provides
    @Singleton
    BookDao provideQuestionDao(SherpaDb sherpaDb) {
        return sherpaDb.bookDao();
    }
}