package abasscodes.com.sherpa.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import abasscodes.com.sherpa.data.model.Book;
import abasscodes.com.sherpa.data.Config;
import io.reactivex.Flowable;

@Dao
public interface BookDao {
    @Query("SELECT * FROM " + Config.BOOKS_TABLE_NAME)
    Flowable<List<Book>> getAllBooks();

    @Query("SELECT * FROM " + Config.BOOKS_TABLE_NAME + " WHERE id == :id")
    Flowable<Book> getBookById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Book question);

    @Query("DELETE FROM " + Config.BOOKS_TABLE_NAME)
    void deleteAll();
}