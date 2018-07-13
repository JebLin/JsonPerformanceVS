package indi.sword.performance.jsonUtil;

import indi.sword.performance.bean.SampleEntity;
import net.minidev.json.JSONValue;

/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 3:25 PM 13/07/2018
 * @MODIFIED BY
 */
public class JsonSmartComponent implements BasicJsonInterface {

    @Override
    public SampleEntity jsonToJavaBean(String str) {
        return JSONValue.parse(str, SampleEntity.class);
    }

    @Override
    public String javaBeanToJson(SampleEntity sampleEntity) {
        return JSONValue.toJSONString(sampleEntity);
    }
}
