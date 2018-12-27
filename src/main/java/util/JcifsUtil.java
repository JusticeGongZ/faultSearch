package util;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DateUtil
 * @Description 共享文件操作工具
 * @Author gz
 * @Date 2018/12/26 15:57
 * @Version 1.0
 **/
public class JcifsUtil {

    public static synchronized boolean readFromSmb(String smbMachine, String localpath) {
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


    public static boolean removeLocalFile(File file) {
        return file.delete();
    }

    public static synchronized int readAndStatic(String filePath, Long startTime, Long endTime) throws IOException {
        FileReader fr = new FileReader(filePath);
        BufferedReader bufferedreader = new BufferedReader(fr);
        String instring = null;
        long LastRecordTime = 0;
        long currentTime = 0;
        int MPSNum = 0;

        while ((instring = bufferedreader.readLine()) != null) {
            if (instring.contains("SB 5\\n")) {
                String s = instring.substring(1, 23);

                currentTime = DateUtil.dateToStandTimeStamp(s);
                if (currentTime > startTime && currentTime < endTime) {
                    if (currentTime - LastRecordTime > 60000) {
                        MPSNum++;
                        LastRecordTime = currentTime;

                    }
                }
            }
        }
        fr.close();
        return MPSNum;
    }

    public static synchronized List<String> PIDChangeStatic(String filePath, Long startTime, Long endTime) throws IOException {
        FileReader fr = new FileReader(filePath);
        BufferedReader bufferedreader = new BufferedReader(fr);
        String instring = null;
        long LastRecordTime = 0;
        long currentTime = 0;
        List<String> list = new ArrayList<String>();
        while ((instring = bufferedreader.readLine()) != null) {
            if (instring.contains("IQPIDChange")) {
                String s = instring.substring(1, 25);

                currentTime = DateUtil.dateToStandTimeStamp(s);
                if (currentTime > startTime && currentTime < endTime) {
                    if (currentTime - LastRecordTime > 60000) {
                        list.add(s);
                        LastRecordTime = currentTime;
                    }
                }
            }
        }
        fr.close();
        return list;
    }

    public static void main(String[] args) throws IOException {
    }
}
