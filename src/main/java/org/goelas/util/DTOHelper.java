package org.goelas.util;

import com.google.gson.Gson;

import java.util.Base64;

/**
 * Utility class to serialize
 */
public class DTOHelper<T> {

    protected final Class<T> tClass;

    public DTOHelper(Class<T> tClass) {
        this.tClass = tClass;
    }

    public String toJson(T dto) {
        Gson gson = new Gson();
        String json  = gson.toJson(dto);
        return json;
    }


    public T toObject(String jsonString) {
        Gson gson = new Gson();
        T obj = gson.fromJson(jsonString, tClass);
        return obj;

    }

    public String getCheckSum(T dto) {
        String json = toJson(dto);
        Base64.Encoder encoder = Base64.getEncoder();
        String base64String = encoder.encodeToString(json.getBytes());
        return base64String;



    }

}
