#!/bin/bash
##look exist ip
for sum in `seq 0 255`
    do
        ipsum=192.168.1.${sum}
	ping -c 1 -W 1 ${ipsum} &>/dev/null
	if [ "$?"=="0" ]
	then
		echo "$ipsum exist";
	else
		continue;
	fi
    done
