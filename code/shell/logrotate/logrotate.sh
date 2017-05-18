#!/bin/bash
cp tomcat.logrotate /etc/logrotate.d/tomcat
logrotate -f /etc/logrotate.conf
echo "0 */12 * * * /usr/sbin/logrotate -f /etc/logrotate.conf" >>  /etc/crontab
crontab -l

