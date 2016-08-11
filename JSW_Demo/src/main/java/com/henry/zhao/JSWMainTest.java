package com.henry.zhao;

//import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by zhaozhou on 16/5/28.
 */
//@Slf4j

public class JSWMainTest {

    public static void main(String[] args) throws IOException {
        while (true) {
//            log.debug("----------------------");
            Properties properties = new Properties();
            InputStream ins = ClassLoader.getSystemResourceAsStream("claspath*:etc/app.properties");
            properties.load(ins);
            String value = properties.getProperty("my");
            HashMap<String, String> hashMap = new HashMap<String, String>();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(value);
//            log.debug("---->{}", value);
        }
    }
}
