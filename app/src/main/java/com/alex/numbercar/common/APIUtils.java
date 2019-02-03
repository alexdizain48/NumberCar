package com.alex.numbercar.common;

import com.alex.numbercar.interfc.EndPoint;

public class APIUtils {
    public APIUtils() {
    }

    private static final String BASE_URL = "https://api.androidhive.info/";

    public static EndPoint getFileServise() {
        return NetworkService.getClient(BASE_URL).create(EndPoint.class);
    }
}
