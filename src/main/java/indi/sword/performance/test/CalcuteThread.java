package indi.sword.performance.test;

import indi.sword.performance.bean.SampleBuilder;
import indi.sword.performance.bean.SampleEntity;
import indi.sword.performance.jsonComponent.JsonTypeEnum;
import indi.sword.performance.util.JsonUtil;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

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
                JsonUtil.calculateJavaBeanToJson(sampleEntityList, type.getCode());
            }
//            System.out.println("thread over -> " + Thread.currentThread().getName());
        } finally {
            this.latch.countDown();
        }

        return null;
    }


}
