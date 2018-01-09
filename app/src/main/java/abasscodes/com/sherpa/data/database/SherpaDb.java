package abasscodes.com.sherpa.data.database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

import abasscodes.com.sherpa.data.model.Book;

@Database(entities = Book.class, version = 1)
public abstract class SherpaDb extends RoomDatabase {

    public abstract BookDao bookDao();
}
