package indi.sword.performance.util;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Description
 * @Author jeb_lin
 * @Date Created in 5:30 PM 12/07/2018
 * @MODIFIED BY
 */
public class RedisUtil {


    /** 关键字的 redis list **/
    public static final String objListkeyOneSample = "objlistonesample";

    public static Jedis getJedis() {
        final String redisIP = "localhost";
        final int redisPort = 6379;
        Jedis jedis = new Jedis(redisIP, redisPort);
        return jedis;
    }


    /**
     * 设置对象
     * @param key
     * @param obj
     */
    public static void setObject(String key ,Object obj){
        try {
            obj = obj == null ? new Object():obj;
            getJedis().set(key.getBytes(), SerializeUtil.serialize(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象
     * @param key
     * @return Object
     */
    public static Object getObject(String key){
        if(getJedis() == null || !getJedis().exists(key)){
            return null;
        }
        byte[] data = getJedis().get(key.getBytes());
        return (Object)SerializeUtil.unserialize(data);
    }

    /**
     * 设置List集合
     * @param key
     * @param list
     */
    public static void setList(String key ,List<?> list){
        try {

            if(!CommonUtil.isEmptyList(list)){
                getJedis().set(key.getBytes(), SerializeUtil.serializeList(list));
            }else{//如果list为空,则设置一个空
                getJedis().set(key.getBytes(), "".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取List集合
     * @param key
     * @return
     */
    public static List<?> getList(String key){
        if(getJedis() == null || !getJedis().exists(key)){
            return null;
        }
        byte[] data = getJedis().get(key.getBytes());
        return SerializeUtil.unserializeList(data);
    }
}
