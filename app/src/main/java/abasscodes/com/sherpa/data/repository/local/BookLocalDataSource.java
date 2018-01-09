package abasscodes.com.sherpa.data.repository.local;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import abasscodes.com.sherpa.data.database.BookDao;
import abasscodes.com.sherpa.data.model.Book;
import abasscodes.com.sherpa.data.repository.BookDataSource;
import io.reactivex.Flowable;

public class BookLocalDataSource implements BookDataSource {
    private BookDao bookDao;

    @Inject
    public BookLocalDataSource(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Flowable<List<Book>> loadBooks(boolean forceRemote) {
        List list = new ArrayList<>();
        list.add(new Book("Things fall apart"));
        list.add(new Book("The Memory Palace"));
        return Flowable.just(list);
    }

    @Override
    public void addBook(Book question) {

    }

    @Override
    public void clearData() {

    }
}
