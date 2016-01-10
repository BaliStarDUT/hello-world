#!/bin/bash
NETWORK=192.168.0
IP=1
while [ "$IP" -lt "244" ]
do
	echo -en "Pinging ${NETWORK}.${IP}..."
	ping -c1 -w1 ${NETWORK}.${IP} > /dev/null 2>&1
	if [ "$?" -eq 0 ]
	then
		echo "OK"
	else
		echo "Failed"
	fi
	let IP=$IP+1
done
exit 0

