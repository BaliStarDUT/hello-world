#! /bin/bash
d=`date -d "-1 min" +%H:%M`
c_502=`grep :$d:  $log  |grep ' 502 '|wc -l`
if [ $c_502 -gt 10 ] && [ $send == 1 ]; then
     echo "$addr $d 502 count is $c_502">../log/502.tmp
     /bin/bash ../mail/mail.sh $addr\_502 $c_502  ../log/502.tmp
fi
echo "`date +%T` 502 $c_502"
