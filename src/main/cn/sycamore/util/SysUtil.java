package cn.sycamore.util;

import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import com.sun.management.OperatingSystemMXBean;

/**
 * ��ȡϵͳ��Ϣ������
 * @author Administrator
 *
 */
public class SysUtil {

    /**
     * JVM�İ汾
     */
    public static final String JVM_VERSION     = PropertiesUtil.key("java.version");
    /**
     * JVM�ı���
     */
    public static final String JVM_ENCODING    = PropertiesUtil.key("file.encoding");
    /**
     * JVMĬ�ϵ���ʱĿ¼
     */
    public static final String JVM_TEMPDIR     = PropertiesUtil.key("java.io.tmpdir");
    public static final String HTTP_PROXY_HOST = "http.proxyHost";
    public static final String HTTP_PROXY_PORT = "http.proxyPort";
    ;
    public static final String HTTP_PROXY_USER = "http.proxyUser";
    ;
    public static final String HTTP_PROXY_PASSWORD = "http.proxyPassword";
    /**
     * ����IP
     */
    public static String HOST_IP;
    /**
     * ������
     */
    public static String HOST_NAME;

    /**
     * �����ܹ�
     */
    public static String OS_ARCH           = PropertiesUtil.key("os.arch");
    /**
     * ��������
     */
    public static String OS_NAME           = PropertiesUtil.key("os.name");
    /**
     * �������Ͱ汾
     */
    public static String OS_VERSION        = PropertiesUtil.key("os.version");
    /**
     * ����ϵͳ����
     */
    public static String SUN_DESKTOP       = PropertiesUtil.key("sun.desktop");
    /**
     * ��ǰ�û�
     */
    public static String CURRENT_USER      = PropertiesUtil.key("user.name");
    /**
     * ��ǰ�û��ļ�Ŀ¼
     */
    public static String CURRENT_USER_HOME = PropertiesUtil.key("user.home");
    /**
     * �����û��Ĺ���Ŀ¼
     */
    public static String CURRENT_USER_DIR  = PropertiesUtil.key("user.dir");
    public static String FILE_SEPARATOR    = PropertiesUtil.key("file.separator");
    public static String PATH_SEPARATOR    = PropertiesUtil.key("path.separator");
    public static String LINE_SEPARATOR    = PropertiesUtil.key("line.separator");
    /**
     * �ܵ������ڴ�
     */
    public static  long                  TotalMemorySize;
    private static OperatingSystemMXBean osmxb;
    private static int kb = 1024;

    static {

        try {

            InetAddress addr = InetAddress.getLocalHost();
            HOST_NAME = addr.getHostName();
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)) {
                if (null != netint.getHardwareAddress()) {
                    List<InterfaceAddress> list = netint.getInterfaceAddresses();
                    for (InterfaceAddress interfaceAddress : list) {
                        InetAddress ip = interfaceAddress.getAddress();
                        if (ip instanceof Inet4Address) {
                            HOST_IP += interfaceAddress.getAddress().toString();
                        }
                    }
                }
            }
            HOST_IP = HOST_IP.replaceAll("null", "");
        } catch (Exception e) {
            System.out.println("��ȡ������IP����");
        }

        try {
            osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

            TotalMemorySize = osmxb.getTotalPhysicalMemorySize() / kb;
        } catch (Exception e) {
            System.out.println("��ȡϵͳ��Ϣʧ��");
            e.printStackTrace();
        }


    }



    /**
     * ��ʹ�õ������ڴ�
     */
    public final static long usedMemory() {
        if (valid.valid(osmxb)) {
            return (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / kb;
        }
        return 0;
    }

    /**
     * ��ȡJVM�ڴ�����
     *
     */
    public final static long JVMtotalMem() {
        return Runtime.getRuntime().totalMemory() / kb;
    }

    /**
     * ����������ڴ���
     *
     */
    public final static long JVMfreeMem() {
        return Runtime.getRuntime().freeMemory() / kb;
    }

    /**
     * �����ʹ������ڴ���
     *
     */
    public final static long JVMmaxMem() {
        return Runtime.getRuntime().maxMemory() / kb;
    }

    /**
     * Sets HTTP proxy settings.
     */
    public final static void setHttpProxy(String host, String port, String username, String password) {
        System.getProperties().put(HTTP_PROXY_HOST, host);
        System.getProperties().put(HTTP_PROXY_PORT, port);
        System.getProperties().put(HTTP_PROXY_USER, username);
        System.getProperties().put(HTTP_PROXY_PASSWORD, password);
    }

    /**
     * Sets HTTP proxy settings.
     */
    public final static void setHttpProxy(String host, String port) {
        System.getProperties().put(HTTP_PROXY_HOST, host);
        System.getProperties().put(HTTP_PROXY_PORT, port);
    }
}
