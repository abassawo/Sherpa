package abasscodes.com.sherpa.ui.main;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import abasscodes.com.sherpa.R;
import abasscodes.com.sherpa.data.model.Book;
import abasscodes.com.sherpa.data.model.BookEntryViewModel;
import abasscodes.com.sherpa.databinding.ActivityMainBinding;
import abasscodes.com.sherpa.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements LifecycleOwner, MainContract.View{
    private LifecycleRegistry mLifecycleRegistry;
    private BookEntryViewModel viewModel;
    private ActivityMainBinding root;
    @Inject MainPresenter presenter;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initializeViews();
        initializePresenter();
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);

        viewModel = ViewModelProviders.of(this).get(BookEntryViewModel.class);

        final Observer<Book> observer = new Observer<Book>() {
            @Override
            public void onChanged(@Nullable final Book book) {
                // Update the UI, in this case, a TextView.
//                updateUI(book);
            }
        };

        viewModel.getCurrentBook().observe(this, observer);
    }

    private void initializePresenter() {
        DaggerMainComponent.builder()
                .mainPresenterModule(new MainPresenterModule(this))
                .bookRepositoryComponent(getBookRepositoryComponent())
                .build()
                .inject(this);
    }

    private void initializeViews() {
        root.booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter();
        root.booksRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @Override
    public void showBooks(List<Book> books) {
        adapter.setBooks(books);
    }

    @Override
    public void clearBook() {

    }

    @Override
    public void showNoDataMessage() {

    }

    @Override
    public void showErrorMessage(String error) {

    }

    @Override
    public void showBookDetail(Book book) {

    }

    @Override
    public void stopLoadingIndicator() {

    }

    @Override
    public void showEmptySearchResult() {

    }
}
