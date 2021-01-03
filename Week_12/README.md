# master-slave 主从复制

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









