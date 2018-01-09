package abasscodes.com.sherpa.data.rest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    // TODO: change this to a real endpoint
    private static final String BASE_URL = "https://api.ipify.org/";
    private static final int CONNECTION_TIMEOUT = 30;

    private static RestApi instance;

    public synchronized static RestApi getApi() {
        if (instance == null) {
            instance = createRestApi(BASE_URL);
        }
        return instance;
    }

    public RestClient() {
    }

    private static RestApi createRestApi(String baseUrl) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient httpClient = builder
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RestApi.class);
    }
}
