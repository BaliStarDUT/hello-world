#!/bin/bash
NETWORK=192.168.1
IP=30
until [ "$IP" -ge "130" ]
do 
	echo -en "Testing machine at IP address :${NETWORK}.${IP}..."
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
