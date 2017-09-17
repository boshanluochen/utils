package cn.sycamore.ftp;

import java.io.IOException;

import cn.sycamore.util.Config;

/**
 * FTP工具类
 */
public class FTPFactory {

    //获取一个实例
    public static FTPUtil getInstance(String Name) throws IOException {

        String host = Config.get(Name + ".host");
        if (host != null) {
            int port = Integer.parseInt(Config.get(Name + ".port"));
            String username = Config.get(Name + ".username");
            String password = Config.get(Name + ".password");
            String remoteDir = Config.get(Name + ".remoteDir");
            String localDir = Config.get(Name + ".localDir");
            String Encoding = Config.get(Name + ".Encoding");
            boolean passiveMode = new Boolean(Config.get(Name + ".passiveMode")).booleanValue();
            FTPVo vo = new FTPVo(host, port, username, password, remoteDir, localDir, Encoding, passiveMode);
            return new FTPUtilImpl(vo);
        } else {
            throw new IOException("config error");
        }
    }
}
