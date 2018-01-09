package abasscodes.com.sherpa.data.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookService {

    @GET("books/")
    Flowable<BookResponse> loadBooks(@Query("user") String userId);
}
