for i in {1..1000}
do

    java -jar -Xmx6g -Xms4g -XX:+UseG1GC JsonPerformanceVS.jar 100000 10 10
    echo 'shell, i-> '$i
done

echo 'OK'