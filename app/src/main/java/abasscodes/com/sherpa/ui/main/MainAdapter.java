package abasscodes.com.sherpa.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import abasscodes.com.sherpa.data.model.Book;

public class MainAdapter extends RecyclerView.Adapter<BookViewHolder> {
    private List<Book> books = new ArrayList<>();

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.bindBook(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }
}
