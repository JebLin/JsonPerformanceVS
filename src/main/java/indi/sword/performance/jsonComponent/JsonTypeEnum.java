package indi.sword.performance.jsonComponent;

/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 3:52 PM 13/07/2018
 * @MODIFIED BY
 */
public enum JsonTypeEnum {
    NATIVE(-1),
    FASTJSON(0),
    GSON(1),
    JACKSON(2),
    JSONSMART(3);


    private int code ;
    JsonTypeEnum(int code){
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
