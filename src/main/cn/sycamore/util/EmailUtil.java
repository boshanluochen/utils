package cn.sycamore.util;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.apache.catalina.Session;
import org.apache.logging.log4j.message.Message;
import org.apache.tomcat.util.http.fileupload.util.mime.MimeUtility;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeBodyPart;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeMultipart;

import sun.rmi.transport.Transport;

/**
 * Description
 * Email������
 */
public class EmailUtil {
    private Properties properties = new Properties();
    /**
     * Message���󽫴洢����ʵ�ʷ��͵ĵ����ʼ���Ϣ��
     */
    private MimeMessage message;

    /**
     * Session�����JavaMail�е�һ���ʼ��Ự��
     */
    private Session session;


    private Transport transport;
    private String mailHost = "";
    private int port = 25;
    private boolean auth = false;
    private String sender_username = "";
    private String sender_password = "";

    /*
     * ��ʼ������
     */
    public EmailUtil(boolean debug) {

        this.mailHost = Config.get("mail.smtp.host");
        this.port = Integer.valueOf(Config.get("mail.smtp.port"));
        this.auth = Boolean.parseBoolean(Config.get("mail.smtp.auth"));
        this.sender_username = Config.get("mail.sender.username");
        this.sender_password = Config.get("mail.sender.password");

        properties.put("mail.smtp.host", mailHost);
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.port", String.valueOf(25));
        properties.put("mail.sender.username", sender_username);
        properties.put("mail.sender.password", sender_password);

        session = Session.getInstance(properties);
        session.setDebug(debug);//�������е�����Ϣ
        message = new MimeMessage(session);
    }

    /**
     * �����ʼ�
     *
     * @param subject     �ʼ�����
     * @param sendHtml    �ʼ�����
     * @param receiveUser �ռ��˵�ַ
     */
    public void doSendHtmlEmail(String subject, String sendHtml, String receiveUser) {
        try {
            // ������
            InternetAddress from = new InternetAddress(sender_username);
            // ������������÷����˵�Nick name
            //InternetAddress from = new InternetAddress(MimeUtility.encodeWord("��Ӱ") + " <" + sender_username + ">");
            message.setFrom(from);

            // �ռ���
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);//��������CC��BCC

            // �ʼ�����
            message.setSubject(subject);

            String content = sendHtml.toString();
            // �ʼ�����,Ҳ����ʹ���ı�"text/plain"
            message.setContent(content, "text/html;charset=UTF-8");

            // �����ʼ�
            message.saveChanges();

            transport = session.getTransport("smtp");
            // smtp��֤���������������ʼ��������û�������
            transport.connect(mailHost, port, sender_username, sender_password);
            // ����
            transport.sendMessage(message, message.getAllRecipients());
            //System.out.println("send success!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * �����ʼ�
     *
     * @param subject     �ʼ�����
     * @param sendHtml    �ʼ�����
     * @param receiveUser �ռ��˵�ַ
     * @param attachment  ����
     */
    public void doSendHtmlEmail(String subject, String sendHtml, String receiveUser, File attachment) {
        try {
            // ������
            InternetAddress from = new InternetAddress(sender_username);
            message.setFrom(from);

            // �ռ���
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);

            // �ʼ�����
            message.setSubject(subject);

            // ��multipart����������ʼ��ĸ����������ݣ������ı����ݺ͸���
            Multipart multipart = new MimeMultipart();

            // ����ʼ�����
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);

            // ��Ӹ���������
            if (attachment != null) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment);
                attachmentBodyPart.setDataHandler(new DataHandler(source));

                // ���������Ľ���ļ�������ķ�������ʵ��MimeUtility.encodeWord�Ϳ��Ժܷ���ĸ㶨
                // �������Ҫ��ͨ�������Base64�����ת�����Ա�֤������ĸ����������ڷ���ʱ����������
                //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
                //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");

                //MimeUtility.encodeWord���Ա����ļ�������
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
                multipart.addBodyPart(attachmentBodyPart);
            }

            // ��multipart����ŵ�message��
            message.setContent(multipart);
            // �����ʼ�
            message.saveChanges();

            transport = session.getTransport("smtp");
            // smtp��֤���������������ʼ��������û�������
            transport.connect(mailHost, port, sender_username, sender_password);
            // ����
            transport.sendMessage(message, message.getAllRecipients());

            System.out.println("send success!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
