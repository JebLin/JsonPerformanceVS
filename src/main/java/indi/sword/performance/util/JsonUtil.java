package indi.sword.performance.util;

import indi.sword.performance.bean.SampleBuilder;
import indi.sword.performance.bean.SampleEntity;
import indi.sword.performance.jsonComponent.*;

import java.util.List;

/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 3:21 PM 13/07/2018
 * @MODIFIED BY
 */
public class JsonUtil {

    private static final GsonComponent gsonComponent = new GsonComponent();

    private static final JackSonComponent jackSonComponent = new JackSonComponent();

    private static final JsonSmartComponent jsonSmartComponent = new JsonSmartComponent();

    private static final FastJsonComponent fastJsonComponent = new FastJsonComponent();

    public static void main(String[] args) throws Exception {
        String objectDataPath = "./file/object/samples_object.txt";
        List<SampleEntity> list = SampleBuilder.loadSamples(objectDataPath);

        String json = javaBeanToJson(list.get(0), JsonTypeEnum.FASTJSON.getCode());
        System.out.println(json);
        System.out.println(jsonToJavaBean(json, JsonTypeEnum.FASTJSON.getCode()));

        System.out.println("-------");

    }

    public static SampleEntity jsonToJavaBean(String str, int typeCode) throws Exception {
        if (typeCode == JsonTypeEnum.GSON.getCode()){
            return gsonComponent.jsonToJavaBean(str);
        } else if(typeCode == JsonTypeEnum.JACKSON.getCode()){
            return jackSonComponent.jsonToJavaBean(str);
        } else if(typeCode == JsonTypeEnum.JSONSMART.getCode()){
            return jsonSmartComponent.jsonToJavaBean(str);
        } else if(typeCode == JsonTypeEnum.FASTJSON.getCode()){
            return fastJsonComponent.jsonToJavaBean(str);
        }else {
            return null;
        }
    }


    public static String javaBeanToJson(SampleEntity sampleEntity, int typeCode) throws Exception {
        if (typeCode == JsonTypeEnum.GSON.getCode()){
            return gsonComponent.javaBeanToJson(sampleEntity);
        } else if(typeCode == JsonTypeEnum.JACKSON.getCode()){
            return jackSonComponent.javaBeanToJson(sampleEntity);
        } else if(typeCode == JsonTypeEnum.JSONSMART.getCode()){
            return jsonSmartComponent.javaBeanToJson(sampleEntity);
        } else if(typeCode == JsonTypeEnum.FASTJSON.getCode()){
            return fastJsonComponent.javaBeanToJson(sampleEntity);
        }else {
            return null;
        }
    }

    public static Long calculateJavaBeanToJson(List<SampleEntity> sampleEntityList, int typeCode) throws Exception {

        long begin = System.currentTimeMillis();
        for (SampleEntity entity :
                sampleEntityList) {
            JsonUtil.javaBeanToJson(entity, typeCode);
        }
        long end = System.currentTimeMillis();

        System.out.println("thread -> " + Thread.currentThread().getName() + ",typeCode -> " + typeCode + ",cost -> " + (end - begin) + " ms");
        return (end - begin);

    }

    public static Long calculateJsonToJavaBean(List<String> jsonList, int typeCode) throws Exception {

        long begin = System.currentTimeMillis();
        for (String json :
                jsonList) {

            JsonUtil.jsonToJavaBean(json, typeCode);
        }
        long end = System.currentTimeMillis();

        System.out.println("thread -> " + Thread.currentThread().getName() + ",typeCode -> " + typeCode + ",cost -> " + (end - begin) + " ms");
        return (end - begin);

    }

}
