# 一、master-slave 主从复制

- 1.复制三份redis文件，修改三个文件夹命名 

- 2.修改master 7001  redis.conf
 ```shell script
bind 0.0.0.0 #任意ip都可以连接

protected-mode no #关闭保护，允许非本地连接

port 7001 #端口号

daemonize yes #后台运行

pidfile /var/run/redis_7001.pid #进程守护文件，就是存放该进程号相关信息的地方

dir /Users/izaodao/Documents/redis-ms/redis-m-7001/data/ #db等相关目录位置

logfile /Users/izaodao/Documents/redis-ms/redis-m-7001/log/redis.log

appendonly yes #开启日志形式

requirepass XX #密码
```

- 3.修改 slave1 7002 redis.conf
```shell script
bind 0.0.0.0 #任意ip都可以连接

protected-mode no #关闭保护，允许非本地连接

port 7002 #端口号

daemonize yes #后台运行

pidfile /var/run/redis_7002.pid #进程守护文件，就是存放该进程号相关信息的地方

dir /Users/izaodao/Documents/redis-ms/redis-s-7002/data/ #db等相关目录位置　　

logfile /Users/izaodao/Documents/redis-ms/redis-s-7002/log/redis.log

replicaof <masterip> <masterport> #主信息

masterauth <master-password> #主信息

appendonly yes #开启日志形式

requirepass XX #密码
```

- 3.修改 slave1 7003 redis.conf
```shell script
bind 0.0.0.0 #任意ip都可以连接

protected-mode no #关闭保护，允许非本地连接

port 7003 #端口号

daemonize yes #后台运行

pidfile /var/run/redis_7003.pid #进程守护文件，就是存放该进程号相关信息的地方

dir /Users/izaodao/Documents/redis-ms/redis-s-7003/data/ #db等相关目录位置　　

logfile /Users/izaodao/Documents/redis-ms/redis-s-7003/log/redis.log

replicaof <masterip> <masterport> #主信息

masterauth <master-password> #主信息

appendonly yes #开启日志形式

requirepass XX #密码
```

- 4.启动

cd /Users/izaodao/Documents/redis-ms/redis-m-7001

redis-server  ./redis.conf

cd /Users/izaodao/Documents/redis-ms/redis-s-7002

redis-server  ./redis.conf

cd /Users/izaodao/Documents/redis-ms/redis-s-7003

redis-server  ./redis.conf

- 5.测试

ps -ef | grep redis

![ps -ef | grep redis](/Week_12/img/grep.png)

info Replication 命令，查看状态

![info Replication 命令](/Week_12/img/ms.png)

get  set 命令

![get set 命令](/Week_12/img/ms_get_set.png)

# 二、sentinel 哨兵模式

- 1.创建目录，创建三份sentinel.conf

- 2.修改sentinel.conf

```shell script
bind 127.0.0.1 192.168.1.1
protected-mode no
port 26001
daemonize yes
pidfile /var/run/redis-sentinel_26001.pid
logfile "/Users/izaodao/Documents/redis-ms/sentinel/26001/log/redis-sentinel.log"
dir /Users/izaodao/Documents/redis-ms/sentinel/26001/tmp
#设置 主名称 ip地址 端口号 参入选举的哨兵数
#配置哨兵需要监控的主节点ip和端口，最后的2代表，如果有2个哨兵主观认为主节点down了，
#那么就客观认为主节点down掉了，开始发起投票选举新主节点的操作。多个主节点配置多个。
sentinel monitor mymaster 127.0.0.1 7001 2
```

26001、26002、26003 三个sentinel.conf均修改上面配置，只是端口不一样

- 3.启动一主二从，三个redis节点

cd /Users/izaodao/Documents/redis-ms/redis-m-7001

redis-server  ./redis.conf

cd /Users/izaodao/Documents/redis-ms/redis-s-7002

redis-server  ./redis.conf

cd /Users/izaodao/Documents/redis-ms/redis-s-7003

redis-server  ./redis.conf

- 4.启动三个sentinel节点
对redis-sentinel做软连接（可以在任意目录执行redis-sentinel ）

ln -s /Users/izaodao/Documents/redis-ms/redis-5   /usr/bin/

redis-sentinel /Users/izaodao/Documents/redis-ms/sentinel/26001/sentinel.conf

redis-sentinel /Users/izaodao/Documents/redis-ms/sentinel/26002/sentinel.conf

redis-sentinel /Users/izaodao/Documents/redis-ms/sentinel/26003/sentinel.conf

- 5.测试故障转移
连上 26001 哨兵节点查看状态
redis-cli -h 127.0.0.1 -c -p 26001

info Replication

![此时 7001 是master](/Week_12/img/sentinel-info-before.png)


![kill 主节点 7001](/Week_12/img/sentinel_kill.png)



![info 命令](/Week_12/img/sentinel_new_master.png)

重新查看info Replication，发现7003已经变成新的master

![info 命令](/Week_12/img/sentinel_reset_slave.png)

重启7001，7001已经由主变成了从节点

## 三、cluster 集群

之前在公司内部做过cluster的技术分享，编写的文章 https://www.jianshu.com/p/fcf24fe0493d











