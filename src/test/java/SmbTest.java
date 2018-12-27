import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import util.Constant;
import util.JcifsUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SmbTest {
    public static void main(String[] args) throws Exception {

//        SmbFile file = new SmbFile("smb://192.168.1.104/share/CM2-5visa.log");
//
//        SmbFileInputStream in = new SmbFileInputStream(file);
//
//        byte bt[] = new byte[10];
//        int count = -1;
//
//        while ((count = in.read(bt, 0, 10)) != -1) {
//
////            System.out.println("开始从10.1.88.3电脑上面读取数据");
//
//            String str = new String(bt, "gb2312");
//            if (str.contains("SB 5\\n")) {
//
//                System.out.println(str);
////                str.substring(1,23);
//            }
//        }
//        File file = readFromSmb("smb://192.168.1.104/share/w.log", "E:\\");
//        removeFile(file);


    }

    public static File readFromSmb(String smbMachine, String localpath){
        File localfile=null;
        InputStream bis=null;
        OutputStream bos=null;
        List<File> files = new ArrayList<File>();
        try {
            SmbFile rmifile = new SmbFile(smbMachine);
            String filename=rmifile.getName();
            bis=new BufferedInputStream(new SmbFileInputStream(rmifile));
            localfile=new File(localpath+File.separator+filename);
            bos=new BufferedOutputStream(new FileOutputStream(localfile));
            int length=rmifile.getContentLength();
            byte[] buffer=new byte[length];
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
        return localfile;
    }
    public static boolean removeFile(File file) {
        return file.delete();
    }
}
