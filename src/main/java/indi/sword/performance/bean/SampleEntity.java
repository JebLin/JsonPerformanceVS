package indi.sword.performance.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description 样本对象
 * @Author jeb_lin
 * @Date Created in 12:18 AM 13/07/2018
 * @MODIFIED BY
 */
@Data
@ToString
public class SampleEntity implements Serializable {
    private static final long serialVersionUID = 6985837216604970006L;
    private Boolean fieldBoolean;
    private Integer fieldInt;
    private Long fieldLong;
    private Double fieldDouble;
    private Date fieldDate;
    private String fieldStr;
    private List<String> fieldList;
    private Map<String, Object> fieldMap;

    /**
     * 随机样本
     */
    public SampleEntity() {
        Random random = ThreadLocalRandom.current();

        fieldBoolean = random.nextBoolean();
        fieldInt = random.nextInt();
        fieldLong = random.nextLong();
        fieldDouble = random.nextDouble();
        fieldDate = new Date();
        fieldStr = DataBuilder.randomString();
        fieldList = DataBuilder.randomStringList();
        fieldMap = DataBuilder.randomMap();
    }

    /**
     * 指定List元素数量和Map元素数量的样本
     *
     * @param listSize  List元素数量
     * @param mapKeyNum Map元素数量
     */
    public SampleEntity(int listSize, int mapKeyNum) {
        //  避免种子不变化
        Random random = ThreadLocalRandom.current();
        fieldBoolean = random.nextBoolean();
        fieldInt = random.nextInt();
        fieldLong = random.nextLong();
        fieldDouble = random.nextDouble();
        fieldDate = new Date();
        fieldStr = DataBuilder.randomString();

        fieldList = DataBuilder.randomStringList(listSize);
        fieldMap = DataBuilder.randomMap(mapKeyNum);

    }


}
