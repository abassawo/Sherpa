package abasscodes.com.sherpa.data.repository.remote;

import java.util.List;

import javax.inject.Inject;

import abasscodes.com.sherpa.data.api.BookResponse;
import abasscodes.com.sherpa.data.api.BookService;
import abasscodes.com.sherpa.data.model.Book;
import abasscodes.com.sherpa.data.repository.BookDataSource;
import abasscodes.com.sherpa.data.Config;
import io.reactivex.Flowable;

public class BookRemoteDataSource implements BookDataSource {
    private BookService bookService;

    @Inject
    public BookRemoteDataSource(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Flowable<List<Book>> loadBooks(boolean forceRemote) {
        return bookService.loadBooks(Config.USER_ID).map(BookResponse::getBooks);
    }

    @Override
    public void addBook(Book question) {
        //Currently, we do not need this for remote source.
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public void clearData() {
        //Currently, we do not need this for remote source.
        throw new UnsupportedOperationException("Unsupported operation");
    }
}
