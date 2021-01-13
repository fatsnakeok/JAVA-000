// 创建三分区两副本 的topic test32
./kafka-topics.sh --zookeeper localhost:2181 --create --topic test32 --partitions 3 - -replication-factor 2 

./kafka-topics.sh --zookeeper localhost:2181 --describe --topic testk32
![](/Week_13/img/分区分布.png)
分区与副本分布情况如下表：
![](/Week_13/img/broker.png)


./kafka-producer-perf-test.sh --topic test32 --num-records 100000 --record-size 1000  --throughput 10000 --producer-props bootstrap.servers=localhost:7090
// 10w 写入结果

./kafka-producer-perf-test.sh --topic test32 --num-records 1000000 --record-size 1000  --throughput 10000 --producer-props bootstrap.servers=localhost:7090
// 100w 写入结果


./kafka-producer-perf-test.sh --topic test32 --num-records 10000000 --record-size 1000  --throughput 10000 --producer-props bootstrap.servers=localhost:7090
// 1000w 写入



./kafka-consumer-perf-test.sh --bootstrap-server localhost:7090 --topic test32 -- fetch-size 1048576 --messages 100000 --threads 1


公司的电脑太垃圾了，未能完成压测