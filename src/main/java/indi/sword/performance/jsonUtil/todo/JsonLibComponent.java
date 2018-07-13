package indi.sword.performance.jsonUtil.todo;

import indi.sword.performance.bean.SampleEntity;
import indi.sword.performance.jsonUtil.BasicJsonInterface;

//import net.sf.json.*;

/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 3:32 PM 13/07/2018
 * @MODIFIED BY
 */
public class JsonLibComponent implements BasicJsonInterface {

    @Override
    public SampleEntity jsonToJavaBean(String str){
        return null;
    }

    @Override
    public String javaBeanToJson(SampleEntity sampleEntity) {
//        return JSONObject.fromObject(sampleEntity);
        return  null;
    }
}
