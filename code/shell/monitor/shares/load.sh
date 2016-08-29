#! /bin/bash
load=`uptime |awk -F 'average:' '{print $2}'|cut -d',' -f1|sed 's/ //g' |cut -d. -f1`
if [ $load -gt 10 ] && [ $send -eq "1" ]
then
    echo "$addr `date +%T` load is $load" >../log/load.tmp
    /bin/bash ../mail/mail.sh $addr\_load $load ../log/load.tmp
fi
echo "`date +%T` load is $load"
