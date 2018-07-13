package indi.sword.performance.jsonUtil;

import indi.sword.performance.bean.SampleBuilder;
import indi.sword.performance.bean.SampleEntity;

import java.util.List;

/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 3:21 PM 13/07/2018
 * @MODIFIED BY
 */
public class JsonTypeProducer {

    private static final GsonComponent gsonComponent = new GsonComponent();

    private static final JackSonComponent jackSonComponent = new JackSonComponent();

    private static final JsonSmartComponent jsonSmartComponent = new JsonSmartComponent();

    private static final FastJsonComponent fastJsonComponent = new FastJsonComponent();

    public static void main(String[] args) throws Exception{
        String objectDataPath = "./samples_object.txt";
        List<SampleEntity> list = SampleBuilder.loadSamples(objectDataPath);

        String json = javaBeanToJson(list.get(0),JsonType.GSON);
        System.out.println(json);
        System.out.println(jsonToJavaBean(json,JsonType.GSON));

    }

    public static SampleEntity jsonToJavaBean(String str, JsonType jsonType) throws Exception {
        switch (jsonType) {
            case GSON:
                return gsonComponent.jsonToJavaBean(str);
            case JACKSON:
                return jackSonComponent.jsonToJavaBean(str);
            case FASTJSON:
                return fastJsonComponent.jsonToJavaBean(str);
            case JSONSMART:
                return jsonSmartComponent.jsonToJavaBean(str);
            default:
                return null;
        }
    }


    public static String javaBeanToJson(SampleEntity sampleEntity, JsonType jsonType) throws Exception {
        switch (jsonType) {
            case GSON:
                return gsonComponent.javaBeanToJson(sampleEntity);
            case JACKSON:
                return jackSonComponent.javaBeanToJson(sampleEntity);
            case FASTJSON:
                return fastJsonComponent.javaBeanToJson(sampleEntity);
            case JSONSMART:
                return jsonSmartComponent.javaBeanToJson(sampleEntity);
            default:
                return null;
        }
    }
}
