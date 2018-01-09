package abasscodes.com.sherpa.ui.main;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import java.util.List;

import javax.inject.Inject;

import abasscodes.com.sherpa.data.model.Book;
import abasscodes.com.sherpa.data.repository.BookRepository;
import abasscodes.com.sherpa.util.schedulers.RunOn;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static abasscodes.com.sherpa.util.schedulers.SchedulerType.IO;
import static abasscodes.com.sherpa.util.schedulers.SchedulerType.UI;

public class MainPresenter implements MainContract.Presenter, LifecycleObserver {

    private BookRepository repository;

    private MainContract.View view;

    private Scheduler ioScheduler;
    private Scheduler uiScheduler;

    private CompositeDisposable disposeBag;

    @Inject
    public MainPresenter(BookRepository repository, MainContract.View view,
                              @RunOn(IO) Scheduler ioScheduler, @RunOn(UI) Scheduler uiScheduler) {
        this.repository = repository;
        this.view = view;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;

        // Initialize this presenter as a lifecycle-aware when a view is a lifecycle owner.
        if (view instanceof LifecycleOwner) {
            ((LifecycleOwner) view).getLifecycle().addObserver(this);
        }

        disposeBag = new CompositeDisposable();
    }

    @Override @OnLifecycleEvent(Lifecycle.Event.ON_RESUME) public void onAttach() {
        loadBook(false);
    }

    @Override @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE) public void onDetach() {
        // Clean up any no-longer-use resources here
        disposeBag.clear();
    }

    @Override public void loadBook(boolean onlineRequired) {
        // Clear old data on view
        view.clearBook();

        // Load new one and populate it into view
        Disposable disposable = repository.loadBooks(onlineRequired)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(this::handleReturnedData, this::handleError, () -> view.stopLoadingIndicator());
        disposeBag.add(disposable);
    }

    @Override public void getBook(long bookId) {
        Disposable disposable = repository.getBook(bookId)
                .filter(book -> book != null)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(question -> view.showBookDetail(question));
        disposeBag.add(disposable);
    }

    @Override public void search(final String bookTitle) {
        // Load new one and populate it into view
        Disposable disposable = repository.loadBooks(false)
                .flatMap(Flowable::fromIterable)
                .filter(book -> book.getTitle() != null)
                .filter(book -> book.getTitle().toLowerCase().contains(bookTitle.toLowerCase()))
                .toList()
                .toFlowable()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(books -> {
                    if (books.isEmpty()) {
                        // Clear old data in view
                        view.clearBook();
                        // Show notification
                        view.showEmptySearchResult();
                    } else {
                        // Update filtered data
                        view.showBooks(books);
                    }
                });

        disposeBag.add(disposable);
    }

    /**
     * Updates view after loading data is completed successfully.
     */
    private void handleReturnedData(List<Book> list) {
        view.stopLoadingIndicator();
        if (list != null && !list.isEmpty()) {
            view.showBooks(list);
        } else {
            view.showNoDataMessage();
        }
    }

    /**
     * Updates view if there is an error after loading data from repository.
     */
    private void handleError(Throwable error) {
        view.stopLoadingIndicator();
        view.showErrorMessage(error.getLocalizedMessage());
    }
}
