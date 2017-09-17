package cn.sycamore.ftp;

import java.util.HashMap;
import java.util.Map;

/**
 * FTP��ص�һЩ����
 */
public class FTPConstant {
	   /**
     * FTP״̬�뼰������
     */
    public static  Map<Integer,String> REPLYCODE        = new HashMap<Integer,String>();
    //���ڱ���ת��
    private static String              ISO_ECODING      = "ISO-8859-1";
    //�������еı���
    private static String              PROJECT_ENCODING = "UTF-8";

    static {
        REPLYCODE.put(120, "�����Ѿ������� nnn ���Ӻ�ʼ��");
        REPLYCODE.put(125, "���������Ѵ򿪣����ڿ�ʼ���䡣");
        REPLYCODE.put(150, "�ļ�״̬������׼�����������ӡ�");
        REPLYCODE.put(202, "δִ�����վ���ϵ�������ࡣ");
        REPLYCODE.put(211, "ϵͳ״̬����ϵͳ�����𸴡�");
        REPLYCODE.put(212, "Ŀ¼״̬��");
        REPLYCODE.put(213, "�ļ�״̬��");
        REPLYCODE.put(214, "������Ϣ��");
        REPLYCODE.put(215, "NAME ϵͳ���ͣ����У�NAME �� Assigned Numbers �ĵ������е���ʽϵͳ���ơ�");
        REPLYCODE.put(220, "�������������ִ�����û�������");
        REPLYCODE.put(221, "����رտ������ӡ�����ʵ�����ע����");
        REPLYCODE.put(225, "�������Ӵ򿪣�û�н����еĴ��䡣");
        REPLYCODE.put(226, "�ر��������ӡ�������ļ������ѳɹ������磬�����ļ�������ļ�����");
        REPLYCODE.put(227, "���뱻��ģʽ (h1,h2,h3,h4,p1,p2)��");
        REPLYCODE.put(230, "�û��ѵ�¼���������С�");
        REPLYCODE.put(250, "������ļ�������ȷ������ɡ�");
        REPLYCODE.put(257, "�Ѵ�����PATHNAME����");
        REPLYCODE.put(332, "��Ҫ��¼�ʻ���");
        REPLYCODE.put(350, "������ļ��������ڵȴ���һ������Ϣ��");
        REPLYCODE.put(425, "�޷����������ӡ�");
        REPLYCODE.put(426, "Connection closed; transfer aborted.");
        REPLYCODE.put(450, "δִ��������ļ��������ļ������ã����磬�ļ���æ����");
        REPLYCODE.put(451, "����Ĳ����쳣��ֹ�����ڴ����ش���");
        REPLYCODE.put(452, "δִ������Ĳ�����ϵͳ�洢�ռ䲻����");
        REPLYCODE.put(501, "�ڲ��������﷨����");
        REPLYCODE.put(502, "δִ�����");
        REPLYCODE.put(503, "������������С�");
        REPLYCODE.put(504, "δִ�иò��������");
        REPLYCODE.put(530, "δ��¼��");
        REPLYCODE.put(532, "�洢�ļ���Ҫ�ʻ���");
        REPLYCODE.put(550, "δִ������Ĳ������ļ������ã����磬δ�ҵ��ļ���û�з���Ȩ�ޣ���");
        REPLYCODE.put(551, "����Ĳ����쳣��ֹ��δ֪��ҳ�����͡�");
        REPLYCODE.put(552, "������ļ������쳣��ֹ�������洢���䣨���ڵ�ǰĿ¼�����ݼ�����");
        REPLYCODE.put(553, "δִ������Ĳ�������������ļ�����");
    }
}
