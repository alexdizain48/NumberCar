package com.alex.numbercar.interfc;


import com.alex.numbercar.model.ItemsMain;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EndPoint {

    @GET("v1/get_number_car/{replacednumber}")
    Call<ItemsMain> getJson(@Path("replacednumber") String replacednumber);


}
