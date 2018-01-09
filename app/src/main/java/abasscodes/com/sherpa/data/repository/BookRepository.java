package abasscodes.com.sherpa.data.repository;

import android.support.annotation.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import abasscodes.com.sherpa.data.model.Book;
import abasscodes.com.sherpa.data.repository.BookDataSource;
import abasscodes.com.sherpa.data.repository.local.Local;
import abasscodes.com.sherpa.data.repository.remote.Remote;
import io.reactivex.Flowable;

public class BookRepository implements BookDataSource {

    private BookDataSource remoteDataSource;
    private BookDataSource localDataSource;

    @VisibleForTesting
    List<Book> caches;

    @Inject
    public BookRepository(@Local BookDataSource localDataSource, @Remote BookDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;

        caches = new ArrayList<>();
    }

    @Override public Flowable<List<Book>> loadBooks(boolean forceRemote) {
        if (forceRemote) {
            return refreshData();
        } else {
            if (caches.size() > 0) {
                // if cache is available, return it immediately
                return Flowable.just(caches);
            } else {
                // else return data from local storage
                return localDataSource.loadBooks(false)
                        .take(1)
                        .flatMap(Flowable::fromIterable)
                        .doOnNext(book -> caches.add(book))
                        .toList()
                        .toFlowable()
                        .filter(list -> !list.isEmpty())
                        .switchIfEmpty(
                                refreshData()); // If local data is empty, fetch from remote source instead.
            }
        }
    }

    /**
     * Fetches data from remote source.
     * Save it into both local database and cache.
     *
     * @return the Flowable of newly fetched data.
     */
    Flowable<List<Book>> refreshData() {
        return remoteDataSource.loadBooks(true).doOnNext(list -> {
            // Clear cache
            caches.clear();
            // Clear data in local storage
            localDataSource.clearData();
        }).flatMap(Flowable::fromIterable).doOnNext(book -> {
            caches.add(book);
            localDataSource.addBook(book);
        }).toList().toFlowable();
    }

    /**
     * Loads a book by its book id.
     *
     * @return a corresponding book from cache.
     */
    public Flowable<Book> getBook(long bookId) {
        return Flowable.fromIterable(caches).filter(book -> book.getId() == bookId);
    }

    @Override public void addBook(Book book) {
        //Currently, we do not need this.
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override public void clearData() {
        caches.clear();
        localDataSource.clearData();
    }
}
