package abasscodes.com.sherpa.data.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import abasscodes.com.sherpa.data.model.Book;

public class BookResponse {
    @SerializedName("items") private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
