package indi.sword.performance.test;

import indi.sword.performance.bean.SampleBuilder;
import indi.sword.performance.bean.SampleEntity;
import indi.sword.performance.util.FileUtil;
import indi.sword.performance.jsonComponent.JsonTypeEnum;

import java.util.List;

/**
 * @Description
 *      此类用于创建样本数据
 * @Author jeb_lin
 * @Date Created in 5:14 PM 13/07/2018
 * @MODIFIED BY
 */
public class CreateTestSample {

    public static void main(String[] args) throws Exception{
        int sampleSize = 100000;
        int listSize = 10;
        int mapKeyNum = 10;
        createTestSample(sampleSize,listSize,mapKeyNum);
    }

    /**
     * @Description 产生测试脚本
     * @Author jeb_lin
     * @Date 5:09 PM 13/07/2018
     * @MODIFIED BY
     */
    public static void createTestSample(int sampleSize,
                                        int listSize, int mapKeyNum) throws Exception {


        String objPath = FileUtil.getObjectSamplesPath(sampleSize, listSize, mapKeyNum);
        /** 1、创建序列化文件 **/
        List<SampleEntity> samplelist = SampleBuilder.buildObjectSamples(sampleSize,listSize,mapKeyNum,objPath);

        /** 2、创建Json文件 **/
        for (JsonTypeEnum type : JsonTypeEnum.values()) {
            SampleBuilder.buildJsonSamplesByType(samplelist, type.getCode(), sampleSize, listSize, mapKeyNum);
        }
    }
}
