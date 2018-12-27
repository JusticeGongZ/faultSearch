package util;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @ClassName MutiDataUpdate
 * @Description 多线程数据读取工具
 * @Author gz
 * @Date 2018/12/26 15:57
 * @Version 1.0
 **/
public class MutiDataUpdate implements Callable<Boolean> {

    private String smbMachine;
    private String localpath;

    public MutiDataUpdate(String smbMachine, String localpath) {
        this.smbMachine = smbMachine;
        this.localpath = localpath;
    }

    @Override
    public Boolean call() {
        File localfile = null;
        InputStream bis = null;
        OutputStream bos = null;
        List<File> files = new ArrayList<File>();
        try {
            SmbFile rmifile = new SmbFile(smbMachine);
            String filename = rmifile.getName();
            bis = new BufferedInputStream(new SmbFileInputStream(rmifile));
            localfile = new File(localpath + File.separator + filename);
            bos = new BufferedOutputStream(new FileOutputStream(localfile));
            int length = rmifile.getContentLength();
            byte[] buffer = new byte[length];
            bis.read(buffer);
            bos.write(buffer);
            try {
                bos.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            files.add(localfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
