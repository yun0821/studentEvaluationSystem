package com.cy.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Properties工具类，默认文件为application.properties
 *
 * @author Cy
 * @date 2019/03/20
 */
public class PropertiesUtil {
    private static final Logger logger = LogManager.getLogger(PropertiesUtil.class);

    /**
     * 默认Properties文件
     */
    private static final String DEFAULT_FILENAME = "/application.properties";

    private String propertiesName;

    public PropertiesUtil() {
        this.propertiesName = PropertiesUtil.DEFAULT_FILENAME;
    }

    public PropertiesUtil(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            this.propertiesName = PropertiesUtil.DEFAULT_FILENAME;
        } else {
            this.propertiesName = fileName;
        }
    }

    /**
     * 读取文件的键值
     *
     * @param key 需要读取的键
     * @return java.lang.String key对应的值
     * @author Cy
     * @date 2019/03/20
     */
    public String readProperty(String key) {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String filePath = path + propertiesName;
        String value = "";
        try {
            Properties prop;
            Map<String, Properties> props = new HashMap<>();
            prop = props.get(propertiesName);
            if (prop == null) {
                prop = new Properties();
                // 使用InPutStream流读取properties文件
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                prop.load(bufferedReader);
                props.put(propertiesName, prop);
            }
            value = prop.getProperty(key);
        } catch (Exception e) {
            logger.error("PropertiesUtil类获取" + propertiesName + "文件的" + key + "的值时发生错误。", e);
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取Properties对象
     *
     * @return java.util.Properties Properties对象
     * @author Cy
     * @date 2019/03/20
     */
    public Properties getProperties() {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String filePath = path + propertiesName;
        Properties prop = new Properties();
        try {
            Map<String, Properties> props = new HashMap<>();
            prop = props.get(propertiesName);
            if (prop == null) {
                prop = new Properties();
                // 使用InPutStream流读取properties文件
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                prop.load(bufferedReader);
                props.put(propertiesName, prop);
            }
        } catch (Exception e) {
            logger.error("PropertiesUtil类的getProperties方法加载" + propertiesName + "文件时发生错误。", e);
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * 向Properties文件写入键值对
     *
     * @param key   需要写入的建
     * @param value 需要写入的值
     * @author Cy
     * @date 2019/03/20
     */
    public void writeProperty(String key, String value) {
        Properties prop;
        OutputStream os = null;
        try {
            Map<String, Properties> props = new HashMap<>();
            prop = props.get(propertiesName);
            if (prop == null) {
                prop = new Properties();
                prop.load(PropertiesUtil.class.getResourceAsStream(propertiesName));
                props.put(propertiesName, prop);
            }

            String path = this.getClass().getClassLoader().getResource("").getPath();
            File cfgFile = new File(path + propertiesName);
            os = new FileOutputStream(cfgFile);
            prop.setProperty(key, value);
            prop.store(os, key);
            os.flush();
            os.close();
        } catch (Exception e) {
            logger.error("PropertiesUtil类写" + propertiesName + "文件（key=" + key + ",value=" + value + "）时发生错误。", e);
            e.printStackTrace();
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
            } catch (IOException e) {
                logger.error("PropertiesUtil类的writeProperty方法关闭" + propertiesName + "文件时发生错误。", e);
                e.printStackTrace();
            }
        }

    }

    /**
     * 读取文件的键值
     *
     * @param filePath 文件路径,为空则为默认文件application.properties
     * @param key      需要读取的键
     * @return java.lang.String key对应的值
     * @author Cy
     * @date 2019/03/20
     */
    public static String readProperty(String filePath, String key) {
        PropertiesUtil ps = new PropertiesUtil(filePath);
        return ps.readProperty(key);
    }

    /**
     * 向Properties文件写入键值对
     *
     * @param filePath 文件路径,为空则为默认文件application.properties
     * @param key      需要写入的建
     * @param value    需要写入的值
     * @author Cy
     * @date 2019/03/20
     */
    public static void writeProperty(String filePath, String key, String value) {
        PropertiesUtil ps = new PropertiesUtil(filePath);
        ps.writeProperty(key, value);
    }
}
