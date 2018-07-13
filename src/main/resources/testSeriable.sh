#!/bin/bash
# @author: Jeb_lin
# @Date: 2018-07-13


#for i in {1..100}
#do
#    for j in {1..4}
#    do
#       java -jar JsonPerformanceVS.jar 10000 10 10 $j > testSeriable.log
#       echo 'shell, i-> '$i'j -> 'j
#    done
#done

for i in {1..1000}
do

    java -jar -Xmx6g -Xms4g -XX:+UseG1GC JsonPerformanceVS.jar 100000 10 10
    echo 'shell, i-> '$i
done

echo 'OK'