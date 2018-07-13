package indi.sword.performance.test;

import indi.sword.performance.bean.SampleBuilder;
import indi.sword.performance.bean.SampleEntity;
import indi.sword.performance.jsonUtil.JsonTypeEnum;
import indi.sword.performance.jsonUtil.JsonUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 6:57 PM 13/07/2018
 * @MODIFIED BY
 */
public class CalcuteThread implements Callable<Long>{

    private int sampleSize = 1000;
    private int listSize = 10;
    private int mapKeyNum = 10;
    private CountDownLatch latch;

    public CalcuteThread(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public Long call() throws Exception {
        try {
            for (JsonTypeEnum type : JsonTypeEnum.values()){
                List<SampleEntity> sampleEntityList = SampleBuilder.buildSamples(sampleSize,listSize,mapKeyNum);
                JsonUtil.calculate(sampleEntityList, type.getCode());
            }
//            System.out.println("thread over -> " + Thread.currentThread().getName());
        } finally {
            this.latch.countDown();
        }

        return null;
    }


}
