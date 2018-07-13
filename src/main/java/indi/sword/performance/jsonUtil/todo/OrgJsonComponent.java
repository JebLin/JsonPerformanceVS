package indi.sword.performance.jsonUtil.todo;

import indi.sword.performance.bean.SampleEntity;
import indi.sword.performance.jsonUtil.BasicJsonInterface;
import org.json.JSONObject;

/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 3:44 PM 13/07/2018
 * @MODIFIED BY
 */
public class OrgJsonComponent implements BasicJsonInterface {
    @Override
    public SampleEntity jsonToJavaBean(String str) throws Exception {
        return null;
    }

    @Override
    public String javaBeanToJson(SampleEntity sampleEntity) throws Exception {
        return new JSONObject(sampleEntity).toString();
    }
}
