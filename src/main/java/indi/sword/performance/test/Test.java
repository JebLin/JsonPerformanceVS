package indi.sword.performance.test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 9:23 PM 13/07/2018
 * @MODIFIED BY
 */
public class Test {
    public static void main(String[] args) {

        int type = 1 + ThreadLocalRandom.current().nextInt(4);
        for (int i = 0; i < 4; i++) {
            type = (type + 1) % 4;
            System.out.println(type);
        }

    }
}
