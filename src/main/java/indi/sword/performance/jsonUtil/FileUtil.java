package indi.sword.performance.jsonUtil;

import java.io.*;

/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 8:25 PM 13/07/2018
 * @MODIFIED BY
 */
public class FileUtil {
    public static String getOutputPath(int typeCode, int sampleSize,
                                       int listSize, int mapKeyNum) {
        StringBuffer path = new StringBuffer("./file/result/");
        path.append("type_");
        path.append(typeCode);
        path.append("_");
        path.append("sampleSize_");
        path.append(sampleSize);
        path.append("_list_");
        path.append(listSize);
        path.append("_mapNum_");
        path.append(mapKeyNum);
        path.append("_result.txt");
        return path.toString();
    }

    public static String getJsonDataPath(int typeCode, int sampleSize,
                                         int listSize, int mapKeyNum) {
        StringBuffer jsonDataPathSb = new StringBuffer("./file/json/");
        jsonDataPathSb.append("type_");
        jsonDataPathSb.append(typeCode);
        jsonDataPathSb.append("_");
        jsonDataPathSb.append("sampleSize_");
        jsonDataPathSb.append(sampleSize);
        jsonDataPathSb.append("_list_");
        jsonDataPathSb.append(listSize);
        jsonDataPathSb.append("_mapNum_");
        jsonDataPathSb.append(mapKeyNum);
        jsonDataPathSb.append("_samplesJson.txt");
        return jsonDataPathSb.toString();
    }

    public static String getObjectSamplesPath(int sampleSize,
                                              int listSize, int mapKeyNum) {
        StringBuffer objectDataPathSb = new StringBuffer("./file/object/");
        objectDataPathSb.append("sampleSize_");
        objectDataPathSb.append(sampleSize);
        objectDataPathSb.append("_list_");
        objectDataPathSb.append(listSize);
        objectDataPathSb.append("_mapNum_");
        objectDataPathSb.append(mapKeyNum);
        objectDataPathSb.append("_samplesObject.txt");
        return objectDataPathSb.toString();
    }



    /**
     * @param content 文件内容
     * @param path    文件路径
     * @throws FileNotFoundException
     * @Description: 写文件
     * @author rd_jianbin_lin
     * @date 2015年8月13日 下午2:39:43
     */
    public static String writeFile(String content, String path, boolean append) throws FileNotFoundException {
        String result = "";
        String dir = getDir(path);
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        OutputStream output = new FileOutputStream(path, append);
        try {
            output.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 获取文件路径的目录地址
     *
     * @param path
     * @return
     */
    public static String getDir(String path) {
        // TODO Auto-generated method stub
        String dir = path.substring(0, getLastSeparator(path));
        // System.out.println(dir);
        return dir;
    }

    public static Integer getLastSeparator(String path) {
        if (path.contains("/")) {
            return path.lastIndexOf('/');
        } else {
            return path.lastIndexOf('\\');
        }
    }
}
