package com.test.Utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.collections.MapUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 */

public class WyHttpUtils {

    private static OkHttpClient okHttpClient=new OkHttpClient().newBuilder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(60,TimeUnit.SECONDS)
            .build();

    public static String get(String url, Map<String,String> params){
        return get(url, null,params);
    }

    public static String get(String url, Map<String,String> header,Map<String,String> params){
        byte[] res = download(url, header, params);
        if (res==null){
            return null;
        }
        return new String(res);
    }

    public static byte[] download(String url, Map<String,String> header,Map<String,String> params){
        url=getRequestUrl(url,params);

        Request.Builder requestBuilder = new Request.Builder().get();
        requestBuilder.url(url);
        parseHeaders(requestBuilder,header);
        Request request = requestBuilder.build();

        Response res=null;
        try {
            res=okHttpClient.newCall(request).execute();
            if (res.isSuccessful()){
                return res.body().bytes();
            }

        }catch (IOException e){

        }
        finally {
            if (res!=null){
                res.close();
            }
        }
        return null;
    }

    public static String post(String url,Map<String,String> params,Object body){
        return post(url, null,params,body);
    }

    public static String post(String url, Map<String,String> header,Map<String,String> params,Object body){
        RequestBody requestBody=null;
        if (body==null){
            requestBody=getRequestForm(params);
        }else{
            requestBody=getRequestBody(body);
            url=getRequestUrl(url,params);
        }
        Request.Builder requestBuilder = new Request.Builder().post(requestBody);
        requestBuilder.url(url);
        parseHeaders(requestBuilder,header);
        Request request = requestBuilder.build();

        Response res=null;
        try {
            res=okHttpClient.newCall(request).execute();
            if (res.isSuccessful()){
                return res.body().string();
            }

        }catch (IOException e){

        }
        finally {
            if (res!=null){
                res.close();
            }
        }
        return null;
    }

    public static String upload(String url, File file, Map<String,String> params){
        Request request = new Request.Builder()
                .url(url)
                .post(getRequestBody(params,file))
                .build();
        Response res=null;
        try {
            res=okHttpClient.newCall(request).execute();
            if (res.isSuccessful()){
                return res.body().string();
            }

        }catch (IOException e){

        }
        finally {
            if (res!=null){
                res.close();
            }
        }
        return null;
    }

    private static void parseHeaders(Request.Builder requestBuilder, Map<String,String> header){
        if (MapUtils.isEmpty(header)){
            return ;
        }
        for (Map.Entry<String,String> entry:header.entrySet()){
            requestBuilder.addHeader(entry.getKey(),entry.getValue());
        }
    }

    private static RequestBody getRequestForm(Map<String,String> params){
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (params!=null){
            for (Map.Entry<String,String> param:params.entrySet()){
                formBuilder.add(param.getKey(),param.getValue());
            }
        }
        return formBuilder.build();
    }

    private static RequestBody getRequestBody(Object body){
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),JSONObject.toJSONString(body));
    }

    private static String getRequestUrl(String url,Map<String,String> params){
        if (MapUtils.isEmpty(params)){
            return url;
        }
        UriComponentsBuilder uriComponentsBuilder= UriComponentsBuilder.fromHttpUrl(url);
        for (Map.Entry<String,String> param:params.entrySet()){
            uriComponentsBuilder.queryParam(param.getKey(),param.getValue());
        }
        return uriComponentsBuilder.toUriString();
    }

    private static RequestBody getRequestBody(Map<String,String> params, File file){
        RequestBody fileBody= RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(),fileBody);
        if (params==null){
            return multipartBodyBuilder.build();
        }
        for (Map.Entry<String,String> param:params.entrySet()){
            multipartBodyBuilder.addFormDataPart(param.getKey(),param.getValue());
        }
        return multipartBodyBuilder.build();
    }

}
