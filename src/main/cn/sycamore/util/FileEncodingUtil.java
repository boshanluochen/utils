package cn.sycamore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;

/**
 * �ļ����빤����
 * @author Administrator
 *
 */
public class FileEncodingUtil {
    /**
     * ��ָ���ļ���Ŀ¼ת����ָ���ı���
     *
     * @param fileName        Ҫת�����ļ�
     * @param fromCharsetName Դ�ļ��ı���
     * @param toCharsetName   Ҫת���ı���
     */
    public static void convert(String fileName, String fromCharsetName, String toCharsetName) {
        convert(new File(fileName), fromCharsetName, toCharsetName, null);
    }

    /**
     * ��ָ���ļ���Ŀ¼ת����ָ���ı���
     *
     * @param file            Ҫת�����ļ���Ŀ¼
     * @param fromCharsetName Դ�ļ��ı���
     * @param toCharsetName   Ҫת���ı���
     */
    public static void convert(File file, String fromCharsetName, String toCharsetName) {
        convert(file, fromCharsetName, toCharsetName, null);
    }

    /**
     * ��ָ���ļ���Ŀ¼ת����ָ���ı���
     *
     * @param fileName        Ҫת�����ļ���Ŀ¼
     * @param fromCharsetName Դ�ļ��ı���
     * @param toCharsetName   Ҫת���ı���
     * @param filter          �ļ���������
     */
    public static void convert(String fileName, String fromCharsetName, String toCharsetName, FilenameFilter filter) {
        convert(new File(fileName), fromCharsetName, toCharsetName, filter);
    }

    /**
     * ��ָ���ļ���Ŀ¼ת����ָ���ı���
     *
     * @param file            Ҫת�����ļ���Ŀ¼
     * @param fromCharsetName Դ�ļ��ı���
     * @param toCharsetName   Ҫת���ı���
     * @param filter          �ļ���������
     */
    public static void convert(File file, String fromCharsetName, String toCharsetName, FilenameFilter filter) {
        if (file.isDirectory()) {
            List<File> list = valid.valid(filter) ? FileUtil.listFileFilter(file, filter) :
                    FileUtil.listFile(file);
            if (valid.valid(list)) {
                for (File f : list) {
                    convert(f, fromCharsetName, toCharsetName, filter);
                }
            }
        } else {
            if (filter == null || filter.accept(file.getParentFile(), file.getName())) {
                String fileContent = getFileContentFromCharset(file, fromCharsetName);
                saveFile2Charset(file, toCharsetName, fileContent);
            }
        }
    }

    /**
     * ��ָ�����뷽ʽ��ȡ�ļ��������ļ�����
     *
     * @param file            Ҫת�����ļ�
     * @param fromCharsetName Դ�ļ��ı���
     */
    public static String getFileContentFromCharset(File file, String fromCharsetName) {
        String str = "";
        if (!Charset.isSupported(fromCharsetName)) {
            throw new UnsupportedCharsetException(fromCharsetName);
        }
        try (InputStream inputStream = new FileInputStream(file);
             InputStreamReader reader = new InputStreamReader(inputStream, fromCharsetName)
        ) {
            char[] chs = new char[(int) file.length()];
            reader.read(chs);
            str = new String(chs).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * ��ָ�����뷽ʽд�ı��ļ������ڻḲ��
     *
     * @param file          Ҫд����ļ�
     * @param toCharsetName Ҫת���ı���
     * @param content       �ļ�����
     */
    public static void saveFile2Charset(File file, String toCharsetName, String content) {
        if (!Charset.isSupported(toCharsetName)) {
            throw new UnsupportedCharsetException(toCharsetName);
        }
        try (
                OutputStream outputStream = new FileOutputStream(file);
                OutputStreamWriter outWrite = new OutputStreamWriter(outputStream, toCharsetName)
        ) {
            outWrite.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
