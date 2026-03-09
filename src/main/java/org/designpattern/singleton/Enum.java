package org.designpattern.singleton;

import java.io.IOException;
import java.util.Properties;

enum AppConfig{
    INSTANCE;

    private final Properties properties;

    AppConfig(){
        properties = new Properties();

        try{
            properties.load(getClass().getResourceAsStream("/env.properties"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}

public class Enum {
    public static void main(String[] args) throws IOException{
        String url = AppConfig.INSTANCE.getProperty("db-url");
        System.out.println("DB URL: " + url );
    }
}
