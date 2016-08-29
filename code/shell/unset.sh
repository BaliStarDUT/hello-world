#!/bin/bash
echo 
read -p "Please input your name:" NAME
echo -e "Welcome,$NAME!\nYour name is saved in variable 'NAME'!"
echo "========================================================"
echo 
echo "Clear variable 'NAME' using 'unset' command."
unset NAME
echo 
echo "Now variable 'NAME'is:"
if [ -z $NAME ]
then
	echo 
	echo "NAME:$NAME"
	echo "Variable NAME is null."
else
	echo
	echo "NAME:$NAME"
	echo "Variable NAME is not null."
fi
echo 
echo "quit"
echo 
exit 0
