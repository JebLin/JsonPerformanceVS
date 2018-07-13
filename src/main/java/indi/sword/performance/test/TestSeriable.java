package indi.sword.performance.test;

import indi.sword.performance.bean.SampleBuilder;
import indi.sword.performance.bean.SampleEntity;
import indi.sword.performance.jsonUtil.FileUtil;
import indi.sword.performance.jsonUtil.JsonTypeEnum;
import indi.sword.performance.jsonUtil.JsonUtil;

import java.io.File;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 5:22 PM 13/07/2018
 * @MODIFIED BY
 */
public class TestSeriable {
    public static void main(String[] args) throws Exception {

        int sampleSize = 10000;
        int listSize = 10;
        int mapKeyNum = 10;

        sampleSize = Integer.valueOf(args[0]);
        listSize = Integer.valueOf(args[1]);
        mapKeyNum = Integer.valueOf(args[2]);

        singleThreadDemo(sampleSize,listSize,mapKeyNum);


//        multiThreadDemo();
    }

    private static void singleThreadDemo(int sampleSize, int listSize, int mapKeyNum) throws Exception {
        int type = 1 + ThreadLocalRandom.current().nextInt(4);
        for (int i = 0; i < 4; i++) {
            type = (type + 1) % 4;
            List<SampleEntity> sampleEntityList = SampleBuilder.buildSamples(sampleSize, listSize, mapKeyNum);
            long cost = JsonUtil.calculate(sampleEntityList, type);

            String outputPath = FileUtil.getOutputPath(type, sampleSize, listSize, mapKeyNum);
            FileUtil.writeFile(String.valueOf(cost) + ",", outputPath, true);
        }
    }


    private static void multiThreadDemo() throws Exception {

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
