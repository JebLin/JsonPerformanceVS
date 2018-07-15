package indi.sword.performance.test;

import indi.sword.performance.bean.SampleBuilder;
import indi.sword.performance.bean.SampleEntity;
import indi.sword.performance.util.FileUtil;
import indi.sword.performance.util.JsonUtil;
import indi.sword.performance.util.RedisUtil;

import java.util.List;
import java.util.concurrent.*;

/**
 * @Description
 *      1个样本。序列化性能测试
 * @Author jeb_lin
 * @Date Created in 5:22 PM 13/07/2018
 * @MODIFIED BY
 */
public class TestSerializableOneSample {
    public static void main(String[] args) throws Exception {

//        saveObjListToRedis();
        int sampleSize = 100000;
        int listSize = 10;
        int mapKeyNum = 10;

//        sampleSize = Integer.valueOf(args[0]);
//        listSize = Integer.valueOf(args[1]);
//        mapKeyNum = Integer.valueOf(args[2]);

        singleThread(sampleSize, listSize, mapKeyNum);


    }

    /*
       把对象存储到redis里面去
    */
    public static void saveObjListToRedis() {
        int sampleSize = 1;
        int listSize = 10;
        int mapKeyNum = 10;

        for (int i = 0; i < 6; i++) {
            String filepath = FileUtil.getObjectSamplesPath(sampleSize, listSize, mapKeyNum);
            List<SampleEntity> sampleEntityList = SampleBuilder.loadSamples(filepath);

            String key = RedisUtil.objListkeyOneSample + "_samplesize" + sampleSize;
            RedisUtil.setList(key, sampleEntityList);


            List<SampleEntity> tempList = (List<SampleEntity>) RedisUtil.getList(key);
//            for (SampleEntity entity : tempList) {
//                System.out.println(entity);
//            }
            System.out.println("key -> " + key + ",sampleSize -> " + sampleSize + ",tempList.size -> " + tempList.size());

            sampleSize *= 10;
        }


    }

    private static void singleThread(int sampleSize, int listSize, int mapKeyNum) throws Exception {
        int type = 1 + ThreadLocalRandom.current().nextInt(4);

        String key = RedisUtil.objListkeyOneSample + "_samplesize" + sampleSize;
        List<SampleEntity> sampleEntityList = (List<SampleEntity>) RedisUtil.getList(key);

        for (int i = 0; i < 4; i++) {
            type = (type + 1) % 4;
            String outputPath = FileUtil.getSerializableOutputPath(type, sampleSize, listSize, mapKeyNum, 0);
            long cost = JsonUtil.calculateJavaBeanToJson(sampleEntityList, type);
            FileUtil.writeFile(String.valueOf(cost) + ",", outputPath, true);
        }

    }


}
