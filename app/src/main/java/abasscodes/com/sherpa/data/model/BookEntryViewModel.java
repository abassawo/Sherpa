package abasscodes.com.sherpa.data.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class BookEntryViewModel extends ViewModel {

    private MutableLiveData<Book> currentBook;

    public MutableLiveData<Book> getCurrentBook() {
        if (currentBook == null) {
            currentBook = new MutableLiveData<Book>();
        }
        return currentBook;
    }

}
