package indi.sword.performance.anasisData;

import indi.sword.performance.util.FileUtil;
import indi.sword.performance.jsonComponent.JsonTypeEnum;

import java.io.File;
import java.util.*;

/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 2:17 PM 14/07/2018
 * @MODIFIED BY
 */
public class AnaysisResult {
        public static void main(String[] args) {
        int sampleSize = 100000;
        int listSize = 10;
        int mapKeyNum = 10;

            anaysisSample(sampleSize,listSize,mapKeyNum,0,true);

    }

    private static void anaysisSample(int sampleSize, int listSize, int mapKeyNum,int differSample,boolean serialization) {
        for (JsonTypeEnum typeEnum:
                JsonTypeEnum.values()) {
            String path = "";
            if(serialization){
                path = FileUtil.getSerializableOutputPath(typeEnum.getCode(),
                        sampleSize,listSize,mapKeyNum,differSample);
            }else {
                path = FileUtil.getDeserializableOutputPath(typeEnum.getCode(),
                        sampleSize,listSize,mapKeyNum,differSample);
            }

            String str = FileUtil.readFile(new File(path));
            String[] costStr = str.split(",");
            List<Integer> costList = new ArrayList<>();
            for (String cost :
                    costStr) {
                costList.add(Integer.parseInt(cost.trim()));
            }
            Collections.sort(costList);

            /** 去特殊项之前的 **/
            System.out.println("costList.size -> " + costList.size() +
                    ",average -> " + costList.stream().mapToInt(Integer::intValue).average().getAsDouble());
            List<Integer> accurateList = new LinkedList<>();
            for (int i = 0; i < costList.size(); i++) {
                if(i >= 50 && i <= costList.size() - 51){
                    accurateList.add(costList.get(i));
                }
            }
            double average = accurateList.stream().mapToInt(Integer::intValue).average().getAsDouble();

            System.out.println("path -> " + path + ",accurateList.size() -> " + accurateList.size() + ",average -> " + average);
        }
    }

}
