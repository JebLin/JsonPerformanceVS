package indi.sword.performance.jsonUtil;

/**
 * @Description
 * @Author jeb_lin
 * @EMAIL lin.jianbin@immomo.com
 * @Date Created in 3:52 PM 13/07/2018
 * @MODIFIED BY
 */
public enum JsonType {
    GSON(1),
    JACKSON(2),
    JSONSMART(3),
    FASTJSON(4);


    private int code ;
    JsonType(int code){
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
