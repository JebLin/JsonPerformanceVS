package indi.sword.performance.test;

import indi.sword.performance.bean.DataBuilder;
import indi.sword.performance.bean.SampleBuilder;
import indi.sword.performance.bean.SampleEntity;
import indi.sword.performance.util.FileUtil;
import indi.sword.performance.util.JsonUtil;
import indi.sword.performance.util.RedisUtil;

import java.util.*;
import java.util.concurrent.*;

/**
 * @Description
 *      多个样本。序列化性能测试
 * @Author jeb_lin
 * @Date Created in 5:22 PM 13/07/2018
 * @MODIFIED BY
 */
public class TestSerializableDifferentSample {
    public static void main(String[] args) throws Exception {

        int sampleSize = 10;
        int listSize = 10;
        int mapKeyNum = 10;

//        sampleSize = Integer.valueOf(args[0]);
//        listSize = Integer.valueOf(args[1]);
//        mapKeyNum = Integer.valueOf(args[2]);

        singleThread(sampleSize,listSize,mapKeyNum);


//        multiThread();
    }



    private static void singleThread(int sampleSize, int listSize, int mapKeyNum) throws Exception {
        int type = 1 + ThreadLocalRandom.current().nextInt(4);


        List<SampleEntity> sampleEntityList = SampleBuilder.buildSamples(sampleSize, listSize, mapKeyNum);

        for (int i = 0; i < 4; i++) {
            type = (type + 1) % 4;
            String outputPath = FileUtil.getSerializableOutputPath(type,
                    sampleSize, listSize, mapKeyNum,1);

            long cost = JsonUtil.calculateJavaBeanToJson(sampleEntityList, type);

            FileUtil.writeFile(String.valueOf(cost) + ",", outputPath, true);
        }
    }


    private static void multiThread() throws Exception {

        // 会越来越快，懂得告诉我为什么会这样？
        ExecutorService pool = Executors.newFixedThreadPool(4);
        int threadNum = 4;
        for (int i = 0; i < 3; i++) {
            pool = Executors.newFixedThreadPool(threadNum);
            List<Callable<Long>> partitions = new ArrayList<>(threadNum);
            CountDownLatch latch = new CountDownLatch(threadNum);

            for (int j = 0; j < threadNum; j++) {
                partitions.add(new CalcuteThread(latch));
            }
            List<Future<Long>> resultFromPart = pool.invokeAll(partitions);

            pool.shutdown();
        }
    }


}
