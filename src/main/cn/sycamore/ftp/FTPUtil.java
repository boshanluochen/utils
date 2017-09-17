package cn.sycamore.ftp;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * FTP������
 *
 * @require apache-commons-net.jar.
 */
public interface FTPUtil {

	   //�ж�Զ���ļ��Ƿ����
    public boolean isExists(String fileName);

    //����Զ���ļ�
    public boolean downLoad(String fileName);

    //����Զ��Ŀ¼
    public boolean downLoadDir(String directory);

    //ɾ��Զ���ļ�
    public boolean deleteFile(String fileName);

    //ɾ��Զ��Ŀ¼
    public boolean deleteDir(String directory);

    //�ϴ������ļ���Զ��Ŀ¼
    public boolean putFile(String fileName, String remoteFileName, boolean isDelete);

    //�ϴ������ļ���Զ��Ŀ¼
    public boolean putFile(File file, String remoteFileName, boolean isDelete);

    //�ϴ�����Ŀ¼��Զ��
    public boolean putDir(String fileName, String remoteDir);

    //�ϴ�����Ŀ¼��Զ��
    public boolean putDir(File file, String remoteDir);

    //�����ļ���
    public boolean mkDir(String destory);


    //��ȡԶ���ļ��б�
    public List<String> listFile(String directory);

    //��ȡԶ���ļ��е�Ŀ¼�ṹ
    public LinkedList<String> listDir(String direcotyr);

    //��ȡԶ���ļ�������Map��ʽ����
    public Map<String,FileAttr> listFileAttr(String directory);

    //�ı�FTP���ӵĹ���Ŀ¼
    public boolean changeWorkDir(String directory);

    //��ȡ��ǰ���ӵĹ���Ŀ¼
    public String getWorkDir();

    //�������ļ�
    public boolean changName(String oldName, String newName);

    //����FTPCliend����(�Ѿ�������)
    public FTPClient client();

    //�ͷ����е���Դ
    public void destory();
}
