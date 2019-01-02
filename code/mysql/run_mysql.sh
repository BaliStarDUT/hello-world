##!/bin/sh
docker run --name mysql-test -e MYSQL_ROOT_PASSWORD=yang -d 192.168.26.46/library/mysql:v1.0.0
docker run --name mysql-data-slave2 -v /home/yang/mysql/slave_data/:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=yang --link c9fb6ee4201a -d 192.168.26.46/onlineshop/mysql:5.7.6
docker run --name mysql-master1 -e MYSQL_ROOT_PASSWORD=yang -v /home/yang/mysql/data/:/var/lib/mysql -v /home/yang/mysql/conf/master:/etc/mysql -d 192.168.26.46/library/mysql:v1.0.0
docker run --name mysql-data-slave1 -v /home/yang/mysql/slave_data/:/var/lib/mysql -v /home/yang/mysql/conf/slave:/etc/mysql -e MYSQL_ROOT_PASSWORD=yang --link d961f19d9253 -d 192.168.26.46/onlineshop/mysql:5.7.6

mysql> show master status;
+-------------------+----------+--------------+------------------+-------------------+
| File              | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
+-------------------+----------+--------------+------------------+-------------------+
| master-bin.000001 |      120 |              |                  |                   |
+-------------------+----------+--------------+------------------+-------------------+
1 row in set (0.00 sec)
insert into message values('2','e28545b26aa3fa384d07924d9164cc85','https://api.netease.im/sms/sendtemplate.action','c0bfefaa0d88','12345','123456','baidu');

describe message;
select * from message;
