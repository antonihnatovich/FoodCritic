package com.example.yoant.foodcritic.helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GsonHelper {

    public static <T> List<T> toList(String json, Class<T> classCast){
        if(json == null)
            return null;
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<T>(){}.getType());
    }
}
