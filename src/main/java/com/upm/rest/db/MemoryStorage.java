package com.upm.rest.db;

import com.upm.rest.entity.Light;
import com.upm.rest.entity.Temprature;
import java.util.HashMap;

/**
 *
 * @author HP
 */
public class MemoryStorage {
    
    private static HashMap<String,Temprature> tempratureStore=new HashMap<String, Temprature>();
     private static HashMap<String,Light> lightStore=new HashMap<String, Light>();

    public static HashMap<String, Temprature> getTempratureStore() {
        return tempratureStore;
    }

    public static void setTempratureStore(HashMap<String, Temprature> tempratureStore) {
        MemoryStorage.tempratureStore = tempratureStore;
    }

    public static HashMap<String, Light> getLightStore() {
        return lightStore;
    }

    public static void setLightStore(HashMap<String, Light> lightStore) {
        MemoryStorage.lightStore = lightStore;
    }

   

}
