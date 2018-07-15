package indi.sword.performance.test;


import indi.sword.performance.bean.SampleBuilder;
import indi.sword.performance.util.FileUtil;
import indi.sword.performance.util.JsonUtil;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description
 *      1个样本。反序列化性能测试
 * @Author jeb_lin
 * @Date Created in 9:23 PM 13/07/2018
 * @MODIFIED BY
 */
public class TestDeserializableOneSample {
    public static void main(String[] args) throws Exception {

        int sampleSize = 10000;
        int listSize = 10;
        int mapKeyNum = 10;

//        sampleSize = Integer.valueOf(args[0]);
//        listSize = Integer.valueOf(args[1]);
//        mapKeyNum = Integer.valueOf(args[2]);


        singleThread(sampleSize,listSize,mapKeyNum);


    }

    private static void singleThread(int sampleSize, int listSize, int mapKeyNum) throws Exception {
        int type = 1 + ThreadLocalRandom.current().nextInt(4);

        for (int i = 0; i < 4; i++) {
            type = (type + 1) % 4;
            String filepath = FileUtil.getJsonDataPath(type,sampleSize,listSize,mapKeyNum);


            List<String> jsonList = SampleBuilder.loadJSONSamples(filepath);

            long cost = 0;
            try {
                cost = JsonUtil.calculateJsonToJavaBean(jsonList, type);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("filepath -> " + filepath + " ,type -> " + type);
            }

            String outputPath = FileUtil.getDeserializableOutputPath(type, sampleSize, listSize, mapKeyNum,0) ;
            FileUtil.writeFile(String.valueOf(cost) + ",", outputPath, true);
        }
    }
}
