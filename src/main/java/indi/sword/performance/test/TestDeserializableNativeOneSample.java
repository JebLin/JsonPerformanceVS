package indi.sword.performance.test;

import indi.sword.performance.bean.SampleEntity;
import indi.sword.performance.jsonComponent.JsonTypeEnum;
import indi.sword.performance.util.FileUtil;
import indi.sword.performance.util.RedisUtil;
import indi.sword.performance.util.SerializeUtil;

import java.util.List;

/**
 * @Description TODO
 *      单一样本：原生，反序列化性能测试
 * @Author jeb_lin
 * @Date Created in 6:11 PM 15/07/2018
 * @MODIFIED BY
 */
public class TestDeserializableNativeOneSample {


    public static void main(String[] args) throws Exception{
        int sampleSize = 10;
        int listSize = 10;
        int mapKeyNum = 10;

//        sampleSize = Integer.valueOf(args[0]);
//        listSize = Integer.valueOf(args[1]);
//        mapKeyNum = Integer.valueOf(args[2]);


        singleThread(sampleSize,listSize,mapKeyNum);
    }

    private static void singleThread(int sampleSize, int listSize, int mapKeyNum) throws Exception {


        String key = RedisUtil.objListkeyOneSample + "_samplesize" + sampleSize;


        if(RedisUtil.getJedis() == null || !RedisUtil.getJedis().exists(key)){
            return;
        }
        byte[] data = RedisUtil.getJedis().get(key.getBytes());

        String outputPath = FileUtil.getDeserializableOutputPath(JsonTypeEnum.NATIVE.getCode(),
                sampleSize,listSize,mapKeyNum,0);


        long begin = System.currentTimeMillis();
        List<SampleEntity> sampleEntityList = (List<SampleEntity>)SerializeUtil.unserializeList(data);
        long end = System.currentTimeMillis();

        long cost = end - begin;
        System.out.println("thread -> " + Thread.currentThread().getName() + ",sampleSize -> " +
                sampleSize + ",cost -> " + cost + " ms");

        FileUtil.writeFile(String.valueOf(cost) + ",", outputPath, true);
    }

}
