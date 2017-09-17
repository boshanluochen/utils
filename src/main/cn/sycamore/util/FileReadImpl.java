package cn.sycamore.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * �ļ���������
 */
public class FileReadImpl {

    /**
     * ����FileChannelֱ��ʵ���ļ��ĶԿ�,���ڴ��ļ��ٶ��ر�����
     *
     * @param source
     * @param target
     */
    public static void copyFileWithChannel(File source, File target) {
        try (
                FileInputStream inStream = new FileInputStream(source);
                FileOutputStream outStream = new FileOutputStream(target);
                FileChannel in = inStream.getChannel();
                FileChannel out = outStream.getChannel();
        ) {
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ʹ��FileChannel+Bufferʵ���ļ��Ķ�ȡ������һ�ּ��ѵķ���
     *
     * @param source
     * @param target
     */
    public static void copyFileWithBuffer(File source, File target) {
        try (
                FileInputStream inStream = new FileInputStream(source);
                FileOutputStream outStream = new FileOutputStream(target);
                FileChannel in = inStream.getChannel();
                FileChannel out = outStream.getChannel()
        ) {
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            while (in.read(buffer) != -1) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ����Bufferʵ���ļ��Ķ�ȡ����
     *
     * @param source
     * @param target
     */
    public static void customBufferBufferedStreamCopy(File source, File target) {
        try (
                InputStream fis = new BufferedInputStream(new FileInputStream(source));
                OutputStream fos = new BufferedOutputStream(new FileOutputStream(target))
        ) {
            byte[] buf = new byte[4096];
            int i;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ����Bufferʵ���ļ��Ķ�ȡ����
     *
     * @param source
     * @param target
     */
    public static void customBufferStreamCopy(File source, File target) {
        try (
                InputStream fis = new FileInputStream(source);
                OutputStream fos = new FileOutputStream(target);
        ) {
            byte[] buf = new byte[4096];
            int i;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
