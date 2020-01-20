package com.example.fuckjava.network.converter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author yk on 2018/9/7  10:55.
 *         Email:763744747@qq.com
 */

public class StringResponseBodyConverter<T> implements Converter<ResponseBody, String> {

    @Override
    public String convert(ResponseBody value) throws IOException {
        try {
            return value.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
