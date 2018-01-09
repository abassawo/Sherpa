package abasscodes.com.sherpa.ui.main;

import java.util.List;

import abasscodes.com.sherpa.data.model.Book;
import abasscodes.com.sherpa.ui.base.BasePresenter;

public interface MainContract {
    interface View {
        void showBooks(List<Book> books);

        void clearBook();

        void showNoDataMessage();

        void showErrorMessage(String error);

        void showBookDetail(Book book);

        void stopLoadingIndicator();

        void showEmptySearchResult();
    }

    interface Presenter extends BasePresenter<View> {
        void loadBook(boolean onlineRequired);

        void getBook(long questionId);

        void search(String title);
    }

}