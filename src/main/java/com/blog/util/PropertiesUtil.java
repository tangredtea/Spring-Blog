package com.blog.util;


import com.blog.config.SettingsConfig;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author Ryan
 */
public class PropertiesUtil {

    private static String path;

    static {
        try {
            path = ResourceUtils.getURL("classpath:").getPath()+"messages.properties";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String GetValueByKey(String filePath, String key) {
        Properties pps = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            pps.load(in);
            String value = pps.getProperty(key);
            System.out.println(key + " = " + value);
            return value;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void GetAllProperties() throws IOException {
        Properties pps = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(path));
        pps.load(in);
        Enumeration en = pps.propertyNames();
        while(en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
            System.out.println(strKey + "=" + strValue);
        }

    }

    public static void WriteProperties (String pKey, String pValue) throws IOException {
        Properties pps = new Properties();
        InputStream in = new FileInputStream(path);
        //从输入流中读取属性列表（键和元素对）
        pps.load(in);
        //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
        //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
        OutputStream out = new FileOutputStream(path);
        pps.setProperty(pKey, pValue);
        //以适合使用 load 方法加载到 Properties 表中的格式，
        //将此 Properties 表中的属性列表（键和元素对）写入输出流
        pps.store(out, "Update " + pKey + " name");
    }

    public static void Write(Class t, SettingsConfig settings) throws IllegalAccessException, IOException {
        Field[] declaredFields = t.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            WriteProperties(declaredField.getName(), (String) declaredField.get(settings));
        }
    }
}
