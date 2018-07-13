# JsonPerformanceVS

一、硬件介绍
```$xslt
MacBook Pro (13-inch, 2017, Four Thunderbolt 3 Ports
Processor：3.1 GHz Intel Core i5
Memory: 8 GB 2133 MHz LPDDR3
disk : 256G

：
```

二、JVM配置
```$xslt
 -Xmx6g -Xms4g -XX:+UseG1GC 
```

三、 对象序列化的目的
> 不同json转成的字符串，不一定能被其他json工具成功转换回来。如 fastjson 把一个bean转成string，gson转回来后报错。

四、 测试步骤
```$xslt
    1. 数据准备：控制变量，生成样板数据sample，序列化到 
        sampleSize_10_list_10_mapNum_10_samplesObject.txt
            （4份json，下面类似 1 ：4）
                type_0_sampleSize_10_list_10_mapNum_10_samplesJson.txt
                type_1_sampleSize_10_list_10_mapNum_10_samplesJson.txt
                type_2_sampleSize_10_list_10_mapNum_10_samplesJson.txt
                type_3_sampleSize_10_list_10_mapNum_10_samplesJson.txt
        sampleSize_100_list_10_mapNum_10_samplesObject.txt
        sampleSize_1000_list_10_mapNum_10_samplesObject.txt
        sampleSize_10000_list_10_mapNum_10_samplesObject.txt
        sampleSize_100000_list_10_mapNum_10_samplesObject.txt
        sampleSize_10000000_list_10_mapNum_10_samplesObject.txt
        
    2. 控制变量，把 samples_object.txt 反序列化生成对象 bean ，循环转 json，测试时间。
    3. 测试结果 result ，1000次测试 （每次测试4个引擎处理的是同一个样本，1000个样本）
    4. 测试结果 result2 ，1000次测试 （每次测试4个引擎处理的是同一个样本，1个样本）
``` 


五、 注意事项；
```$xslt
    1. 为了避免垃圾回收带来的影响，每一次running只针对一个样板数据，调用4个json引擎
    2. json引擎具有强化能力，即使你换了样本，下次running同样效率会高很多，所以每一次测试都重启JVM，保证测试结果。
    3. 用G1垃圾收集器，
```

六、shell脚本
```
#!/bin/bash
# @author: Jeb_lin
# @Date: 2018-07-13


for i in {1..1000}
do

    java -jar -Xmx6g -Xms4g -XX:+UseG1GC JsonPerformanceVS.jar 100000 10 10
    echo 'shell, i-> '$i
done

echo 'OK'
```