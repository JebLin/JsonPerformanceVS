package indi.sword.performance.jsonUtil;

import com.google.gson.Gson;
import indi.sword.performance.bean.SampleEntity;


/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 3:08 PM 13/07/2018
 * @MODIFIED BY
 */
public class GsonComponent implements BasicJsonInterface {

    private static final Gson gson = new Gson();

    /**
     * @Description json 2 bean
     * @Author jeb_lin
     * @Date 3:05 PM 13/07/2018
     * @MODIFIED BY
     */
    @Override
    public SampleEntity jsonToJavaBean(String str) {
        return gson.fromJson(str, SampleEntity.class);
    }

    /**
     * @Description bean 2 json
     * @Author jeb_lin
     * @Date 3:05 PM 13/07/2018
     * @MODIFIED BY
     */
    @Override
    public String javaBeanToJson(SampleEntity sampleEntity) {
        return gson.toJson(sampleEntity);
    }
}
