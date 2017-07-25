package tcm.com.gistone.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件压缩帮助类
 * @author WangShanxi
 * @date 2017年6月13日
 * @version v0.0.1
 */
public class ZipUtil {
	/**
	 * 生成zip包
	 * @param files  对应的文件
	 * @param path	zip生成的路径
	 */
    public static void createZip(List<File> files,String path){
        try {
        	//生成文件
            File file = new File(path);
            if (!file.exists()){
                file.createNewFile();
            }
            //创建文件输出流
            FileOutputStream fous = new FileOutputStream(file);
            //打包输出流
            ZipOutputStream zipOut = new ZipOutputStream(fous);
            /**这个方法接受的就是一个所要打包文件的集合，
             * 还有一个ZipOutputStream*/
            zipFile(files, zipOut);
            zipOut.close();
            fous.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把接受的全部文件打成压缩包
     * @param files
     * @param outputStream
     */
    public static void zipFile
    (List<File> files,ZipOutputStream outputStream) {
        int size = files.size();
        for(int i = 0; i < size; i++) {
            File file =  files.get(i);
            zipFile(file, outputStream);
        }
    }

    /**
     * 根据输入的文件与输出流对文件进行打包
     * @param inputFile
     * @param ouputStream
     */
    public static void zipFile(File inputFile,
                               ZipOutputStream ouputStream) {
        try {
            if(inputFile.exists()) {
                /**如果是目录的话这里是不采取操作的，
                 * 至于目录的打包正在研究中*/
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
                    //org.apache.tools.zip.ZipEntry
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    // 关闭创建的流对象
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
