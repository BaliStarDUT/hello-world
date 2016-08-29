#!/bin/bash
linenumber=1
read oneline
while["$oneline"!=""]
do 
	echo -e "$linenumber:$oneline\n"
	linenumber=`expr $linenumber+1`
	read oneline
done
exit 0
