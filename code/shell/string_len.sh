#!/bin/bash
echo 
echo "This script will tell you the length of your input:"
while:
do 
    read -p "Please enter a String(or quit):"
    case "$REPLY" in
		[Qq]|[Qq][Uu][Ii][Tt]
			echo "Bye."
			exit 0
			;;
	*)
		LEN=$(expr length "$REPLY")
		echo "Length:$LEN"
		;;
	esac
done
exit 0
