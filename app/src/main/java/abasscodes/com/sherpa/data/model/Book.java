package abasscodes.com.sherpa.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import abasscodes.com.sherpa.data.Config;

@Entity(tableName = Config.BOOKS_TABLE_NAME)
public class Book {
    @SerializedName("book_id")
    @PrimaryKey
    private long id;

    @SerializedName("creation_date")
    @ColumnInfo(name = "creation_date")
    private long creationDate;

    @SerializedName("title")
    private String title;

    @SerializedName("link")
    private String link;

    public Book(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }
}
