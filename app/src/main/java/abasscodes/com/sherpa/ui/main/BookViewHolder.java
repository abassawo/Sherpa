package abasscodes.com.sherpa.ui.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import abasscodes.com.sherpa.R;
import abasscodes.com.sherpa.data.model.Book;
import abasscodes.com.sherpa.databinding.BookViewHolderBinding;

class BookViewHolder extends RecyclerView.ViewHolder {
    static BookViewHolderBinding rootView;
    private static final int ITEM_LAYOUT = R.layout.book_view_holder;

    public BookViewHolder(ViewGroup parent) {
        super(inflateView(parent, ITEM_LAYOUT));
    }

    private static View inflateView(ViewGroup parent, @LayoutRes int layoutRes) {
        rootView =  DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutRes, parent, false);
        return rootView.getRoot();
    }

    public void bindBook(Book book) {
       rootView.bookTitle.setText(book.getTitle());
    }
}
