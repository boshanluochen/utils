package cn.sycamore.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * ������ص�һЩ������
 */
public class ConfigUtil {

	/**
	 * ��ȡ�����ļ���Դ
	 */
	public static URL findAsResource(final String path){
		URL url = null;
		ClassLoader contextClassLoader = ClassUtil.getContextClassLoader();
		if (null != contextClassLoader){
			url = contextClassLoader.getResource(path);
		}
		if (null != url){
			return url;
		}
		url = ConfigUtil.class.getClassLoader().getResource(path);
		if (null != url){
			return url;
		}
		url = ClassLoader.getSystemClassLoader().getResource(path);
		return url;
	}
	
    /**
     * @param path
     * @return
     */
    public static String resourcePath(final String path) {
        URL asResource = findAsResource(path);
        return new File(asResource.getFile()).getPath();
    }


    private static InputStream getConfigStream(final String path) throws RuntimeException {
        try {
            URL url = new URL(path);
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException("Unable to open config file: " + path);
        }
    }
    
    /**
     * ��ȡ��Դ��
     *
     * @param path
     * @return
     * @throws IOException
     */
    private static InputStream resourceStream(final String path) throws IOException {
        URL asResource = findAsResource(path);
        return asResource.openStream();
    }

    /**
     * ��ȡ��Դ����
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static Properties getConfigProperties(String path) throws IOException {
        Properties properties = new Properties();
        properties.load(resourceStream(path));
        return properties;
    }

	
}
