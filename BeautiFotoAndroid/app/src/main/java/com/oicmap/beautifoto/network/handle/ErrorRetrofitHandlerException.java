package com.oicmap.beautifoto.network.handle;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

public class ErrorRetrofitHandlerException implements ErrorHandler {
    @Override
    public Throwable handleError(RetrofitError cause) {
        return cause.getCause();
    }
}