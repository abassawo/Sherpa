package abasscodes.com.sherpa.data.repository;

import java.util.List;

import abasscodes.com.sherpa.data.model.Book;
import io.reactivex.Flowable;

public interface BookDataSource {
    Flowable<List<Book>> loadBooks(boolean forceRemote);

    void addBook(Book question);

    void clearData();
}
