学习笔记

## jdbcDemo  是插入100数据测试效率
主要是测试了多值和addbatch这两种方式
结论：基于insert into table1 values的多值执行效率高于addbatch

## multidatasource  读写分离-动态切换数据源版本1.0
基本功能实现，欠缺一个从库的负载功能

