package cn.sycamore.util;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

import org.junit.Test;


public class CharsetUtilsTest {

    @Test
    public void test() throws UnsupportedEncodingException, CharacterCodingException {

        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("file.encoding=" + System.getProperty("file.encoding"));
        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("Default Charset in Use=" + CharsetUtil.getDefaultCharSet());
        //System.out.println("JVM_ENCODING ->" + SysUtil.JVM_ENCODING);
        String str = "���ĵ��ַ���,��������Ӧ��Ŀ�ı����JVM�����л�������Ӱ��";
        String gbk = CharsetUtil.toGBK(str);
        System.out.println("ת����GBK��: " + gbk);
        String ascii = CharsetUtil.toASCII(str);
        System.out.println("ת����US-ASCII��: " + ascii);
        gbk = CharsetUtil.changeCharset(ascii, CharsetUtil.US_ASCII, CharsetUtil.GBK);
        System.out.println("�ٰ�ASCII����ַ���ת����GBK��: " + gbk);
        String iso88591 = CharsetUtil.toISO_8859_1(str);
        System.out.println("ת����ISO-8859-1��: " + iso88591);
        gbk = CharsetUtil.changeCharset(iso88591, CharsetUtil.ISO_8859_1, CharsetUtil.GBK);
        System.out.println("�ٰ�ISO-8859-1����ַ���ת����GBK��: " + gbk);
        String utf8 = CharsetUtil.toUTF_8(str);
        System.out.println("ת����UTF-8��: " + utf8);
        gbk = CharsetUtil.changeCharset(utf8, CharsetUtil.UTF_8, CharsetUtil.GBK);
        System.out.println("�ٰ�UTF-8����ַ���ת����GBK��: " + gbk);
        String utf16be = CharsetUtil.toUTF_16BE(str);
        System.out.println("ת����UTF-16BE��:" + utf16be);
        gbk = CharsetUtil.changeCharset(utf16be, CharsetUtil.UTF_16BE, CharsetUtil.GBK);
        System.out.println("�ٰ�UTF-16BE����ַ���ת����GBK��: " + gbk);
        System.out.println();
        String utf16le = CharsetUtil.toUTF_16LE(str);
        System.out.println("ת����UTF-16LE��:" + utf16le);
        gbk = CharsetUtil.changeCharset(utf16le, CharsetUtil.UTF_16LE, CharsetUtil.GBK);
        System.out.println("�ٰ�UTF-16LE����ַ���ת����GBK��: " + gbk);
        System.out.println();
        String utf16 = CharsetUtil.toUTF_16(str);
        System.out.println("ת����UTF-16��:" + utf16);
        gbk = CharsetUtil.changeCharset(utf16, CharsetUtil.UTF_16LE, CharsetUtil.GBK);
        System.out.println("�ٰ�UTF-16����ַ���ת����GBK��: " + gbk);
        String s = new String("����".getBytes("UTF-8"), "UTF-8");
        System.out.println(s);
    }

}
