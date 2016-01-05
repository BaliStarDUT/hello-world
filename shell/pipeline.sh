#!/bin/bash
linenumber=1
cat $1 | while read oneline
do 
	echo -e "$linenumber:$oneline\n"
	linenumber=`expr $linenumber+1`
done
exit 0
