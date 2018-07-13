package indi.sword.performance;

import indi.sword.performance.bean.SampleBuilder;

import java.util.List;

/**
 * @Description
 * @Author jeb_lin
 * @MODIFIED BY
 */
public class Calculate {
    public static void main(String[] args) {
        String jsonDataPath = "./samples_json.txt";

        List<String> loadJSONSamples = SampleBuilder.loadJSONSamples(jsonDataPath);
        for (String sampleJson :
                loadJSONSamples) {



        }
    }
}
