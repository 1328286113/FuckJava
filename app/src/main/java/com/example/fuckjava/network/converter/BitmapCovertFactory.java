package com.example.fuckjava.network.converter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.annotation.Nullable;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class BitmapCovertFactory extends Converter.Factory {

    public static BitmapCovertFactory create(){
        return new BitmapCovertFactory();
    }

    private BitmapCovertFactory(){

    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new BitmapResponseBodyCoverter();
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new BitmapRequestBodyCoverter<>();
    }

    private class BitmapRequestBodyCoverter<T> implements Converter<T,RequestBody>{
        private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
        @Nullable
        @Override
        public RequestBody convert(T value) throws IOException {
            return RequestBody.create(MEDIA_TYPE, value.toString());
        }
    }

    private class BitmapResponseBodyCoverter implements Converter<ResponseBody, Bitmap>{

        @Nullable
        @Override
        public Bitmap convert(ResponseBody value) {
            return readInputStreamToBitmap(value.byteStream());
        }
    }

    public static Bitmap readInputStreamToBitmap(InputStream ins) {
        if (ins == null) {
            return null;
        }
        byte[] b;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int size = -1;
            int len = 0;// 已经接收长度
            size = ins.read(buffer);
            while (size != -1) {
                len = len + size;//
                bos.write(buffer, 0, size);
//                if (fileSize == len) {// 接收完毕
//                    break;
//                }
                size = ins.read(buffer);
            }
            b = bos.toByteArray();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        }
        return null;
    }
}
