#!/bin/bash
if [ $# -lt 2 ]
then
	echo "Error!Arguments are not enough."
	echo "Usage:$0 operator file."
	exit 1
elif [ $# -gt 2 ]
then 
	echo "Error! Your specify too many arguments."
	echo "Usage:$0 operator file."
	exit 1
fi
echo "My shell script begins running."
exit 0
