#! /bin/bash

cur = 'dirname $0'
cd $cur
python gen.py
md5 = 'find hosts/ -type f -exec md5sum {} \; | md5sum'
cd /etc/nagios/conf.d
conf_md5 = 'find hosts/ -type f -exec md5sum {} \; | md5sum'
if [ "$md5" != "$conf_md5" ];then
    cd -
    cp -rp hosts /etc/nagios/conf.d
    /etc/init.d/nagios restart
fi
