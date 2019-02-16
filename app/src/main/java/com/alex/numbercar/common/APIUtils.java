package com.alex.numbercar.common;

import com.alex.numbercar.interfc.EndPoint;

public class APIUtils {
    public APIUtils() {
    }

    private static final String BASE_URL = "http://api-photocar.avtoistoria.ru/";

    public static EndPoint getFileServise() {
        return NetworkService.getClient(BASE_URL).create(EndPoint.class);
    }
}
