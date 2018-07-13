package indi.sword.performance.jsonUtil;


import indi.sword.performance.bean.SampleEntity;


/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 3:17 PM 13/07/2018
 * @MODIFIED BY
 */
public interface BasicJsonInterface {

    SampleEntity jsonToJavaBean(String str) throws Exception;

    String javaBeanToJson(SampleEntity sampleEntity) throws Exception;


}
