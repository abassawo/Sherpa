package abasscodes.com.sherpa.data.rest;

import retrofit2.http.GET;

public interface RestApi {
    @GET("/format=json")
    public void getSherpaData();
}
