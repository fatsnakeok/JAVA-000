![不同gc分析](/Week_02/resources/不同GC分析.png)

# 一、对GCLogAnalysis的不同GC分析
- mac  16 内存 jdk 11
- 内存分配：256M 512M 1024M  2048M 4096M
- 启动命令
java -XX:+UseSerialGC -Xmx256m   -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
- GC 策略
-XX:+UseSerialGC -XX:+UseParallelGC -XX:+UseConcMarkSweepGC -XX:+UseG1GC

现象：
1.256m内存下只有 cms是正常的
2.512m、1024M  2048M，ps、cms、G1的效果差不多，具体待压测时进一步分析
3.4096M时 G1 创建对象多且时间相对较少

初步结论： 大内存下，推荐G1策略

# 二、压测 gateway-server-0.0.1-SNAPSHOT.jar 
压测结果：https://shimo.im/docs/3HgJpQ8RRdjRgTKJ/read
压测分析：
并行GC，在512m内存下，吞吐量有优势，但是延迟较大
CMSGC，在512m内存下，吞吐量比并行GC差点，但延迟较小，1G内存，吞吐量及延迟均比并行GC好一点


# 三、结论
1.512m 内存推荐，使用并行
2.1~2G内存，低延迟高吞吐量兼顾的，推荐使用cms
3.4096M及以上，需要创建多对象时，推荐G1




