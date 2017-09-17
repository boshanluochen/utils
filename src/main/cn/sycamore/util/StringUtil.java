package cn.sycamore.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �ַ���������
 * @author Administrator
 *
 */
public class StringUtil {

    /**
     * �ж��Ƿ��ǿ��ַ��� null��"" ������ true
     *
     * @param str �жϵ��ַ���
     * @return �Ƿ���Ч
     */
    public  static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    /**
     * ��string array or list�ø����ķ���symbol���ӳ�һ���ַ���
     *
     * @param list   ��Ҫ������б�
     * @param symbol ���ӵķ���
     * @return �������ַ���
     */
    public  static String joinString(List list, String symbol) {
        String result = "";
        if (list != null) {
            for (Object o : list) {
                String temp = o.toString();
                if (temp.trim().length() > 0)
                    result += (temp + symbol);
            }
            if (result.length() > 1) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }

    /**
     * �ж���һ���ַ����Ƿ���ڵĵڶ����ַ����е�ĳһ��ֵ
     *
     * @param str1 ���Ե��ַ���
     * @param str2 �ַ�������(��,�ָ�)
     * @return �Ƿ����
     */
    public  static boolean requals(String str1, String str2) {
        if (str1 != null && str2 != null) {
            str2 = str2.replaceAll("\\s*", "");
            String[] arr = str2.split(",");
            for (String t : arr) {
                if (t.equals(str1.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * �ж���һ���ַ����Ƿ���ڵĵڶ����ַ����е�ĳһ��ֵ
     *
     * @param str1  ���Ե��ַ���
     * @param str2  �ַ�������
     * @param split str2�ַ����ķָ���
     * @return �Ƿ����
     */
    public  static boolean requals(String str1, String str2, String split) {
        if (str1 != null && str2 != null) {
            str2 = str2.replaceAll("\\s*", "");
            String[] arr = str2.split(split);
            for (String t : arr) {
                if (t.equals(str1.trim())) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * �ַ���ʡ�Խ�ȡ
     * �ַ�����ȡ��ָ������size+...����ʽ
     *
     * @param subject ��Ҫ������ַ���
     * @param size    ��ȡ�ĳ���
     * @return �������ַ���
     */
    public  static String subStringOmit(String subject, int size) {
        if (subject != null && subject.length() > size) {
            subject = subject.substring(0, size) + "...";
        }
        return subject;
    }


    /**
     * ��ȡ�ַ������������ַ���symbol����
     *
     * @param str    ��Ҫ������ַ���
     * @param len    �ַ�������
     * @param symbol ���ƴ�ӵ��ַ���
     * @return ���Ժ���ַ���
     */
    public  static String subStringSymbol(String str, int len, String symbol) {
        String temp = "";
        if (str != null && str.length() > len) {
            temp = str.substring(0, len) + symbol;
        }
        return temp;
    }


    /**
     * ��string array or list�ø����ķ���symbol���ӳ�һ���ַ���
     *
     * @param array  ��Ҫ������ַ�������
     * @param symbol ���ӵķ���
     * @return �������ַ���
     */
    public  static String joinString(String[] array, String symbol) {
        String result = "";
        if (array != null) {
            for (String temp : array) {
                if (temp != null && temp.trim().length() > 0)
                    result += (temp + symbol);
            }
            if (result.length() > 1 && valid.valid(symbol)) {
                result = result.substring(0, result.length() - symbol.length());
            }
        }
        return result;
    }

    /**
     * ��һ���ַ�����ָ�����ַ���������
     * @param linkStr �����ַ�
     * @param strs  ��Ҫ���ӵĶ�̬����
     * @return
     */
    public  static String join(String linkStr,String ... strs){
        StringBuffer result = new StringBuffer();
            for (String temp : strs) {
                if (temp != null && temp.trim().length() > 0)
                    result.append(temp + linkStr);
            }
            if (result.length() > 1 && valid.valid(linkStr)) {
                return result.substring(0, result.length() - linkStr.length());
            }
        return result.toString();
    }


    /**
     * �����ʼ���ַǰ׺��
     *
     * @param email - EMail�����ַ ����: ssss@koubei.com �ȵ�...
     * @return ����������ǰ׺�ʼ���ַ, �� *********@koubei.com.
     */
    public  static String getHideEmailPrefix(String email) {
        if (null != email) {
            int index = email.lastIndexOf('@');
            if (index > 0) {
                email = repeat("*", index).concat(email.substring(index));
            }
        }
        return email;
    }

    /**
     * repeat - ͨ��Դ�ַ����ظ�����N������µ��ַ�����
     *
     * @param src - Դ�ַ��� ����: �ո�(" "), �Ǻ�("*"), "�㽭" �ȵ�...
     * @param num - �ظ����ɴ���
     * @return ���������ɵ��ظ��ַ���
     */
    public  static String repeat(String src, int num) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < num; i++)
            s.append(src);
        return s.toString();
    }

    /**
     * ��ȡ�ַ�������Numλ��ȡ��ĩβ
     *
     * @param str1 ������ַ���
     * @param num  ��ʼλ��
     * @return ��ȡ����ַ���
     */
    public  static String ltrim(String str1, int num) {
        String tt = "";
        if (!isEmpty(str1) && str1.length() >= num) {
            tt = str1.substring(num, str1.length());
        }
        return tt;

    }

    /**
     * ��ȡ�ַ����Ҳ��0λ����Numλ
     *
     * @param str ������ַ���
     * @param num ��ȡ��λ��
     * @return ��ȡ����ַ���
     */
    public  static String rtrim(String str, int num) {
        if (!isEmpty(str) && str.length() > num) {
            str = str.substring(0, str.length() - num);
        }
        return str;
    }

    /**
     * ����ָ�����ַ���Դ�ַ����ָ��һ��list
     *
     * @param src     ������ַ���
     * @param pattern �ָ��ַ���
     * @return ������list
     */
    public  static List<String> parseString2List(String src, String pattern) {
        List<String> list = new ArrayList<>();
        if (src != null) {
            String[] tt = src.split(pattern);
            list.addAll(Arrays.asList(tt));
        }
        return list;
    }

    /**
     * ��ʽ��һ��float
     *
     * @param format Ҫ��ʽ���ɵĸ�ʽ such as #.00, #.#
     * @return ��ʽ������ַ���
     */
    public  static String formatDouble(double f, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(f);
    }


    /**
     * ��ȡ�ַ������ָ�����ȵ��ַ���
     *
     * @param input �����ַ���
     * @param count ��ȡ����
     * @return ��ȡ�ַ���
     */
    public  static String left(String input, int count) {
        if (isEmpty(input)) {
            return "";
        }
        count = (count > input.length()) ? input.length() : count;
        return input.substring(0, count);
    }

    /**
     * ��ȡ�ַ����Ҳ�ָ�����ȵ��ַ���
     *
     * @param input �����ַ���
     * @param count ��ȡ����
     * @return ��ȡ�ַ���
     * Summary ����������д�����
     */
    public  static String right(String input, int count) {
        if (isEmpty(input)) {
            return "";
        }
        count = (count > input.length()) ? input.length() : count;
        return input.substring(input.length() - count, input.length());
    }



    /**
     * ȫ���ַ������ַ�
     *
     * @param str ��Ҫ������ַ���
     * @return �������ַ���
     */
    public  static String full2Half(String str) {
        if(isEmpty(str)){
            return "";
        }
        return BCConvert.qj2bj(str);
    }

    /**
     * ����ַ���ȫ���ַ�
     * @param str ��Ҫ������ַ���
     * @return �������ַ���
     */
    public  static String Half2Full(String str){
        if(isEmpty(str)){
            return "";
        }
        return BCConvert.bj2qj(str);
    }


    /**
     * ҳ����ȥ���ַ����еĿո񡢻س������з����Ʊ��
     *
     * @param str ��Ҫ������ַ���
     */
    public  static String replaceBlank(String str) {
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            str = m.replaceAll("");
        }
        return str;
    }


    /**
     * �ж��ַ����������Ƿ����ĳ�ַ���Ԫ��
     *
     * @param substring ĳ�ַ���
     * @param source    Դ�ַ�������
     * @return �����򷵻�true�����򷵻�false
     */
    public  static boolean isIn(String substring, String[] source) {
        if(isEmpty(substring) || !valid.valid(source)){
            return false;
        }
        for (String t:source) {
            if (substring.equals(t)) {
                return true;
            }
        }
        return false;
    }


    /**
     * �ַ���ת��unicode.ʵ��native2ascii.exe���ƵĹ���
     *
     * @param string ��Ҫ������ַ���
     */
    public  static String string2Unicode(String string) {
        StringBuilder uni = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            String temp ="\\u"+String.valueOf(Integer.toHexString(string.charAt(i)));
            uni.append(temp);
        }
        return uni.toString();
    }

    /**
     * ת�ַ��� ʵ��native2ascii.exe���ƵĹ���
     *
     * @param unicode ��Ҫ������ַ���
     */
    public  static String unicode2String(String unicode) {
        StringBuilder str = new StringBuilder();
        String[]     hex    = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);
            str.append((char) data);
        }
        return str.toString();
    }


    /**
     * ɾ�����еı�����
     *
     * @param str ������ַ���
     */
    public  static String trimPunct(String str) {
        if(isEmpty(str)){
            return "";
        }
        return str.replaceAll("[\\pP\\p{Punct}]", "");
    }

    /**
     * �ַ������ƶȱȽ�(�ٶȽϿ�)
     */
    public  static double SimilarityRatio(String str1, String str2) {
        str1 = StringUtil.trimPunct(str1);
        str2 = StringUtil.trimPunct(str2);
        if (str1.length() > str2.length()) {
            return StringImpl.SimilarityRatio(str1, str2);
        } else {
            return StringImpl.SimilarityRatio(str2, str1);
        }

    }

    /**
     * �ַ������ƶȱȽ�(�ٶȽϿ�)
     */
    public  static double SimilarDegree(String str1, String str2) {
        str1 = StringUtil.trimPunct(str1);
        str2 = StringUtil.trimPunct(str2);
        if (str1.length() > str2.length()) {
            return StringImpl.SimilarDegree(str1, str2);
        } else {
            return StringImpl.SimilarDegree(str2, str1);
        }
    }





    /**
     * ��ȡ�ַ���str��String�г��ֵĴ���
     *
     * @param string ������ַ���
     * @param str ���ַ���
     */
    public  static int countSubStr(String string, String str) {
        if ((str == null) || (str.length() == 0) || (string == null) || (string.length() == 0)) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = string.indexOf(str, index)) != -1) {
            count++;
            index += str.length();
        }
        return count;
    }


    /**
     * �滻һ�����ֵ��Ӵ�
     *
     * @param s    source string
     * @param sub  substring to replace
     * @param with substring to replace with
     */
    public  static String replaceFirst(String s, String sub, String with) {
        int i = s.indexOf(sub);
        if (i == -1) {
            return s;
        }
        return s.substring(0, i) + with + s.substring(i + sub.length());
    }


    /**
     * �滻���һ�γ��ֵ��ִ�
     * Replaces the very last occurrence of a substring with supplied string.
     *
     * @param s    source string
     * @param sub  substring to replace
     * @param with substring to replace with
     */
    public  static String replaceLast(String s, String sub, String with) {
        int i = s.lastIndexOf(sub);
        if (i == -1) {
            return s;
        }
        return s.substring(0, i) + with + s.substring(i + sub.length());
    }


    /**
     * ɾ�����е��Ӵ�
     * Removes all substring occurrences from the string.
     *
     * @param s   source string
     * @param sub substring to remove
     */
    public  static String remove(String s, String sub) {
        int c      = 0;
        int sublen = sub.length();
        if (sublen == 0) {
            return s;
        }
        int i = s.indexOf(sub, c);
        if (i == -1) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length());
        do {
            sb.append(s.substring(c, i));
            c = i + sublen;
        } while ((i = s.indexOf(sub, c)) != -1);
        if (c < s.length()) {
            sb.append(s.substring(c, s.length()));
        }
        return sb.toString();
    }

    /**
     * ���ַ�������ĸת��д
     * @param str ��Ҫ������ַ���
     */
    public  static String upperFirstChar(String str){
        if(isEmpty(str)){
            return "";
        }
        char[] cs=str.toCharArray();
        if((cs[0] >= 'a') && (cs[0] <= 'z')){
            cs[0] -= (char) 0x20;
        }
        return String.valueOf(cs);
    }

    /**
     * ���ַ�������ĸתСд
     * @param str
     * @return
     */
    public  static String lowerFirstChar(String str){
        if(isEmpty(str)){
            return "";
        }
        char[] cs=str.toCharArray();
        if((cs[0] >= 'A') && (cs[0] <= 'Z')){
            cs[0] += (char) 0x20;
        }
        return String.valueOf(cs);
    }

    /**
     * �ж������ַ����Ҳ��length���ַ����Ƿ�һ��
     * @param str1
     * @param str2
     * @param length
     * @return
     */
    public  static boolean rigthEquals(String str1,String str2,int length){
        return right(str1,length).equals(right(str2,length));
    }

    /**
     * �ж������ַ�������length���ַ����Ƿ�һ��
     * @param str1
     * @param str2
     * @param length
     * @return
     */
    public  static boolean leftEquals(String str1,String str2,int length){
        return left(str1,length).equals(left(str2,length));
    }
}
