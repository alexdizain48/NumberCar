package com.alex.numbercar.interfc;


import com.alex.numbercar.model.Images;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EndPoint {

    @GET("{json}/glide.json")
    Call<List<Images>> getJson(@Path("json") String json);


}
