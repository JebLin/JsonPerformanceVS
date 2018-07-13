package indi.sword.performance.jsonUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import indi.sword.performance.bean.SampleEntity;

import java.io.IOException;

/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 3:29 PM 13/07/2018
 * @MODIFIED BY
 */
public class JackSonComponent implements BasicJsonInterface {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public SampleEntity jsonToJavaBean(String str) throws IOException {
        return mapper.readValue(str,SampleEntity.class);
    }

    @Override
    public String javaBeanToJson(SampleEntity sampleEntity) throws JsonProcessingException {
        return mapper.writeValueAsString(sampleEntity);
    }
}
