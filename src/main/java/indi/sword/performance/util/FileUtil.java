package indi.sword.performance.util;

import java.io.*;

/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 8:25 PM 13/07/2018
 * @MODIFIED BY
 * @param differentSample 0:NO 1:YES
 *                        type -1:NATIVE
 */
public class FileUtil {

    public static String getSerializableOutputPath(int typeCode, int sampleSize,
                                                   int listSize, int mapKeyNum, int differentSample) {
        StringBuffer path = new StringBuffer("./file/result/serializable/" + differentSample + "/");
        path.append("type_");
        path.append(typeCode);
        path.append("_");
        path.append("sampleSize_");
        path.append(sampleSize);
        path.append("_list_");
        path.append(listSize);
        path.append("_mapNum_");
        path.append(mapKeyNum);
        path.append("_serializable_result.txt");
        return path.toString();
    }

    public static String getDeserializableOutputPath(int typeCode, int sampleSize,
                                                     int listSize, int mapKeyNum, int differentSample) {
        StringBuffer path = new StringBuffer("./file/result/deserializable/" + differentSample + "/");
        path.append("type_");
        path.append(typeCode);
        path.append("_");
        path.append("sampleSize_");
        path.append(sampleSize);
        path.append("_list_");
        path.append(listSize);
        path.append("_mapNum_");
        path.append(mapKeyNum);
        path.append("_deserializable_result.txt");
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
        String dir = path.substring(0, getLastSeparator(path));
        return dir;
    }

    public static Integer getLastSeparator(String path) {
        if (path.contains("/")) {
            return path.lastIndexOf('/');
        } else {
            return path.lastIndexOf('\\');
        }
    }

    /**
     * @Description:
     * @author rd_jianbin_lin
     * @date 2015年11月20日 下午5:25:03
     */
    public static String getCharset(String fileName) {
        File file = new File(fileName);
        return getCharset(file);
    }

    /**
     * @Description:
     * @author rd_jianbin_lin
     * @date 2015年9月23日 下午3:55:50
     */
    public static String readFile(File file) {
        String result = "";
        try {
            if (file.isFile() && file.exists()) {
                // 获取字符串
                String file_charset = getCharset(file.getPath());
                // logger.info("读取的文本编码格式为：" + file_charset);
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), file_charset);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTXT = null;
                while ((lineTXT = bufferedReader.readLine()) != null) {
                    result += lineTXT.toString().trim();
                    // logger.info("该行文本为：" + lineTXT.toString().trim());
                }
                read.close();
                bufferedReader.close();
            } else {
                System.out.println("找不到指定的文件！");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * @Description:
     * @author rd_jianbin_lin
     * @date 2015年11月20日 下午5:25:03
     */
    public static String getCharset(File file) {
        try {
            // 获取文件的编码
            String file_charset = null;
            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
            int p = (bin.read() << 8) + bin.read();
            switch (p) {
                case 0xefbb:
                    file_charset = "UTF-8";
                    break;
                case 0xfffe:
                    file_charset = "Unicode";
                    break;
                case 0xfeff:
                    file_charset = "UTF-16BE";
                    break;
                default:
                    file_charset = "GBK";
            }
            bin.close();

            // 获取字符串编码（通过前20行内的文本来判断）
            int line = 0;
            String content = "";
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), file_charset);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTXT = null;
            while ((lineTXT = bufferedReader.readLine()) != null) {
                line++;
                content += lineTXT.toString().trim();
                if (line >= 20)
                    break;
            }

            // 矫正文件编码
            String str_charset = getStringCode(content);
            read.close();
            bufferedReader.close();
            return str_charset;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @Description:
     * @author rd_jianbin_lin
     * @date 2015年11月20日 下午5:25:03
     */
    private static String getStringCode(String str) {
        try {
            String encode = "GB2312";
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
            encode = "ISO-8859-1";
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
            encode = "UTF-8";
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
            encode = "GBK";
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
