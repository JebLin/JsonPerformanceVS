package indi.sword.performance.util;

import java.util.List;
/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 1:53 PM 15/07/2018
 * @MODIFIED BY
 */
public class CommonUtil {

    public static boolean isEmptyList(List<?> list) {
        return null == list || list.isEmpty();
    }
}
