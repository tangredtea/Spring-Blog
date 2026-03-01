package com.blog.util;


import com.blog.config.SettingsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author tangredtea
 */
@Slf4j
public class PropertiesUtil {

    private static String path;

    static {
        try {
            path = ResourceUtils.getURL("classpath:").getPath()+"messages.properties";
        } catch (FileNotFoundException e) {
            log.error("messages.properties not found", e);
        }
    }

    public static String getValueByKey(String filePath, String key) {
        Properties pps = new Properties();
        try (InputStream in = new BufferedInputStream(new FileInputStream(filePath))) {
            pps.load(in);
            return pps.getProperty(key);
        } catch (IOException e) {
            log.error("Failed to read property: {}", key, e);
            return null;
        }
    }

    public static void writeProperties(String pKey, String pValue) throws IOException {
        Properties pps = new Properties();
        try (InputStream in = new FileInputStream(path)) {
            pps.load(in);
        }
        pps.setProperty(pKey, pValue);
        try (OutputStream out = new FileOutputStream(path)) {
            pps.store(out, "Update " + pKey + " name");
        }
    }

    public static void write(Class<?> t, SettingsConfig settings) throws IllegalAccessException, IOException {
        Field[] declaredFields = t.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            writeProperties(declaredField.getName(), (String) declaredField.get(settings));
        }
    }
}
