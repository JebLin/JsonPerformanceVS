package indi.sword.performance.jsonComponent;

import com.alibaba.fastjson.JSON;
import indi.sword.performance.bean.SampleEntity;

/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 2:59 PM 13/07/2018
 * @MODIFIED BY
 */
public class FastJsonComponent implements BasicJsonInterface {


    /**
     * @Description json 2 bean
     * @Author jeb_lin
     * @Date 3:05 PM 13/07/2018
     * @MODIFIED BY
     */
    @Override
    public SampleEntity jsonToJavaBean(String str){
        SampleEntity entity = JSON.parseObject(str,SampleEntity.class);
        return entity;
    }

    /**
     * @Description bean 2 json
     * @Author jeb_lin
     * @Date 3:05 PM 13/07/2018
     * @MODIFIED BY
     */
    @Override
    public String javaBeanToJson(SampleEntity sampleEntity){
        return JSON.toJSONString(sampleEntity);
    }


}
