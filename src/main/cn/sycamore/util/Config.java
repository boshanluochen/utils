package cn.sycamore.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.util.PropertiesUtil;

public class Config {
    /*��ȡCLASS_PATH*/
    public static String CLASS_PATH = "";


    public static Map<String, String> INIT_MAP = new HashMap();

    static {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = contextClassLoader.getResource("0opslab-default.properties").openStream()) {
            CLASS_PATH = new File(contextClassLoader.getResource("").toURI()).getPath();


            INIT_MAP = PropertiesUtil.properties(inputStream);

            //��ȡ��jar���ڵ�������Ϣ
            try {
                String config_file = new File(contextClassLoader.getResource("0opslab.properties").toURI()).getPath();
                Map<String, String> CONFIG_MAP = PropertiesUtil.GetAllProperties(config_file);
                INIT_MAP.putAll(CONFIG_MAP);
            } catch (Exception e) {
            }

        } catch (IOException | URISyntaxException e) {
            logger.error("init config error:" + e.getMessage());
        }
//        System.out.println(CLASS_PATH);
//        System.out.println(CollectionUtil.join(INIT_MAP, "\n", ":"));
    }


    /*����������*/
    public static final String HOST_FEATURE = INIT_MAP.get("HOST_FEATURE");

    /*����ʱ�����͸�ʽ*/
    public static final String DATETIME_FORMAT = INIT_MAP.get("DATETIME_FORMAT");

    /*�������͸�ʽ*/
    public static final String DATE_FORMAT = INIT_MAP.get("DATE_FORMAT");

    /*ʱ�����͵ĸ�ʽ*/
    public static final String TIME_FORMAT = INIT_MAP.get("TIME_FORMAT");

    /**
     * ��ȡ������Ϣ
     * @param key
     * @return
     */
    public static String get(String key){
        return INIT_MAP.get(key);
    }

    public static void main(String[] args) {

    }
}